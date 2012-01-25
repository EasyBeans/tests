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
 * $Id: DBManager.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.db;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.sql.DataSource;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Manages the connection with the database. It is make possible to open and
 * close connections with the database and also, use statements.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class DBManager implements DataSource, Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 1208377914757260026L;

    /**
     * A Constant that holds the name of the JDBC driver class.
     */
    public static final Integer JDBC_DRIVER = new Integer(1);

    /**
     * Constant that holds the database URL.
     */
    public static final Integer URL = new Integer(2);

    /**
     * Constant that holds the user login used to connect with the database.
     */
    public static final Integer LOGIN = new Integer(3);

    /**
     * Constant that holds the user passwd used to connect with the database.
     */
    public static final Integer PASSWD = new Integer(4);

    /**
     * The database URL.
     */
    private String strURL = null;

    /**
     * The user login used to connect with the database.
     */
    private String strLogin = null;

    /**
     * The user passwd used to connect with the database.
     */
    private String strPasswd = null;

    /**
     * The JDBC driver used to connect with the database.
     */
    private String strJDBCDriver = null;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(DBManager.class);

    /**
     * Creates a instance of DBManager.Loads the database driver and sets the
     * parameters.
     * @param dbParameters parameters used to make the connection.
     * @throws ClassNotFoundException if the jdbc driver class was not found.
     */
    public DBManager(final Hashtable<Integer, String> dbParameters) throws ClassNotFoundException {

        Class.forName(dbParameters.get(JDBC_DRIVER));
        strURL = dbParameters.get(URL);
        strJDBCDriver = dbParameters.get(JDBC_DRIVER);
        strLogin = dbParameters.get(LOGIN);
        strPasswd = dbParameters.get(PASSWD);
    }

    /**
     * Starts the connection with the database.
     * @return the datasource connection.
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(strJDBCDriver);
        } catch (Exception e) {
            logger.debug("Cannot uses the driver {0}", e);
        }
        return DriverManager.getConnection(strURL, strLogin, strPasswd);

    }

    /**
     * Starts the connection with the database.
     * @param username the name used to connect with the database.
     * @param password the user password.
     * @return the datasource connection.
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection(final String username, final String password) throws SQLException {
        return DriverManager.getConnection(strURL, username, password);
    }

    /**
     * Not used.
     * @return not used
     * @throws SQLException if a database access error occurs
     */
    public int getLoginTimeout() throws SQLException {
        throw new SQLException("Not implemented");
    }

    /**
     * Not used.
     * @return not used
     * @throws SQLException if a database access error occurs
     */
    public PrintWriter getLogWriter() throws SQLException {
        throw new SQLException("Not implemented");
    }

    /**
     * Not used.
     * @param seconds not used
     * @throws SQLException if a database access error occurs
     */
    public void setLoginTimeout(final int seconds) throws SQLException {
        throw new SQLException("Not implemented");
    }

    /**
     * Not used.
     * @param out not used.
     * @throws SQLException if a database access error occurs
     */
    public void setLogWriter(final PrintWriter out) throws SQLException {
        throw new SQLException("Not implemented");
    }

    /**
     * Returns an object that implements the given interface to allow access to
     * non-standard methods, or standard methods not exposed by the proxy. If
     * the receiver implements the interface then the result is the receiver or
     * a proxy for the receiver. If the receiver is a wrapper and the wrapped
     * object implements the interface then the result is the wrapped object or
     * a proxy for the wrapped object. Otherwise return the the result of
     * calling unwrap recursively on the wrapped object or a proxy for that
     * result. If the receiver is not a wrapper and does not implement the
     * interface, then an SQLException is thrown.
     * @param iface A Class defining an interface that the result must
     *        implement.
     * @param <T> type of object.
     * @return an object that implements the interface. May be a proxy for the
     *         actual implementing object.
     * @throws SQLException If no object found that implements the interface
     */
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return null;
    }

    /**
     * Returns true if this either implements the interface argument or is
     * directly or indirectly a wrapper for an object that does. Returns false
     * otherwise. If this implements the interface then return true, else if
     * this is a wrapper then return the result of recursively calling
     * isWrapperFor on the wrapped object. If this does not implement the
     * interface and is not a wrapper, return false. This method should be
     * implemented as a low-cost operation compared to unwrap so that callers
     * can use this method to avoid expensive unwrap calls that may fail. If
     * this method returns true then calling unwrap with the same argument
     * should succeed.
     * @param iface a Class defining an interface.
     * @return true if this implements the interface or directly or indirectly
     *         wraps an object that does.
     * @throws SQLException if an error occurs while determining whether this is
     *         a wrapper for an object with the given interface.
     */
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return false;
    }

}
