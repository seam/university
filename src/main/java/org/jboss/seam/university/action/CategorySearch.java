package org.jboss.seam.university.action;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.jboss.seam.university.model.Category;
import org.jboss.seam.remoting.annotations.WebRemote;

/**
 * Used to locate content categories
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class CategorySearch {

    @WebRemote
    public List<Category> listCategories() {
        // FIXME implement this
        
        // for now, let's just hard code a couple of dummy entries
        List<Category> cats = new ArrayList<Category>();

        Category cat = new Category();
        cat.setCategoryId(1L);
        cat.setName("Article");
        cats.add(cat);
        
        cat = new Category();
        cat.setCategoryId(2L);
        cat.setName("FAQ");
        cats.add(cat);
        
        return cats;
    }
}
