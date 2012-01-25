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
 * $Id: TestTransactionStatus.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.beanmanaged;

import static org.testng.Assert.assertEquals;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.transaction.Status;

import org.ow2.easybeans.tests.common.asserts.Assert;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction.ItfBeanManagedTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction.SFSBBeanManagedTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.EmbeddedHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages correctly the user transaction for stateful
 * bean.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.SFSBBeanManagedTransaction
 *              must be deployed.
 * @setup gets the reference of SFSBBeanManagedTransaction and binds the
 *        databases specified in the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestTransactionStatus {

    /**
     * Bean used during the tests.
     */
    private ItfBeanManagedTransaction sfsbBeanManagedTransaction;

    /**
     * Bean used to manage the database in the server side.
     */
    private ItfDatabaseManager slsbDatabaseManager;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestTransactionStatus.class);

    /**
     * The database used during the tests.
     */
    private static final String DATABASE = "jdbc_1";

    /**
     * Creates the bean used during the tests.
     * @throws Exception if an error during the test startup occurs.
     */
    @BeforeClass
    public void setup() throws Exception {
        // Inserts all database before execute the test
        // used because the container does not provide this feature yet
        // Is defined in each test to allows run each test separately.
        EmbeddedHelper.bindDatasource();
        // creates the bean used to manages the databse in the server site.
        slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);

    }

    /**
     * Verifies if the status is no transaction before the bean uses the
     * transaction.
     * @input -
     * @output the transaction status is no transaction.
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testStatusBeforeBegin() throws Exception {
        int intTransactionStatus = sfsbBeanManagedTransaction.getTransactionStatus();
        assertEquals(Status.STATUS_NO_TRANSACTION, intTransactionStatus);

    }

    /**
     * Verifies if the status changes after a begin.
     * @input -
     * @output the transaction status that must be active.
     * @throws Exception if an error during the tests occurs.
     */
    @Test(dependsOnMethods = "testStatusBeforeBegin")
    public void testStatusAfterBegin() throws Exception {
        // starts the transaction without close
        sfsbBeanManagedTransaction.insertTableWithoutCommitTransaction();
        // verifies if the transaction is openned
        int intTransactionStatus = sfsbBeanManagedTransaction.getTransactionStatus();
        assertEquals(Status.STATUS_ACTIVE, intTransactionStatus);
    }

    /**
     * Verifies if the status is commited after makes a commit.
     * @input -
     * @output the table inserted and commited.
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testStatusAfterCommit() throws Exception {
        // starts and commit the transaction
        sfsbBeanManagedTransaction.insertTableWithBeginCommitTransaction();
        // verifies if the transaction is committed
        int intTransactionStatus = sfsbBeanManagedTransaction.getTransactionStatus();
        Integer[] expected = {new Integer(Status.STATUS_COMMITTED), new Integer(Status.STATUS_NO_TRANSACTION)};
        Assert.assertEquals(new Integer(intTransactionStatus), expected,
                "The transaction status must be commited or no_transaction");

    }

    /**
     * Verifies if the status is marked_rollback after makes a rollback.
     * @input -
     * @output the table inserted and rolled back.
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testStatusAfterRollback() throws Exception {
        // starts and rollback the transaction
        sfsbBeanManagedTransaction.insertTableWithBeginRollback();
        // verifies if the transaction is committed
        int intTransactionStatus = sfsbBeanManagedTransaction.getTransactionStatus();
        Integer[] expected = {new Integer(Status.STATUS_ROLLEDBACK), new Integer(Status.STATUS_NO_TRANSACTION)};
        Assert.assertEquals(new Integer(intTransactionStatus), expected,
                "The transaction status must be rolledback or no_transaction");

    }

    /**
     * Deletes the table to avoid errors in each test.
     */
    @BeforeMethod
    public void deletesTable() {
        // deletes the table after each test to avoid errors.
        try {
            slsbDatabaseManager.deleteTable(DATABASE, ItfBeanManagedTransaction.TABLE);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }

    /**
     * Creates a new bean instance before each method.
     * @throws Exception if an error during the bean creation occurs.
     */
    @BeforeMethod
    public void createBean() throws Exception {
        // creates the bean
        sfsbBeanManagedTransaction = EJBHelper.getBeanRemoteInstance(SFSBBeanManagedTransaction.class,
                ItfBeanManagedTransaction.class);
        sfsbBeanManagedTransaction.startup(ItfBeanManagedTransaction.CREATE_TABLE, DATABASE);
    }

}
