package org.jboss.seam.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class Content implements Serializable {
	private static final long serialVersionUID = 8476274048268308572L;

	@Id @GeneratedValue private Long id;
	@ManyToOne private ContentType contentType;
	private String summary;
		
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public ContentType getContentType() {
	    return contentType;
	}
	
	public void setContentType(ContentType contentType) {
	    this.contentType = contentType;
	}
	
	public String getSummary() {
	    return summary;
	}
	
	public void setSummary(String summary) {
	    this.summary = summary;
	}


}
