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
 * $Id: TestSLPostConstruct00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.lifecycle.stateless.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheckPostConstruct;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.lifecallback.SLSBPostConstructExternalOrder00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.lifecallback.SLSBPostConstructExternalOrder01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.lifecallback.SLSBPostConstructInternalOrder00;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if interceptors invocation order.
 * @reference JSR 220 - EJB 3.0 Core - 12.4
 * @requirement Application Server must be running; the beans
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.lifecallback.*
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.lifecycle)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLPostConstruct00 {

    /**
     * Bean.
     */
    private ItfCheckPostConstruct beanPostConstructExternal00;

    /**
     * Bean.
     */
    private ItfCheckPostConstruct beanPostConstructExternal01;

    /**
     * Bean.
     */
    private ItfCheckPostConstruct beanPostConstructInternal;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void startUp() throws Exception {
        beanPostConstructInternal = getBeanRemoteInstance(SLSBPostConstructInternalOrder00.class, ItfCheckPostConstruct.class);
        beanPostConstructExternal00 = getBeanRemoteInstance(SLSBPostConstructExternalOrder00.class,
                ItfCheckPostConstruct.class);
        beanPostConstructExternal01 = getBeanRemoteInstance(SLSBPostConstructExternalOrder01.class,
                ItfCheckPostConstruct.class);
    }

    /**
     * Verifies if the post construct callback in invoked in the correct order.
     * The bean contains an interceptor that sets to true a boolean value and
     * the check() method verifies if the value was set.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testInternal() throws Exception {
        beanPostConstructInternal.check();
    }

    /**
     * Verifies if the post construct callback in an external class is invoked.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testExternal00() throws Exception {
        beanPostConstructExternal00.check();
    }

    /**
     * Verifies if the post construct callback in invoked in the correct order.
     * The bean contains two interceptors(inheritance) that one throw an
     * exception and the other must catch, the last must throw an exception
     * that is expected by the first invoked interceptor.
     * @input -
     * @output
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testExternal01() throws Exception {
        beanPostConstructExternal01.check();
    }
}
