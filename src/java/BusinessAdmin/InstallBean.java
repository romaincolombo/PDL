/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.Book;
import Entity.Category;
import Entity.Customer;
import Entity.CustomerOrder;
import Entity.OrderLine;
import Entity.OrderLinePK;
import Facade.CategoryFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author colombor
 */
@ManagedBean
@RequestScoped
public class InstallBean {
    @EJB
    private CategoryFacade categoryFacade;
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
    
    @Resource
    private javax.transaction.UserTransaction utx;

    
    /**
     * Creates a new instance of InstallBean
     */
    public InstallBean() {
    }
    
    public String getInstallDB() {
        try {
            ut.begin();
            
            Category cat_test1 = new Category();
            cat_test1.setName("Category Test 1");
            cat_test1.setDescription("Ceci est la description de la categorie test 1");
            em.persist(cat_test1);
            
            Book book_test1 = new Book();
            book_test1.setCategory(cat_test1);
            book_test1.setTitle("Comment faire ses tests ?");
            book_test1.setDescription("Ce livre de test est une mise en abime pour pouvoir tester notre propre application.");
            book_test1.setAuthor("Romain Colombo & Arnaud Doucerain");
            book_test1.setPublisher("Dev AR");
            book_test1.setDateBook(new Date());
            book_test1.setPrice(new BigDecimal(55));
            book_test1.setRating(10);
            book_test1.setLanguageBook("Français");
            book_test1.setStock(1);
            book_test1.setIsbn("978-2012010673");
            book_test1.setHardcover(512);
            book_test1.setNewSelection(true);
            book_test1.setImage("test.jpg");
            em.persist(book_test1);
            
            Book book_test2 = new Book();
            book_test2.setCategory(cat_test1);
            book_test2.setTitle("Comment faire ses tests 2 ?");
            book_test2.setDescription("Ce livre de test est une mise en abime pour pouvoir tester notre propre application.");
            book_test2.setAuthor("Romain Colombo & Arnaud Doucerain");
            book_test2.setPublisher("Dev AR");
            book_test2.setDateBook(new Date());
            book_test2.setPrice(new BigDecimal(55));
            book_test2.setRating(10);
            book_test2.setLanguageBook("Français");
            book_test2.setStock(125);
            book_test2.setIsbn("978-2012010673");
            book_test2.setHardcover(512);
            book_test2.setNewSelection(true);
            book_test2.setImage("test.jpg");
            em.persist(book_test2);
            
            
            /*
            Customer cust_test1 = new Customer();
            cust_test1.setEmail("test@test.com");
            cust_test1.setPassword("1234");
            em.persist(cust_test1);
            
            
            em.flush();
            
            
            CustomerOrder order_test1 = new CustomerOrder();
            order_test1.setCustomer(cust_test1);
            em.persist(order_test1);
            
            em.flush();
            
            OrderLine orderline_test1 = new OrderLine();
            orderline_test1.setOrderLinePK(new OrderLinePK(order_test1.getId(), book_test1.getId()));
            orderline_test1.setQuantity(123);
            em.persist(orderline_test1);
            */

            ut.commit();

            
        } catch (Exception ex) {
            
            Logger.getLogger(InstallBean.class.getName()).log(Level.SEVERE, null, ex);
            return "Nok";
        }
        
        return "ok";
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
}