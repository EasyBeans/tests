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
 * $Id: TestRequiredNewException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Verifies if the container-managed transaction in the session bean is
 * following the JSR 220. The container must handle the different types of
 * exception in the transaction context. The items covered in this test are:
 * 13.3
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean .
 *              SFSBContainerTransactionRequiredNewApp,
 *              SFSBContainerTransactionRequiredNewRollback and
 *              SFSBContainerTransactionRequiredNewRuntime must be deployed for
 *              testing stateful session bean. And, the bean
 *              SLSBContainerTransactionRequiredNewApp,
 *              SLSBContainerTransactionRequiredNewRollback and
 *              SLSBContainerTransactionRequiredNewRuntime must be deployed for
 *              testing stateless session bean.
 * @setup gets the reference of the bean and binds the databases specified in
 *        the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class TestRequiredNewException extends TestContainerTransactionException {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestRequiredNewException.class);

    /**
     * Verifies if the container does not use the same transaction when the
     * other bean has the transaction attribute NOT_SUPPORTED.The bean method throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testCallOtherBeanNotSup() throws Exception {
        super.testCallOtherBeanNotSup();
        //verifies if the transaction in the bean was rolled back.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
            fail("The container did not make a rollback in the transaction.");
        } catch (SQLException e) {
            logger.debug("The test threw an expected exception {0}", e);
        }
        // verifies if the table in the second bean was destroyed. This table
        // must not to be destroyed because the other bean has the transaction
        // attribute NOT_SUPPORTED.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_2, ItfTransactionMisc00.TABLE);
        } catch (SQLException e) {
            fail("The container made a rollback in the transaction.");
        }
    }

    /**
     * Verifies if the container uses the same transaction when the other bean
     * has the transaction attribute REQUIRED.The method called
     * throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testCallOtherBeanReq() throws Exception {
        super.testCallOtherBeanReq();
        //verifies if the transaction in the bean was rolled back.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
            fail("The container did not make a rollback in the transaction.");
        } catch (SQLException e) {
            logger.debug("The test threw an expected exception {0}", e);
        }
        // verifies if the table in the second bean was destroyed. This table
        // must to be destroyed because the two beans are using the same
        // transaction.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_2, ItfTransactionMisc00.TABLE);
            fail("The container did not make a rollback in the transaction.");
        } catch (SQLException e) {
            logger.debug("The test threw an expected exception {0}", e);
        }
    }

    /**
     * Verifies if the container uses the same transaction that the client. The
     * @input -
     * @output the table created by the client must to be correctly commited.
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTrans() throws Exception {
        // gets the transaction
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        // starts the transaction
        utx.begin();
        // creates a table in the second database
        ItfDatabaseManager slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class,
                ItfDatabaseManager.class);
        slsbDatabaseManager.insertTable(DATABASE_2, ItfContainerTransaction.TABLE);
        /*
         * Calls a bean method that forces an error and makes a roll back in the
         * method. The bean is using a required new attribute, so the bean
         * cannot use the same transaction that the client.
         */
        try {
            getRuntimeBean().insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
        } catch (EJBException e) {
            logger.debug("The bean threw an expected exception {0}", e);
        }
        // commits the transaction
        utx.commit();
        /*
         * the bean roll back must not to make any influence in the user
         * transaction context. So, the client table must be create correctly,
         * and consequently the "select" query in the table must not to throw
         * exception.
         */
        ExceptionHandleUtil.verifyTable(DATABASE_2, ItfContainerTransaction.TABLE);
    }

}
