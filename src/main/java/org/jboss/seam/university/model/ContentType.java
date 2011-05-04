package org.jboss.seam.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Lookup table containing content types
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class ContentType implements Serializable {
    private static final long serialVersionUID = -5626674137004826592L;
    
    @Id @GeneratedValue private Long id;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
