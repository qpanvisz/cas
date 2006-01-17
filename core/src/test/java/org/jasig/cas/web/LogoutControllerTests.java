/*
 * Copyright 2005 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.web;

import javax.servlet.http.Cookie;

import org.jasig.cas.AbstractCentralAuthenticationServiceTest;
import org.jasig.cas.web.util.SecureCookieGenerator;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public class LogoutControllerTests extends
    AbstractCentralAuthenticationServiceTest {

    private static final String COOKIE_TGC_ID = "CASTGC";
    
    private LogoutController logoutController;
    
    private SecureCookieGenerator warnCookieGenerator;
    
    private SecureCookieGenerator ticketGrantingTicketCookieGenerator;

    protected void onSetUp() throws Exception {
        super.onSetUp();
        
       this.warnCookieGenerator = new SecureCookieGenerator();
        
        this.warnCookieGenerator.setCookieName("test");
        this.warnCookieGenerator.setCookieValue("true");
        
        this.ticketGrantingTicketCookieGenerator = new SecureCookieGenerator();
        this.ticketGrantingTicketCookieGenerator.setCookieName(COOKIE_TGC_ID);
        
        
        this.logoutController = new LogoutController();
        this.logoutController
            .setCentralAuthenticationService(getCentralAuthenticationService());
        this.logoutController.setLogoutView("test");
        this.logoutController.setWarnCookieGenerator(this.warnCookieGenerator);
        this.logoutController.setTicketGrantingTicketCookieGenerator(this.ticketGrantingTicketCookieGenerator);
        this.logoutController.afterPropertiesSet();
    }

    public void testLogoutNoCookie() throws Exception {
        assertNotNull(this.logoutController.handleRequestInternal(
            new MockHttpServletRequest(), new MockHttpServletResponse()));
    }

    public void testLogoutForServiceWithFollowRedirects() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("service", "TestService");
        this.logoutController.setFollowServiceRedirects(true);
        assertTrue(this.logoutController.handleRequestInternal(request,
            new MockHttpServletResponse()).getView() instanceof RedirectView);
    }

    public void testLogoutForServiceWithNoFollowRedirects() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("service", "TestService");
        this.logoutController.setFollowServiceRedirects(false);
        assertTrue(!(this.logoutController.handleRequestInternal(request,
            new MockHttpServletResponse()).getView() instanceof RedirectView));
    }

    public void testLogoutCookie() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Cookie cookie = new Cookie(COOKIE_TGC_ID, "test");
        request.setCookies(new Cookie[] {cookie});
        assertNotNull(this.logoutController.handleRequestInternal(request,
            new MockHttpServletResponse()));
    }

}
