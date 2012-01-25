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
 * $Id: EmbeddedTest.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.easybeans.server.EmbeddedException;
import org.ow2.easybeans.tests.common.db.DBManager;

/**
 * Publishes the databases in the context. This class will be deprecated when
 * the container provides this funcionality.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class EmbeddedTest {

    /**
     * The file separator for the current operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Path of the properties file. The path is current diretory + /tests/conf.
     */
    private static final String PATH_PROPERTIES_FILE = System.getProperty("user.dir") + FILE_SEPARATOR + "tests"
            + FILE_SEPARATOR + "conf" + FILE_SEPARATOR;

    /**
     * Alternate Path of the properties file. The path is current diretory + /tests/conf.
     */
    private static final String ALTERNATE_PATH_PROPERTIES_FILE = System.getProperty("user.dir") + FILE_SEPARATOR + "conf" + FILE_SEPARATOR;

    /**
     * The name of the properties file.
     */
    private static final String PROPERTIES_FILE = "dbTest.properties";

    /**
     * Says if the postgresql database must be published.
     */
    private static final boolean BIND_POSTGRESQL = false;

    /**
     * Says if the oracle database must be published.
     */
    private static final boolean BIND_ORACLE = false;

    /**
     * Says if the mysql database must be published.
     */
    private static final boolean BIND_MYSQL = false;

    /**
     * The propriety file.
     */
    private Properties properties = new Properties();

    /**
     * Sets the property file.
     * @throws EmbeddedException if an error to load the property file occurs.
     */
    public EmbeddedTest() throws EmbeddedException {
            try {
                properties.load(new FileInputStream(PATH_PROPERTIES_FILE + PROPERTIES_FILE));
            } catch (FileNotFoundException e) {
                // try alternate file
                try {
                    properties.load(new FileInputStream(ALTERNATE_PATH_PROPERTIES_FILE + PROPERTIES_FILE));
                } catch (IOException ioe) {
                    throw new EmbeddedException("Cannot open the properties file.", ioe);
                }
            } catch (IOException ioe) {
                throw new EmbeddedException("Cannot open the properties file.", ioe);
            }
    }

    /**
     * Sets the parameters to publish the postgresql database.
     * @return the parameters values.
     */
    private Hashtable<Integer, String> createDefaultPostgreSQL() {
        Hashtable<Integer, String> htParameters = new Hashtable<Integer, String>();
        htParameters.put(DBManager.JDBC_DRIVER, properties.getProperty("PostgresqlDriver"));
        htParameters.put(DBManager.URL, properties.getProperty("PostgresqlURL"));
        htParameters.put(DBManager.LOGIN, properties.getProperty("PostgresqlLogin"));
        htParameters.put(DBManager.PASSWD, properties.getProperty("PostgresqlPassword"));
        return htParameters;

    }

    /**
     * Sets the parameters to publish the mysql database.
     * @return the parameters values.
     */
    private Hashtable<Integer, String> createDefaultMySQL() {
        Hashtable<Integer, String> htParameters = new Hashtable<Integer, String>();
        htParameters.put(DBManager.JDBC_DRIVER, properties.getProperty("MysqlDriver"));
        htParameters.put(DBManager.URL, properties.getProperty("MysqlURL"));
        htParameters.put(DBManager.LOGIN, properties.getProperty("MysqlLogin"));
        htParameters.put(DBManager.PASSWD, properties.getProperty("MysqlPassword"));
        return htParameters;

    }

    /**
     * Sets the parameters to publish the oracle database.
     * @return the parameters values.
     */
    private Hashtable<Integer, String> createDefaultOracle() {
        Hashtable<Integer, String> htParameters = new Hashtable<Integer, String>();
        htParameters.put(DBManager.JDBC_DRIVER, properties.getProperty("OracleDriver"));
        htParameters.put(DBManager.URL, properties.getProperty("OracleURL"));
        htParameters.put(DBManager.LOGIN, properties.getProperty("OracleLogin"));
        htParameters.put(DBManager.PASSWD, properties.getProperty("OraclePassword"));
        return htParameters;

    }

    /**
     * Publishes the databases.
     * @throws EmbeddedException if a naming error occurs or when the jdbc
     *         drivers were not found.
     */
    public void bindDatabases() throws EmbeddedException {
        // if user don't use jclient/client container
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
        // Adds new databases
        // Bind a PostgreSQL datasource
        if (BIND_POSTGRESQL) {
            try {
                new InitialContext().rebind("postgresql", new DBManager(createDefaultPostgreSQL()));
            } catch (NamingException e) {
                throw new EmbeddedException("Cannot start the PostgresSQL database.", e);
            } catch (ClassNotFoundException e) {
                throw new EmbeddedException("Cannot start the Postgres database.", e);
            }
        }
        // Bind a MySQL datasource
        if (BIND_MYSQL) {
            try {
                new InitialContext().rebind("mysql", new DBManager(createDefaultMySQL()));
            } catch (NamingException e) {
                throw new EmbeddedException("Cannot start the MySQL database.", e);
            } catch (ClassNotFoundException e) {
                throw new EmbeddedException("Cannot start the MySQL database.", e);
            }
        }

        // Bind a Oracle datasource
        if (BIND_ORACLE) {
            try {
                new InitialContext().rebind("oracle", new DBManager(createDefaultOracle()));
            } catch (NamingException e) {
                throw new EmbeddedException("Cannot start the Oracle database.", e);
            } catch (ClassNotFoundException e) {
                throw new EmbeddedException("Cannot start the Oracle database.", e);
            }
        }

    }

    /**
     * Unbind the databases of the registry.
     * @throws EmbeddedException if a naming error occurs.
     */
    public void unbindDataBases() throws EmbeddedException {
        // if user don't use jclient/client container
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        if (BIND_POSTGRESQL) {
            unbindDataBase("postgresql");
        }

        if (BIND_MYSQL) {
            unbindDataBase("mysql");
        }
        if (BIND_ORACLE) {
            unbindDataBase("oracle");
        }

    }

    /**
     * Deletes the database of the registry.
     * @param dbName the database name in the registry.
     * @throws EmbeddedException if a naming error occurs
     */
    private void unbindDataBase(final String dbName) throws EmbeddedException {
        try {
            new InitialContext().unbind(dbName);
        } catch (NamingException e) {
            throw new EmbeddedException("Cannot stop the " + dbName + "dataBase", e);
        }
    }
}
