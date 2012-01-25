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
 * $Id: TestContainerTransactionBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.EmbeddedHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Contains all base methods for using during the tests about the
 * container-managed.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestContainerTransactionBase implements ItfTestContainerManaged {

    /**
     * Constant that defines the first database name.
     */
    protected static final String DATABASE_1 = "jdbc_1";

    /**
     * Constant that defines the second database name.
     */
    protected static final String DATABASE_2 = "jdbc_2";

    /**
     * Bean used during the tests.
     */
    private ItfContainerTransaction sfsbContainerTransaction = null;

    /**
     * Bean used to manage the database in the server side.
     */
    private ItfDatabaseManager slsbDatabaseManager;


    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestContainerTransactionBase.class);

    /**
     * Verifies if the table was created in the database.
     * @param database the database name.
     * @param tableName the table name verified.
     * @throws NamingException if a lookup error occurs.
     * @throws SQLException if a database error occurs.
     */
    protected void verifyTable(final String database, final String tableName) throws NamingException, SQLException {
        slsbDatabaseManager.verifyTable(database, tableName);
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged#setup()
     * @throws Exception if a database error occurs.
     */
    public void setup() throws Exception {
        // Inserts all database before execute the test
        // used because the container does not provide this feature yet
        EmbeddedHelper.bindDatasource();
        // creates the bean used to manages the databse in the server site.
        slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);
        //cleans the transaction active in other test
        cleanTransaction();
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged#createBean(java.lang.Class)
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBean(final Class beanClass) throws Exception {
        sfsbContainerTransaction = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Creates a transaction, executes a method in the bean that creates a
     * database and after forces a rollback. The specification says that the
     * bean and the client, in this case, must to use the same transaction. So,
     * the rollback must to be done in the bean also.
     * @throws Exception if an error during the transaction occurs.
     */
    private void executeErrorTransaction() throws Exception {
        UserTransaction utx = getUserTransaction();
        // starts the transaction
        utx.begin();
        // call the method that must have the same transaction context
        sfsbContainerTransaction.insertCorrectTableInBothDB(DATABASE_1, DATABASE_2);
        // rollback the transaction
        utx.rollback();
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged#testSetRollbackOnly()
     * @throws Exception if an error during the tests occurs.
     */
    public void testSetRollbackOnly() throws Exception {
        // calls the setRollbackOnly
        try {
            sfsbContainerTransaction.setRollbackOnly(DATABASE_1, DATABASE_2);
        } catch (TransactionException e) {
            throw e.getParentException();
        }
        /*
         * Verifies if the tables were inserted. The transaction attributes,
         * that supports the setRollbackOnly, must to make a roll back when the
         * method invocation completes.
         */
        verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
        verifyTable(DATABASE_2, ItfContainerTransaction.TABLE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged
     * @throws Exception if an error during the tests occurs.
     */
    public void testGetRollbackOnly() throws Exception {
        try {
            sfsbContainerTransaction.getRollbackOnly();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged#testUsingClientTrans()
     * @throws Exception if an error during the tests occurs.
     */
    public void testUsingClientTrans() throws Exception {
        executeErrorTransaction();

        // verifies if the table was created in the DATABASE_1
        verifyTable(DATABASE_1, ItfContainerTransaction.TABLE);
        // verifies if the table was created in the DATABASE_2
        verifyTable(DATABASE_2, ItfContainerTransaction.TABLE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.interfaces.ItfTestContainerManaged
     */
    public void deleteTable() {
        // deletes the table after each test to avoid errors.
        deleteTable(DATABASE_1, ItfContainerTransaction.TABLE);
        deleteTable(DATABASE_2, ItfContainerTransaction.TABLE);
    }

    /**
     * Deletes the table in the database.
     * @param dbName the database name in the registry.
     * @param tableName the table name.
     */
    protected void deleteTable(final String dbName, final String tableName) {
        // deletes the table after each test to avoid errors.
        try {
            slsbDatabaseManager.deleteTable(dbName, tableName);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }

    /**
     * Returns the bean used during the tests.
     * @return the bean.
     */
    protected ItfContainerTransaction getBean() {
        return sfsbContainerTransaction;
    }


    /**
     * Verifies if the container throws an IllegalStateException when a bean with
     * container-managed transaction tries to get an UserTransaction. The bean
     * gets the UserTransaction by using a EJBContext.
     * @input -
     * @output an IllegalStateException.
     * @throws Exception if an error during the tests occurs.
     */
    public void testGetUserTransactionWithLookup() throws Exception {
        try {
            sfsbContainerTransaction.getUserTransactionWithLookup();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
    }

    /**
     * Verifies if the container throws a NameNotFoundException when a bean with
     * container-managed transaction tries to get an UserTransaction. The bean
     * gets the UserTransaction by using a lookup.
     * @input -
     * @output an IllegalStateException.
     * @throws Exception if an error during the tests occurs.
     */
    public void testGetUserTransactionWithEJBContext() throws Exception {
        try {
            sfsbContainerTransaction.getUserTransactionWithEJBContext();
        } catch (TransactionException e) {
            throw e.getParentException();
        }
    }

    /**
     * Gets a new transaction.
     * @return the class UserTransaction.
     * @throws Exception if a lookup error occurs.
     */
    protected UserTransaction getUserTransaction() throws Exception {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        logger.debug("Transaction status after get = {0} ", new Integer(utx.getStatus()));
        return utx;
    }

    /**
     * Makes a rollback in the transaction that is active.
     * @throws Exception if an error occurs during the rollback.
     */
    public void cleanTransaction() throws Exception {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try {
            if (transactionIsActive()) {
                utx.rollback();
           }
        } catch (Exception e) {
            throw new Exception("Cannot clean the transaction. The test cannot be started", e);
        }
    }

    /**
     * Verifies if the transaction in the client side is active.
     * @return true if the transaction is active, false otherwise.
     * @throws Exception id a lookup error occurs
     */
    protected boolean transactionIsActive() throws Exception {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        boolean bolResult = false;
        if (utx != null) {
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                bolResult = true;
            }
        }
        return bolResult;
    }

}
