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
 * $Id: SLSBDatabaseManager.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.db.TableManager;

/**
 * Manages the database in the server side.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless(name="SLSBDatabaseManager")
@Remote(ItfDatabaseManager.class)
public class SLSBDatabaseManager implements ItfDatabaseManager {

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager#deleteTable(java.lang.String,
     *      java.lang.String)
     * @param dbName name in the registry.
     * @param tableName name in the database.
     * @throws NamingException if a lookup error occurs.
     * @throws SQLException if a database error occurs.
     */
    public void deleteTable(final String dbName, final String tableName) throws NamingException, SQLException {
         // deletes the table
        TableManager tableManager = new TableManager(dbName);
        tableManager.deleteTable(tableName);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager#verifyTable(java.lang.String,
     *      java.lang.String)
     * @param dbName name in the registry.
     * @param tableName name in the database.
     * @throws NamingException if a lookup error occurs.
     * @throws SQLException if a database error occurs.
     */
    public void verifyTable(final String dbName, final String tableName) throws NamingException, SQLException {
        // verifies the table
        TableManager tableManager = new TableManager(dbName);
        tableManager.verifyTable(tableName);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager#insertTable(java.lang.String,
     *      java.lang.String)
     * @param dbName name in the registry.
     * @param tableName name in the database.
     * @throws NamingException if a lookup error occurs.
     * @throws SQLException if a database error occurs.
     */
    public void insertTable(final String dbName, final String tableName) throws NamingException, SQLException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(tableName);
    }

}
