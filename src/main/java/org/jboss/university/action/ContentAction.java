package org.jboss.university.action;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.persistence.EntityManager;

import org.jboss.seam.remoting.annotations.WebRemote;
import org.jboss.seam.security.annotations.LoggedIn;
import org.jboss.seam.transaction.Transactional;
import org.jboss.university.model.Category;
import org.jboss.university.model.Content;

/**
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class ContentAction {
       
    @Inject Repository repository;
    
    @Inject LatestContentAction latestContent;
    //@Inject KeywordAction keywordAction;
    
    @Inject TempFileManager fileManager;
    
    @Inject EntityManager entityManager;
        
    @WebRemote @LoggedIn
    public boolean saveContent(Category category, boolean local, String title, String summary, String contentText, String[] keywords) throws Exception  {   
        
        if (title == null) {
            throw new IllegalArgumentException("Error - title parameter cannot be null");
        }
        
        Session session = repository.login("default");
        try {          
            Node rootNode = session.getRootNode();            
            Node categoryParent = rootNode.hasNode(category.getName()) ? rootNode.getNode(category.getName()) :
                rootNode.addNode(category.getName());

            Node contentNode = categoryParent.addNode(title);
            contentNode.setProperty("content", contentText);
                                   
            Content content = createContent(contentNode.getIdentifier());
            //            keywordAction.setContentKeywords()
            
            session.save();            
            
            latestContent.add(content);
                       
            return true;
        }
        finally {
            session.logout();
        }
    }
    
    public @Transactional Content createContent(String identifier) {
        Content content = new Content();
        content.setIdentifier(identifier);
        Date now = new Date();
        content.setCreated(now);
        content.setUpdated(now);
        entityManager.persist(content);
        return content;        
    }
}
