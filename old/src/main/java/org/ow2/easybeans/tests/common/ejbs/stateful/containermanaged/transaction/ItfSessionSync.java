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
 * $Id: ItfSessionSync.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction;

import java.sql.SQLException;

import javax.naming.NamingException;

/**
 * Inserts a table in the database using container-managed transaction.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfSessionSync {

    /**
     * Table used during the tests.
     */
    String TABLE = "SFSBSEssionSync";

    /**
    * Inserts the table test in the second database with the transaction
    * attribute REQUIRED.
    * @throws SQLException if a databse error occurs.
    * @throws NamingException if a lookup error occurs.
    */
    void insertTableRequired()throws SQLException, NamingException;

    /**
     * Inserts the table test in the second database with the transaction
     * attribute MANDATORY.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    void insertTableMandatory()throws SQLException, NamingException;

    /**
     * Inserts the table test in the second database with the transaction
     * attribute REQUIRED_NEW.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    void insertTableRequiredNew()throws SQLException, NamingException;

    /**
     * Inserts the table test in the second database with the transaction
     * attribute SUPPORTS.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    void insertTableSupports()throws SQLException, NamingException;

    /**
     * Inserts the table test in the second database with the transaction
     * attribute NOT_SUPPORTED.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    void insertTableNotSupported() throws SQLException, NamingException;

    /**
     * Inserts the table test in the second database with the transaction
     * attribute NEVER.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    void insertTableNever() throws SQLException, NamingException;

    /**
     * Initializes the bean with the database name in the registry.
     * @param dbName1 database1 name in the registry.
     * @param dbName2 database2 name in the registry.
     */
    void startup(final String dbName1, final String dbName2);

    /**
     * Verifies if the transaction was rolled back.
     * @return true if the transaction was rolled back, false otherwise.
     */
    boolean isRolledback();
}
