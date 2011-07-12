package org.jboss.seam.university.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.persistence.EntityManager;

import org.jboss.seam.jcr.annotations.JcrConfiguration;
import org.jboss.seam.remoting.annotations.WebRemote;
import org.jboss.seam.university.dto.Content;
import org.jboss.seam.university.model.LatestContent;

public @RequestScoped class ContentSearch {

    @Inject EntityManager entityManager;
    
    @Inject Repository repository;
    
    @WebRemote
    public List<Content> listLatestContent() throws Exception  {   
        
        List<LatestContent> latest = entityManager
                .createQuery("select c from LatestContent c order by updated desc")
                .getResultList();
        
        List<Content> latestContent = new ArrayList<Content>();
        
        Session session = repository.login("default");
        try {
            
            for (LatestContent lc : latest) {
                Node contentNode = session.getNodeByIdentifier(lc.getUuid());
                Content content = new Content();
                content.setTitle(contentNode.getName());
                content.setContent(contentNode.getProperty("content").getString());
                content.setCreated(lc.getUpdated());
                latestContent.add(content);               
            }            
            
            return latestContent;
        }
        finally {
            session.logout();
        }
    }
}
