package org.jboss.seam.university.action;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.jboss.seam.jcr.annotations.JcrConfiguration;
import org.jboss.seam.remoting.annotations.WebRemote;

/**
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class ContentAction {

    @Inject @JcrConfiguration(name="org.modeshape.jcr.URL", value="modeshape-config.xml?repositoryName=repository")
    Repository repository;
    
    @Inject @JcrConfiguration(name="org.modeshape.jcr.URL",value="modeshape-config.xml?repositoryName=repository")
    Session session;
    
    @WebRemote
    public boolean save() {

        String user = session.getUserID();
        String name = repository.getDescriptor(Repository.REP_NAME_DESC);
        System.out.println("Logged in as " + user + " to a " + name + " repository."); 
        
        return true;
    }
}
