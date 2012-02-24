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
 * Common stuff
 * @author Florent Benoit
 */
public abstract class CommonBean implements IContext {
    
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(XMLContextBean.class.getName());

    
    private EJBContext injectedEJBContext;
    

    
    private SessionContext privateInjectedSessionContext;
    
    private SessionContext protectedInjectedSessionContext;
    
    private SessionContext publicInjectedSessionContext;
    
    private boolean superAroundInvokeCalled = false;
    
    private boolean superPostConstructCalled = false;
    
    
    
    @SuppressWarnings("unused")
    @Resource
    private void setSessionContext(SessionContext sessionContext) {
        this.privateInjectedSessionContext = sessionContext;
    }
    
    @Resource
    protected void setProtectedSessionContext(SessionContext sessionContext) {
        this.protectedInjectedSessionContext = sessionContext;
    }
    
    @Resource
    public void setPublicSessionContext(SessionContext sessionContext) {
        this.publicInjectedSessionContext = sessionContext;
    }
    
    public void testSuperInjection() {
        // Ensure that all has been injected on this class
        boolean ok = true;
        if (injectedEJBContext == null) {
            LOGGER.info("injectedEJBContext KO");
            ok = false;
        }
        
        if (privateInjectedSessionContext == null) {
            LOGGER.info("privateInjectedSessionContext KO");
            ok = false;
        }

        if (protectedInjectedSessionContext == null) {
            LOGGER.info("privateInjectedSessionContext KO");
            ok = false;
        }

        if (publicInjectedSessionContext == null) {
            LOGGER.info("publicInjectedSessionContext KO");
            ok = false;
        }
        
        if (!ok) {
            throw new IllegalStateException("Injection has failed");
        }
    }
    
    
    @SuppressWarnings("unused")
    @PostConstruct
    private void postConstruct() {
        superPostConstructCalled = true;
    }

    
    @SuppressWarnings("unused")
    @AroundInvoke
    private Object myOwnInterceptor(InvocationContext invocationContext) throws Exception {
        superAroundInvokeCalled = true;
        return invocationContext.proceed();
    }
    
    

    
    public void testSuperPostConstruct() {
        if (!superPostConstructCalled) {
            throw new IllegalStateException("PostConstruct not called");
        }
    }
    
    
    public void testSuperAroundInvoke() {
        if (!superAroundInvokeCalled) {
            throw new IllegalStateException("AroundInvokeCalled not called");
        }
    }

}
