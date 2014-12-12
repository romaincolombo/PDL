/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author colombor
 */
@Embeddable
public class OrderLinePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id")
    private Long orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_id")
    private Long bookId;

    public OrderLinePK(Long orderId, Long productId) {
        this.orderId = orderId;
        this.bookId = productId;
    }

    public OrderLinePK() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return bookId;
    }

    public void setProductId(Long productId) {
        this.bookId = productId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderId.intValue();
        hash += (int) bookId.intValue();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLinePK)) {
            return false;
        }
        OrderLinePK other = (OrderLinePK) object;
        if (this.orderId.equals(other.orderId)) {
            return false;
        }
        if (this.bookId.equals(other.bookId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderLinePK[ orderId=" + orderId + ", bookId=" + bookId + " ]";
    }
}
