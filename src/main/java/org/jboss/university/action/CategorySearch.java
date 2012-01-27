package org.jboss.university.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.framework.MessageBus;
import org.jboss.seam.remoting.annotations.WebRemote;
import org.jboss.seam.transaction.Transactional;
import org.jboss.university.model.Category;
import org.jboss.university.model.Category.ContentEditor;

/**
 * Used to locate content categories
 * 
 * @author Shane Bryzak
 *
 */
public @ApplicationScoped class CategorySearch {

    @Inject Instance<EntityManager> entityManager;
    
    @Inject MessageBus messageBus;
    
    List<Category> categories;
    
    @WebRemote
    public List<Category> listCategories() throws Exception {
        return categories;
    }
    
    @PostConstruct
    public void loadCategories() {
        categories = entityManager.get().createQuery(
                "select c from Category c order by c.displayOrder", Category.class)
                .getResultList();
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    @Transactional
    public void addCategory(String name) {
        Category cat = new Category();
        cat.setName(name);
        cat.setLocalEditor(ContentEditor.richText);
        cat.setRemoteEditor(ContentEditor.url);
        entityManager.get().persist(cat);
        
        MessageBuilder.createMessage("CategoryService")
            .command("create")
            .with("category", cat)
            .done()
            .sendNowWith(messageBus);
    }
    
}
