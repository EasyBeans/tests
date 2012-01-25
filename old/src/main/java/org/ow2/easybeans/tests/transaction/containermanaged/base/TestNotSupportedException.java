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
 * $Id: TestNotSupportedException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.fail;

import java.sql.SQLException;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00;

/**
* Verifies if the container-managed transaction in the session bean is
* following the JSR 220. The container must handle the different types of
* exception in the transaction context. The items covered in this test are:
* 13.3
* @reference JSR 220-PROPOSED FINAL
* @requirement Application Server must be running; the bean .
*              SFSBContainerTransactionNotSupportedApp,
*              SFSBContainerTransactionNotSupportedRollback and
*              SFSBContainerTransactionNotSupportedRuntime must be deployed for
*              testing stateful session bean. And, the bean
*              SLSBContainerTransactionNotSupportedApp,
*              SLSBContainerTransactionNotSupportedRollback and
*              SLSBContainerTransactionNotSupportedRuntime must be deployed for
*              testing stateless session bean.
* @setup gets the reference of the bean and binds the databases specified in
*        the EmbeddedTest.
* @author Gisele Pinheiro Souza
* @author Eduardo Studzinski Estima de Castro
*/
public abstract class TestNotSupportedException extends TestContainerTransactionException{


    /**
     * Verifies if the container does not use the same transaction when the
     * other bean has the transaction attribute REQUIRED. The attribute
     * transaction NOT_SUPPORTED does not has a transaction context, so there is not
     * transaction to be managed.The method throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testCallOtherBeanReq() throws Exception {
          super.testCallOtherBeanReq();
    }

    /**
     * Verifies if the container does not use the same transaction when the
     * other bean has the transaction attribute NOT_SUPPORTED. The attribute
     * transaction NOT_SUPPORTED does not has a transaction context, so there is not
     * transaction to be managed.The method throws a runtime exception.
     * @input -
     * @output the method execution without error
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    public void testCallOtherBeanNotSup() throws Exception {
        super.testCallOtherBeanNotSup();
        verifyCallOtherBean();
    }

    /**
     * Verifies if the container made the rollback. The transaction attribute is
     * not_supports, so there is not transaction context.
     * @throws Exception if an error occurs.
     */
    private void verifyCallOtherBean() throws Exception{
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
