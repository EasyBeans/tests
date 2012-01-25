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
 * $Id: ItfTransAttributeTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

/**
 * Verifies if the deployment descriptor overrides the transaction attribute
 * defined in the class. Also, verifies if each type of transaction attribute
 * can be specified by deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfTransAttributeTester {

    /**
     * Name of the JBDC used during the tests.
     */
    String DB_NAME = "jdbc_1";

    /**
     * Name of the table inserted in the JDBC.
     */
    String TABLE_NAME = "TESTCMT";

    /**
     * Verifies if the deployment descriptor overrides the default transaction
     * attribute value. Also, verifies if the transaction attribute type is really mandatory.
     * @throws Exception if an error occurs.
     */
    void testMandatory() throws Exception;

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really required.
     * @throws Exception if an error occurs.
     */
    void testRequired() throws Exception;

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really requireds new.
     * @throws Exception if an error occurs.
     */
    void testRequiresNew() throws Exception;

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really supports.
     * @throws Exception if an error occurs.
     */
    void testSupports() throws Exception;

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really not supported.
     * @throws Exception if an error occurs.
     */
    void testNotSupported() throws Exception;

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really never.
     * @throws Exception if an error occurs.
     */
    void testNever() throws Exception;

}
