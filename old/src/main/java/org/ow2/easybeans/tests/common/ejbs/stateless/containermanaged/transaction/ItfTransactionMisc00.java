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
 * $Id: ItfTransactionMisc00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction;

import java.sql.SQLException;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.NamingException;

/**
 * Creates the test table with different transaction attributes.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfTransactionMisc00 {

    /**
     * Table used during the tests.
     */
    String TABLE = "SLSBTransactionMisc00";

    /**
     * Inserts the table test in the database with the transaction attribute REQUIRED.
     * @param dbName is the name of the database in the registry.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void insertTableWithRequired(final String dbName) throws SQLException, NamingException;

    /**
     * Inserts the table test in the database with the transaction attribute NOT_SUPPORTED.
     * @param dbName is the name of the database in the registry.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    void insertTableWithNotSupported(final String dbName) throws SQLException, NamingException;

}
