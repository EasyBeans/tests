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
 * $Id: TestInterceptor01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml.ItfInterceptorTester01;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml.SFSBInterceptorTester01;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can deploy an bean with the interceptors defined in
 * the deployment description. Item 12.8
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SFSBInterceptorTester01 and SFSBInterceptorXML must be
 *              deployed to make the tests, and, the deployment descriptors must
 *              be used too.
 * @setup gets an instance of the SFSBInterceptorTester01.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestInterceptor01 {

    /**
     * Bean used to verify the interceptor definition..
     */
    private ItfInterceptorTester01 tester;


    /**
     * Creates the beans used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
         tester = EJBHelper.getBeanRemoteInstance(SFSBInterceptorTester01.class, ItfInterceptorTester01.class);
    }

    /**
     * Verifies if the default interceptor defined
     * in the deployment descriptors is called.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testInterceptorOrder01() {
        tester.testInterceptorOrder01();
    }

    /**
     * Verifies if the interceptor defined for a method specific(defined by name
     * and parameters) is called only for this method. The interceptor is
     * defined to the methods with the name insertOrder2(List), so only the the
     * method insertOrder2(List) must have the method interceptor called.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testInterceptorOrder02() {
        tester.testInterceptorOrder02();
    }

    /**
     * Verifies the element exclude default interceptors.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testInterceptorOrder03() {
        tester.testInterceptorOrder03();
    }
}
