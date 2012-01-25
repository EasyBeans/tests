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
 * $Id: TestSLSessionBeanInterface.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.sessionbean.stateless.sbinterface;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.sbinterface.SLSBSessionBeanInterface00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests beans that implements the SessionBean interface.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLSessionBeanInterface {

    /**
     * Bean.
     */
    private ItfCheck01 bean;

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
        bean = getBeanRemoteInstance(SLSBSessionBeanInterface00.class, ItfCheck01.class);
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
        beanLogger.deleteAll();
    }

    /**
     * Verifies the setSessionContext.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void verifySessionContext() throws Exception {
        bean.check();
    }

    /**
     * Verifies the ejbRemove().
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    // @Test
    public void verifyEjbRemove() throws Exception {
        // TODO: test
    }
}
