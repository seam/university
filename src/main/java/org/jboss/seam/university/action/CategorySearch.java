package org.jboss.seam.university.action;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.remoting.annotations.WebRemote;
import org.jboss.seam.university.model.Category;

/**
 * Used to locate content categories
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class CategorySearch {

    @Inject EntityManager entityManager;
    
    @WebRemote
    public List<Category> listCategories() throws Exception {
        return entityManager.createQuery("select c from Category c order by c.displayOrder", Category.class).getResultList();
    }
}
