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
 * $Id: TestSLAccessInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.stateless.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;
import static org.ow2.easybeans.tests.common.helper.ExceptionHelper.checkCause;

import javax.ejb.EJBException;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEJB;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEMFactory;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEntityManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessJNDI;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessResourceManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessUserTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptoraccess.SLSBAccessInterceptorTest00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptoraccess.SLSBAccessSessionCtxInterceptor00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptoraccess.SLSBAccessSessionCtxInterceptor01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptoraccess.SLSBAccessUserTxInterceptor00;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.interceptors.business.base.access.BaseAccessOperationsInterceptor00;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if interceptors in an external class can perform the operations
 * specified by the JSR-220.
 * @reference JSR 220 - EJB 3.0 Core - 4.5.2 - Table 2
 * @requirement (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of SLSBAccessInterceptorTest00,
 *        SLSBAccessTimerInterceptor00
 *        (Ant task: install.jar.tests.interceptor.business)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLAccessInterceptor00 extends BaseAccessOperationsInterceptor00 {

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void startUp() throws Exception {
        ItfAccessJNDI bean00 = getBeanRemoteInstance(SLSBAccessInterceptorTest00.class, ItfAccessJNDI.class);
        ItfAccessEJB bean01 = getBeanRemoteInstance(SLSBAccessInterceptorTest00.class, ItfAccessEJB.class);
        ItfAccessResourceManager bean02 = getBeanRemoteInstance(SLSBAccessInterceptorTest00.class,
                ItfAccessResourceManager.class);
        ItfAccessEntityManager bean03 = getBeanRemoteInstance(SLSBAccessInterceptorTest00.class,
                ItfAccessEntityManager.class);
        ItfAccessEMFactory bean04 = getBeanRemoteInstance(SLSBAccessInterceptorTest00.class, ItfAccessEMFactory.class);
        ItfAccessSessionContext bean05 = getBeanRemoteInstance(SLSBAccessInterceptorTest00.class,
                ItfAccessSessionContext.class);

        ItfAccessSessionContext bean07 = getBeanRemoteInstance(SLSBAccessSessionCtxInterceptor00.class,
                ItfAccessSessionContext.class);
        ItfAccessSessionContext bean08 = getBeanRemoteInstance(SLSBAccessSessionCtxInterceptor01.class,
                ItfAccessSessionContext.class);
        super.setBeans(bean00, bean01, bean02, bean03, bean04, bean05, null, bean07, bean08);
    }

    /**
     * Verifies if the interceptor can access the "java:comp/env" via JNDI. This
     * operation is allowed, so there isn't an expected exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"java:comp/env access"})
    public void testJNDI00() throws Exception {
        super.testJNDI00();
    }

    /**
     * Verifies if the interceptor can access an EJB. This operation is allowed,
     * so there isn't an expected exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"EJB access"})
    public void testEJB00() throws Exception {
        super.testEJB00();
    }

    /**
     * Verifies if the interceptor can access the resource manager. This
     * operation is allowed, so there isn't an expected exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"Resource access"})
    public void testResource00() throws Exception {
        super.testResource00();
    }

    /**
     * Verifies if the interceptor can access the entity manager. This operation
     * is allowed, so there isn't an expected exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"Entity Manager access"})
    public void testEntityManager00() throws Exception {
        super.testEntityManager00();
    }

    /**
     * Verifies if the interceptor can access the entity factory. This operation
     * is allowed, so there isn't an expected exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"Entity Factory access"})
    public void testEntityFactory00() throws Exception {
        super.testEntityFactory00();
    }

    /**
     * Verifies if the interceptor can access the session context. This
     * operation is allowed, so there isn't an expected exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"SessionContext access"})
    public void testSessionContext00() throws Exception {
        super.testSessionContext00();
    }

    /**
     * Verifies if an interceptor can access the "getTimerService()" of a
     * SessionContext instance. This operation is allowed, so there isn't an
     * expected exception.
     * @input -
     * @output No exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"SessionContext access"})
    public void testSessionContext01() throws Exception {
        super.testSessionContext01();
    }

    /**
     * Verifies if an interceptor can access the "getUserTransaction()" of a
     * SessionContext instance. The specification defines that if a bean
     * instance attempts to invoke a method and that access is not allowed, the
     * container must throw an EJBException with java.lang.IllegalStateException
     * as cause.
     * @input -
     * @output javax.ejb.EJBException.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"SessionContext access"})
    public void testSessionContext02() throws Exception {
        try {
            super.testSessionContext02();
        } catch (EJBException e) {
            checkCause(e, IllegalStateException.class);
        }
    }

    /**
     * Verifies if an interceptor can access the user transaction. The
     * specification denies the use of this service, the container must throw
     * the javax.naming.NameNotFoundException
     * @input -
     * @output javax.naming.NameNotFoundException
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"UserTransaction access"}, expectedExceptions = CustomException00.class)
    @SuppressWarnings("unused")
    public void testUserTransaction00() throws Exception {
        ItfAccessUserTransaction bean = getBeanRemoteInstance(SLSBAccessUserTxInterceptor00.class,
                ItfAccessUserTransaction.class);
        bean.accessUserTransaction(null);
    }

}
