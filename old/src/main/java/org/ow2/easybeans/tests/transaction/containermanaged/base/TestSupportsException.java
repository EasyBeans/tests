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
 * $Id: TestSupportsException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJBTransactionRolledbackException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Verifies if the container-managed transaction in the session bean is
 * following the JSR 220. The container must handle the different types of
 * exception in the transaction context. The items covered in this test are:
 * 13.3
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean .
 *              SFSBContainerTransactionSupportsNewApp,
 *              SFSBContainerTransactionSupportsNewRollback and
 *              SFSBContainerTransactionSupportsNewRuntime must be deployed for
 *              testing stateful session bean. And, the bean
 *              SLSBContainerTransactionSupportsNewApp,
 *              SLSBContainerTransactionSupportsNewRollback and
 *              SLSBContainerTransactionSupportsNewRuntime must be deployed for
 *              testing stateless session bean.
 * @setup gets the reference of the bean and binds the databases specified in
 *        the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class TestSupportsException extends TestContainerTransactionException {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestSupportsException.class);

    /**
     * Verifies if the container does not use the same transaction when the
     * other bean has the transaction attribute NOT_SUPPORTED. In this case, the
     * client has a transaction, so the bean performs in the same way than the
     * REQUIRED.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    public void testCallOtherBeanNotSupWithTrans() throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();

        try {
            getRuntimeBean().insertTablesUsingAuxBeanNotSup(DATABASE_1, DATABASE_2);
            fail("The container did not throw the EJBTransactionRolledbackException.");
        } catch (EJBTransactionRolledbackException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the container discarded the instance.
        if (!ExceptionHandleUtil.isDiscarded(getRuntimeBean())) {
            fail("The bean was not discarded.");
        }

        // tries to commit the transaction
        try {
            utx.commit();
            fail("The transaction is marked as rollback. The client cannot make the commit.");
        } catch (Exception e) {
            logger.debug("Expected exception {0}", e);
        }

        // verifies if the transaction in the bean was rolled back.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
            fail("The container did not make a rollback in the transaction.");
        } catch (SQLException e) {
            logger.debug("The test threw an expected exception {0}", e);
        }
        // verifies if the table in the second bean was destroyed. This table
        // must not to be destroyed because the other bean has the transaction
        // atttribute NOT_SUPPORTED.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_2, ItfTransactionMisc00.TABLE);
        } catch (SQLException e) {
            fail("The container made a rollback in the transaction.");
        }
    }

    /**
     * Verifies if the container uses the same transaction when the other bean
     * has the transaction attribute REQUIRED. In this case, the client has a
     * transaction, so the bean performs in the same way than the REQUIRED.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    public void testCallOtherBeanReqWithTrans() throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();
        try {
            getRuntimeBean().insertTablesUsingAuxBeanReq(DATABASE_1, DATABASE_2);
            fail("The container did not throw the EJBTransactionRolledbackException.");
        } catch (EJBTransactionRolledbackException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the container discarded the instance.
        if (!ExceptionHandleUtil.isDiscarded(getRuntimeBean())) {
            fail("The bean was not discarded.");
        }
        // tries to commit the transaction
        try {
            utx.commit();
            fail("The transaction is marked as rollback. The client cannot make the commit.");
        } catch (Exception e) {
            logger.debug("Expected exception {0}", e);
        }

        // verifies if the transaction in the bean was rolled back.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
            fail("The container did not make a rollback in the transaction.");
        } catch (SQLException e) {
            logger.debug("The test threw an expected excpetion {0}", e);
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
     * Verifies if the container does not use the same transaction when the
     * other bean has the transaction attribute NOT_SUPPORTED. The attribute
     * transaction SUPPORTS has the same behavior that the attribute
     * NOT_SUPPORTED that does not have a transaction context, so there is not
     * transaction to be managed.The bean method throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    public void testCallOtherBeanNotSupWithoutTrans() throws Exception {
        super.testCallOtherBeanNotSup();
        verifyCallOtherBean();
    }

    /**
     * Verifies if the container uses the same transaction when the other bean
     * has the transaction attribute REQUIRED.The bean method throws a runtime
     * exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    public void testCallOtherBeanReqWithoutTrans() throws Exception {
        super.testCallOtherBeanReq();
        verifyCallOtherBean();
    }

    /**
     * Verifies if the bean did not made a rollback, because there is not a
     * transaction context in this case.
     * @throws Exception if a problem occurs.
     */
    private void verifyCallOtherBean() throws Exception {
        //verifies if the transaction in the bean was rolled back.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
        } catch (SQLException e) {
            fail("The container made a rollback in the transaction.");
        }
        // verifies if the table in the second bean was destroyed. This table
        // must not to be destroyed because the two beans are not using the same
        // transaction.
        try {
            ExceptionHandleUtil.verifyTable(DATABASE_2, ItfTransactionMisc00.TABLE);
        } catch (SQLException e) {
            fail("The container made a rollback in the transaction.");
        }
    }

}
