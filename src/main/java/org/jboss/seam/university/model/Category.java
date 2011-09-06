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
    
    public enum ContentEditor {richText, url, video, faq, tip, upload};
    
    private @Id @GeneratedValue Long id;
    private String name;
    private ContentEditor localEditor;
    private ContentEditor remoteEditor;
    private boolean privileged;
    private int displayOrder;
    
    public Category() {}
    
    public Category(String name, ContentEditor localEditor, ContentEditor remoteEditor, boolean privileged, int displayOrder) {
        this.name = name;
        this.localEditor = localEditor;
        this.remoteEditor = remoteEditor;
        this.privileged = privileged;
        this.displayOrder = displayOrder;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() { 
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
       
    public ContentEditor getLocalEditor() {
        return localEditor;
    }
    
    public void setLocalEditor(ContentEditor localEditor) {
        this.localEditor = localEditor;
    }
    
    public ContentEditor getRemoteEditor() {
        return remoteEditor;
    }
    
    public void setRemoteEditor(ContentEditor remoteEditor) {
        this.remoteEditor = remoteEditor;
    }
    
    public boolean isPrivileged() {
        return privileged;
    }
    
    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
