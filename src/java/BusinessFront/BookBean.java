/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.Book;
import Facade.BookFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author colombor
 */
@ManagedBean
@ViewScoped
public class BookBean {
    @EJB
    private BookFacade bookFacade;
   
    Long bookId;
    Book currentBook;
    
    String searchText;
    List<Book> searchResults;

   public void initBook(ComponentSystemEvent event) {
        if (bookId != null)
            currentBook = bookFacade.find(bookId);
   }
   
   public String findBook() {
       //TODO: Search !
       return "search.xhtml";
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

    public List<Book> getSearchResults() {
        return searchResults;
    }
    
    public boolean isBookExists() {
        return (currentBook != null);
    }
    
   
    /**
     * Creates a new instance of bookBean
     */
    public BookBean() {
    }
    
}
