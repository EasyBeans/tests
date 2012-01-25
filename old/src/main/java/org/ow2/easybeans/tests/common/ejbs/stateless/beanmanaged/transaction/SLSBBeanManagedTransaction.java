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
 * $Id: SLSBBeanManagedTransaction.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.transaction;

import java.sql.SQLException;

import javax.ejb.EJBContext;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.db.TableManager;
import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.EJBContextHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;

/**
 * Inserts the table test in the database using the UserTransaction. This class
 * has many methods with differents combinations of begin/commit.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless(name="SLSBBeanManagedTransaction")
@Remote(ItfBeanManagedTransaction.class)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class SLSBBeanManagedTransaction implements ItfBeanManagedTransaction {

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
     * @throws TransactionException if a rollback was made.
     */
    public void deleteTableWithBeginCommit(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException, HeuristicRollbackException, RollbackException,
            HeuristicMixedException, TransactionException {
        UserTransaction utx = TransactionHelper.getUserTransaction();
        utx.begin();
        try {
            if (!callOnlyTransaction) {
                TableManager tableManager = new TableManager(dbName);
                tableManager.deleteTable(TABLE);
            }
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw new TransactionException("Error during commit.", e);
        }

    }

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
    public void deleteTableWithoutBegin(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException, HeuristicRollbackException, RollbackException,
            HeuristicMixedException {
        UserTransaction utx = TransactionHelper.getUserTransaction();
        if (!callOnlyTransaction) {
            TableManager tableManager = new TableManager(dbName);
            tableManager.deleteTable(TABLE);
        }
        utx.commit();

    }

    /**
     * Calls userTransaction.begin(), inserts the table and calls userTransaction.commit().
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
     * @throws TransactionException if a rollback was made.
     */
    public void insertTableWithBeginCommit(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException, HeuristicRollbackException, RollbackException,
            HeuristicMixedException, TransactionException {
        UserTransaction utx = TransactionHelper.getUserTransaction();
        utx.begin();
        try {
            if (!callOnlyTransaction) {
                TableManager tableManager = new TableManager(dbName);
                tableManager.insertTable(TABLE);
            }
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw new TransactionException("Error during commit.", e);
        }

    }

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
    public void insertTableWithoutCommit(final boolean callOnlyTransaction, final String dbName) throws SQLException,
            NamingException, SystemException, NotSupportedException {
        UserTransaction utx = TransactionHelper.getUserTransaction();
        utx.begin();
        if (!callOnlyTransaction) {
            TableManager tableManager = new TableManager(dbName);
            tableManager.insertTable(TABLE);
        }
    }

    /**
     * Makes a setRollbackOnly that must to throw en exception.
     * @throws NamingException if a lookup error occurs.
     * @throws IllegalStateException if the bean try to call the setRollBackOnly
     */
    public void setRollbackOnly() throws NamingException, IllegalStateException {
        EJBContext ejbContext = EJBContextHelper.getEJBContext();
        ejbContext.setRollbackOnly();
    }

    /**
     * Makes a getRollbackOnly that must to throw en exception.
     * @throws NamingException if a lookup error occurs.
     * @throws IllegalStateException if the bean try to call the getRollBackOnly
     */
    public void getRollbackOnly() throws NamingException, IllegalStateException {
        EJBContext ejbContext = EJBContextHelper.getEJBContext();
        ejbContext.getRollbackOnly();
    }

}
