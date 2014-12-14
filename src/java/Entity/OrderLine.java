/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author colombor
 */
@Entity
@NamedQuery(name = "OrderLine.findByOrder", query = "SELECT ol FROM OrderLine ol WHERE ol.order = :order")
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderLinePK orderLinePK;
    
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerOrder order;
    
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Book book;
    
    
    private Integer quantity;
    private BigDecimal unitPrice;
    

    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    
    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public OrderLinePK getOrderLinePK() {
        return orderLinePK;
    }

    public void setOrderLinePK(OrderLinePK orderLinePK) {
        this.orderLinePK = orderLinePK;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderLinePK != null ? orderLinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLine)) {
            return false;
        }
        OrderLine other = (OrderLine) object;
        if ((this.orderLinePK == null && other.orderLinePK != null) || (this.orderLinePK != null && !this.orderLinePK.equals(other.orderLinePK))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Entity.OrderLine[ id=" + orderLinePK + " ]";
    }
    
}
