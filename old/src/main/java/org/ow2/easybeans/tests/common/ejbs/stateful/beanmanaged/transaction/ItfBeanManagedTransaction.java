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
 * $Id: ItfBeanManagedTransaction.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.ow2.easybeans.tests.common.exception.TransactionException;

/**
 * Inserts a table called Test in the database.Creates differents combinations
 * of transaction begin/commit.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfBeanManagedTransaction {

    /**
     * Table used during the tests.
     */
    String TABLE = "BeanManaged";

    /**
     * Constants used to say that the table must not be created, only the
     * transaction is called.
     */
    boolean CALL_TRANSACTION_ONLY = true;

    /**
     * Constants used to say that the table must be created.
     */
    boolean CREATE_TABLE = false;

    /**
     * Gets the transaction.
     * @param callOnlyTransaction if only the transactions must be called.
     * @param dbName the database where the table is inserted.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    void startup(final boolean callOnlyTransaction, final String dbName) throws NamingException, SQLException;

    /**
     * Inserts the table called test. The transaction is open, but it is not
     * commited in this method.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     */
    void insertTableWithoutCommitTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException;

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
     * @throws TransactionException if a rollback was made.
     */
    void insertTableWithBeginCommitTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException;

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
     */
    void dropTableWithoutBeginTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException;

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
     */
    void dropTableWithBeginCommitTransaction() throws SQLException, NamingException, SystemException,
            NotSupportedException, HeuristicRollbackException, RollbackException, HeuristicMixedException,
            TransactionException;

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
     */
    void insertTableWithNestedTrans() throws SQLException, NamingException, SystemException, NotSupportedException,
            HeuristicRollbackException, RollbackException, HeuristicMixedException, TransactionException;

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
     */
    void insertTableWithNewTransaction() throws SQLException, NamingException, SystemException, NotSupportedException,
            HeuristicRollbackException, RollbackException, HeuristicMixedException, TransactionException;

    /**
     * Obtains the bean transaction status.
     * @return the bean transaction status.
     * @throws SystemException if an unexpected error occurs.
     */
    int getTransactionStatus() throws SystemException;

    /**
     * Inserts the table called test. The transaction is openned and after
     * insert the table, an rollback is called.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     */
    void insertTableWithBeginRollback() throws SQLException, NamingException, SystemException, NotSupportedException;

    /**
     * Makes a rollback in the transaction
     * @throws IllegalStateException if a transaction is not associated with a transaction.
     * @throws SecurityException if the transaction is not allowed to make a rollback.
     * @throws SystemException if an unexpected error occurs.
     */
    public void setRollback() throws IllegalStateException, SecurityException, SystemException;

    /**
     * Makes a setRollbackOnly that must to throw en exception.
     * @throws NamingException if a lookup error occurs.
     * @throws IllegalStateException if the bean try to call the setRollBackOnly
     */
    public void setRollbackOnly() throws NamingException, IllegalStateException;

    /**
     * Makes a getRollbackOnly that must to throw en exception.
     * @throws NamingException if a lookup error occurs.
     * @throws IllegalStateException if the bean try to call the getRollBackOnly
     */
    public void getRollbackOnly() throws NamingException,  IllegalStateException;
}
