package org.jboss.seam.university.system;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.security.management.picketlink.IdentitySessionProducer;
import org.jboss.solder.servlet.WebApplication;
import org.jboss.solder.servlet.event.Initialized;
import org.jboss.seam.transaction.Transactional;
import org.jboss.seam.university.model.Category;
import org.jboss.seam.university.model.Category.ContentEditor;
import org.jboss.seam.university.model.IdentityObjectCredentialType;
import org.jboss.seam.university.model.IdentityObjectType;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.IdentitySessionFactory;
import org.picketlink.idm.api.User;
import org.picketlink.idm.common.exception.IdentityException;

/**
 * Validates that the database contains the minimum required entities to function 
 *   
 * @author Shane Bryzak
 */
public class ValidateDatabase {
    @PersistenceContext EntityManager entityManager;
    
    @Inject IdentitySessionFactory identitySessionFactory;
    
    @Transactional
    public void validate(@Observes @Initialized WebApplication webapp) throws IdentityException {
        validateIdentityObjectTypes();
        validateCategories();
        validateSecurity();
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
    
    private void validateCategories() {
        Category[] expectedCategories = {
                new Category("Article", ContentEditor.richText, ContentEditor.url, false, 1),
                new Category("Blog", ContentEditor.richText, ContentEditor.url, false, 2),
                new Category("Tip", ContentEditor.tip, null, false, 3),
                new Category("FAQ", ContentEditor.faq, null, false, 4),
                new Category("Reference Documentation", null, ContentEditor.url, true, 5),
                new Category("API Documentation", null, ContentEditor.url, true, 6),
                new Category("News", ContentEditor.richText, ContentEditor.url, false, 7),
                new Category("IRC Log", ContentEditor.upload, ContentEditor.url, true, 8),
                new Category("Podcast", ContentEditor.upload, ContentEditor.url, false, 9),
                new Category("Presentation", ContentEditor.upload, ContentEditor.url, false, 10)
        };        
        
        Set<Category> categorySet = new HashSet<Category>();
        categorySet.addAll(Arrays.asList(expectedCategories));        
        
        for (Category found : (List<Category>) entityManager.createQuery("select c from Category c", Category.class).getResultList()) {
            Iterator<Category> iter = categorySet.iterator();
            while (iter.hasNext()) {
                Category cat = iter.next();
                if (cat.getName().equals(found.getName())) {
                    iter.remove();
                    break;
                }                
            }
        }        
        
        for (Category cat : categorySet) {
            entityManager.persist(cat);
        }
    }
    
    private void validateSecurity() throws IdentityException {
        // Validate credential types
        if (entityManager.createQuery("select t from IdentityObjectCredentialType t where t.name = :name")
                .setParameter("name", "PASSWORD")
                .getResultList().size() == 0) {
                
            IdentityObjectCredentialType PASSWORD = new IdentityObjectCredentialType();
            PASSWORD.setName("PASSWORD");
            entityManager.persist(PASSWORD);
        }        
        
        Map<String, Object> sessionOptions = new HashMap<String, Object>();
        sessionOptions.put(IdentitySessionProducer.SESSION_OPTION_ENTITY_MANAGER, entityManager);
        

        IdentitySession session = identitySessionFactory.createIdentitySession("default", sessionOptions);
        
        if (session.getPersistenceManager().findUser("shane") == null) {
            User u = session.getPersistenceManager().createUser("shane");
            session.getAttributesManager().updatePassword(u, "password");
        }
    }
}
