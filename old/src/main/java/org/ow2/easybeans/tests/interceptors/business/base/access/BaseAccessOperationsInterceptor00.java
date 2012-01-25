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
 * $Id: BaseAccessOperationsInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.base.access;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEJB;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEMFactory;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEntityManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessJNDI;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessResourceManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessUserTransaction;

/**
 * Verifies the interceptor access to the following items. <li>SessionContext
 * Methods</li> <li>JNDI Access to java:comp/env</li> <li>Resource Manager</li>
 * <li>Enterprise bean</li> <li>EntityManagerFactory</li> <li>EntityManager</li>
 * <li>TimerService</li> <li>UserTransaction</li>
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseAccessOperationsInterceptor00 {

    /**
     * Bean used to implement the test.
     */
    private ItfAccessJNDI beanAccessJNDI;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessEJB beanAccessEJB;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessResourceManager beanAccessResourceManager;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessEntityManager beanAccessEntityManager;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessEMFactory beanAccessEMFactory;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessSessionContext beanAccessSessionContext01;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessUserTransaction beanAcessUserTransaction;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessSessionContext beanAccessSessionContext00;

    /**
     * Bean used to implement the test.
     */
    private ItfAccessSessionContext beanAccessSessionContext02;

    /**
     * Verifies if the interceptor can access the "java:comp/env" via JNDI.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testJNDI00() throws Exception {
        beanAccessJNDI.accessJNDI(null);
    }

    /**
     * Verifies if the interceptor can access an ejb.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testEJB00() throws Exception {
        beanAccessEJB.accessEJB(null);
    }

    /**
     * Verifies if the interceptor can access a resource.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testResource00() throws Exception {
        beanAccessResourceManager.accessResManager(null);
    }

    /**
     * Verifies if the interceptor can access an Entity Manager.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testEntityManager00() throws Exception {
        beanAccessEntityManager.accessEntityManager(null);
    }

    /**
     * Verifies if the interceptor can access an Entity Factory.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testEntityFactory00() throws Exception {
        beanAccessEMFactory.accessEntityManagerFactory(null);
    }

    /**
     * Verifies if the interceptor can access a SessionContext.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testSessionContext00() throws Exception {
        beanAccessSessionContext01.accessSessionContext(null);
    }

    /**
     * Verifies if the interceptor can access an UserTransaction.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testUserTransaction00() throws Exception {
        beanAcessUserTransaction.accessUserTransaction(null);
    }

    /**
     * Verifies if the interceptor can call the "getTimerService()" of a
     * SessionContext.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testSessionContext01() throws Exception {
        beanAccessSessionContext00.accessSessionContext(null);
    }

    /**
     * Verifies if the interceptor can call "getUserTransaction()" of a
     * SessionContext.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testSessionContext02() throws Exception {
        beanAccessSessionContext02.accessSessionContext(null);
    }

    /**
     * Sets bean(s) used in the tests.
     * @param bean00 The bean to set.
     * @param bean01 The bean to set.
     * @param bean02 The bean to set.
     * @param bean03 The bean to set.
     * @param bean04 The bean to set.
     * @param bean05 The bean to set.
     * @param bean06 The bean to set.
     */
    public void setBeans(final ItfAccessJNDI bean00, final ItfAccessEJB bean01, final ItfAccessResourceManager bean02,
            final ItfAccessEntityManager bean03, final ItfAccessEMFactory bean04, final ItfAccessSessionContext bean05,
            final ItfAccessUserTransaction bean06) {
        this.beanAccessJNDI = bean00;
        this.beanAccessEJB = bean01;
        this.beanAccessResourceManager = bean02;
        this.beanAccessEntityManager = bean03;
        this.beanAccessEMFactory = bean04;
        this.beanAccessSessionContext01 = bean05;
        this.beanAcessUserTransaction = bean06;
    }

    /**
     * Sets bean(s) used in the tests.
     * @param bean00 The bean to set.
     * @param bean01 The bean to set.
     * @param bean02 The bean to set.
     * @param bean03 The bean to set.
     * @param bean04 The bean to set.
     * @param bean05 The bean to set.
     * @param bean06 The bean to set.
     * @param bean07 The bean to set.
     * @param bean08 The bean to set.
     */
    public void setBeans(final ItfAccessJNDI bean00, final ItfAccessEJB bean01, final ItfAccessResourceManager bean02,
            final ItfAccessEntityManager bean03, final ItfAccessEMFactory bean04, final ItfAccessSessionContext bean05,
            final ItfAccessUserTransaction bean06,
            final ItfAccessSessionContext bean07, final ItfAccessSessionContext bean08) {
        this.setBeans(bean00, bean01, bean02, bean03, bean04, bean05, bean06);
        this.beanAccessSessionContext00 = bean07;
        this.beanAccessSessionContext02 = bean08;
    }

}
