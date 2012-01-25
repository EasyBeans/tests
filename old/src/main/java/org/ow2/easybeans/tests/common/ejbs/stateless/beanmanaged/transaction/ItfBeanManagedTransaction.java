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
package org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.transaction;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.ow2.easybeans.tests.common.exception.TransactionException;

/**
 * Inserts table test in the database using bean-managed transaction. This class
 * uses differents combination of begin/commit.
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
     * Inserts the table and makes an UserTransaction.begin() before create the
     * table.
     * @param callOnlyTransaction says if the table must be created or only the
     *        UserTransaction.begin() and UserTransaction.commit() must be
     *        called.
     * @param dbName database where the table must be inserted.
     * @throws SQLException if a database error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     */
    void insertTableWithoutCommit(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException;

    /**
     * Inserts the table and makes an UserTransaction.begin() before create the
     * table and an UserTransaction.commit() after create.
     * @param callOnlyTransaction says if the table must be created or only the
     *        UserTransaction.begin() and UserTransaction.commit() must be
     *        called.
     * @param dbName database where the table must be inserted.
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
     * @throws TransactionException if the transaction must to make a rollback.
     */
    void insertTableWithBeginCommit(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException, HeuristicRollbackException, RollbackException,
            HeuristicMixedException, TransactionException;

    /**
     * Deletes the table and makes an UserTransaction.commit() after create.
     * @param callOnlyTransaction says if the table must be created or only the
     *        UserTransaction.begin() and UserTransaction.commit() must be
     *        called.
     * @param dbName database where the table must be inserted.
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
    void deleteTableWithoutBegin(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException, HeuristicRollbackException, RollbackException,
            HeuristicMixedException;

    /**
     * Deletes the table and makes an UserTransaction.begin() before create the
     * table and an UserTransaction.commit() after create.
     * @param callOnlyTransaction says if the table must be created or only the
     *        UserTransaction.begin() and UserTransaction.commit() must be
     *        called.
     * @param dbName database where the table must be inserted.
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
     * @throws TransactionException if the transaction must to make a rollback.
     */
    void deleteTableWithBeginCommit(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException, HeuristicRollbackException, RollbackException,
            HeuristicMixedException, TransactionException;

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
    public void getRollbackOnly() throws NamingException, IllegalStateException;

}
