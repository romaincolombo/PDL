/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessAdmin;

import Entity.Category;
import Facade.CategoryFacade;
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
 * @author colombor
 */
@ManagedBean
@ViewScoped
public class CategoryAdminBean {
    @EJB
    private CategoryFacade categoryFacade;
    
    private Long categoryId;
    private Category currentCategory;
    
    @Size(min = 1, message = "Veuillez entrer un nom")
    private String name;
    @Size(min = 1, message = "Veuillez entrer une description")
    private String description;
    
    private boolean okForEdit;
    private boolean okForView;

    public void initCategory(ComponentSystemEvent event) {
        if (categoryId != null && categoryId != 0) {
            currentCategory = categoryFacade.find(categoryId);
            if(currentCategory != null) {
                fillBean();
                okForEdit = true;
                okForView = true;
            }
            else {
                okForEdit = false;
                okForView = false;
            }
        }
        else if (categoryId == 0) {
            okForEdit = true;
            okForView = false;
        }
    }
    
    public String create() {
        currentCategory = new Category();
        fillObject();
        categoryFacade.create(currentCategory);
        categoryId = currentCategory.getId();
        return "categoryView?faces-redirect=true&includeViewParams=true";
    }
    
    public String save() {
        fillObject();
        categoryFacade.edit(currentCategory);
        return "categoryView?faces-redirect=true&includeViewParams=true";
    }
    
    public String delete() {
        try {
            categoryFacade.remove(currentCategory);
        } catch (Exception e) {
            String errorMessage = "Impossible de supprimer. L'élément peut avoir des dépendances toujours présentes.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("form:errorMessage", message);
            return null;
        }
        
        return "categoriesList";
    }
    
    private void fillObject() {
        currentCategory.setName(name);
        currentCategory.setDescription(description);
    }
    
    private void fillBean() {
        name = currentCategory.getName();
        description = currentCategory.getDescription();
    }
    
    
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Category> getAllCategories() {
        return categoryFacade.findAll();
    }
    
    public boolean isOkForEdit() {
        return okForEdit;
    }

    public boolean isOkForView() {
        return okForView;
    }
    
    /**
     * Creates a new instance of CategoryAdminBean
     */
    public CategoryAdminBean() {
    }
    
}
