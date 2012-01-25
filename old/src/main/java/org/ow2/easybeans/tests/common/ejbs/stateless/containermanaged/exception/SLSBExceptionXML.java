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
 * $Id: SLSBExceptionXML.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.exception;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.ow2.easybeans.tests.common.db.TableManager;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.common.exception.CustomException01;
import org.ow2.easybeans.tests.common.exception.IllegalException;
import org.ow2.easybeans.tests.common.exception.RollbackApplicationException;

/**
 * Inserts a table in the database and throws application exceptions defined in
 * the deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfExceptionXML.class)
public class SLSBExceptionXML implements ItfExceptionXML {

    /**
     * Inserts a table in the database and after throws an exception that is
     * defined in the deployment descriptor as application exception with
     * rollback false.
     * @param dbName the database name in the registry.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws SQLException if a database error occurs.
     */
    public void createTableWithAppException(final String dbName) throws NamingException, NotSupportedException,
            SystemException, SQLException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
        throw new CustomException01("The bean threw an application exception defined in the deployment descriptor.");
    }

    /**
     * Inserts a table in the database and after throws an exception that is
     * defined in the deployment descriptor as application exception with
     * rollback true.
     * @param dbName the database name in the registry.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws SQLException if a database error occurs.
     * @throws CustomException00 an application exception defined in the
     *         deployment descriptor, it is always thrown.
     */
    public void createTableWithAppExceptionAndRollback(final String dbName) throws NamingException,
            NotSupportedException, SystemException, SQLException, CustomException00 {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
        throw new CustomException00("The bean threw an application exception defined in the deployment descriptor.");
    }

    /**
     * Inserts a table in the database and after throws an exception that is
     * defined in the deployment descriptor as application exception with
     * the default value for the rollback.
     * @param dbName the database name in the registry.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws SQLException if a database error occurs.
     */
    public void createTableWithAppExceptionDefault(final String dbName) throws NamingException, NotSupportedException,
            SystemException, SQLException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
        throw new IllegalException("The bean threw an application exception defined in the deployment descriptor.");
    }

    /**
     * Inserts a table in the database and after throws an exception that is
     * defined by annotation, but the rollback element is overrided in the
     * deployment descriptor.
     * @param dbName the database name in the registry.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws SQLException if a database error occurs.
     * @throws RollbackApplicationException an application exception defined in
     *         the deployment descriptor, it is always thrown.
     */
    public void createTableWithAppExceptionOverride(final String dbName) throws NamingException, NotSupportedException,
            SystemException, SQLException, RollbackApplicationException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
        throw new RollbackApplicationException("The bean threw an application exception defined by annotation.");
    }

}
