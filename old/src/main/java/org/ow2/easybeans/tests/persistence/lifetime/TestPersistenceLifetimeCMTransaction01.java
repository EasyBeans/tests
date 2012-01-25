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
 * $Id: TestPersistenceLifetimeCMTransaction01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.persistence.lifetime;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.persistencectxlife.SLSBPCtxLifeCMTTest00;
import org.ow2.easybeans.tests.common.interfaces.ItfTestPCtxLifeCM00;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests container-managed transaction-scoped persistence context. It uses this test class as client and a
 * Stateless with transaction-scoped persistence context.
 * In this scope, the lifecycle of a persistence context ends when the associated transaction ends.
 * @reference JSR 220 - Persistence API - FINAL DRAFT - 5.6.1
 * @requirement Application Server must be running; the package
 *              org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectxlife
 *              must be deployed
 * @setup gets the reference of the bean.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestPersistenceLifetimeCMTransaction01{

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
        bean = getBeanRemoteInstance(SLSBPCtxLifeCMTTest00.class, ItfTestPCtxLifeCM00.class);
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
     * exists after the commit and it must become detached, because it is an transaction persistence context.
     * @input With a client transaction, invocation of a bean method which creates an entity.
     * @output After the commit, the bean must become detached.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test01() throws Exception {
        bean.test01();
    }

    /**
     * This test begins a transaction and creates an entity. As it uses an transaction
     * persistence context and the transaction is still open,
     * the entity remains managed after its creation. After this step,
     * the test rolls back the transaction and the entity must be removed.
     * @input With a client transaction, invocation of a bean method which creates an entity.
     * @output The entity must be removed.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test02() throws Exception {
        bean.test02();
    }

    /**
     * This test creates an entity and verifies if it remains managed.
     * In this test, the transaction is created by the container for each bean method invocation,
     * as it is a transaction persistence context, it becomes detached.
     * @input Without providing a client transaction, invocation of a bean method which creates an entity and persists it.
     * @output A detached entity.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test03() throws Exception {
        bean.test03();
    }

    /**
     * This test creates an entity, persists the entity and verifies if it becomes detached.
     * In this test, the transaction is created by the container for each bean method invocation,
     * as it is a transaction persistence context, it must becomes detached.
     * @input Without providing a client transaction, invocation of a bean method which creates an entity and persists it.
     * @output A detached entity.
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
