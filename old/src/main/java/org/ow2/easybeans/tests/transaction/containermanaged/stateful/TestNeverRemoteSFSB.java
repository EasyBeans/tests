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
 * $Id: TestNeverRemoteSFSB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.stateful;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransactionNever;
import org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container-managed transaction in the stateful bean is
 * following the JSR 220.The items covered in this test are: 12.6.2.6.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.SFSBContainerTransactionNever
 *              must be deployed.
 * @setup gets the reference of SFSBContainerTransactionNever and binds the
 *        databases specified in the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestNeverRemoteSFSB extends TestNever {

    /**
     * Creates a new instance of the bean.
     * @throws Exception if a lookup error occurs.
     */
    @BeforeMethod
    public void createBean() throws Exception {
        super.createBean(SFSBContainerTransactionNever.class);
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     * @throws Exception if an error during the test startup occurs.
     */
    @BeforeClass
    @Override
    public void setup() throws Exception {
        super.setup();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"never attribute features"}, expectedExceptions = javax.ejb.EJBException.class)
    @Override
    public void testUsingClientTrans() throws Exception {
        super.testUsingClientTrans();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     * @throws Exception if an error during the test occurs.
     */
    @Test(groups = {"roll back"}, expectedExceptions = java.lang.IllegalStateException.class)
    @Override
    public void testSetRollbackOnly() throws Exception {
        super.testSetRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     * @throws Exception if an error during the test occurs.
     */
    @Test(groups = {"roll back"}, expectedExceptions = java.lang.IllegalStateException.class)
    @Override
    public void testGetRollbackOnly() throws Exception {
        super.testGetRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     */
    @BeforeMethod
    @Override
    public void deleteTable() {
        super.deleteTable();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"general attribute features"}, expectedExceptions = java.lang.IllegalStateException.class)
    @Override
    public void testGetUserTransactionWithEJBContext() throws Exception {
        super.testGetUserTransactionWithEJBContext();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestNever
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"general attribute features"}, expectedExceptions = javax.naming.NameNotFoundException.class)
    @Override
    public void testGetUserTransactionWithLookup() throws Exception {
        super.testGetUserTransactionWithLookup();
    }

    /**
     * Makes a rollback in the transaction actives.
     * @throws Exception if a transaction exception occurs.
     */
    @BeforeMethod
    @Override
    public void cleanTransaction() throws Exception{
        super.cleanTransaction();
    }

}
