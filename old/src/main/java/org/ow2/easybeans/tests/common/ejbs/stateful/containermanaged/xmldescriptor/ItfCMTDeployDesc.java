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
 * $Id: ItfCMTDeployDesc.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

/**
 * Verifies if the CMT can be defined by deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfCMTDeployDesc {

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    void insertTable01(final String dbName, final String tableName) throws Exception;

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    void insertTable02(final String dbName, final String tableName) throws Exception;

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @param dummy is used to provide two methods with the same name.
     * @throws Exception if an error occurs.
     */
    void insertTable02(final String dbName, final String tableName, final int dummy) throws Exception;

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @throws Exception if an error occurs.
     */
    void insertTable03(final String dbName, final String tableName) throws Exception;

    /**
     * Inserts a table the database. The transaction attribute is defined in the
     * deployment descriptor.
     * @param dbName is the name of the database in the registry.
     * @param tableName is the table name.
     * @param dummy is used to provide two methods with the same name.
     * @throws Exception if an error occurs.
     */
    void insertTable03(final String dbName, final String tableName, final int dummy) throws Exception;

}
