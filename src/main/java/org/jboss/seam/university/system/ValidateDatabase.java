package org.jboss.seam.university.system;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Initialized;
import org.jboss.seam.transaction.Transactional;
import org.jboss.seam.university.model.IdentityObjectType;

/**
 * 
 * @author Shane Bryzak
 *
 */
public class ValidateDatabase {
    @PersistenceContext EntityManager entityManager;
    
    @Transactional
    public void validate(@Observes @Initialized WebApplication webapp) {
        validateIdentityObjectTypes();
        
    }
    
    private void validateIdentityObjectTypes() {
        if (entityManager.createQuery("select t from IdentityObjectType t where t.name = :name")
            .setParameter("name", "USER")
            .getResultList().size() == 0) {
            
            IdentityObjectType user = new IdentityObjectType();
            user.setName("USER");
            entityManager.persist(user);    
        }
        
        if (entityManager.createQuery("select t from IdentityObjectType t where t.name = :name")
                .setParameter("name", "GROUP")
                .getResultList().size() == 0) {
                
                IdentityObjectType group = new IdentityObjectType();
                group.setName("GROUP");
                entityManager.persist(group);    
        }
    }
    
}
