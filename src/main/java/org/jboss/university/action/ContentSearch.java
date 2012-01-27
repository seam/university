package org.jboss.university.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.persistence.EntityManager;

import org.jboss.seam.remoting.annotations.WebRemote;
import org.jboss.university.dto.ContentDTO;
import org.jboss.university.model.Content;
import org.jboss.university.model.LatestContent;

public @RequestScoped class ContentSearch {

    @Inject EntityManager entityManager;
    
    @Inject Repository repository;
    
    @SuppressWarnings("unchecked")
    @WebRemote
    public List<ContentDTO> listLatestContent() throws Exception  {   
        
        List<LatestContent> latest = entityManager.createQuery(
                "select c from LatestContent c order by c.content.created desc")
                .getResultList();
        
        List<ContentDTO> latestContent = new ArrayList<ContentDTO>();
        
        Session session = repository.login("default");
        try {
            
            for (LatestContent lc : latest) {
                Node contentNode = session.getNodeByIdentifier(lc.getContent().getIdentifier());
                
                ContentDTO content = new ContentDTO();
                content.setIdentifier(contentNode.getIdentifier());
                content.setTitle(contentNode.getName());
                content.setContent(contentNode.getProperty("content").getString());
                content.setCreated(lc.getContent().getCreated());
                content.setCommentCount(lc.getContent().getCommentCount());
                
                latestContent.add(content);               
            }            
            
            return latestContent;
        }
        finally {
            session.logout();
        }
    }
    
    public Content findContentByIdentifier(String identifier) {
        return (Content) entityManager.createQuery(
                "select c from Content c where c.identifier = :identifier")
                .setParameter("identifier", identifier)
                .getSingleResult();
    }
    
    @WebRemote
    public List<ContentDTO> findContent(String expr) throws Exception {
        List<ContentDTO> results = new ArrayList<ContentDTO>();
        
        Session session = repository.login("default");
        QueryManager qm = session.getWorkspace().getQueryManager();
        Query query = qm.createQuery(expr,  "Search");
        
        QueryResult r = query.execute();

        NodeIterator ni = r.getNodes();
        while (ni.hasNext()) {
           Node n = ni.nextNode();
           ContentDTO content = new ContentDTO();
           content.setTitle(n.getName());
           content.setContent(n.getProperty("content").getString());
           results.add(content);
        }
        
        return results;
    }
}
