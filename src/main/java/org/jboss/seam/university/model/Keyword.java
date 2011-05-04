package org.jboss.seam.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contains a list of all keywords that can be assigned to content
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class Keyword implements Serializable {
    private static final long serialVersionUID = -3064896334539620324L;
    
    @Id @GeneratedValue private Long id;
    private String description;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
