package org.jboss.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class LatestContent implements Serializable {
    private static final long serialVersionUID = 605682452324199250L;
    
    @Id @GeneratedValue private Long id;
    @OneToOne private Content content;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }       
}
