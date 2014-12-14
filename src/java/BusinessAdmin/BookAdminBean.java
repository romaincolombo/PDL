/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.Book;
import Facade.BookFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.Size;

/**
 *
 * @author colombor
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
    }
    
    private void fillBean() {
        title = currentBook.getTitle();
        description = currentBook.getDescription();
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
    
    public List<Book> getAllCategories() {
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
