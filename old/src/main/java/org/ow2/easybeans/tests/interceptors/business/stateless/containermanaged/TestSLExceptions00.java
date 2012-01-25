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
 * $Id: TestSLExceptions00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.stateless.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;
import static org.ow2.easybeans.tests.common.helper.ExceptionHelper.checkCause;

import javax.ejb.EJBException;

import org.ow2.easybeans.tests.common.ejbs.base.ItfExceptions;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptorexception.SLSBInterceptorExceptions00;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.common.exception.CustomException01;
import org.ow2.easybeans.tests.interceptors.business.base.exception.BaseExceptions00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the interceptors and exceptions are working well.
 * @reference JSR 220 - EJB 3.0 Core - Item: 12.3.2
 * @requirement Application Server must be running; all test beans must be
 *              deployed.
 *              (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of SLSBInterceptorExceptions00
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLExceptions00 extends BaseExceptions00{

    /**
     * Gets bean instance used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        ItfExceptions bean = getBeanRemoteInstance(SLSBInterceptorExceptions00.class, ItfExceptions.class);
        super.setBean(bean);
    }

    /**
     * Verifies if the interceptor can throw a customized exception.
     * @throws Exception if there is an enhancer exception.
     */
    @Override
    @Test(groups = {"Exceptions"}, expectedExceptions = CustomException00.class)
    public void testException00() throws Exception {
        super.testException00();
    }

    /**
     * Verifies if the interceptor can throw a customized exception. The EJBException is expected and its cause must be a CustomException01.
     * @throws Exception if there is an enhancer exception.
     */
    @Override
    @Test(groups = {"Exceptions"})
    public void testException01() throws Exception {
        try{
            super.testException01();
        }catch(EJBException e){
            checkCause(e, CustomException01.class);
        }

    }

    /**
     * Verifies if the interceptor can throw a customized exception. The EJBException is expected and its cause must be a CustomException01.
     * @throws Exception if there is an enhancer exception.
     */
    @Override
    @Test(groups = {"Exceptions"})
    public void testException02() throws Exception {
        try{
            super.testException02();
        }catch(EJBException e){
            checkCause(e, CustomException01.class);
        }
    }

    /**
     * Verifies if the interceptor can throw a customized exception.
     * @throws Exception if there is an enhancer exception.
     */
    @Override
    @Test(groups = {"Exceptions"}, expectedExceptions = {CustomException00.class, CustomException01.class})
    public void testException03() throws Exception {
        super.testException03();
    }
}
