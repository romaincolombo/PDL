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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author colombor
 */
@ManagedBean
@ViewScoped
public class NewBooksAdminBean {
    @EJB
    private BookFacade bookFacade;
    
    private Long bookId;
    private Book currentBook;
    private boolean newSelection;
    
    private String searchText;
    
    private boolean okForEdit = false;
    
    public void initBook(ComponentSystemEvent event) {
        if (bookId != null) {
            currentBook = bookFacade.find(bookId);
            if(currentBook != null) {
                newSelection = currentBook.getNewSelection();
                okForEdit = true;
            }
        }
    }
    
    public String save() {
        currentBook.setNewSelection(newSelection);
        bookFacade.edit(currentBook);
        return "newBooksList";
    }
    
    public String findBook() {
       return "newBooksSearch?faces-redirect=true&includeViewParams=true";
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    
    public Book getCurrentBook() {
        return currentBook;
    }

    public boolean isNewSelection() {
        return newSelection;
    }

    public void setNewSelection(boolean newSelection) {
        this.newSelection = newSelection;
    }

    public boolean isOkForEdit() {
        return okForEdit;
    }

    public List<Book> getAllNewBooks() {
        return bookFacade.getNewBooks();
    }
    
    public List<Book> getSearchResults() {
        List<Book> searchResults = bookFacade.searchByTitleOrAuthor(searchText);
        return searchResults;
    }
    
    
    /**
     * Creates a new instance of NewBooksBean
     */
    public NewBooksAdminBean() {
    }
    
}
