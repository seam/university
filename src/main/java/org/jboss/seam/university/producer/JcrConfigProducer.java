package org.jboss.seam.university.producer;

import java.net.URL;
import java.util.Collections;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jboss.seam.jcr.ConfigParams;
import org.jboss.seam.solder.resourceLoader.Resource;

public class JcrConfigProducer {
    @Produces @Named(ConfigParams.JCR_REPOSITORY_CONFIG_MAP) Map<String,String> produceJcrConfig(@Resource("modeshape.xml") URL url) {
        return Collections.singletonMap(ConfigParams.MODESHAPE_URL,url.toExternalForm()); 
    }    
}
