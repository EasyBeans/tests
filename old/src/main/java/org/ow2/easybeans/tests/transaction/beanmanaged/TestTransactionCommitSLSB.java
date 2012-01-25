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
 * $Id: TestTransactionCommitSLSB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.beanmanaged;

import static org.ow2.easybeans.tests.common.helper.ExceptionHelper.checkCause;
import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.transaction.ItfBeanManagedTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.transaction.SLSBBeanManagedTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.EmbeddedHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages correctly the beand-managed transaction for
 * stateless bean.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.SLSBBeanManagedTransaction
 *              must be deployed.
 * @setup gets the reference of SLSBBeanManagedTransaction and binds the
 *        databases specified in the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestTransactionCommitSLSB {

    /**
     * Bean used during the tests.
     */
    private ItfBeanManagedTransaction slsbBeanManagedTransaction;

    /**
     * Bean used to manage the database in the server side.
     */
    private ItfDatabaseManager slsbDatabaseManager;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestTransactionCommitSLSB.class);

    /**
     * The database used during the tests.
     */
    private static final String DATABASE = "jdbc_1";

    /**
     * Publishes the databases.
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
     * Tests if the container supports the bean does not close the transaction
     * in the same method that the transaction was opened. The container must
     * to:
     * <ul>
     * <li>Makes a log to alert the system administrator.</li>
     * <li>Rollbacks the transaction.</li>
     * <li>Discard the session bean instance</li>
     * <li>Throws the javax.ejb.EJBException if using EJB3 client</li>
     * </ul>
     * @input -
     * @output all actions listed above.
     * @throws Exception if an error during the test occurs.
     */
    @Test
    public void testBeginWithoutCommit() throws Exception {
        try {
            // inserts a table without making the commit.
            slsbBeanManagedTransaction.insertTableWithoutCommit(ItfBeanManagedTransaction.CREATE_TABLE, DATABASE);
            fail("The container must to throw the javax.ejb.EJBException, if the stateless bean does not make the commit. "
                    + "However the container did not throw any exception.");
        } catch (Exception e) {
            if (!(e instanceof javax.ejb.EJBException)) {
                fail("The container must to throw the javax.ejb.EJBException, if the stateless bean does not makes the commit. "
                        + "However the container did not throw the correct exception.");
            }
        }
        // verifies if the container makes the roll back
        try {
            slsbDatabaseManager.verifyTable(DATABASE, ItfBeanManagedTransaction.TABLE);
            fail("The container must to make the rollback, if the stateless bean does not make the commit.");
        } catch (SQLException e) {
            logger.debug("the container made the rollback {0}", e);
        }
        //TODO: verifies if the container discard the bean

        // test if the application log the error
        //TODO: verify how easybeans makes the log
    }

    /**
     * Calls userTransaction.begin(), inserts the table and calls
     * userTransaction.commit().
     * @input -
     * @output the table created.
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testTransInSameMethod() throws Exception {
        slsbBeanManagedTransaction.insertTableWithBeginCommit(ItfBeanManagedTransaction.CREATE_TABLE, DATABASE);
        // verifies if the table was created
        slsbDatabaseManager.verifyTable(DATABASE, ItfBeanManagedTransaction.TABLE);
    }

    /**
     * Tests if the container does not allow the bean to use the
     * setRollbackOnly.
     * @input -
     * @output an IllegalStateException
     * @throws Exception if an error during the tests occurs.
     */
    @Test
    public void testSetRollbackOnly() throws Exception {
        try {
            // calls the setRollbackOnly that must to throw exception
            slsbBeanManagedTransaction.setRollbackOnly();
        } catch (Exception e) {
            checkCause(e, IllegalStateException.class);
        }
    }

    /**
     * Tests if the container does not allow the bean to use the
     * getRollbackOnly.
     * @input -
     * @output an IllegalStateException wrapped in EJBException
     */
    @Test
    public void testGetRollbackOnly() {
        try {
            // calls the setRollbackOnly that must to throw exception
            slsbBeanManagedTransaction.getRollbackOnly();
        } catch (EJBException e) {
            checkCause(e, IllegalStateException.class);
        } catch (NamingException e) {
            fail("Shouldn't throw Naming Exception" + e.getMessage());
        }
    }

    /**
     * Deletes the databases entries from the registry.
     * @throws Exception if an error occurs during the unbind.
     */
    @AfterClass
    public void tierDown() throws Exception {
        // Remove all database after execute the test
        // used because the container does not provide this feature yet.
        EmbeddedHelper.unbindDatasource();
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
     * Creates the bean before each test, because there are test that discard
     * the bean.
     * @throws Exception if an error during the bea lookup occurs.
     */
    @BeforeMethod
    public void createBean() throws Exception {
        // creates the bean
        slsbBeanManagedTransaction = EJBHelper.getBeanRemoteInstance(SLSBBeanManagedTransaction.class,
                ItfBeanManagedTransaction.class);
    }

}
