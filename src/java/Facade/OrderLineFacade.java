/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.OrderLine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author colombor
 */
@Stateless
public class OrderLineFacade extends AbstractFacade<OrderLine> {
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderLineFacade() {
        super(OrderLine.class);
    }
    
    public List findByOrder(Object order) {
        return em.createNamedQuery("OrderLine.findByOrder").setParameter("order", order).getResultList();
    }
    
}
