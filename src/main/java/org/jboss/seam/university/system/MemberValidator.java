package org.jboss.seam.university.system;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.PostAuthenticateEvent;
import org.jboss.seam.security.external.openid.OpenIdUser;
import org.jboss.seam.transaction.Transactional;
import org.jboss.seam.xwidgets.dto.AuthResult;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.User;

/**
 * Performs post authentication handling to validate the member
 * 
 * @author Shane Bryzak
 *
 */
public @RequestScoped class MemberValidator {

    @Inject Identity identity;
    @Inject AuthResult authResult;
    @Inject IdentitySession identitySession;
    
    public @Transactional void observePostAuthenticate(@Observes PostAuthenticateEvent event) {
        User user = identity.getUser();
        
        if (user instanceof OpenIdUser) {
            OpenIdUser oid = (OpenIdUser) user;
            authResult.setAttribute("firstName", oid.getAttribute("firstName"));
            authResult.setAttribute("lastName", oid.getAttribute("lastName"));
        }
    }
}
