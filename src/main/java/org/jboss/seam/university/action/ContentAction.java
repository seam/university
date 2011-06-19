package org.jboss.seam.university.action;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.jboss.seam.jcr.annotations.JcrConfiguration;
import org.jboss.seam.remoting.annotations.WebRemote;
import org.jboss.seam.university.model.Category;

/**
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class ContentAction {

    //@Inject Logging log;
    
    @Inject @JcrConfiguration(name="org.modeshape.jcr.URL", value="jndi:jcr/local?repositoryName=repository")
    Repository repository;
    
    //@Inject @JcrConfiguration(name="org.modeshape.jcr.URL",value="jndi:jcr/local?repositoryName=repository")
    //Session session;
    
    @WebRemote
    public boolean saveLocalContent(Category category, String title, String content) throws Exception  {   
        
        if (title == null) {
            throw new IllegalArgumentException("Error - title parameter cannot be null");
        }
        
        Session session = repository.login("default");
        try {
            
            String user = session.getUserID();
            String name = repository.getDescriptor(Repository.REP_NAME_DESC);           
            
            Node rootNode = session.getRootNode();            
            Node categoryParent = rootNode.hasNode(category.getName()) ? rootNode.getNode(category.getName()) :
                rootNode.addNode(category.getName());

            Node contentNode = categoryParent.addNode(title);
            contentNode.setProperty("content", content);
            contentNode.setProperty("created", System.currentTimeMillis());
            session.save();
            
            return true;
        }
        finally {
            session.logout();
        }
    }
}
