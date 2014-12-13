/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.Book;
import Facade.BookFacade;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author colombor
 */
@ManagedBean
@SessionScoped
public class CartBean {
    @ManagedProperty(value="#{connectBean}")
    ConnectBean connectBean;
    
    @EJB
    private BookFacade bookFacade;
    
    HashMap<Book,Integer> items;
    
    @PostConstruct
    public void init() {
         items = new HashMap();
    }
    
    public double getTotal() {
        double total = 0;
        for(Entry<Book,Integer> entry : items.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            total += book.getPrice().doubleValue() * quantity;
        }
        return total;
    }
    
    public int getNumberOfItems() {
        int number = 0;
        for(Entry<Book,Integer> entry : items.entrySet()) {
            int quantity = entry.getValue();
            number += quantity;
        }
        return number;
    }
    
    
    // addBook = increment Book
    private void incrementBook(Book book) {
        if(items.containsKey(book)) {
            Integer nb = items.get(book);
            nb++;
            items.put(book, nb);
        }
        else {
            items.put(book, 1);
        }
    }
    
    private void decrementBook(Book book) {
        if(items.containsKey(book)) {
            Integer nb = items.get(book);
            nb--;
            if(nb < 1) {
                items.remove(book);
            }
            else {
                items.put(book, nb);
            }
        }
    }
    
    private void removeBook(Book book) {
        items.remove(book);
    }
    
    /*
    private void modifyBookQuantity(Book book, int quantity) {
        if (quantity < 1) {
            removeBook(book);
        }
        else {
            items.put(book, quantity);
        }
    }
    */
    
    public String incrementBookForm(Long id) {
        Book book = bookFacade.find(id);
        if(book != null) {
            incrementBook(book);
        }
        return "cart?faces-redirect=true";
    }
    
    
    public String decrementBookForm(Long id) {
        Book book = bookFacade.find(id);
        if(book != null) {
            decrementBook(book);
        }
        return "cart";
    }
    
    public String removeBookForm(Long id) {
        Book book = bookFacade.find(id);
        if(book != null) {
            removeBook(book);
        }
        return "cart";
    }
    
    public Set<Entry<Book,Integer>> getCart() {
        return items.entrySet();
    }
    
    public String checkoutAction() {
        if(connectBean.isConnected()) {
            return "/user/checkout?faces-redirect=true";
        }
        else {
            connectBean.setRedirectToCheckout(true);
            return "/connect?faces-redirect=true";
        }
    }

    public void setConnectBean(ConnectBean connectBean) {
        this.connectBean = connectBean;
    }
    
    public void emptyCart() {
        this.items.clear();
    }
    
    
    /**
     * Creates a new instance of CartBean
     */
    public CartBean() {
    }
    
}
