package org.jboss.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class ContentKeyword implements Serializable {
    private static final long serialVersionUID = 591389384673321084L;
    
    @Id @GeneratedValue private Long id;
    @ManyToOne private Content content;
    @ManyToOne private Keyword keyword;
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setContent(Content content) {
        this.content = content;
    }
    
    public Content getContent() {
        return content;
    }
    
    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
    
    public Keyword getKeyword() {
        return keyword;
    }

}
