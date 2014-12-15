/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.Book;
import Entity.Category;
import Facade.BookFacade;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author douceraa
 */
@ManagedBean
@ViewScoped
public class BookAdminBean {
    @EJB
    private BookFacade bookFacade;
    
    private Long bookId;
    private Book currentBook;
    
    @Size(min = 1, message = "Veuillez entrer un titre")
    private String title;
    
    @Size(min = 1, message = "Veuillez entrer une description")
    private String description;
    
    @Size(min = 1, message = "Veuillez entrer un auteur")
    private String author;
    
    @Size(min = 1, message = "Veuillez entrer un éditeur")
    private String publisher;
    
    private Date date;
    
    private BigDecimal price;
    
    @Size(min = 1, message = "Veuillez entrer une langue")
    private String language;
    
    @Min(value = 1, message = "Veuillez entrer un stock positif ou nul")
    private Integer stock;
    
    @Min(value = 1, message = "Note du livre invalide")
    @Max(value = 10, message = "Note du livre invalide")
    @NotNull(message = "Veuillez choisir une note non nulle")
    private Integer rating;
    
    @Pattern(regexp = "[0-9]{3}-[0-9]{10}", 
            message = "Veuillez entre un code ISBN valide (3 chiffres + tiret + 10 chiffres)")
    private String isbn;
    
    @Min(value = 1, message = "Nombre de pages invalide")
    @NotNull(message = "Veuillez choisir un nombre de pages non nul")
    private Integer hardcover;
    
    @Size(min = 1, message = "Veuillez entrer une adresse d'image")
    private String image;
    
    private Category category;
    
    private boolean okForEdit;
    private boolean okForView;

    public void initBook(ComponentSystemEvent event) {
        if (bookId != null && bookId != 0) {
            currentBook = bookFacade.find(bookId);
            if(currentBook != null) {
                fillBean();
                okForEdit = true;
                okForView = true;
            }
            else {
                okForEdit = false;
                okForView = false;
            }
        }
        else if (bookId == 0) {
            okForEdit = true;
            okForView = false;
        }
    }
    
    public String create() {
        currentBook = new Book();
        fillObject();
        bookFacade.create(currentBook);
        bookId = currentBook.getId();
        return "bookView?faces-redirect=true&includeViewParams=true";
    }
    
    public String save() {
        fillObject();
        bookFacade.edit(currentBook);
        return "bookView?faces-redirect=true&includeViewParams=true";
    }
    
    public String delete() {
        try {
            bookFacade.remove(currentBook);
        } catch (Exception e) {
            String errorMessage = "Impossible de supprimer. L'élément peut avoir des dépendances toujours présentes.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("form:errorMessage", message);
            return null;
        }
        
        return "booksList";
    }
    
    private void fillObject() {
        currentBook.setTitle(title);
        currentBook.setDescription(description);
        currentBook.setAuthor(author);
        currentBook.setPublisher(publisher);
        currentBook.setDateBook(date);
        currentBook.setPrice(price);
        currentBook.setLanguageBook(language);
        currentBook.setStock(stock);
        currentBook.setRating(rating);
        currentBook.setIsbn(isbn);
        currentBook.setHardcover(hardcover);
        currentBook.setImage(image);
        //currentBook.setCategory(category);
    }
    
    private void fillBean() {
        title = currentBook.getTitle();
        description = currentBook.getDescription();
        author = currentBook.getAuthor();
        publisher = currentBook.getPublisher();
        date = currentBook.getDateBook();
        price = currentBook.getPrice();
        language = currentBook.getLanguageBook();
        stock = currentBook.getStock();
        rating = currentBook.getRating();
        isbn = currentBook.getIsbn();
        hardcover = currentBook.getHardcover();
        image = currentBook.getImage();
        //category = currentBook.getCategory();
    }
    
    
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookFacade getBookFacade() {
        return bookFacade;
    }

    public void setBookFacade(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getHardcover() {
        return hardcover;
    }

    public void setHardcover(Integer hardcover) {
        this.hardcover = hardcover;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public List<Book> getAllBooks() {
        return bookFacade.findAll();
    }
    
    public boolean isOkForEdit() {
        return okForEdit;
    }

    public boolean isOkForView() {
        return okForView;
    }
    
    /**
     * Creates a new instance of BookAdminBean
     */
    public BookAdminBean() {
    }
    
}
