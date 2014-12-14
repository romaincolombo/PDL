/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.CustomerOrder;
import Entity.OrderLine;
import Facade.BookFacade;
import Facade.OrderFacade;
import Facade.OrderLineFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author colombor
 */
@ManagedBean
@ViewScoped
public class MyAccountOrdersBean {
    @EJB
    private BookFacade bookFacade;
    @EJB
    private OrderLineFacade orderLineFacade;
    @EJB
    private OrderFacade orderFacade;
    
    
    
    @ManagedProperty(value="#{connectBean}")
    ConnectBean connectBean;
    
    private CustomerOrder currentMyOrder;
    private Long myOrderId;
    
    private boolean okForView = false;
    
    public void initMyOrder(ComponentSystemEvent event) {
        if (myOrderId != null) {
            currentMyOrder = orderFacade.find(myOrderId);
            if(currentMyOrder != null) {
                if(currentMyOrder.getCustomer().equals(connectBean.getCustomer())) {
                    okForView = true;
                }
                else {
                    currentMyOrder = null;
                    okForView = false;
                }
            }
            else {
                okForView = false;
            }
        }
    }
    
    public String getStateText(int state) {
        if(state == 1) {
            return "En préparation";
        }
        else if (state == 2) {
            return "Envoyé";
        }
        else if (state == 3) {
            return "Livré";
        }
        else {
            return "Inconnu";
        }
    }

    public CustomerOrder getCurrentMyOrder() {
        return currentMyOrder;
    }

    public Long getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(Long myOrderId) {
        this.myOrderId = myOrderId;
    }

    public boolean isOkForView() {
        return okForView;
    }

    public void setConnectBean(ConnectBean connectBean) {
        this.connectBean = connectBean;
    }
    
    

    public List<CustomerOrder> getAllMyOrders() {
        return orderFacade.getOrdersByCustomer(connectBean.getCustomer());
    }
    
    public List<OrderLine> getCurrentOrderLines() {
        List<OrderLine> list = orderLineFacade.findByOrder(currentMyOrder);
        for(OrderLine line: list) {
            line.setBook(bookFacade.find(line.getOrderLinePK().getBookId()));
        }
        return list;
    }
    
    
    /**
     * Creates a new instance of myAccountOrdersBean
     */
    public MyAccountOrdersBean() {
    }
    
}
