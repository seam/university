package org.jboss.seam.university.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = -7595594645224678119L;
    
    @Id @GeneratedValue private Long id;
    private String text;
    private Date posted;
    private Date updated;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Date getPosted() {
        return posted;
    }
    
    public void setPosted(Date posted) {
        this.posted = posted;
    }
    
    public Date getUpdated() {
        return updated;
    }
    
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
