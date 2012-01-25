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
 * $Id: TestSFSessionBeanInterfaceWithAnnotation.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.sessionbean.stateful.sbinterface;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.PRE_DESTROY;
import static org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess.SLEEP;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.sbinterface.SFSBSessionBeanItfWithAnnotation;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests beans that implements the SessionBean interface.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFSessionBeanInterfaceWithAnnotation {

    /**
     * Bean.
     */
    private ItfCheck02 bean;

    /**
     * Logger bean.
     */
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SFSBSessionBeanItfWithAnnotation.class, ItfCheck02.class);
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
        beanLogger.deleteAll();
    }

    /**
     * Verifies the ejbActivate() with the &#64;PostActivate annotation is
     * working properly.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    // @Test
    public void verifyEjbActivate() throws Exception {
        // TODO: test
    }

    /**
     * Verifies the ejbPassivate() with the &#64;PrePassivate annotation is
     * working properly.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    // @Test
    public void verifyEjbPassivate() throws Exception {
        // TODO: test
    }

    /**
     * Verifies if the ejbRemove() with the &#64;PreDestroy annotation is
     * working properly.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void verifyEjbRemove() throws Exception {
        bean.remove();

        // Sleep used to wait all interceptors execution
        Thread.sleep(SLEEP);

        // Interceptors list
        List<String> arLife = new ArrayList<String>();

        arLife.add(SFSBSessionBeanItfWithAnnotation.class.getName());

        beanLogger.verifyCallbackOrder(SFSBSessionBeanItfWithAnnotation.class, PRE_DESTROY, arLife
                .toArray(new String[0]));
    }
}
