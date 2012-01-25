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
 * $Id: ContainerTransaction.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.db.TableManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.SLSBTransactionMisc00;
import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.DBHelper;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;

/**
 * Has all methods needed to do the tests with container-managed transaction.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class ContainerTransaction implements ItfContainerTransaction {

    /**
     * The bean session context.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Inserts the table test the database.
     * @param dbName is the name of the database in the registry.
     * @throws Exception if an error occurs.
     */
    protected void insertTable(final String dbName) throws Exception {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
    }

    /**
     * Inserts the table test in both database.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error occurs.
     */
    public void insertCorrectTableInBothDB(final String db1, final String db2) throws Exception {
        insertTable(db1);
        insertTable(db2);

    }

    /**
     * Makes an incorrect query in the database to force an exception.
     * @param db1 the database name in the registry.
     * @throws Exception if an error occurs.
     */
    private void makeSQLError(final String db1) throws Exception {
        Connection connection = null;
        DataSource ds = DBHelper.getDataSource(db1);
        try {
            // gets a connection
            connection = ds.getConnection();
            PreparedStatement stmUpdate = null;
            // connect and insert a query with error
            try {
                stmUpdate = connection.prepareStatement("CREATE TABLE error(");
                stmUpdate.executeUpdate();
            } finally {
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Inserts the table test in the first database and makes an incorrect query
     * in the second database.
     * @param db1 the name of the first database.
     * @param db2 the name of the second databse.
     * @throws Exception if an error occurs.
     */
    public void insertCorrectFirstErrorSecond(final String db1, final String db2) throws Exception {
        // inserts a correct table
        insertTable(db1);
        // makes an incorrect sql query
        makeSQLError(db2);

    }

    /**
     * Calls the method SessionContext.setRollbackOnly().
     * @param dbName1 the first database where the table should be inserted.
     * @param dbName2 the second database where the table should be inserted.
     * @throws TransactionException if an IllegalStateException occurs.
     * @throws Exception if an error occurs.
     */
    public void setRollbackOnly(final String dbName1, final String dbName2) throws TransactionException, Exception {
        insertTable(dbName1);
        insertTable(dbName2);
        try {
            ctx.setRollbackOnly();
        } catch (IllegalStateException e) {
            throw new TransactionException("There was an exception in the getRollbackOnly", e);
        }
    }

    /**
     * Calls the method SessionContext.getRollbackOnly().
     * @return true if the rollback only is set, false otherwise.
     * @throws TransactionException if rollbackonly fails
     */
    public boolean getRollbackOnly() throws TransactionException {
        boolean bolResult;
        try {
            bolResult = ctx.getRollbackOnly();
        } catch (IllegalStateException e) {
            throw new TransactionException("There was an exception in the getRollbackOnly", e);
        }
        return bolResult;
    }

    /**
     * Inserts a table in the first database,calls an auxiliary bean to create a
     * table in the second database and makes an exception to force a rollback
     * if the transaction attribute supports. The auxiliary bean uses the
     * transaction attribute REQUIRED.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error during the execution occurs.
     */
    public void insertTablesUsingAuxBeanReq(final String db1, final String db2) throws Exception {
        // inserts a correct table in the first database
        insertTable(db1);
        // creates the auxiliary bean
        ItfTransactionMisc00 slsbTransactionMisc00 = EJBHelper.getBeanRemoteInstance(SLSBTransactionMisc00.class,
                ItfTransactionMisc00.class);
        // calls insert table for the auxiliary bean in the second database
        slsbTransactionMisc00.insertTableWithRequired(db2);
        // makes an illegal insertion to force a rollback.
        makeSQLError(db2);

    }

    /**
     * Inserts a table in the first database,calls an auxiliary bean to create a
     * table in the second database and makes an exception to force a rollback
     * if the transaction attribute supports. The auxiliary bean uses the
     * transaction attribute NOT_SUPPORTED.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error during the execution occurs.
     */
    public void insertTablesUsingAuxBeanNotSup(final String db1, final String db2) throws Exception {
        // inserts a correct table in the first database
        insertTable(db1);
        // creates the auxiliary bean
        ItfTransactionMisc00 slsbTransactionMisc00 = EJBHelper.getBeanRemoteInstance(SLSBTransactionMisc00.class,
                ItfTransactionMisc00.class);
        // calls insert table for the auxiliary bean in the second database
        slsbTransactionMisc00.insertTableWithNotSupported(db2);
        // makes an illegal insertion to force a rollback.
        makeSQLError(db2);

    }

    /**
     * Makes a lookup in the registry to get an UserTransaction.An exception
     * must be thrown because the bean with container-managed transaction cannot
     * call a bean-managed transaction.
     * @throws Exception if an error occurs.
     */
    public void getUserTransactionWithLookup() throws Exception {
        try {
            UserTransaction utx = TransactionHelper.getUserTransaction();
            utx.begin();
            utx.commit();
        } catch (Exception e) {
            throw new TransactionException("The bean cannot get the user transaction with the JNDI", e);
        }
    }

    /**
     * Call the method getUserTransaction() in the EJBContext to get an
     * UserTransaction.An exception must be thrown because the bean with
     * container-managed transaction cannot call a bean-managed transaction.
     * @throws Exception if an error occurs.
     */
    public void getUserTransactionWithEJBContext() throws Exception {
        try {
            UserTransaction utx = ctx.getUserTransaction();
            utx.begin();
            utx.commit();
        } catch (Exception e) {
            throw new TransactionException("The bean cannot get the user transaction with the EJBContext", e);
        }
    }

}
