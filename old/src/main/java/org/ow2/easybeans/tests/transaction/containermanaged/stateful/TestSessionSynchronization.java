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
 * $Id: TestSessionSynchronization.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.stateful;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.ItfSessionSync;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBSessionSync;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.transaction.containermanaged.base.TestContainerTransactionBase;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container-managed transaction in the stateful bean is
 * following the JSR 220.The test verifies if the SessionSynchronization Callbacks are working properly.
 * The items covered in this test are: 12.6.2.11.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.SFSBSessionSync
 *              must be deployed.
 * @setup gets the reference of SFSBSessionSync and binds the
 *        databases specified in the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestSessionSynchronization extends TestContainerTransactionBase {

    /**
     * Bean used during the tests.
     */
    private ItfSessionSync sfsbSessionSync;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestSessionSynchronization.class);

    /**
     * Creates the bean before each test to avoid errors during the tests.
     * @throws Exception if an error during the bean creation occurs.
     */
    @BeforeMethod
    public void createBean() throws Exception {
        sfsbSessionSync = EJBHelper.getBeanRemoteInstance(SFSBSessionSync.class, ItfSessionSync.class);
        sfsbSessionSync.startup(DATABASE_1, DATABASE_2);
    }

    /**
     * Binds the databases used.
     * @throws Exception if an error during the test startup occurs.
     */
    @BeforeClass
    @Override
    public void setup() throws Exception {
        super.setup();
    }

    /**
     * Verifies if the callback methods were called correctly. The afterbegin,
     * before completion must stay in the same transaction that the method
     * called. The beforeCompletation calls a setRollbackOnly, so the table
     * created in the afterBegin must to be deleted and the afterCompletation
     * must receive that the transaction was not commited.
     * @throws Exception if an unexpected error occurs during the test.
     */
    private void verifyCallbacks() throws Exception {
        // verifies if the table was created in the DATABASE_1
        // the afterBegin method inserts the table in this database.
        try {
            verifyTable(DATABASE_1, ItfSessionSync.TABLE);
            fail("The beforeCompletion method was not called "
                    + " or the afterBegin method and the method called were not in the same transaction.");
        } catch (SQLException e) {
            logger.debug("The method threw an expected exception {0}", e);
        }

        // verifies if the table was created in the DATABASE_2
        // the insertTableRequired method inserts the table in this database.
        try {
            verifyTable(DATABASE_2, ItfSessionSync.TABLE);
            fail("The beforeCompletion method was not called.");
        } catch (SQLException e) {
            logger.debug("The method threw an expected exception {0}", e);
        }
        // the after completion method inserts the value in this variable,
        // so it tests if the afterCompletion is called.
        assertTrue(sfsbSessionSync.isRolledback());
    }

    /**
     * Verifies if the table created by the afterBegin method is created when a
     * method that does not support transaction is called.Also, this method
     * verifies if the table in the method was create properly.
     * @throws Exception if an unexpected error occurs during the test.
     */
    private void verifyTablesWithouCallbacks() throws Exception {
        // verifies if the table was created in the DATABASE_1
        // the afterTransaction method inserts the table in this database.
        try {
            verifyTable(DATABASE_1, ItfSessionSync.TABLE);
            fail("The method afterbegin was called, but it is not correct in this case.");
        } catch (SQLException e) {
            logger.debug("The method threw an expected exception {0}", e);
        }
        // verifies if the table was created in the DATABASE_?
        // the method CALLED inserts the table in this database.
        try {
            verifyTable(DATABASE_2, ItfSessionSync.TABLE);
            logger.debug("The table was inserted correctly.");
        } catch (SQLException e) {
            fail("The container rolled back the transaction, but there is not transaction associated.");
        }
    }

    /**
     * Calls the method that uses the required attribute.The bean implements the
     * SessionSynchronization interface, so the methods afterBegin,
     * beforeCompletion and afterCompletion must be called. The method
     * beforeCompletion calls setRollbackOnly, so the transaction must be rolled
     * back.
     * @input -
     * @output the tables not created because the transaction rolled back.
     * @throws Exception if an error occurs during the tests.
     */
    @Test(dependsOnMethods = {"testSessionSyncWithUserTransAndSupports"})
    public void testSessionSyncWithRequired() throws Exception {
        sfsbSessionSync.insertTableRequired();
        verifyCallbacks();
    }

    /**
     * Calls the method that uses the requires_new attribute.The bean implements the
     * SessionSynchronization interface, so the methods afterBegin,
     * beforeCompletion and afterCompletion must be called. The method
     * beforeCompletion calls setRollbackOnly, so the transaction must be
     * rolled back.
     * @input -
     * @output the tables not created because the transaction rolled back.
     * @throws Exception if an error occurs during the tests.
     */
    @Test
    public void testSessionSyncWithRequiresNew() throws Exception {
        sfsbSessionSync.insertTableRequiredNew();
        verifyCallbacks();
    }

    /**
     * Calls the method that uses the mandatory attribute.The bean implements the
     * SessionSynchronization interface, so the methods afterBegin,
     * beforeCompletion and afterCompletion must be called. The method
     * beforeCompletion calls setRollbackOnly, so the transaction must be
     * rolled back.
     * @input -
     * @output the tables not created because the transaction rolled back.
     * @throws Exception if an error occurs during the tests.
     */
    @Test
    public void testSessionSyncWithMandatory() throws Exception {
        UserTransaction utx = getUserTransaction();
        utx.begin();
        sfsbSessionSync.insertTableMandatory();
        try{
            utx.commit();
        }catch(Exception e){
           logger.debug("The method threw an expected exception {e}", e);
        }
        verifyCallbacks();
    }

    /**
     * Calls the method with transaction attribute supports and verifies if the
     * methods callback are called. The transaction attribute supports must to
     * have the same behaviour that the required.
     * @input -
     * @output the databases without tables, because the container made a
     *         rollback.
     * @throws Exception if an error occurs during the tests.
     */
    @Test
    public void testSessionSyncWithUserTransAndSupports() throws Exception {
        UserTransaction utx = getUserTransaction();
        utx.begin();
        sfsbSessionSync.insertTableSupports();
        try{
            utx.commit();
        }catch(Exception e){
           logger.debug("The method threw an expected exception {e}", e);
        }
        verifyCallbacks();
    }

    /**
     * Calls the method with transaction attribute supports and verifies if the
     * container did not call the callbacks methdos from SessionSynchronization
     * interface. The container must no call this methods, because the method is
     * not in a transaction context.
     * @throws Exception if an error occurs during the tests.
     */
    @Test
    public void testSessionSyncWithOnlySupports() throws Exception {
        sfsbSessionSync.insertTableSupports();
        verifyTablesWithouCallbacks();
    }

    /**
     * Calls the method with transaction attribute not_supported and verifies if the
     * container did not call the callbacks methdos from SessionSynchronization
     * interface. The container must no call this methods, because the method is
     * not in a transaction context.
     * @throws Exception if an error occurs during the tests.
     */
    @Test
    public void testSessionSyncWithNotSupported() throws Exception {
        sfsbSessionSync.insertTableNotSupported();
        verifyTablesWithouCallbacks();
    }

    /**
     * Calls the method with transaction attribute never and verifies if the
     * container did not call the callbacks methdos from SessionSynchronization
     * interface. The container must no call this methods, because the method is
     * not in a transaction context.
     * @throws Exception if an error occurs during the tests.
     */
    @Test
    public void testSessionSyncWithNever() throws Exception {
        sfsbSessionSync.insertTableNever();
        verifyTablesWithouCallbacks();
    }

    /**
     * Deletes the table before each test to avoid errors in each test.
     */
    @BeforeMethod
    @Override
    public void deleteTable() {
        deleteTable(DATABASE_1, ItfSessionSync.TABLE);
        deleteTable(DATABASE_2, ItfSessionSync.TABLE);
    }

    /**
     * Makes a rollback in the transaction actives.
     * @throws Exception if a transaction exception occurs.
     */
    @BeforeMethod
    @Override
    public void cleanTransaction() throws Exception{
        super.cleanTransaction();
    }

}
