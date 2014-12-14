/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.Customer;
import Facade.CustomerFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.Size;

/**
 *
 * @author douceraa
 */
@ManagedBean
@ViewScoped
public class CustomerAdminBean {
    @EJB
    private CustomerFacade customerFacade;
    
    private Long customerId;
    private Customer currentCustomer;
    
    @Size(min = 1, message = "Veuillez entrer un titre")
    private String firstName;
    @Size(min = 1, message = "Veuillez entrer une lastName")
    private String lastName;
    
    private boolean okForEdit;
    private boolean okForView;

    public void initCustomer(ComponentSystemEvent event) {
        if (customerId != null && customerId != 0) {
            currentCustomer = customerFacade.find(customerId);
            if(currentCustomer != null) {
                fillBean();
                okForEdit = true;
                okForView = true;
            }
            else {
                okForEdit = false;
                okForView = false;
            }
        }
        else if (customerId == 0) {
            okForEdit = true;
            okForView = false;
        }
    }
    
    public String create() {
        currentCustomer = new Customer();
        fillObject();
        customerFacade.create(currentCustomer);
        customerId = currentCustomer.getId();
        return "customerView?faces-redirect=true&includeViewParams=true";
    }
    
    public String save() {
        fillObject();
        customerFacade.edit(currentCustomer);
        return "customerView?faces-redirect=true&includeViewParams=true";
    }
    
    public String delete() {
        try {
            customerFacade.remove(currentCustomer);
        } catch (Exception e) {
            String errorMessage = "Impossible de supprimer. L'élément peut avoir des dépendances toujours présentes.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("form:errorMessage", message);
            return null;
        }
        
        return "customerList";
    }
    
    private void fillObject() {
        currentCustomer.setFirstName(firstName);
        currentCustomer.setLastName(lastName);
    }
    
    private void fillBean() {
        firstName = currentCustomer.getFirstName();
        lastName = currentCustomer.getLastName();
    }
    
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public List<Customer> getAllCategories() {
        return customerFacade.findAll();
    }
    
    public boolean isOkForEdit() {
        return okForEdit;
    }

    public boolean isOkForView() {
        return okForView;
    }
    
    /**
     * Creates a new instance of CustomerAdminBean
     */
    public CustomerAdminBean() {
    }
    
}
