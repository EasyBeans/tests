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
 * $Id: TestRequiredNewRemoteSLSB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.stateless;

import java.sql.SQLException;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.SLSBContainerTransactionRequiredNew;
import org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container-managed transaction in the stateless bean is
 * following the JSR 220.The items covered in this test are: 12.6.2.4.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBContainerTransactionRequiredNew
 *              must be deployed.
 * @setup gets the reference of SLSBContainerTransactionRequiredNew and binds
 *        the databases specified in the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestRequiredNewRemoteSLSB extends TestRequiredNew {

    /**
     * Creates a new instance of the bean.
     * @throws Exception if a lookup error occurs.
     */
    @BeforeMethod
    public void createBean() throws Exception {
        super.createBean(SLSBContainerTransactionRequiredNew.class);
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew
     * @throws Exception if an error during the test startup occurs.
     */
    @BeforeClass
    @Override
    public void setup() throws Exception {
        super.setup();
    }


    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew
     * @throws Exception if an error occurs during the tests.
     */
    @Test(groups = {"rollback"}, expectedExceptions = SQLException.class)
    @Override
    public void testSetRollbackOnly() throws Exception {
        super.testSetRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew
     * @throws Exception if an error occurs during the tests.
     */
    @Test(groups = {"rollback"})
    @Override
    public void testGetRollbackOnly() throws Exception {
        super.testGetRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew
     */
    @BeforeMethod
    @Override
    public void deleteTable() {
        super.deleteTable();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"general attribute features"}, expectedExceptions = java.lang.IllegalStateException.class)
    @Override
    public void testGetUserTransactionWithEJBContext() throws Exception {
        super.testGetUserTransactionWithEJBContext();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNew
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
