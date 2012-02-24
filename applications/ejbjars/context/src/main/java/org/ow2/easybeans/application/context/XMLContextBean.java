/**
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: easybeans@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * --------------------------------------------------------------------------
 * $Id: MetadataMerge.java 4697 2009-02-23 17:03:23Z sauthieg $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.context;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


/**
 * Bean that will be injected the session context
 * @author Florent Benoit
 */
public class XMLContextBean extends CommonBean {

    private EJBContext injectedEJBContext;
  
    private SessionContext injectedMethodSessionContext;
    
    public static EJBContext ejbContextExternalInterceptor;
    
    private boolean aroundInvokeCalled = false;
    
    private boolean postConstructCalled = false;
    
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(XMLContextBean.class.getName());

    
    @SuppressWarnings("unused")
    @Resource
    private void setSessionContext(SessionContext sessionContext) {
        this.injectedMethodSessionContext = sessionContext;
    }
    
    
    public void testInjection() {
        // Ensure that all has been injected on this class
        boolean ok = true;
        if (injectedEJBContext == null) {
            LOGGER.info("injectedEJBContext KO");
            ok = false;
        }
        
        if (injectedMethodSessionContext == null) {
            LOGGER.info("injectedMethodSessionContext KO");
            ok = false;
        }

        if (!aroundInvokeCalled) {
            LOGGER.info("aroundInvoke KO");
            ok = false;
        }

        if (!postConstructCalled) {
            LOGGER.info("postConstructCalled KO");
            ok = false;
        }

        if (!ok) {
            throw new IllegalStateException("Injection has failed");
        }
    }
    
    public void testExternalInterceptorInjection() {
        if (ejbContextExternalInterceptor == null) {
            throw new IllegalStateException("Injection has failed in external interceptor");
        }
    }
    
    public void testPostConstruct() {
        if (!postConstructCalled) {
            throw new IllegalStateException("PostConstruct not called");
        }
    }
    
    
    public void testAroundInvoke() {
        if (!aroundInvokeCalled) {
            throw new IllegalStateException("AroundInvokeCalled not called");
        }
    }
    
    
    @SuppressWarnings("unused")
    @PostConstruct
    private void postConstruct() {
        postConstructCalled = true;
    }

    
    @SuppressWarnings("unused")
    @AroundInvoke
    private Object myOwnLowerClassInterceptor(InvocationContext invocationContext) throws Exception {
        aroundInvokeCalled = true;
        return invocationContext.proceed();
    }
    
    

}
