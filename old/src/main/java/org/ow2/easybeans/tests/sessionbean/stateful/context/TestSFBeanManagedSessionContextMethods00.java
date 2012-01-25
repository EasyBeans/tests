/**
 * EasyBeans
 * Copyright (C) 2006 Bull S.A.S.
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
 * $Id: TestSFBeanManagedSessionContextMethods00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.sessionbean.stateful.context;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;
import static org.ow2.easybeans.tests.common.helper.ExceptionHelper.checkCause;
import static org.testng.Assert.fail;

import javax.ejb.EJBException;

import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextBMT;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.sessioncontext.SFSBBeanManagedSessionCtxMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies the SessionContext's method invocation. The tests for the methods
 * isCallerPrincipal, isCallerInRole and getCallerIdentity are defined in the
 * security package.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the beans
 *              org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.sessioncontext.*
 *              must be deployed.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFBeanManagedSessionContextMethods00 implements ItfSessionContextBMT, ItfSessionContextCMT,
        ItfSessionContextEJB3, ItfSessionContextTimer {

    /**
     * Bean.
     */
    private ItfSessionContextBMT beanBMT;

    /**
     * Bean.
     */
    private ItfSessionContextCMT beanCMT;

    /**
     * Bean.
     */
    private ItfSessionContextEJB3 beanEJB3;


    /**
     * Bean.
     */
    private ItfSessionContextTimer beanTimer;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        beanBMT = getBeanRemoteInstance(SFSBBeanManagedSessionCtxMethods.class, ItfSessionContextBMT.class);
        beanCMT = getBeanRemoteInstance(SFSBBeanManagedSessionCtxMethods.class, ItfSessionContextCMT.class);
        beanEJB3 = getBeanRemoteInstance(SFSBBeanManagedSessionCtxMethods.class, ItfSessionContextEJB3.class);
        beanTimer = getBeanRemoteInstance(SFSBBeanManagedSessionCtxMethods.class, ItfSessionContextTimer.class);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextBMT
     * @input -
     * @output -
     */
    @Test
    public void verifyGetUserTransaction() {
        beanBMT.verifyGetUserTransaction();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3
     * @input -
     * @output -
     */
    @Test
    public void verifyGetBusinessObject() {
        beanEJB3.verifyGetBusinessObject();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3
     * @input -
     * @output -
     */
    @Test
    public void verifyGetInvokedBusinessInterface() {
        beanEJB3.verifyGetInvokedBusinessInterface();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer
     * @input -
     * @output IllegalStateException encapsulated into an EJBException.
     */
    @Test
    public void verifyGetTimerService() {
        try {
            beanTimer.verifyGetTimerService();
            fail("The bean must throw an exception.");
        } catch (EJBException e) {
            checkCause(e, IllegalStateException.class);
        }

    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer
     * @input -
     * @output IllegalStateException encapsulated into an EJBException.
     */
    @Test
    public void verifySetRollbackOnly() {
        try {
            beanCMT.verifySetRollbackOnly();
            fail("The bean must throw an exception.");
        } catch (EJBException e) {
            checkCause(e, IllegalStateException.class);
        }
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer
     * @input -
     * @output IllegalStateException encapsulated into an EJBException.
     */
    @Test
    public void verifyGetRollbackOnly() {
        try {
            beanCMT.verifyGetRollbackOnly();
            fail("The bean must throw an exception.");
        } catch (EJBException e) {
            checkCause(e, IllegalStateException.class);
        }
    }


}
