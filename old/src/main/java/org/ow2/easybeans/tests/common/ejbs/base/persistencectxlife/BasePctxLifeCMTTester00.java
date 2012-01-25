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
 * $Id: BasePctxLifeCMTTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;
import static org.ow2.easybeans.tests.common.helper.TransactionHelper.getInternalUserTransaction;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectxlife.SFSBPCtxLifeCMT00;

/**
 * Tests container-managed transaction-scoped persistence context.
 * In this scope, the lifecycle of a persistence context ends when the associated transaction ends.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class BasePctxLifeCMTTester00 {

    /**
     * Bean.
     */
    private ItfPCtxLifetime00 bean00;

    /**
     * UserTransaction.
     */
    private UserTransaction utx;

    /**
     * Sets the bean used in the tests.
     * @param bean instance
     * @throws Exception if a problem occurs.
     */
    public void setBean(final ItfPCtxLifetime00 bean) throws Exception{
        bean00 = bean;
        bean00.initEntityManager();
        utx = getInternalUserTransaction();
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
    public void test00() throws Exception{
        utx.begin();

        bean00.createCheckEntity00();

        utx.rollback();

        assertFalse(bean00.existsEntity(), "The bean must not exists, the transaction was rolled back.");
    }

    /**
     * This test begins a transaction, creates an entity and commits the transaction. The entity must
     * exists after the commit and it must become detached, because it is an transaction persistence context.
     * @input With a client transaction, invocation of a bean method which creates an entity.
     * @output After the commit, the bean must become detached.
     * @throws Exception if a problem occurs.
     */
    public void test01() throws Exception{
        utx.begin();

        bean00.createCheckEntity00();

        utx.commit();

        //Checks if the bean is detached, the transaction was finished.
        bean00.checkDetached();
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
    public void test02() throws Exception{
        utx.begin();

        bean00.createCheckEntity00();

        //Checks if the bean is managed, the transaction is open.
        bean00.checkManaged();

        utx.rollback();

        assertFalse(bean00.existsEntity(), "The bean must not exists, the transaction was rolled back.");
    }

    /**
     * This test creates an entity and verifies if it remains managed.
     * In this test, the transaction is created by the container for each bean method invocation,
     * as it is a transaction persistence context, it becomes detached.
     * @input Without providing a client transaction, invocation of a bean method which creates an entity and persists it.
     * @output A detached entity.
     * @throws Exception if a problem occurs.
     */
    public void test03() throws Exception{
        bean00.createCheckEntity00();

        assertTrue(bean00.existsEntity(), "The bean must exists.");

        bean00.checkDetached();
    }

    /**
     * This test creates an entity, persists the entity and verifies if it becomes detached.
     * In this test, the transaction is created by the container for each bean method invocation,
     * as it is a transaction persistence context, it must becomes detached.
     * @input Without providing a client transaction, invocation of a bean method which creates an entity and persists it.
     * @output A detached entity.
     * @throws Exception if a problem occurs.
     */
    public void test04() throws Exception{
        bean00.createCheckEntity01();
        bean00.checkDetached();
    }

    /**
     * Cleans the test results.
     * @throws Exception if a problem occurs
     */
    public void tearDown() throws Exception{
        ItfPCtxLifetime00 beanRemove = getBeanRemoteInstance(SFSBPCtxLifeCMT00.class, ItfPCtxLifetime00.class);
        beanRemove.initEntityManager();
        beanRemove.removeEntity();
    }
}
