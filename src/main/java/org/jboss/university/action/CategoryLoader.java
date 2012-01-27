package org.jboss.university.action;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.MessageCallback;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.server.annotations.Service;

@ApplicationScoped
@Service
public class CategoryLoader implements MessageCallback {
    
    @Inject CategorySearch categorySearch;

    @Override
    public void callback(Message message) {
                MessageBuilder.createConversation(message)
                    .toSubject("CategoryService")
                    .command("load")
                    .with("categories", categorySearch.getCategories())
                    .done()
                    .reply();                
       
    }
    
    
    
}
