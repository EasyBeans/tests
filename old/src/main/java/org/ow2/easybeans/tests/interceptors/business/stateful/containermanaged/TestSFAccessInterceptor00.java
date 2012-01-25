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
 * $Id: TestSFAccessInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.stateful.containermanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;
import static org.ow2.easybeans.tests.common.helper.ExceptionHelper.checkCause;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEJB;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEMFactory;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEntityManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessJNDI;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessResourceManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessUserTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptoraccess.SFSBAccessInterceptorTest00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptoraccess.SFSBAccessSessionCtxInterceptor00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptoraccess.SFSBAccessSessionCtxInterceptor01;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptoraccess.SFSBAccessUserTxInterceptor00;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.interceptors.business.base.access.BaseAccessOperationsInterceptor00;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if interceptors in an external class can access the perform the
 * operations specified by the JSR-220.
 * @reference JSR 220 - EJB 3.0 Core - 4.4.1
 * @requirement Application Server must be running; the beans
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SFSB*AccessInterceptor*
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of SFSBAccessInterceptorTest00, SFSBAccessTimerInterceptor00
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFAccessInterceptor00 extends BaseAccessOperationsInterceptor00 {

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void startUp() throws Exception {
        ItfAccessJNDI bean00 = getBeanRemoteInstance(SFSBAccessInterceptorTest00.class, ItfAccessJNDI.class);
        ItfAccessEJB bean01 = getBeanRemoteInstance(SFSBAccessInterceptorTest00.class, ItfAccessEJB.class);
        ItfAccessResourceManager bean02 = getBeanRemoteInstance(SFSBAccessInterceptorTest00.class,
                ItfAccessResourceManager.class);
        ItfAccessEntityManager bean03 = getBeanRemoteInstance(SFSBAccessInterceptorTest00.class,
                ItfAccessEntityManager.class);
        ItfAccessEMFactory bean04 = getBeanRemoteInstance(SFSBAccessInterceptorTest00.class, ItfAccessEMFactory.class);
        ItfAccessSessionContext bean05 = getBeanRemoteInstance(SFSBAccessInterceptorTest00.class,
                ItfAccessSessionContext.class);

        ItfAccessSessionContext bean07 = getBeanRemoteInstance(SFSBAccessSessionCtxInterceptor00.class,
                ItfAccessSessionContext.class);
        ItfAccessSessionContext bean08 = getBeanRemoteInstance(SFSBAccessSessionCtxInterceptor01.class,
                ItfAccessSessionContext.class);
        super.setBeans(bean00, bean01, bean02, bean03, bean04, bean05, null, bean07, bean08);
    }

    /**
     * Verifies if the interceptor can access the "java:comp/env" via JNDI. This
     * operation is allowed, so there isn't an expected
     * exception.
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
     * Verifies if the interceptor can access an EJB. This
     * operation is allowed, so there isn't an expected
     * exception.
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
     * operation is allowed, so there isn't an expected
     * exception.
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
     * Verifies if the interceptor can access the entity manager. This
     * operation is allowed, so there isn't an expected
     * exception.
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
     * Verifies if the interceptor can access the entity factory. This
     * operation is allowed, so there isn't an expected
     * exception.
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
     * operation is allowed, so there isn't an expected
     * exception.
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
     * Verifies if an interceptor can access the "getTimerService()" of a SessionContext instance.
     * The specification defines that if a bean instance attempts to invoke a method and that access is not allowed, the container
     * must throw the java.lang.IllegalStateException.
     * @input -
     * @output javax.ejb.EJBException with a java.lang.IllegalStateException as cause.
     */
    @Override
    @Test(groups = {"SessionContext access"})
    public void testSessionContext01() {
        try {
            super.testSessionContext01();
        } catch (Exception e) {
            checkCause(e, IllegalStateException.class);
        }
    }

    /**
     * Verifies if an interceptor can access the "getUserTransaction()" of a SessionContext instance.
     * The specification defines that if a bean instance attempts to invoke a method and that access is not allowed, the container
     * must throw the java.lang.IllegalStateException.
     * @input -
     * @output javax.ejb.EJBException with a java.lang.IllegalStateException as cause.
     */
    @Override
    @Test(groups = {"SessionContext access"})
    public void testSessionContext02(){
        try {
            super.testSessionContext02();
        } catch (Exception e) {
            checkCause(e, IllegalStateException.class);
        }
    }

    /**
     * Verifies if an interceptor can access the user transaction.
     * The specification denies the use of this service, the reference must be null and
     * an exception must be thrown.
     * @input -
     * @output CustomException00
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"UserTransaction access"}, expectedExceptions = CustomException00.class)
    @SuppressWarnings("unused")
    public void testUserTransaction00() throws Exception {
        ItfAccessUserTransaction bean = getBeanRemoteInstance(SFSBAccessUserTxInterceptor00.class,
                ItfAccessUserTransaction.class);
        bean.accessUserTransaction(null);
    }

}
