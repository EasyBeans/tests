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
 * $Id: TestSFMethodInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.stateful.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfMethodInterceptor;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptororder.SFSBMethodInterceptorTest;
import org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the order to execute the method interceptors is
 * correct. The correct order is:
 * <li>Default Interceptor in the order of theirs specification.</li>
 * <li>Interceptor Classes in the order of theirs specification.</li>
 * <li>Interceptor Methods in the order of theirs specification.</li>
 * <li>Interceptors annotated to a business method in the order of theirs specification.</li>
 * <br> The bean used to the test has one interceptor.
 * @reference JSR 220 - EJB 3.0 Core - 4.4.1
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBMethodInterceptorTest.java
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of SFSBMethodInterceptorTest
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFMethodInterceptor extends BaseMethodInterceptor{

    /**
     * Gets bean instance used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void startUp() throws Exception {
        ItfMethodInterceptor<Integer> bean = getBeanRemoteInstance(SFSBMethodInterceptorTest.class, ItfMethodInterceptor.class);
        super.setBean(bean);
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withoutInterceptor"})
    public void interceptorMethodTest00() {
        super.interceptorMethodTest00();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest02() {
        super.interceptorMethodTest02();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest03() {
        super.interceptorMethodTest03();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest04() {
        super.interceptorMethodTest04();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest05() {
        super.interceptorMethodTest05();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest06() {
        super.interceptorMethodTest06();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest07() {
        super.interceptorMethodTest07();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest08() {
        super.interceptorMethodTest08();
    }

    /**
     * @see org.ow2.easybeans.tests.interceptors.business.base.invocationorder.BaseMethodInterceptor
     */
    @Override
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest09() {
        super.interceptorMethodTest09();
    }
}
