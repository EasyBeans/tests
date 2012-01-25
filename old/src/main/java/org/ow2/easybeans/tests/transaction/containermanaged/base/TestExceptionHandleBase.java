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
 * $Id: TestExceptionHandleBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.exception.AppException;
import org.ow2.easybeans.tests.common.exception.AppRuntimeException;
import org.ow2.easybeans.tests.common.exception.RollbackAppRuntimeException;
import org.ow2.easybeans.tests.common.exception.RollbackApplicationException;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.ExceptionHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Access different beans that have the same behaviour, but the beans throw
 * different types of exception.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class TestExceptionHandleBase {

    /**
     * Constant that defines the first database name.
     */
    protected static final String DATABASE_1 = "jdbc_1";

    /**
     * Constant that defines the second database name.
     */
    protected static final String DATABASE_2 = "jdbc_2";

    /**
     * Bean used during the tests that throws an application exception with
     * rollback = false.
     */
    private ItfContainerTransaction sfsbContainerTransactionApp = null;

    /**
     * Bean used during the tests that throws an application exception with
     * rollback = true.
     */
    private ItfContainerTransaction sfsbContainerTransactionRollback = null;

    /**
     * Bean used during the tests that throws an application exception(that
     * extends a runtime exception) with rollback = false.
     */
    private ItfContainerTransaction sfsbContainerTransactionApp01 = null;

    /**
     * Bean used during the tests that throws an application exception(that
     * extends a runtime exception) with rollback = true.
     */
    private ItfContainerTransaction sfsbContainerTransactionApp02 = null;

    /**
     * Bean used during the tests that throws a runtime exception.
     */
    private ItfContainerTransaction sfsbContainerTransactionRuntime = null;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestExceptionHandleBase.class);

    /**
     * Initiates all things needed to execute the tests.
     * @throws Exception if an error occurs.
     */
    public void setup() throws Exception {
        // cleans the transaction active in other test
        ExceptionHandleUtil.cleanTransaction();
        // creates all beans
        createBeanApp();
        createBeanApp01();
        createBeanApp02();
        createBeanRollback();
        createBeanRuntime();
        // cleans the database
        deleteTable();
    }

    /**
     * Creates a bean that throws always an application exception that extends
     * Exception and has a the rollback element as false.
     * @throws Exception if a lookup error occurs.
     */
    public abstract void createBeanApp() throws Exception;

    /**
     * Creates a bean that throws always an application exception that extends
     * RuntimeException and has a the rollback element as false.
     * @throws Exception if a lookup error occurs.
     */
    public abstract void createBeanApp01() throws Exception;

    /**
     * Creates a bean that throws always a application exception that extends
     * RuntimeException and has a the rollback element as true.
     * @throws Exception if a lookup error occurs.
     */
    public abstract void createBeanApp02() throws Exception;

    /**
     * Creates a bean that throws always an application exception that extends
     * Exception and has a the rollback element as true.
     * @throws Exception if a lookup error occurs.
     */
    public abstract void createBeanRollback() throws Exception;

    /**
     * Creates a bean that throws always a runtime exception.
     * @throws Exception if a lookup error occurs.
     */
    public abstract void createBeanRuntime() throws Exception;

    /**
     * Gets a new instance of the bean used in the application exception (with
     * rollback=false) tests.
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBeanApp(final Class beanClass) throws Exception {
        sfsbContainerTransactionApp = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Gets a new instance of the bean used in the application exception that
     * extends a runtime exception (with rollback=false) tests.
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBeanApp01(final Class beanClass) throws Exception {
        sfsbContainerTransactionApp01 = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Gets a new instance of the bean used in the application exception that
     * extends a runtime exception (with rollback=true) tests.
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBeanApp02(final Class beanClass) throws Exception {
        sfsbContainerTransactionApp02 = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Gets a new instance of the bean used in the application exception (with
     * rollback=true) tests.
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBeanRollback(final Class beanClass) throws Exception {
        sfsbContainerTransactionRollback = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Gets a new instance of the bean used in the runtime exception tests.
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBeanRuntime(final Class beanClass) throws Exception {
        sfsbContainerTransactionRuntime = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends Exception (with rollback = true) and marks the transaction for
     * rollback if there is transaction context.
     * @throws Exception if an error during the tests occurs.
     */
    public void testNotUsingClientTransWithAppRollbackException() throws Exception {
        try {
            sfsbContainerTransactionRollback.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (RollbackApplicationException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends Exception(with rollback = true) and marks the transaction for
     * rollback in some cases.This test uses a client transaction.
     * @param canCommit true if the container does not need to mark the
     *        transaction as rollback(transaction attribute: NotSupported,
     *        Never). False if the container must mark the transaction for
     *        rollback(transaction attribute:Required, Mandatory and Supports).
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTransWithAppRollbackException(final boolean canCommit) throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();
        try {
            sfsbContainerTransactionRollback.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (RollbackApplicationException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // tries to commit
        try {
            utx.commit();
            if (!canCommit) {
                fail("The transaction was marked as rolled back, the client cannot make the commit.");
            }
        } catch (Exception e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends Exception(with rollback = false) and does not do the rollback in
     * this case.This test uses a client transaction.
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTransWithAppException() throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();
        try {
            sfsbContainerTransactionApp.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (AppException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // tries to commit
        try {
            utx.commit();
        } catch (Exception e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends Exception(with rollback = false) and does not do the rollback in
     * this case.
     * @throws Exception if an error during the tests occurs.
     */
    public void testNotUsingClientTransWithAppException() throws Exception {
        try {
            sfsbContainerTransactionApp.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (AppException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends a runtime exception (with rollback = true) and marks the
     * transaction for rollback in this case.
     * @throws Exception if an error during the tests occurs.
     */
    public void testNotUsingClientTransWithAppRuntimeRollbackException() throws Exception {
        try {
            sfsbContainerTransactionApp02.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (RollbackAppRuntimeException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends a runtime exception (with rollback = true) and marks the
     * transaction for rollback in this case. This test uses a client
     * transaction.
     * @param canCommit says if the bean is able to commit the client
     *        transaction. The client is able to commit when the bean does not
     *        use the client transaction.
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTransWithAppRuntimeRollbackException(final boolean canCommit) throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();
        try {
            sfsbContainerTransactionApp02.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (RollbackAppRuntimeException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // tries to commit
        try {
            utx.commit();
            if (!canCommit) {
                fail("The transaction was marked as rolled back, the client cannot make the commit.");
            }
        } catch (Exception e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends a runtime exception (with rollback = false) and does not do the
     * rollback in this case.
     * @throws Exception if an error during the tests occurs.
     */
    public void testNotUsingClientTransWithAppRuntimeException() throws Exception {
        try {
            sfsbContainerTransactionApp01.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (AppRuntimeException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container re-throws the application exception that
     * extends a runtime exception (with rollback = false) and does not do the
     * rollback in this case. The test uses a client transaction.
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTransWithAppRuntimeException() throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();
        try {
            sfsbContainerTransactionApp01.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not re-throw the application exception.");
        } catch (AppRuntimeException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // tries to commit
        try {
            utx.commit();
        } catch (Exception e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
    }

    /**
     * Verifies if the container throws the EJBException when the bean throws a
     * RuntimeException, discards the bean and does the rollback in this case.
     * @throws Exception if an error during the tests occurs.
     */
    public void testNotUsingClientTransWithRuntimeException() throws Exception {
        // verifies if the container threw the correct exception
        try {
            sfsbContainerTransactionRuntime.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
            fail("The container did not throw the EJBException.");
        } catch (EJBException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }

        // verifies if the bean was discarded
        assertTrue(ExceptionHandleUtil.isDiscarded(sfsbContainerTransactionRuntime),
                "There was a runtime exception and the container did not discarded the bean.");

        // TODO - verifies if the container log the error.

        // verifies if the table was created in the DATABASE_1
        ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);

    }

    /**
     * Verifies if the container throws the correct exception (EJBException for
     * RequiresNew and NotSupports, and EJBTransactionRolledbackException for
     * Require, Supports and Mandatory) when the bean throws a RuntimeException,
     * discards the bean and does the rollback if the bean uses the client transaction.
     * @param expectedException EJBException for
     * RequiresNew and NotSupports, and EJBTransactionRolledbackException for
     * Require, Supports and Mandatory
     * @param canCommit true if the bean does not use the client transaction.
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTransWithRuntimeException(final Class expectedException, final boolean canCommit)
            throws Exception {
        UserTransaction utx = ExceptionHandleUtil.getUserTransaction();
        utx.begin();
        // verifies if the exception thrown is correct
        try {
            sfsbContainerTransactionRuntime.insertCorrectFirstErrorSecond(DATABASE_1, DATABASE_2);
        } catch (Exception e) {
            assertTrue(ExceptionHelper.isEquals(e, expectedException),
                    "The container did not throw the correct exception, the expected exception is "
                            + expectedException.getName() + ", but the container threw " + e.getClass().getName());
        }
        // tries to commit
        try {
            utx.commit();
            if (!canCommit) {
                fail("The transaction was marked as rolled back, the client cannot make the commit.");
            }
        } catch (Exception e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the bean was discarded
        assertTrue(ExceptionHandleUtil.isDiscarded(sfsbContainerTransactionRuntime),
                "There was a runtime exception and the container did not discarded the bean.");
        // verifies if the transaction was rolled back
        if (!canCommit) {
            try {
                // verifies if the table was created in the DATABASE_1
                ExceptionHandleUtil.verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
                fail("There was an error during the commit and the transaction was not rolled back.");
            } catch (SQLException e) {
                logger.debug("The bean threw an expected error during the execution {0}", e);
            }
        }

        // TODO - verifies if the container log the error.

    }

    /**
     * Deletes the tables created during test.
     * @throws Exception if a lookup error occurs.
     */
    public void deleteTable() throws Exception {
        // deletes the table after each test to avoid errors.
        ExceptionHandleUtil.deleteTable(DATABASE_1, ItfContainerTransaction.TABLE);
        ExceptionHandleUtil.deleteTable(DATABASE_2, ItfContainerTransaction.TABLE);
    }

}
