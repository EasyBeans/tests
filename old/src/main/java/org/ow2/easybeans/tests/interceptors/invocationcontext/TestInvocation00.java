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
 * $Id: TestInvocation00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.invocationcontext;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod00;
import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocation00;
import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocationParameter01;
import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocationParameterTest;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationContext00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationContext01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationContext02;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationContext03;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationParameter00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationParameter01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext.SLSBInvocationParameterTest;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.BeanDescriptor;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ComplexObject00;
import org.testng.annotations.Test;

/**
 * Verifies if the invocation context is following the JSR 220 spec.
 * @reference JSR 220 - EJB 3.0 Core - 12.5
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.SLSB*Invocation*
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.invocationcontext)
 * @setup gets the reference of SLSBInvocation*
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestInvocation00 {

    /**
     * Constant value.
     */
    public static final int VALUE = 5000;

    /**
     * Verifies if the InvocationContext in interceptor classes maintains the
     * same bean reference.
     * @input First parameter of the method must be a BeanDescriptor
     * @output Comparation Result the BeanDescriptor created by the Client and
     *         the BeanDescriptor created into the Interceptor Method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext"})
    public void testInvocation00() throws Exception {
        testInvocation(SLSBInvocationContext00.class);
    }

    /**
     * Verifies if the InvocationContext in interceptor method of a bean class
     * maintains the same bean reference.
     * @input First parameter of the method must be a BeanDescriptor
     * @output Comparation Result the BeanDescriptor created by the Interceptor
     *         and the BeanDescriptor created into the bean method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext"})
    public void testInvocation01() throws Exception {
        testInvocation(SLSBInvocationContext01.class);
    }

    /**
     * Verifies if the InvocationContext in interceptor method of a bean class
     * maintains the same bean reference.
     * @param beanClass bean class
     * @throws Exception Exception if there is a problem with the test.
     */
    private void testInvocation(final Class beanClass) throws Exception {
        ItfInvocation00 icBean = getBeanRemoteInstance(beanClass, ItfInvocation00.class);
        icBean.check();
    }

    /**
     * Verifies if the intercepted method parameters can be modified to a null
     * reference. The interceptor method is on the bean class.
     * @input First parameter of the method must be a ComplexObject00
     * @output Comparation result between the objects passed as parameters and
     *         the objects returned by the method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "get/setParameters"})
    public void testParameters00() throws Exception {
        ItfInvocationParameterTest bean = getBeanRemoteInstance(SLSBInvocationParameterTest.class,
                ItfInvocationParameterTest.class);
        bean.testNull(SLSBInvocationParameter00.class);
    }

    /**
     * Verifies if the intercepted method parameters can be modified to a null
     * reference. The interceptor method is on the bean class.
     * @input First parameter of the method must be a ComplexObject00
     * @output Comparation result between the objects passed as parameters and
     *         the objects returned by the method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "get/setParameters"})
    public void testParameters01() throws Exception {
        ItfInvocationParameterTest bean = getBeanRemoteInstance(SLSBInvocationParameterTest.class,
                ItfInvocationParameterTest.class);
        bean.testNull(SLSBInvocationParameter01.class);
    }

    /**
     * Verifies if the intercepted method parameters can be returned WITHOUT
     * modifications. The interceptor is a external class.
     * @input First parameter of the method must be a ComplexObject00
     * @output Comparation result between the objects passed as parameters and
     *         the objects returned by the method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "get/setParameters"})
    public void testParameters02() throws Exception {
        ItfInvocationParameterTest bean = getBeanRemoteInstance(SLSBInvocationParameterTest.class,
                ItfInvocationParameterTest.class);
        bean.testWithoutModification(SLSBInvocationParameter00.class);
    }

    /**
     * Verifies if the intercepted method parameters can be returned WITHOUT
     * modifications. The interceptor is a external class.
     * @input First parameter of the method must be a ComplexObject00
     * @output Comparation result between the objects passed as parameters and
     *         the objects returned by the method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "get/setParameters"})
    public void testParameters03() throws Exception {
        ItfInvocationParameterTest bean = getBeanRemoteInstance(SLSBInvocationParameterTest.class,
                ItfInvocationParameterTest.class);
        bean.testWithoutModification(SLSBInvocationParameter01.class);
    }

    /**
     * Verifies if the intercepted method parameters can be returned WITH
     * modifications. The interceptor is a external class.
     * @input First parameter of the method must be a ComplexObject00
     * @output Comparation result between the objects passed as parameters and
     *         the objects returned by the method.
     * @throws Exception if there is a problem with the test.
     */
    @SuppressWarnings("boxing")
    @Test(groups = {"invocationContext", "get/setParameters"})
    public void testParameters04() throws Exception {
        ItfInvocationParameterTest bean = getBeanRemoteInstance(SLSBInvocationParameterTest.class,
                ItfInvocationParameterTest.class);
        bean.testWithModification(SLSBInvocationParameter00.class);
    }

    /**
     * Verifies if the intercepted method parameters can be returned WITH
     * modifications. The interceptor is a external class.
     * @input First parameter of the method must be a ComplexObject00
     * @output Comparation result between the objects passed as parameters and
     *         the objects returned by the method.
     * @throws Exception if there is a problem with the test.
     */
    @SuppressWarnings("boxing")
    @Test(groups = {"invocationContext", "get/setParameters"})
    public void testParameters05() throws Exception {
        ItfInvocationParameterTest bean = getBeanRemoteInstance(SLSBInvocationParameterTest.class,
                ItfInvocationParameterTest.class);
        bean.testWithModification(SLSBInvocationParameter01.class);
    }

    /**
     * Verifies if the context data is working following the specification.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "getContextData"})
    public void testContextData00() throws Exception {
        ItfInvocationParameter01 icBean = getBeanRemoteInstance(SLSBInvocationContext02.class,
                ItfInvocationParameter01.class);
        icBean.getObjects(null, null);
    }

    /**
     * Verifies if the context data is working following the specification.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "getContextData"})
    @SuppressWarnings("boxing")
    public void testContextData01() throws Exception {
        ItfInvocationParameter01 icBean = getBeanRemoteInstance(SLSBInvocationContext02.class,
                ItfInvocationParameter01.class);
        icBean.getObjects(new ComplexObject00(), new BeanDescriptor());
    }

    /**
     * Verifies if the context data is working following the specification.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "getContextData"})
    @SuppressWarnings("boxing")
    public void testContextData02() throws Exception {
        ItfInvocationParameter01 icBean = getBeanRemoteInstance(SLSBInvocationContext02.class,
                ItfInvocationParameter01.class);
        icBean.getObjects(new ComplexObject00(), new BeanDescriptor());
    }

    /**
     * Verifies if the proceed is returning null with a void interceptor method.
     * @throws Exception if there is a problem with the test.
     */
    @Test(groups = {"invocationContext", "proceed"})
    public void testProceed00() throws Exception {
        ItfOneMethod00 icBean = getBeanRemoteInstance(SLSBInvocationContext03.class, ItfOneMethod00.class);
        icBean.doOne(null);
    }
}
