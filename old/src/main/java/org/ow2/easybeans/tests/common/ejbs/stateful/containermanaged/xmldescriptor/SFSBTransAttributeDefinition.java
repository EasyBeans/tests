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
 * $Id: SFSBTransAttributeDefinition.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.ow2.easybeans.tests.common.db.TableManager;

/**
 * Adds values in the database to verify the transaction attribute that id
 * defined by deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class SFSBTransAttributeDefinition implements ItfTransAttributeDefinition {

    /**
     * Inserts a table the database.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    protected void insertTable(final String dbName, final String tableName) throws Exception {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(tableName);
    }

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    public void insertTableCorrect(final String dbName, final String tableName) throws Exception{
        insertTable(dbName, tableName);
    }

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    public void insertTableError(final String dbName, final String tableName) throws Exception{
        insertTable(dbName, tableName);
        throw new RuntimeException();
    }

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void insertTableCorrectMandatory(final String dbName, final String tableName) throws Exception{
        insertTable(dbName, tableName);
    }

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void insertTableErrorMandatory(final String dbName, final String tableName) throws Exception{
        insertTable(dbName, tableName);
        throw new RuntimeException();
    }


}
