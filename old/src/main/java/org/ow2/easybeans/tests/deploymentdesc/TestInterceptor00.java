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
 * $Id: TestInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml.ItfInterceptorTester00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml.SFSBInterceptorTester00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can deploy an bean with the interceptors defined in
 * the deployment description. Item 12.8
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SFSBInterceptorTester00,SFSBInterceptorXML and
 *              SLSBCallbackLoggerAccess must be deployed to make the tests,
 *              and, the deployment descriptors must be used too.
 * @setup gets an instance of the SFSBInterceptorTester00 and the
 *        SLSBCallbackLoggerAccess. Also, it cleans the log.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestInterceptor00 {

    /**
     * Bean used to verify the interceptor definition..
     */
    private ItfInterceptorTester00 tester;

    /**
     * Bean used to clean the callback methods.
     */
    private ItfCallbackLoggerAccess clBean;

    /**
     * Creates the beans used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        clBean = EJBHelper.getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
        clBean.deleteAll();
        tester = EJBHelper.getBeanRemoteInstance(SFSBInterceptorTester00.class, ItfInterceptorTester00.class);
    }

    /**
     * Verifies if the default interceptors and the class interceptors defined
     * in the deployment descriptors are called.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testInterceptorOrder01() {
        tester.testInterceptorOrder01();
    }

    /**
     * Verifies if the interceptor defined for a method works correctly. The
     * interceptor is defined to the methods with the name insertOrder2, so the
     * both methods( insertOrder2(List) and insertOrder2(List, int) ) must have
     * the same interceptors.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testInterceptorOrder02() {
        tester.testInterceptorOrder02();
    }

    /**
     * Verifies the element exclude class interceptors.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testInterceptorOrder03() {
        tester.testInterceptorOrder03();
    }

    /**
     * Verifies if the postConstruct defined by deployment descriptor in a bean
     * class is called.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testPostConstruct() {
        tester.testPostConstruct();
    }

    /**
     * Verifies if the preDestroy defined by deployment descriptor in a bean
     * class is called.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testPreDestroy() {
        tester.testPreDestroy();
    }

    /**
     * Verifies if the prePassivate defined by deployment descriptor in a bean
     * class is called.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testPrePassivate() throws Exception {
        tester.testPrePassivate();
        //TODO: Needs to be implemented
    }

    /**
     * Verifies if the postActivate defined by deployment descriptor in a bean
     * class is called.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testPostActivate() throws Exception {
        tester.testPostActivate();
        //TODO: Needs to be implemented
    }
}
