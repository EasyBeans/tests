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
 * $Id: SFSBBeanManagedException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.transaction;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateful;
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
import org.ow2.easybeans.tests.common.exception.RollbackAppRuntimeException;
import org.ow2.easybeans.tests.common.exception.TransactionRuntimeException;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;

/**
 * Used to test the Exceptions and the bean-managed transaction.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
@Remote
public class SFSBBeanManagedException implements ItfBeanManagedException {

    /**
     * Transaction used during the database operations.
     */
    private UserTransaction utx = null;

    /**
     * Class used to create the tables.
     */
    private TableManager tableManager = null;

    /**
     * Creates an instance of the tableManager.
     * @param dbName the database name.
     * @throws NamingException if a lookup error occurs.
     */
    public void startup(final String dbName) throws NamingException{
        tableManager = new TableManager(dbName);
    }
    /**
     * Begins a transaction, inserts the data in the database, throws an
     * application exception and tries to commit.
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
     * @throws IllegalStateException if the bean is not associated with a
     *         transaction.
     * @throws SecurityException if the bean is not allowed to commit.
     */
    public void insertTableWithAppException() throws NamingException, SQLException, NotSupportedException, SystemException,
            IllegalStateException, SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException {
        utx = TransactionHelper.getUserTransaction();
        try {
            utx.begin();
            tableManager.insertTable(TABLE);
            throw new RollbackAppRuntimeException(new Throwable("Application exception with rollback = true"));
        } finally {
            utx.commit();
        }
    }

    /**
     * Begins a transaction, inserts the data in the database, throws a
     * runtime exception and tries to rollback.
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
     * @throws IllegalStateException if the bean is not associated with a
     *         transaction.
     * @throws SecurityException if the bean is not allowed to commit.

     */
    public void insertTableWithRuntimeException() throws NamingException, SQLException, NotSupportedException, SystemException,
            IllegalStateException, SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException {
        utx = TransactionHelper.getUserTransaction();
        utx.begin();
        tableManager.insertTable(TABLE);
        try {
            // will throw a runtime exception
            internallCall();
            // we wanted to commit the transaction
            utx.commit();
        } catch (TransactionRuntimeException e) {
            utx.rollback();
            throw e;
        }

    }

    /**
     * Simulates an internal call that throws a Runtime exception.
     */
    private void internallCall() {
        throw new TransactionRuntimeException(null);
    }

    /**
     * Does nothing.
     *
     */
    public void emptyMethod(){

    }
}
