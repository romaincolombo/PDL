/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Book;
import Entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author colombor
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public List findByCategory(Object category) {
        return em.createNamedQuery("Book.findByCategory").setParameter("category", category).getResultList();
    }
    
    public List searchByTitleOrAuthor(String search) {
        return em.createNamedQuery("Book.searchByTitleOrAuthor").setParameter("search", "%"+search.toLowerCase()+"%").getResultList();
    }
    
    public List getNewBooks() {
        return em.createNamedQuery("Book.getNewBooks").getResultList();
    }
}
