package org.jboss.university.action;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.transaction.Transactional;
import org.jboss.university.model.Content;
import org.jboss.university.model.LatestContent;

/**
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class LatestContentAction {

    @Inject EntityManager entityManager;
    
    @Transactional
    public void add(Content content) {
        LatestContent lc = new LatestContent();
        lc.setContent(content);
        entityManager.persist(lc);
    }
}
