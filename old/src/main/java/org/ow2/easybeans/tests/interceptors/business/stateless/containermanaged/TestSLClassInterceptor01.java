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
 * $Id: TestSLClassInterceptor01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.stateless.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfClassInterceptor;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBClassInterceptorTest01;
import org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the order to execute the class and method interceptors is
 * correct. The correct order is:
 * <li>Default Interceptor in the order of theirs specification.</li>
 * <li>Interceptor Classes in the order of theirs specification.</li>
 * <li>Interceptor Methods in the order of theirs specification.</li>
 * <li>Interceptors annotated to a business method in the order of theirs specification.</li>
 * <br> The bean used to the test has one interceptor.
 * @reference JSR 220 - EJB 3.0 Core - 12.3.1
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBClassInterceptorTest01.java
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of SLSBClassInterceptorTest01
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestSLClassInterceptor01 extends BaseClassInterceptor01{

    /**
     * Gets bean instance used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void startUp() throws Exception{
        ItfClassInterceptor<Integer> bean = getBeanRemoteInstance(SLSBClassInterceptorTest01.class, ItfClassInterceptor.class);
        super.setBean(bean);
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"onlyClassInterceptor"})
    public void testClassInterCallOrder00() {
        super.testClassInterCallOrder00();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"excludeDefaultInterceptor", "onlyClassInterceptor"})
    public void testClassInterCallOrder01() {
        super.testClassInterCallOrder01();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"excludeClassInterceptor", "onlyClassInterceptor"})
    public void testClassInterCallOrder02() {
        super.testClassInterCallOrder02();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"methodInterceptor", "excludeClassInterceptor"})
    public void testClassInterCallOrder03() {
        super.testClassInterCallOrder03();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"excludeClassInterceptor", "methodInterceptor", "withInheritance"})
    public void testClassInterCallOrder04() {
        super.testClassInterCallOrder04();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"methodInterceptor", "withInheritance"})
    public void testClassInterCallOrder05() {
        super.testClassInterCallOrder05();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseClassInterceptor01
     *
     */
    @Override
    @Test(groups = {"methodInterceptor"})
    public void testClassInterCallOrder06() {
        super.testClassInterCallOrder06();
    }
}
