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
 * $Id: TestMandatory.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Verifies if the container-managed transaction in the session bean is
 * following the JSR 220.The items covered in this test are: 12.6.2.5.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean .
 *              objectweb.easybeans.tests.common.ejbs.stateful.containermanaged.SFSBContainerTransactionMandatory
 *              must be deployed for testing stateful session bean. And, the
 *              bean
 *              objectweb.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBContainerTransactionMandatory
 *              must be deployed for testing stateless session bean.
 * @setup gets the reference of the bean and binds the databases specified in
 *        the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestMandatory extends TestContainerTransactionBase {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestMandatory.class);

    /**
     * Verifies if the container uses the same transaction that the client. The
     * client rollback must to force a bean rollback, because the both use the
     * same transaction.
     * @input -
     * @output a SQLException because the table must not be created.
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testUsingClientTrans() throws Exception {
        super.testUsingClientTrans();
    }

    /**
     * Calls a setRollbackOnly and after inserts two tables. The container must
     * do a roll back before the method invocation completes. So, the tables
     * must not be created and a SQLException must be thrown when the test tries
     * to verify if the table exists.
     * @input -
     * @output an exception when the method verifies if the table exists.
     * @throws Exception if an error occurs during the tests.
     */
    public void testSetRollbackOnlyWithUserTransaction() throws Exception {
        UserTransaction utx = getUserTransaction();
        utx.begin();
        try {
            ItfContainerTransaction sfsbContainerTransaction = getBean();
            // calls the setRollbackOnly
            try {
                sfsbContainerTransaction.setRollbackOnly(DATABASE_1, DATABASE_2);
            } catch (TransactionException e) {
                throw e.getParentException();
            }
            // tries to commit the transaction
            try {
                utx.commit();
                fail("The transaction is marked as rollback. The client cannot make the commit.");
            } catch (Exception e) {
                logger.debug("Expected exception {0}", e);
            }
        } finally {
            assertFalse(transactionIsActive(),
                    "There was an exception in the server side. The container is using the same transaction "
                            + "that the client, so the cointainer should rollback the transaction.");
        }
    }

    /**
     * Calls the getRollbackOnly that must throw an exception for this type of
     * transaction attribute.The EJBTransactionRequiredException is thrown,
     * because the setRollbackOnly will be called without an transaction
     * context.
     * @input -
     * @output an exception when the method call a method without a transaction
     *         context.
     * @throws Exception if an error occurs during the tests.
     */
    public void testSetRollbackOnlyWithoutUserTransaction() throws Exception {
        try {
            testSetRollbackOnly();
        } finally {
            assertFalse(transactionIsActive(),
                    "There was an exception in the server side. The container is using the same transaction "
                            + "that the client, so the cointainer should rollback the transaction.");
        }
    }

    /**
     * Calls the getRollbackOnly that must not throw any exception for this type
     * of transaction attribute.
     * @input -
     * @output the method execution without error.
     * @throws Exception if an error occurs during the tests.
     */
    public void testGetRollbackOnlyWithUserTransaction() throws Exception {
        UserTransaction utx = getUserTransaction();

        utx.begin();
        testGetRollbackOnly();
        utx.commit();
    }

    /**
     * Calls the getRollbackOnly that must throw an exception for this type of
     * transaction attribute.The EJBTransactionRequiredException is thrown,
     * because the setRollbackOnly will be called without an transaction
     * context.
     * @input -
     * @output an exception when the method call a method without a transaction
     *         context.
     * @throws Exception if an error occurs during the tests.
     */
    public void testGetRollbackOnlyWithoutUserTransaction() throws Exception {
        testGetRollbackOnly();

    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged#testGetUserTransactionWithEJBContext()
     * @throws Exception if an error occurs during the tests.
     */
    @Override
    public void testGetUserTransactionWithEJBContext() throws Exception {
        UserTransaction utx = getUserTransaction();
        utx.begin();
        try {
            super.testGetUserTransactionWithEJBContext();
        } finally {
            utx.rollback();
        }
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged#testGetUserTransactionWithLookup()
     * @throws Exception if an error occurs during the tests.
     */
    @Override
    public void testGetUserTransactionWithLookup() throws Exception {
        UserTransaction utx = getUserTransaction();
        utx.begin();
        try {
            super.testGetUserTransactionWithLookup();
        } finally {
            utx.rollback();
        }
    }

}
