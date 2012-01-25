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
 * $Id: TestRemoteAnnotation.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.sessionbean.misc;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic.ItfSessionBeanTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic.SLSBSessionBeanTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies the annotation remote when a bean has only one interface and has the annotation without value defined.
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the beans SLSBSessionBeanTester, SLSBDeployTest and SFSBDeployTest
 *               must be deployed.
 * @setup gets a reference of the bean SLSBSessionBeanTester.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestRemoteAnnotation {

    /**
     * Bean used during the test.
     */
    private ItfSessionBeanTester bean;

    /**
     * Gets an instance of the bean.
     * @throws Exception if an error occurs.
     */
    @BeforeMethod
    public void setup() throws Exception {
        // Gets a bean instance.
        bean = EJBHelper.getBeanRemoteInstance(SLSBSessionBeanTester.class, ItfSessionBeanTester.class);
    }

    /**
     * Verifies if the container sets a stateful bean interface as remote when the bean
     * has only one interface and the annotation Remote without value. The bean
     * was goten by annotation.
     * @input the bean injected
     * @output the correct method execution
     */
    @Test
    public void testSFByInjection() {
        bean.testSFSBByInjection();
    }

    /**
     * Verifies if the container sets a stateful bean interface as remote when the bean
     * has only one interface and the annotation Remote without value. The bean
     * was goten by lookup.
     * @input the bean injected
     * @output the correct method execution
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testSFByLookup() throws Exception {
        bean.testSFSBByLookup();
    }

    /**
     * Verifies if the container sets a stateless bean interface as remote when the bean
     * has only one interface and the annotation Remote without value. The bean
     * was goten by annotation.
     * @input the bean injected
     * @output the correct method execution
     */
    @Test
    public void testSLByInjection() {
        bean.testSLSBByInjection();
    }

    /**
     * Verifies if the container sets a stateless bean interface as remote when the bean
     * has only one interface and the annotation Remote without value. The bean
     * was goten by annotation.
     * @input the bean injected
     * @output the correct method execution
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testSLByLookup() throws Exception {
        bean.testSLSBByLookup();
    }
}
