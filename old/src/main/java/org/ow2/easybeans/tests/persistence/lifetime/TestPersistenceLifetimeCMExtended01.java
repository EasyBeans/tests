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
 * $Id: TestPersistenceLifetimeCMExtended01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.persistence.lifetime;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.persistencectxlife.SLSBPCtxLifeCMETest00;
import org.ow2.easybeans.tests.common.interfaces.ItfTestPCtxLifeCM00;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests container-managed extended-scoped persistence context. It uses a Stateless as client and a
 * Stateful with extendended-scoped persistence context.
 * @reference JSR 220 - Persistence API - FINAL DRAFT - 5.6.2
 * @requirement Application Server must be running; the package
 *              org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectxlife
 *              must be deployed
 * @setup gets the reference of the bean.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestPersistenceLifetimeCMExtended01{

    /**
     * Bean.
     */
    private ItfTestPCtxLifeCM00 bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBPCtxLifeCMETest00.class, ItfTestPCtxLifeCM00.class);
        bean.startUp();
    }

    /**
     * This test begins a transaction, creates an entity and rolls back the transaction.
     * The entity must not exists after the rollback and the entity instance must become detached.
     * A rollback in a transaction, which is used with the persistence context,
     * always turns detached all entities associated with the persistence context.
     * @input UserTransaction and entity.
     * @output After the rollback, the bean must not exists.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test00() throws Exception {
        bean.test00();
    }

    /**
     * This test begins a transaction, creates an entity and commits the transaction. The entity must
     * exists after the commit and it must remains managed, because it is an extended persistence context.
     * @input With a client transaction, invocation of a bean method which creates an entity.
     * @output After the commit, the bean must remains managed.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test01() throws Exception {
        bean.test01();
    }

    /**
     * This test begins a transaction and creates an entity. As it uses an extended persistence context,
     * the entity must be managed after its creation. After this step,
     * the test rolls back the transaction and the entity must become detached.
     * @input With a client transaction, invocation of a bean method which creates an entity.
     * @output An detached entity.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test02() throws Exception {
        bean.test02();
    }

    /**
     * This test creates an entity and verifies if it remains managed.
     * In this test, the transaction is created by the container for each bean method invocation,
     * however, as it is an extended persistence context, it must remains managed.
     * @input Without providing a client transaction, invocation of a bean method which creates an entity and persists it.
     * @output A managed entity.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test03() throws Exception {
        bean.test03();
    }

    /**
     * This test creates an entity, persist the entity and verifies if it remains managed.
     * In this test, the transaction is created by the container for each bean method invocation,
     * however, as it is an extended persistence context, it must remains managed.
     * @input Without providing a client transaction, invocation of a bean method which creates an entity and persists it.
     * @output A managed entity.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test04() throws Exception {
        bean.test04();
    }

    /**
     * Cleans the test results.
     * @throws Exception if a problem occurs
     */
    @AfterMethod
    public void tearDown() throws Exception {
        bean.tearDown();
    }
}
