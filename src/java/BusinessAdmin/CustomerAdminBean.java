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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    
    @Size(min = 1, message = "Veuillez entrer un prénom")
    private String firstName;
    
    @Size(min = 1, message = "Veuillez entrer une nom")
    private String lastName;
    
    @Pattern(regexp = "[0-9]{10,}", 
            message = "Veuillez entre un téléphone valide (10 chiffres)")
    private String telephone;
    
    @Pattern(regexp = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]", 
            message = "Veuillez entre une adresse e-mail valide")
    private String email;
    
    @Size(min = 8, message = "Veuillez entrer un mot de passe de 8 caractères minimum")
    private String password;
    
    @Size(min = 1, message = "Veuillez entrer une adresse")
    private String street1;
    // Le street 2 n'est pas obligatoire
    private String street2;
    
    @Size(min = 1, message = "Veuillez entrer une ville")
    private String city;
    
    @Pattern(regexp = "[0-9]{5,}", 
            message = "Veuillez entre un code postal valide (5 chiffres)")
    private String zipCode;
    
    @Size(min = 1, message = "Veuillez entrer un pays")
    private String country;
    
    @Min(value = 1, message = "Type client invalide")
    @Max(value = 2, message = "Type client invalide")
    @NotNull(message = "Veuillez choisir un type client")
    private Integer type;
    
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
        currentCustomer.setTelephone(telephone);
        currentCustomer.setEmail(email);
        currentCustomer.setPasswordHash(password);
        currentCustomer.setStreet1(street1);
        currentCustomer.setStreet2(street2);
        currentCustomer.setCity(city);
        currentCustomer.setZipCode(zipCode);
        currentCustomer.setCountry(country);
        currentCustomer.setType(type);
    }
    
    private void fillBean() {
        firstName = currentCustomer.getFirstName();
        lastName = currentCustomer.getLastName();
        telephone = currentCustomer.getTelephone();
        email = currentCustomer.getEmail();
        // On ne récupère pas le password, question de sécurité
        street1 = currentCustomer.getStreet1();
        street2 = currentCustomer.getStreet2();
        city = currentCustomer.getCity();
        zipCode = currentCustomer.getZipCode();
        country = currentCustomer.getCountry();
        type = currentCustomer.getType();
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

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    public List<Customer> getAllCustomers() {
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
