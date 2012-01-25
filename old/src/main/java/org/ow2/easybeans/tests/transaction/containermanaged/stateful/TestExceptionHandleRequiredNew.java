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
 * $Id: TestExceptionHandleRequiredNew.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.stateful;

import java.sql.SQLException;

import javax.ejb.EJBException;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransacRequiredNewApp01;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransacRequiredNewApp02;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransacRequiredNewRollback;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransacRequiredNewRuntime;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransactionRequiredNewApp;
import org.ow2.easybeans.tests.transaction.containermanaged.base.ExceptionHandleUtil;
import org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container-managed transaction in the session bean is
 * following the JSR 220. The container must handle the different types of
 * exception in the transaction context. The items covered in this test are:
 * 14.3.1
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the bean .
 *              SFSBContainerTransactionRequiresNewApp,
 *              SFSBContainerTransacRequiresNewApp01,
 *              SFSBContainerTransacRequiresNewApp02
 *              SFSBContainerTransactionRequiresNewRollback and
 *              SFSBContainerTransactionRequiresNewRuntime must be deployed .
 * @setup gets the reference of the beans , cleans the database and close all
 *        transactions.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestExceptionHandleRequiredNew extends TestExceptionHandleBase {

    /**
     * Creates the bean intances, closes the transactions and cleans the
     * database.
     * @throws Exception if an error during the bean creation occurs.
     */
    @BeforeMethod
    @Override
    public void setup() throws Exception {
        super.setup();
    }

    /**
     * Creates a bean that throws always an application exception that extends
     * Exception and has the rollback element as false.
     * @throws Exception if an error during the bean creation occurs.
     */
    @Override
    public void createBeanApp() throws Exception {
        super.createBeanApp(SFSBContainerTransactionRequiredNewApp.class);
    }

    /**
     * Creates a bean that throws always an application exception that extends
     * RuntimeException and has the rollback element as false.
     * @throws Exception if an error during the bean creation occurs.
     */
    @Override
    public void createBeanApp01() throws Exception {
        super.createBeanApp01(SFSBContainerTransacRequiredNewApp01.class);
    }

    /**
     * Creates a bean that throws always an application exception that extends
     * RuntimeException and has the rollback element as true.
     * @throws Exception if an error during the bean creation occurs.
     */
    @Override
    public void createBeanApp02() throws Exception {
        super.createBeanApp02(SFSBContainerTransacRequiredNewApp02.class);
    }

    /**
     * Creates a bean that throws always an application exception that extends
     * Exception and has the rollback element as true.
     * @throws Exception if an error during the bean creation occurs.
     */
    @Override
    public void createBeanRollback() throws Exception {
        super.createBeanRollback(SFSBContainerTransacRequiredNewRollback.class);
    }

    /**
     * Creates a bean that throws always aa runtime exception.
     * @throws Exception if an error during the bean creation occurs.
     */
    @Override
    public void createBeanRuntime() throws Exception {
        super.createBeanRuntime(SFSBContainerTransacRequiredNewRuntime.class);
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends Exception and has the rollback element as true. So, the
     * container must rollback the transaction and must re-throw the exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"application rollback exception tests"}, expectedExceptions = SQLException.class)
    @Override
    public void testNotUsingClientTransWithAppRollbackException() throws Exception {
        super.testNotUsingClientTransWithAppRollbackException();
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends Exception and has the rollback element as false. So, the
     * container must not rollback the transaction and must re-throw the
     * exception.
     * @input -
     * @output the correct method execution.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"application exception tests"})
    @Override
    public void testNotUsingClientTransWithAppException() throws Exception {
        super.testNotUsingClientTransWithAppException();
    }

    /**
     * Inserts a table in an database and after throws a runtime exception. So,
     * the container must rollback the transaction, discards the bean and throws
     * an EJBException.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"runtime exception tests"}, expectedExceptions = SQLException.class)
    @Override
    public void testNotUsingClientTransWithRuntimeException() throws Exception {
        super.testNotUsingClientTransWithRuntimeException();
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends RuntimeException and has the rollback element as true. So,
     * the container must rollback the transaction and must re-throw the
     * exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(expectedExceptions = SQLException.class)
    @Override
    public void testNotUsingClientTransWithAppRuntimeRollbackException() throws Exception {
        super.testNotUsingClientTransWithAppRuntimeRollbackException();
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends RuntimeException and has the rollback element as false. So,
     * the container must not rollback the transaction and must re-throw the
     * exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    @Override
    public void testNotUsingClientTransWithAppRuntimeException() throws Exception {
        super.testNotUsingClientTransWithAppRuntimeException();
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends Exception and has the rollback element as true. A client
     * transaction is used, but the container must starts a new one.So, the
     * container must rollback the transaction and must re-throw the exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"application rollback exception tests"}, expectedExceptions = SQLException.class)
    public void testUsingClientTransWithAppRollbackException() throws Exception {
        super.testUsingClientTransWithAppRollbackException(true);
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends Exception and has the rollback element as false. A client
     * transaction is used, but the container must starts a new one.So, the
     * container must not rollback the transaction and must re-throw the
     * exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    @Test(groups = {"application exception tests"})
    public void testUsingClientTransWithAppException() throws Exception {
        super.testUsingClientTransWithAppException();
    }

    /**
     * Inserts a table in an database and after throws a runtime exception. So,
     * the container must rollback the transaction, discards the bean and throws
     * an EJBException. A client transaction is used, but the transaction must
     * be resumed.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"runtime exception tests"}, expectedExceptions = SQLException.class)
    public void testUsingClientTransWithRuntimeException() throws Exception {
        super.testUsingClientTransWithRuntimeException(EJBException.class, true);
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends RuntimeException and has the rollback element as true. A
     * client transaction is used, but the container must starts a new one.So,
     * the container must rollback the transaction and must re-throw the
     * exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Test(expectedExceptions = SQLException.class)
    public void testUsingClientTransWithAppRuntimeRollbackException() throws Exception {
        super.testUsingClientTransWithAppRuntimeRollbackException(true);
    }

    /**
     * Inserts a table in an database and after throws an application exception
     * that extends RuntimeException and has the rollback element as false. A
     * client transaction is used, but the container must starts a new one.So,
     * the container must not rollback the transaction and must re-throw the
     * exception.
     * @input -
     * @output the exception when the test makes a select in the database to
     *         find the table created.
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestExceptionHandleBase
     * @throws Exception if an error during the tests occurs.
     */
    @Override
    @Test
    public void testUsingClientTransWithAppRuntimeException() throws Exception {
        super.testUsingClientTransWithAppRuntimeException();

    }

}
