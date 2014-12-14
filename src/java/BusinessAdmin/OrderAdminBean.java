/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.CustomerOrder;
import Facade.OrderFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author romino
 */
@ManagedBean
@ViewScoped
public class OrderAdminBean {
    @EJB
    private OrderFacade orderFacade;
    
    private Long orderId;
    private CustomerOrder currentOrder;
    
    private Integer stateOrder;
    private boolean okForEditView = false;
    
    public void initOrder(ComponentSystemEvent event) {
        if (orderId != null) {
            currentOrder = orderFacade.find(orderId);
            if(currentOrder != null) {
                stateOrder = currentOrder.getStateOrder();
                okForEditView = true;
            }
            else {
                okForEditView = false;
            }
        }
    }
    
    public String getOrderStateText() {
        if(currentOrder.getStateOrder() == 1) {
            return "En préparation";
        }
        else if(currentOrder.getStateOrder() == 2) {
            return "Envoyé";
        }
        else if(currentOrder.getStateOrder() == 3) {
            return "Livré";
        }
        else {
            return "Inconnu";
        }
    }
    
    public String getOrderStateColor() {
        if(currentOrder.getStateOrder() == 1) {
            return "red";
        }
        else if(currentOrder.getStateOrder() == 2) {
            return "orange";
        }
        else if(currentOrder.getStateOrder() == 3) {
            return "green";
        }
        else {
            return "white";
        }
    }
    public List<CustomerOrder> getAllOrderByDate() {
        return orderFacade.getAllByDate();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStateOrder() {
        return stateOrder;
    }

    public void setStateOrder(Integer stateOrder) {
        this.stateOrder = stateOrder;
    }

    public boolean isOkForEditView() {
        return okForEditView;
    }

    

    public CustomerOrder getCurrentOrder() {
        return currentOrder;
    }

    
    
    /**
     * Creates a new instance of OrderAdminBean
     */
    public OrderAdminBean() {
    }
    
}
