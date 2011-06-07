package org.jboss.seam.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Lookup table containing a list of content categories
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 8122857831607922307L;
    
    private @Id @GeneratedValue Long categoryId;
    private String name;
    
    public Long getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getName() { 
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
