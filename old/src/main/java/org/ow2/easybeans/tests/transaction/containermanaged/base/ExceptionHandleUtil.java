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
 * $Id: ExceptionHandleUtil.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import java.sql.SQLException;

import javax.ejb.NoSuchEJBException;
import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Has useful methods for the transaction tests.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public final class ExceptionHandleUtil {


    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(ExceptionHandleUtil.class);

    /**
     * Creates a new instance.
     *
     */
    private ExceptionHandleUtil(){

    }

    /**
     * Gets a bean instance.
     * @throws Exception if a lookup error occurs.
     * @return a new instance of the bean.
     */
    private static ItfDatabaseManager getBean() throws Exception{
        return  EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);

    }
    /**
     * Verifies if the table was created in the database.
     * @param database the database name.
     * @param tableName the table name verified.
     * @throws Exception if an error when the bean is goten occurs.
     */
    public static void verifyTable(final String database, final String tableName) throws Exception {
         getBean().verifyTable(database, tableName);
    }

    /**
     * Calls the bean method toString() to verify if the bean was discarded.
     * @param bean the bean that is verified.
     * @return true if the bean is discarded and false otherwise.
     * @throws Exception if an error during the test occurs.
     */
    public static boolean isDiscarded(final ItfContainerTransaction bean) throws Exception {
        boolean bolResult = false;
        try {
            bean.getUserTransactionWithLookup();
        } catch (NoSuchEJBException e) {
            bolResult = true;
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        return bolResult;
    }


    /**
     * Deletes the table in the database.
     * @param dbName the database name in the registry.
     * @param tableName the table name.
     * @throws Exception if a lookup error occurs.
     */
    public static  void deleteTable(final String dbName, final String tableName) throws Exception {
        // deletes the table after each test to avoid errors.
        try {
            getBean().deleteTable(dbName, tableName);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }
    /**
     * Makes a rollback in the transaction that is active.
     * @throws Exception if an error occurs during the rollback.
     */
    public static void cleanTransaction() throws Exception {
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
     * @throws SystemException if a transaction exception occurs.
     * @throws NamingException if a lookup error occurs.
     */
    public static boolean transactionIsActive() throws SystemException, NamingException {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        boolean bolResult = false;
        if (utx != null) {
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                bolResult = true;
            }
        }
        return bolResult;
    }

    /**
     * Gets a new transaction.
     * @throws NamingException if a lookup error occurs.
     * @return the class UserTransaction.
     */
    public static UserTransaction getUserTransaction() throws NamingException {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        return utx;
    }
}
