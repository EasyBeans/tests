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
 * $Id: SFSBBeanManagedTransaction.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.db.TableManager;
import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;

/**
 * Inserts the table test in the database. This class uses usertransactions for
 * making the operations.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful(name="SFSBBeanManagedTransaction")
@Remote(ItfBeanManagedTransaction.class)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class SFSBBeanManagedTransaction implements ItfBeanManagedTransaction {

    /**
     * The bean sessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Transaction used during the database operations.
     */
    private UserTransaction utx = null;

    /**
     * Class used to create the tables.
     */
    private TableManager tableManager = null;

    /**
     * Says if the table must be created. Used to tests if the container accepts
     * the transaction order.
     */
    private boolean bolOnlyCreateTrans = false;

    /**
     * Deletes the table called test. The bean transaction is started and
     * commited in this method.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws TransactionException if a rollback was executed.
     */
    public void dropTableWithBeginCommitTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException {
        utx.begin();
        try {
            if (!bolOnlyCreateTrans) {
                tableManager.deleteTable(TABLE);
            }
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw new TransactionException("Error during commit.", e);
        }

    }

    /**
     * Deletes the table called test. The transaction is not open, but it is
     * commited in this method.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws TransactionException if a rollback was executed.
     */
    public void dropTableWithoutBeginTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException {
        try {
            if (!bolOnlyCreateTrans) {
                tableManager.deleteTable(TABLE);
            }
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw new TransactionException("Error during commit.", e);
        }
    }

    /**
     * Inserts the table called test. The transaction is open and commited in
     * this method.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws TransactionException if a rollback was executed.
     */
    public void insertTableWithBeginCommitTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException {

        utx.begin();
        try {
            if (!bolOnlyCreateTrans) {
                tableManager.insertTable(TABLE);
            }
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw new TransactionException("Error during commit.", e);
        }
    }

    /**
     * Inserts the table called test. The transaction will be open, but it will not
     * be commited in this method.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     */
    public void insertTableWithoutCommitTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException {
        utx.begin();
        if (!bolOnlyCreateTrans) {
           tableManager.insertTable(TABLE);
        }
    }

    /**
     * Creates the table called test.The bean transaction is started and
     * commited in this method and, also, there is a nested transaction.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws TransactionException if a rollback was executed.
     */
    public void insertTableWithNestedTrans() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException {
        // does a begin in the bean transaction
        utx.begin();
        // creates a new transaction
        UserTransaction utxNested = TransactionHelper.getUserTransaction();

        // does a begin in other transaction
        utxNested.begin();
        // creates the table
        if (!bolOnlyCreateTrans) {
            tableManager.insertTable(TABLE);
        }
        // does the commit in the nested transaction
        utxNested.commit();
        // does the commit in the bean transaction
        utx.commit();

    }

    /**
     * Creates the table called test.A new transaction is created and it makes
     * the begin and commit transaction. The bean transaction is not started or
     * commited in this method.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws TransactionException if a rollback was executed.
     */
    public void insertTableWithNewTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException {
        // creates a new transaction
        UserTransaction utxNested = TransactionHelper.getUserTransaction();

        // does a begin in other transaction
        utxNested.begin();
        try {
            // creates the table
            if (!bolOnlyCreateTrans) {
                tableManager.insertTable(TABLE);
            }
            // does the commit in the nested transaction
            // does the commit in the nested transaction
            utxNested.commit();
        } catch (Exception e) {
            utxNested.rollback();
            if (this.getTransactionStatus() != Status.STATUS_NO_TRANSACTION) {
                utx.rollback();
            }
            throw new TransactionException("Error during commit.", e);
        }
    }

    /**
     * Inserts the table called test. The transaction is openned and after
     * insert the table, an rollback is called.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     */
    public void insertTableWithBeginRollback() throws SQLException, NamingException, SystemException,
            NotSupportedException {
        utx.begin();
        if (!bolOnlyCreateTrans) {
            tableManager.insertTable(TABLE);
        }
        utx.rollback();
    }

    /**
     * Makes a rollback in the transaction.
     * @throws IllegalStateException if a transaction is not associated with a transaction.
     * @throws SecurityException if the transaction is not allowed to make a rollback.
     * @throws SystemException if an unexpected error occurs.
     */
    public void setRollback() throws IllegalStateException, SecurityException, SystemException{
        utx.rollback();
    }

    /**
     * Gets the transaction.
     * @param callOnlyTransaction if only the transactions must be called.
     * @param dbName the database where the table is inserted.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    public void startup(final boolean callOnlyTransaction, final String dbName) throws NamingException, SQLException {
        utx = TransactionHelper.getUserTransaction();
        bolOnlyCreateTrans = callOnlyTransaction;
        tableManager = new TableManager(dbName);

    }

    /**
     * Obtains the bean transaction status.
     * @return the bean transaction status.
     * @throws SystemException if an unexpected error occurs.
     */
    public int getTransactionStatus() throws SystemException {
        return utx.getStatus();
    }

    /**
     * Makes a setRollbackOnly that must to throw en exception.
     * @throws NamingException if a lookup error occurs.
     * @throws IllegalStateException if the bean try to call the setRollBackOnly
     */
    public void setRollbackOnly() throws NamingException, IllegalStateException{
        sessionContext.setRollbackOnly();
    }

    /**
     * Makes a getRollbackOnly that must to throw en exception.
     * @throws NamingException if a lookup error occurs.
     * @throws IllegalStateException if the bean try to call the getRollBackOnly
     */
    public void getRollbackOnly() throws NamingException,  IllegalStateException{
        sessionContext.getRollbackOnly();
     }
}
