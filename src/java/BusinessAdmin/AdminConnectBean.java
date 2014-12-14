/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.AdminUser;
import Facade.AdminUserFacade;
import Utils.PasswordHash;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author colombor
 */
@ManagedBean
@SessionScoped
public class AdminConnectBean {
    @EJB
    private AdminUserFacade adminUserFacade;
    
    private AdminUser adminUser;
    private String login;
    private String password;
    
    public String connectAdmin() {
        try {
            disconnect();
            AdminUser adminTry = adminUserFacade.findByLogin(login);
            if(adminTry == null) {
                // Existe pas
                String errorMessage = "Compte invalide !";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("form_adminconnect:login", message);

                password="";
                return null;
            }
            else if (!PasswordHash.validatePassword(password, adminTry.getPasswordHash())) {
                // Password faux
                
                String errorMessage = "Mot de passe invalide !";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("form_adminconnect:login", message);
                password="";
                return null;
            }
            else {
                // OK
                adminUser = adminTry;
                password="";
                return "/admin/adminIndex?faces-redirect=true";
            }
            
            
        }catch (Exception ex) {
            // Problème technique
            System.out.println("problème tech");
            return null;
        }
    }
    
    public String disconnect() {
        adminUser = null;
        return "/index?faces-redirect=true";
    }
    
    public boolean isAdmin() {
        return (adminUser != null);
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    /**
     * Creates a new instance of AdminConnectBean
     */
    public AdminConnectBean() {
    }
    
}
