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
 * $Id: ItfBeanManagedException.java 5369 2010-02-24 14:58:19Z benoitf $
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

/**
 * Used to test the Exceptions and the bean-managed transaction.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfBeanManagedException {

    /**
     * The table name.
     */
     String TABLE = "SFSBBeanManagedException";

     /**
      * Creates an instance of the tableManager.
      * @param dbName the database name.
      * @throws NamingException if a lookup error occurs.
      */
     void startup(final String dbName) throws NamingException;

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
    void insertTableWithAppException() throws NamingException, SQLException, NotSupportedException, SystemException,
            IllegalStateException, SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException;

    /**
     * Begins a transaction, inserts the data in the database, throws a
     * runtime exception and tries to commit.
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
    void insertTableWithRuntimeException() throws NamingException, SQLException, NotSupportedException, SystemException,
            IllegalStateException, SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException;

    /**
     * Does nothing.
     *
     */
    void emptyMethod();
}
