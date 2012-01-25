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
 * $Id: TableManager.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.ow2.easybeans.server.Embedded;
import org.ow2.easybeans.tests.common.helper.DBHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Manages a table in the database.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TableManager {

    /**
     * The primary key inserted in the table.
     */
    protected static final int PRIMARY_KEY = 1;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(Embedded.class);

    /**
     * Link to a datasource.
     */
    private DataSource ds = null;

    /**
     * Creates a new instance of TableManager.
     * @param dbName the database name in the registry.
     * @throws NamingException if a lookup error occurs.
     */
    public TableManager(final String dbName) throws NamingException {
        this(DBHelper.getDataSource(dbName));
    }

    /**
     * Creates a new instance of TableManager.
     * @param ds the datasource.
     */
    public TableManager(final DataSource ds) {
        this.ds = ds;
        if (ds == null) {
            throw new IllegalArgumentException("DataSource is null");
        }
    }

    /**
     * Creates the table in the database done without make the commit after the
     * insertion.
     * @param tableName the table name.
     * @throws SQLException if a database error occurs.
     */
    public void insertTable(final String tableName) throws SQLException {
        Connection connection = null;
        try {
            logger.debug("Before insert table.");
            connection = ds.getConnection();
            logger.debug("Connection opened.");

            PreparedStatement stmUpdate = null;
            try {
                stmUpdate = connection.prepareStatement("CREATE TABLE " + tableName + " (CodeTest integer, NameTest varchar(30), "
                        + "PRIMARY KEY (CodeTest))");
                stmUpdate.executeUpdate();
            } finally {
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
            }
            logger.debug("Table created.");

            //creates a line in the table
            PreparedStatement stmUpdateField = null;
            try {
                stmUpdateField = connection.prepareStatement("INSERT INTO " + tableName + " (CodeTest) VALUES  ("
                        + PRIMARY_KEY + ")");
                stmUpdateField.executeUpdate();
            } finally {
                if (stmUpdateField  != null) {
                    stmUpdateField .close();
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
                logger.debug("Connection closed.");
            }
        }
    }

    /**
     * Deletes the table test. This methods does not make the commit after
     * delete.
     * @param tableName the table name.
     * @throws SQLException if a database error occurs.
     */
    public void deleteTable(final String tableName) throws SQLException {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            // deletes the table
            PreparedStatement stmUpdate = null;
            try {
                stmUpdate = connection.prepareStatement("DROP TABLE " + tableName + " CASCADE");
                stmUpdate.executeUpdate();
            } finally {
                if (stmUpdate != null) {
                    stmUpdate.close();
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Verifies if the table was created.
     * @param tableName the table name.
     * @throws SQLException if a database error occurs or the table not exists.
     */
    public void verifyTable(final String tableName) throws SQLException {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE CodeTest = " + PRIMARY_KEY);
                if(!rs.next()){
                    throw new SQLException("There are not values in the table");
                }
            } finally {
                if(stmt != null){
                    stmt.close();
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Creates and deletes a table.
     * @param tableName the table name.
     * @throws SQLException if a database error occurs or the table not exists.
     */
    public void test(final String tableName) throws SQLException {
        insertTable(tableName);
        deleteTable(tableName);
    }
}
