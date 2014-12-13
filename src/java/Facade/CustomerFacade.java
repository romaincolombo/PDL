/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author colombor
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    public Customer findByEmail(Object email) {
        List<Customer> list = em.createNamedQuery("Customer.findByEmail").setParameter("email", email).setMaxResults(1).getResultList();
        if (list.isEmpty()) {
            return null;
        }
        else {
            return list.get(0);
        }
    }
}
