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
 * $Id: TestSFAllCallbackOrder00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.lifecycle.stateful.containermanaged;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.POST_CONSTRUCT;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.PRE_DESTROY;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.BasePostConstructAllOrder00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.BasePreDestroyAllOrder00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.SFSBPostConstructAllOrder;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.SFSBPreDestroyAllOrder;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct.PostConstructLogger00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct.PostConstructLogger01;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.predestroy.PreDestroyLogger00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.predestroy.PreDestroyLogger01;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if interceptors invocation order.
 * @reference JSR 220 - EJB 3.0 Core - 12.4.1
 * @requirement Application Server must be running; the beans
 *              org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.*
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.lifecycle)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFAllCallbackOrder00 {

    /**
     * Bean.
     */
    private ItfCheck02 beanPostConstructAllOrder;

    /**
     * Bean.
     */
    private ItfCheck02 beanPreDestroyAllOrder;

    /**
     * Logger bean.
     */
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void startBeans() throws Exception {
        beanPostConstructAllOrder = getBeanRemoteInstance(SFSBPostConstructAllOrder.class, ItfCheck02.class);
        beanPreDestroyAllOrder = getBeanRemoteInstance(SFSBPreDestroyAllOrder.class, ItfCheck02.class);
    }

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startLog() throws Exception {
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
        beanLogger.deleteAll();
    }

    /**
     * Verifies if the PostConstruct callback in invoked in the correct order.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testPostConstruct() throws Exception {
        beanPostConstructAllOrder.check();

        // Interceptors list
        List<String> arLife = new ArrayList<String>();

        arLife.add(PostConstructLogger00.class.getName());
        arLife.add(PostConstructLogger01.class.getName());
        arLife.add(BasePostConstructAllOrder00.class.getName());
        arLife.add(SFSBPostConstructAllOrder.class.getName());

        beanLogger.verifyCallbackOrder(SFSBPostConstructAllOrder.class, POST_CONSTRUCT, arLife.toArray(new String[0]));
    }

    /**
     * Verifies if the PreDestroy callback in invoked in the correct order.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testPreDestroy() throws Exception {
        /* TODO: review after PreDestroy implementation. */

        beanPreDestroyAllOrder.check();
        beanPreDestroyAllOrder.remove();

        // Interceptors list
        List<String> arLife = new ArrayList<String>();

        arLife.add(PreDestroyLogger00.class.getName());
        arLife.add(PreDestroyLogger01.class.getName());
        arLife.add(BasePreDestroyAllOrder00.class.getName());
        arLife.add(SFSBPreDestroyAllOrder.class.getName());

        beanLogger.verifyCallbackOrder(SFSBPreDestroyAllOrder.class, PRE_DESTROY, arLife.toArray(new String[0]));
    }

    /* TODO: tests for PrePassivate and PostActivate. */

    /**
     * Clears callback event log.
     */
    @AfterMethod
    public void tearDown() {
        beanLogger.deleteAll();
    }
}
