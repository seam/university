package org.jboss.seam.university.system;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Initialized;
import org.jboss.seam.transaction.Transactional;
import org.jboss.seam.university.model.Category;
import org.jboss.seam.university.model.Category.ContentEditor;
import org.jboss.seam.university.model.IdentityObjectType;

/**
 * Validates that the database contains the minimum required entities to function 
 *   
 * @author Shane Bryzak
 */
public class ValidateDatabase {
    @PersistenceContext EntityManager entityManager;
    
    @Transactional
    public void validate(@Observes @Initialized WebApplication webapp) {
        validateIdentityObjectTypes();
        validateCategories();
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
}
