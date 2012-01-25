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
 * $Id: SLSBTransactionMisc00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.db.TableManager;

/**
 * Creates the test table in the database.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Remote(ItfTransactionMisc00.class)
public class SLSBTransactionMisc00 implements ItfTransactionMisc00 {

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00
     * @param dbName is the name of the database in the registry.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void insertTableWithRequired(final String dbName) throws SQLException, NamingException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00
     * @param dbName is the name of the database in the registry.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void insertTableWithNotSupported(final String dbName) throws SQLException, NamingException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
    }

}
