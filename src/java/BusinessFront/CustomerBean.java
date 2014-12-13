/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.Customer;
import Facade.CustomerFacade;
import Utils.PasswordHash;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author colombor
 */
@ManagedBean
@RequestScoped
public class CustomerBean {
    @EJB
    private CustomerFacade customerFacade;
    
    @ManagedProperty(value="#{connectBean}")
    ConnectBean connectBean;

    @Min(value = 1, message = "Type client invalide")
    @Max(value = 2, message = "Type client invalide")
    @NotNull(message = "Veuillez choisir un type client")
    private Integer type;
    
    @Size(min = 1, message = "Veuillez entrer un prénom")
    private String firstName;
    
    @Size(min = 1, message = "Veuillez entrer un nom")
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
    
    private String street2;
    
    @Size(min = 1, message = "Veuillez entrer une ville")
    private String city;
    
    @Pattern(regexp = "[0-9]{5,}", 
            message = "Veuillez entre un code postal valide (5 chiffres)")
    private String zipCode;
    
    @Size(min = 1, message = "Veuillez entrer un pays")
    private String country;
    
    private boolean checkout = false;

    public boolean isCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }
    
    
    
    
    public String register() {
        if(customerFacade.findByEmail(email) != null) {
            return null;
        }
        try {
            Customer cust = new Customer();
            setFields(cust);
            setPassword(cust);
            customerFacade.create(cust);
            connectBean.setCustomer(cust);
            connectBean.setEmail(email);
            return connectBean.getRedirection();
            
        } catch (Exception e) {
            return null;
        }
        
        
    }
    
    private void setFields(Customer cust) {
        cust.setType(type);
        cust.setFirstName(firstName);
        cust.setLastName(lastName);
        cust.setTelephone(telephone);
        cust.setEmail(email);
        cust.setStreet1(street1);
        cust.setStreet2(street2);
        cust.setCity(city);
        cust.setZipCode(zipCode);
        cust.setCountry(country);
    }
    
    private void setPassword(Customer cust) throws InvalidKeySpecException, NoSuchAlgorithmException {
        cust.setPasswordHash(PasswordHash.createHash(password));
    }
            
    public String preRegister() {
        System.out.println("preregister");
        return "register";
    }
    public void validateSamePassword(FacesContext context, UIComponent toValidate, Object value) {
        String confirmPassword = (String)value;
        UIInput otherInput = (UIInput) toValidate.getAttributes().get("confirm");
        String otherValue = (String) otherInput.getSubmittedValue();
            if (!confirmPassword.equals(otherValue)) {
                String messageText = "Les mots de passe ne correspondent pas";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText, messageText);
                throw new ValidatorException(message);
            }
    }

    public void setConnectBean(ConnectBean connectBean) {
        this.connectBean = connectBean;
    }
    
    
    
    
    
    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    

    
    
    /**
     * Creates a new instance of CustomerBean
     */
    
    public CustomerBean() {
    }
    
}
