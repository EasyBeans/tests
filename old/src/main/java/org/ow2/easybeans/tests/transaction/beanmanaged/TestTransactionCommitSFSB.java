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
 * $Id: TestTransactionCommitSFSB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.beanmanaged;

import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.asserts.Assert;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction.ItfBeanManagedTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction.SFSBBeanManagedTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.EmbeddedHelper;
import org.ow2.easybeans.tests.common.helper.ExceptionHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the bean-managed transaction in the stateful bean is following
 * the JSR 220.The items couvered in this test are: 12.2.3 and 12.3.3 spec.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.SFSBBeanManagedTransaction
 *              must be deployed.
 * @setup gets the reference of SFSBBeanManagedTransaction and binds the
 *        databases specified in the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestTransactionCommitSFSB {

    /**
     * Bean used during the tests.
     */
    private ItfBeanManagedTransaction sfsbBeanManagedTransaction;

    /**
     * Bean used to manage the database in the server side.
     */
    private ItfDatabaseManager slsbDatabaseManager;

    /**
     * The database used during the tests.
     */
    private static final String DATABASE = "jdbc_1";

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestTransactionCommitSFSB.class);

    /**
     * Creates the bean used during the tests and binds the databases used.
     * @throws Exception if an error during the test startup occurs.
     */
    @BeforeClass
    public void setup() throws Exception {
        // Inserts all database before execute the test
        // used because the container does not provide this feature yet
        EmbeddedHelper.bindDatasource();

        // creates the bean used to manages the databse in the server site.
        slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);
    }

    /**
     * Tests if the container supports the bean that does not close the
     * transaction in the same method that the transaction was opened.
     * @input -
     * @output SQLException because the insert was not commited.
     * @throws Exception if an error during the test occurs.
     */
    @Test
    public void testTransInTwoMethods() throws Exception {
        try {
            // inserts a table without making the commit.
            sfsbBeanManagedTransaction.insertTableWithoutCommitTransaction();
            // tries to delete the table in other connection.
            sfsbBeanManagedTransaction.dropTableWithoutBeginTransaction();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
    }

    /**
     * Tests if the container allows the bean open a transaction without close
     * the other one, i.e., if the container allows nested transactions. The
     * transaction is opened twice, but in differents methods.
     * @input -
     * @output a NotSupportedException
     * @throws Exception if an error during the tests occurs.
     */
    @Test(expectedExceptions = javax.transaction.NotSupportedException.class)
    public void testBeginTwiceSameTrans() throws Exception {
        try {
            // call an userTransaction.begin()
            sfsbBeanManagedTransaction.insertTableWithoutCommitTransaction();
            // call other userTransaction without finish the first one
            sfsbBeanManagedTransaction.dropTableWithBeginCommitTransaction();
        } catch (TransactionException e) {
            throw e.getParentException();
        }

    }

    /**
     * Tests if the container allows the bean open a transaction without close
     * the other one, i.e., if the container allows nested transactions. There
     * are two transactions where the second one is started before the first one
     * makes the commit. The both transaction are in the same method.
     * @input -
     * @output a NotSupportedException.
     * @throws Exception if an error durong the tests occurs.
     */
    @Test(expectedExceptions = javax.transaction.NotSupportedException.class)
    public void testBeginTwoTransSameMethod() throws Exception {
        try {
            // call an userTransaction.begin()
            sfsbBeanManagedTransaction.insertTableWithNestedTrans();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
    }

    /**
     * Tests if the container allows the bean open a transaction without close
     * the other one, i.e., if the container allows nested transactions. There
     * are two transactions where the second one is started before the first one
     * makes the commit. Each transaction is in a different method.
     * @input -
     * @output a NotSupportedException
     * @throws Exception if an error during the tests occurs.
     */
    @Test(expectedExceptions = javax.transaction.NotSupportedException.class)
    public void testBeginTwoTransDifMethod() throws Exception {
        try {
            // call an userTransaction.begin()
            sfsbBeanManagedTransaction.insertTableWithoutCommitTransaction();
            // call other userTransaction without finish the first one
            sfsbBeanManagedTransaction.insertTableWithNewTransaction();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
    }

    /**
     * If the bean has a transaction active and the client too, the client
     * transaction must be resumed when the bean is called.
     * @input -
     * @output the bean transaction will make a rollback and the client
     *         transaction must be active.
     * @throws Exception if a erro during the tests occurs.
     */
    @Test
    public void testUsingClientTransaction() throws Exception {
        // calls a method that starts a transaction
        sfsbBeanManagedTransaction.insertTableWithoutCommitTransaction();
        // gets the transaction
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        // starts the transaction
        utx.begin();
        // makes a rollback in the bean transaction, the container
        // must to resume the user transaction and execute the
        // bean transaction in other transaction
        sfsbBeanManagedTransaction.setRollback();
        // tries to commit the transaction to avoid a nested transaction
        try {
            utx.commit();
        } catch (Exception e) {
            logger.debug("Error when the transaction made a rollback {0}", e);
        }
        Integer[] expected = {new Integer(Status.STATUS_COMMITTED), new Integer(Status.STATUS_NO_TRANSACTION) };

        // the user transaction must be active yet.
        Assert.assertEquals(new Integer(utx.getStatus()), expected,
                "After the commit the transaction must be commited or not_transaction");

    }

    /**
     * Tests if the container does not allow the bean uses the setRollbackOnly.
     * @input -
     * @output an IllegalStateException
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testSetRollbackOnly() throws Exception {
        // calls the setrollbackonly that must to throw exception
        try{
            sfsbBeanManagedTransaction.setRollbackOnly();
        }catch(EJBException e){
            ExceptionHelper.checkCause(e, java.lang.IllegalStateException.class);
        }
    }
    /**
     * Tests if the container does not allow the bean uses the getRollbackOnly.
     * @input -
     * @output an IllegalStateException
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testgetRollbackOnly() throws Exception {
        // calls the setrollbackonly that must to throw exception
        try{
            sfsbBeanManagedTransaction.getRollbackOnly();
        }catch(EJBException e){
            ExceptionHelper.checkCause(e, java.lang.IllegalStateException.class);
        }
    }
    /**
     * Tests if the transaction begin and the transaction commit work well.
     * Tries to create a table and verifies if the table was created correctly.
     * @input -
     * @output method executed without exception.
     * @throws Exception if an error during the test occurs.
     */
    @Test
    public void testTransInSameMethod() throws Exception {
        try {
            sfsbBeanManagedTransaction.insertTableWithBeginCommitTransaction();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
        // verifies if the table was created
        slsbDatabaseManager.verifyTable(DATABASE, ItfBeanManagedTransaction.TABLE);

    }

    /**
     * Tests if the rollback works properly. The method calls an
     * UserTransaction.begin(), inserts a table and makes a rollback in the
     * transaction.
     * @input -
     * @output the select * from test must return an exception, because the
     *         table does not exists.
     * @throws Exception if an error during the test occurs.
     */
    @Test(expectedExceptions = SQLException.class)
    public void testRollbackInSameMethod() throws Exception {
        sfsbBeanManagedTransaction.insertTableWithBeginRollback();

        // verifies if the table was created, the expected is the table not
        // exists.
        slsbDatabaseManager.verifyTable(DATABASE, ItfBeanManagedTransaction.TABLE);

    }

    /**
     * Deletes the databases entries from the registry.
     * @throws Exception if an error occurs during the unbind.
     */
    @AfterClass
    public void tierDown() throws Exception {
        // Removes all database registry references after execute the test
        // it's used because the container does not provide this feature yet.
        // It's run after each test to allow run each test separately.
        EmbeddedHelper.unbindDatasource();
    }

    /**
     * Deletes the table to avoid errors in each test.
     */
    @BeforeMethod
    public void deletesTable() {
        // deletes the table after each test to avoid errors.
        try {
            // verifies if the table was created
            slsbDatabaseManager.deleteTable(DATABASE, ItfBeanManagedTransaction.TABLE);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }

    /**
     * Gets a new instance of the bean before each test.
     * @throws Exception if an error during the bean creation occurs.
     */
    @BeforeMethod
    public void createBean() throws Exception {
        sfsbBeanManagedTransaction = EJBHelper.getBeanRemoteInstance(SFSBBeanManagedTransaction.class,
                ItfBeanManagedTransaction.class);
        sfsbBeanManagedTransaction.startup(ItfBeanManagedTransaction.CREATE_TABLE, DATABASE);
    }
}
