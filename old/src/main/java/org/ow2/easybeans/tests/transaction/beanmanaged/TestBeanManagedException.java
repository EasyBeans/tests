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
 * $Id: TestBeanManagedException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.beanmanaged;

import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.ejb.NoSuchEJBException;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction.ItfBeanManagedException;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction.SFSBBeanManagedException;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.exception.RollbackAppRuntimeException;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the bean-managed transaction and the exceptions are correctly
 * managed by the container.The item couvered in this test is: 14.3.1(table 15).
 * @reference JSR 220- FINAL RELEASE
 * @requirement Application Server must be running; the bean
 *              SFSBBeanManagedException and SLSBDatabaseManager. must be
 *              deployed.
 * @setup gets the reference of SFSBBeanManagedException and
 *        SLSBDatabaseManager, cleans the database.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestBeanManagedException {

    /**
     * Bean used during the tests.
     */
    private ItfBeanManagedException bean;

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
    private static Log logger = LogFactory.getLog(TestBeanManagedException.class);

    /**
     * Creates the bean used during the tests and binds the databases used.
     * @throws Exception if an error during the test startup occurs.
     */
    @BeforeMethod
    public void setup() throws Exception {
        // creates the bean used to manages the databse in the server site.
        slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);

        //deletes the table
        deleteTable();

        //creates a bean
        bean = EJBHelper.getBeanRemoteInstance(SFSBBeanManagedException.class, ItfBeanManagedException.class);
        bean.startup(DATABASE);
    }

    /**
     * Verifies if the container only throws the application exception when it
     * occurs in a bean-managed exception.
     * @throws Exception if an error occurs.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void insertTableWithAppException() throws Exception {
        try {
            bean.insertTableWithAppException();
            fail("The container did not throw the application exception");
        } catch (RollbackAppRuntimeException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
        //The transaction must not be rolled back.
        slsbDatabaseManager.verifyTable(DATABASE, ItfBeanManagedException.TABLE);
    }

    /**
     * Verifies if the container manages a runtime exception in a bean-management transaction. The container must:
     * <li>Log the exception</li>
     * <li>Mark the transaction for rollback</li>
     * <li>Discard the bean</li>
     * <li>Throw EJBEXception</li>
     * @throws Exception if an error occurs.
     */
    @Test
    public void insertTableWithRuntimeException() throws Exception {
        try {
            bean.insertTableWithRuntimeException();
            fail("The container did not throw EJBEexception");
        } catch (EJBException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
        //verifies if the instance is discarded
        try {
            bean.emptyMethod();
            fail("The instance was not discarded");
        } catch (NoSuchEJBException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }

        //TODO - verify log

        //verifies if the transaction was rolled back
        try {
            slsbDatabaseManager.verifyTable(DATABASE, ItfBeanManagedException.TABLE);
            fail("The transaction was not rolled back");
        } catch (SQLException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }

    }

    /**
     * Deletes the table used during the test.
     *
     */
    public void deleteTable() {
        // deletes the table after each test to avoid errors.
        try {
            // verifies if the table was created
            slsbDatabaseManager.deleteTable(DATABASE, ItfBeanManagedException.TABLE);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }
}
