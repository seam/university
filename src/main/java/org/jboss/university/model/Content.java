package org.jboss.university.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Used to store additional data against a content entry.  The actual content itself is stored in the JCR repository.
 * 
 * @author Shane Bryzak
 *
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "identifier"))
public class Content implements Serializable {
    private static final long serialVersionUID = 6343126861144373546L;
    
    @Id @GeneratedValue private long id;
    @NotNull private String identifier;
    private Date created;
    private Date updated;
    private boolean published;
    private int commentCount;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
