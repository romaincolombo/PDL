/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.AdminUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author colombor
 */
@Stateless
public class AdminUserFacade extends AbstractFacade<AdminUser> {
    @PersistenceContext(unitName = "PDL_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminUserFacade() {
        super(AdminUser.class);
    }
    
    public AdminUser findByLogin(Object login) {
        List<AdminUser> list = em.createNamedQuery("AdminUser.findByLogin").setParameter("login", login).setMaxResults(1).getResultList();
        if (list.isEmpty()) {
            return null;
        }
        else {
            return list.get(0);
        }
    }
    
    public Long countAll() {
        return (Long) em.createNamedQuery("AdminUser.countAll").getSingleResult();
    }
    
}
