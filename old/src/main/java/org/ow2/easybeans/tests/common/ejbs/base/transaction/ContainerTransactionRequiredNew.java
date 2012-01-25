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
 * $Id: ContainerTransactionRequiredNew.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.transaction;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.exception.TransactionException;

/**
 * Inserts the table test in two databases using the annotation @TransactionAttribute(REQUIRED_NEW) in all methods.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class ContainerTransactionRequiredNew extends ContainerTransaction {

    /**
     * Inserts the table test in both database using required new transaction.
     * @param db1 the first databse name.
     * @param db2 the second database name.
     * @throws Exception if an error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void insertCorrectTableInBothDB(final String db1, final String db2) throws Exception {
        super.insertCorrectTableInBothDB(db1, db2);
    }

    /**
     * Inserts the table test in the first database and makes an incorrect query in the second database.
     * This method is using required new transaction, so this must to force a roll back.
     * @param db1 the first databse name.
     * @param db2 the second database name.
     * @throws Exception if an error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void insertCorrectFirstErrorSecond(final String db1, final String db2) throws Exception {
        super.insertCorrectFirstErrorSecond(db1, db2);
    }

    /**
     * Calls the method EJBContext.getRollbackOnly().
     * @return true if the rollback only is set, false otherwise.
     * @throws TransactionException if an IllegalStateException occurs.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public boolean getRollbackOnly() throws TransactionException {
        return super.getRollbackOnly();
    }

    /**
     * Calls the method SessionContext.setRollbackOnly().
     * @param dbName1 the first database where the table should be inserted.
     * @param dbName2 the second database where the table should be inserted.
     * @throws Exception if an error occurs.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void setRollbackOnly(final String dbName1, final String dbName2) throws Exception, NamingException {
        super.setRollbackOnly(dbName1, dbName2);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.transaction.ContainerTransaction
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error during the execution occurs.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void insertTablesUsingAuxBeanReq(final String db1, final String db2) throws Exception {
        super.insertTablesUsingAuxBeanReq(db1, db2);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.transaction.ContainerTransaction
     * @param db1 the name of the first database.
     * @param db2 the name of the second database.
     * @throws Exception if an error during the execution occurs.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void insertTablesUsingAuxBeanNotSup(final String db1, final String db2) throws Exception {
        super.insertTablesUsingAuxBeanNotSup(db1, db2);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.transaction.ContainerTransaction
     * @throws Exception if an error occurs.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void getUserTransactionWithLookup() throws Exception {
        super.getUserTransactionWithLookup();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.transaction.ContainerTransaction
     * @throws Exception if an error occurs.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void getUserTransactionWithEJBContext() throws Exception {
        super.getUserTransactionWithEJBContext();
    }
}
