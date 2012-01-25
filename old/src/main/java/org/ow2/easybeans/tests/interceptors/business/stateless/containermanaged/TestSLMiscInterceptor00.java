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
 * $Id: TestSLMiscInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.stateless.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleBean;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest02;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest03;
import org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the order to execute the method and callbacks interceptors is
 * correct, also verifies the interceptors inheritance. The correct order is: <li>Default
 * Interceptor in the order of theirs specification.</li> <li>Interceptor
 * Classes in the order of theirs specification.</li> <li>Interceptor Methods
 * in the order of theirs specification.</li> <li>Interceptors annotated to a
 * business method in the order of theirs specification.</li> <br> The bean
 * used to the test has one interceptor.
 * @reference JSR 220 - EJB 3.0 Core - 12.3.1
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBSimpleInterceptorTest.java
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of SLSBSimpleInterceptorTest00,
 *        SLSBSimpleInterceptorTest01, SLSBSimpleInterceptorTest02
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLMiscInterceptor00 extends BaseMiscInterceptor00{

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void startUp() throws Exception {
        ItfSimpleBean<Integer> bean00 = getBeanRemoteInstance(SLSBSimpleInterceptorTest00.class, ItfSimpleBean.class);
        ItfSimpleBean<Integer> bean01 = getBeanRemoteInstance(SLSBSimpleInterceptorTest01.class, ItfSimpleBean.class);
        ItfSimpleBean<Integer> bean02 = getBeanRemoteInstance(SLSBSimpleInterceptorTest02.class, ItfSimpleBean.class);
        ItfSimpleBean<Integer> bean03 = getBeanRemoteInstance(SLSBSimpleInterceptorTest03.class, ItfSimpleBean.class);
        super.setBeans(bean00, bean01, bean02, bean03);
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void testMiscPublicInterCallOrder() {
        super.testMiscPublicInterCallOrder();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void testMiscProtectedInterCallOrder() {
        super.testMiscProtectedInterCallOrder();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void testMiscPrivateInterCallOrder() {
        super.testMiscPrivateInterCallOrder();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void testMiscInheritanceInterCallOrder00() {
        super.testMiscInheritanceInterCallOrder00();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void testMiscInheritanceInterCallOrder01() {
        super.testMiscInheritanceInterCallOrder01();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void testMiscInheritanceInterCallOrder02() {
        super.testMiscInheritanceInterCallOrder02();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMiscInterceptor00
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void testMiscPackageInterCallOrder() {
        super.testMiscPackageInterCallOrder();
    }
}
