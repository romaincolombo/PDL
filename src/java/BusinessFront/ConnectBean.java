/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.Customer;
import Facade.CustomerFacade;
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
public class ConnectBean {
    @EJB
    private CustomerFacade customerFacade;
    
    Customer customer;
    String email;
    String password;
    boolean redirectToCheckout = false;


    
    public String connect() {
        try {
            disconnect();
            Customer custTry = customerFacade.findByEmail(email);
            if(custTry == null) {
                // Existe pas
                String errorMessage = "Compte invalide !";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("form_connect:email", message);

                password="";
                return null;
            }
            else if (!PasswordHash.validatePassword(password, custTry.getPasswordHash())) {
                // Password faux
                
                String errorMessage = "Mot de passe invalide !";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("form_connect:email", message);
                password="";
                return null;
            }
            else {
                // OK
                customer = custTry;
                password="";
                return getRedirection();
            }
            
            
        }catch (Exception ex) {
            // Problème technique
            System.out.println("problème tech");
            return null;
        }
        
        
    }
    
    public String disconnect() {
        customer = null;
        // To Do renew session saveCart
        return null;
    }
    
    public boolean isConnected() {
        return (customer != null);
    }
    
    public String getRedirection() {
        if(redirectToCheckout) {
            this.redirectToCheckout = false;
            return "/user/checkout?faces-redirect=true";
        }
        return "/user/myAccountIndex?faces-redirect=true";
    }
    
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isRedirectToCheckout() {
        return redirectToCheckout;
    }

    
    public void setRedirectToCheckout(boolean redirectToCheckout) {
        this.redirectToCheckout = redirectToCheckout;
    }
    
    /**
     * Creates a new instance of ConnectBean
     */
    public ConnectBean() {
    }
    
}
