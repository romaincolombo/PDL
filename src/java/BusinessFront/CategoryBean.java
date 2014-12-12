/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessFront;

import Entity.Book;
import Entity.Category;
import Facade.BookFacade;
import Facade.CategoryFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author colombor
 */
@ManagedBean
@RequestScoped
public class CategoryBean {
    @EJB
    private BookFacade bookFacade;
    @EJB
    private CategoryFacade categoryFacade;
    
    Long categoryId;
    Category currentCategory;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    

    public Category getCurrentCategory() {
        return currentCategory;
    }
    
    public void initCategory(ComponentSystemEvent event) {
       if(categoryId != null)
        currentCategory = categoryFacade.find(categoryId);
   }
    
    public List<Category> getAllCategories() {
        return categoryFacade.findAll();
    }
    
    public List<Book> getCurrentCategoryBooks() {
        return bookFacade.findByCategory(currentCategory);
    }

    public boolean isCategoryExists() {
        return (currentCategory != null);
    }
    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
    }
    
}
