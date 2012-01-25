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
 * $Id: ItfContainerTransaction.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.transaction;

import org.ow2.easybeans.tests.common.exception.TransactionException;

/**
 * Inserts table in two database and uses the annotation @TransactionAttribute(REQUIRED) in all methods.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfContainerTransaction {

    /**
     * Table name used during the tests.
     */
    String TABLE = "test";

    /**
     * Inserts the table test in the both database using a correct statement.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error occurs.
     */
    void insertCorrectTableInBothDB(final String db1, final String db2) throws Exception;

    /**
     * Inserts the table test in the first database and makes an incorrect query
     * in the second database. This incorrect query forces a roll back.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error occurs.
     */
    void insertCorrectFirstErrorSecond(final String db1, final String db2) throws Exception;

    /**
     * Calls the method SessionContext.setRollbackOnly().
     * @param dbName1 the first database where the table should be inserted.
     * @param dbName2 the second database where the table should be inserted.
     * @throws TransactionException if an IllegalStateException occurs.
     * @throws Exception if an error occurs.
     */
    public void setRollbackOnly(final String dbName1, final String dbName2) throws TransactionException, Exception;

    /**
     * Calls the method EJBContext.getRollbackOnly().
     * @return true if the rollback only is set, false otherwise.
     */
    boolean getRollbackOnly() throws TransactionException;

    /**
     * Inserts a table in the first database,calls an auxiliary bean to create a
     * table in the second database and makes an exception to force a rollback
     * if the transaction attribute supports. The auxiliary bean uses the
     * transaction attribute REQUIRED.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database
     * @throws Exception if an error during the execution occurs.
     */
    void insertTablesUsingAuxBeanReq(final String db1, final String db2) throws Exception;

    /**
     * Inserts a table in the first database,calls an auxiliary bean to create a
     * table in the second database and makes an exception to force a rollback
     * if the transaction attribute supports. The auxiliary bean uses the
     * transaction attribute NOT_SUPPORTED.
     * @param db1 the name of the first database.
     * @param db2 the name of the second database
     * @throws Exception if an error during the execution occurs.
     */
    void insertTablesUsingAuxBeanNotSup(final String db1, final String db2) throws Exception;

    /**
     * Makes a lookup in the registry to get an UserTransaction.An exception
     * must be throwed because the bean with container-managed transaction
     * cannot call a bean-managed transaction.
     * @throws Exception if an error occurs.
     */
    void getUserTransactionWithLookup() throws Exception;

    /**
     * Call the method getUserTransaction() in the EJBContext to get an
     * UserTransaction.An exception must be throwed because the bean with
     * container-managed transaction cannot call a bean-managed transaction.
     * @throws Exception if an error occurs.
     */
    void getUserTransactionWithEJBContext() throws Exception;

}
