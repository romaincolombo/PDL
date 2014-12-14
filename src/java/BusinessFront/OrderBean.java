/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.Book;
import Entity.CustomerOrder;
import Entity.OrderLine;
import Entity.OrderLinePK;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Pattern;

/**
 *
 * @author colombor
 */
@ManagedBean
@RequestScoped
public class OrderBean {
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;
    
    @ManagedProperty(value="#{connectBean}")
    ConnectBean connectBean;
    
    @ManagedProperty(value="#{cartBean}")
    CartBean cartBean;
    
    @Resource
    UserTransaction ut;
    
    @Pattern(regexp = "[0-9]{16}", 
            message = "Veuillez entre un numéro de carte valide (16 chiffres)")
    private String ccNumber;
    @Pattern(regexp = "(visa)|(mastercard)|(amex)", 
            message = "Veuillez choisir un type de carte entre Visa, Master Card et American Express")
    private String ccType;
    @Pattern(regexp = "(0[1-9]|1[012])[\\/](19|20)\\d\\d", 
            message = "Veuillez entrer une date d'expiration au format MM/AAAA")
    private String ccExpiry;
    
    private Long orderId;


    public String confirm() {
        orderId = placeOrder();
        if(orderId != 0) {
            cartBean.emptyCart();
        }
        return "checkout?faces-redirect=true&includeViewParams=true";
    }
    
    
    
    private long placeOrder() {
        try {
            ut.begin();
            CustomerOrder order = addOrder();
            addOrderedItems(order);
            ut.commit();
            return order.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    private CustomerOrder addOrder() {
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(connectBean.getCustomer());
        order.setStateOrder(1);
        order.setTotalPrice(getTotalPrice());
        order.setCcNumber(ccNumber);
        order.setCcExpiry(ccExpiry);
        order.setCcType(ccType);
        order.setDateOrder(new Date());
        em.persist(order);
        return order;
    }
    
    private void addOrderedItems(CustomerOrder order) {
        em.flush();
        
        Set<Entry<Book,Integer>> cart = cartBean.getCart();
        for(Entry<Book,Integer> entry : cart) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            
            OrderLinePK pk = new OrderLinePK(order.getId(), book.getId());
            
            OrderLine line = new OrderLine();
            line.setOrderLinePK(pk);
            line.setQuantity(quantity);
            line.setUnitPrice(BigDecimal.valueOf(book.getPrice().doubleValue()*getDiscount()));
            em.persist(line);
        }
    }
    
    private BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(cartBean.getTotal()*getDiscount());
    }
    
    private double getDiscount() {
        if(connectBean.getCustomer().getType() == 2) {
            return 0.8;
        }
        return 1.0;
    }

    public void setConnectBean(ConnectBean connectBean) {
        this.connectBean = connectBean;
    }

    public void setCartBean(CartBean cartBean) {
        this.cartBean = cartBean;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcExpiry() {
        return ccExpiry;
    }

    public void setCcExpiry(String ccExpiry) {
        this.ccExpiry = ccExpiry;
    }
    
    
    
    
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
    }
    
    
}
