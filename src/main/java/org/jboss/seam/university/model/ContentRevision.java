package org.jboss.seam.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class ContentRevision implements Serializable {
    private static final long serialVersionUID = 2555835616823757601L;
    
    @Id @GeneratedValue private Long id;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

}
