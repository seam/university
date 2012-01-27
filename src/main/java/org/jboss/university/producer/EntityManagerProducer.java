package org.jboss.university.producer;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

/**
 * 
 * @author Shane Bryzak
 *
 */
public class EntityManagerProducer
{
   @Produces
   @ExtensionManaged
   //@ConversationScoped
   @RequestScoped
   @PersistenceUnit
   EntityManagerFactory emf;
}
