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
 * $Id: TestRequiredException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.fail;

import java.sql.SQLException;

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
 *              SFSBContainerTransactionRequiredApp,
 *              SFSBContainerTransactionRequiredRollback and
 *              SFSBContainerTransactionRequiredRuntime must be deployed for
 *              testing stateful session bean. And, the bean
 *              SLSBContainerTransactionRequiredApp,
 *              SLSBContainerTransactionRequiredRollback and
 *              SLSBContainerTransactionRequiredRuntime must be deployed for
 *              testing stateless session bean.
 * @setup gets the reference of the bean and binds the databases specified in
 *        the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class TestRequiredException extends TestContainerTransactionException {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestRequiredException.class);



    /**
     * Verifies if the container does not use the same transaction when the
     * other bean has the transaction attribute NOT_SUPPORTED.The bean method
     * throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testCallOtherBeanNotSup() throws Exception {
        super.testCallOtherBeanNotSup();
        verifyOtherBeanNotSupport();
    }

    /**
     * Verifies if the container uses the same transaction when the other bean
     * has the transaction attribute REQUIRED. The bean method
     * throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testCallOtherBeanReq() throws Exception {
        super.testCallOtherBeanReq();
        verifyOtherBeanReq();
    }

    /**
     * Verifies if the container made a rollback only in the method required.The
     * method with transaction attribute not_supported must no be in the
     * transaction context.
     * @throws Exception if an error occurs.
     */
    private void verifyOtherBeanNotSupport() throws Exception {
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
     * Verifies if the bean makes a rollback in the both bean method. The
     * methods have the transaction attribute required, so they must stay in the
     * same transaction context.
     * @throws Exception if a problem occurs.
     */
    private void verifyOtherBeanReq() throws Exception {
        // verifies if the transaction in the bean was rolled back.
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


}
