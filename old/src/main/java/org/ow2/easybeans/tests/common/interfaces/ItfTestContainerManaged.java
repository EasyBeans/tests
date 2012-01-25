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
 * $Id: ItfTestContainerManaged.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.interfaces;


/**
 * Is the common interface that will be used to local and remote tests.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfTestContainerManaged {

    /**
     * Binds the databases used.
     * @throws Exception if an error during the test startup occurs.
     */
    void setup() throws Exception;

    /**
     * Creates a new instance of the bean. This method makes a lookup with the
     * beanName.
     * @param beanClass the bean class used during the tests;
     * @throws Exception if a lookup error occurs.
     */
    void createBean(final Class beanClass) throws Exception;


    /**
     * Calls the method setRollbackOnly that must throw exception for some types
     * of transactionattribute.After, this method tries to insert tables in the
     * databases.
     * @throws Exception if an error during the test occurs.
     */
    void testSetRollbackOnly() throws Exception;

    /**
     * Calls the method getRollbackOnly that must throw exception for some types
     * of transactionattribute.
     * @throws Exception if an error during the test occurs.
     */
    void testGetRollbackOnly() throws Exception;

    /**
     * Verifies if the container uses the same transaction that the client.
     * Creates an UserTransaction and calls the bean method.
     * @throws Exception if an error during the tests occurs.
     */
    void testUsingClientTrans() throws Exception;

    /**
     * Deletes the tables.
     */
    void deleteTable();

    /**
     * Tries to get an UserTransaction that is denied to beans with
     * container-managed transaction.
     * @throws Exception if an error occurs during the tests.
     */
    void testGetUserTransactionWithLookup() throws Exception;

    /**
     * Tries to get an UserTransaction that is denied to beans with
     * container-managed transaction.
     * @throws Exception if an error occurs during the tests.
     */
    void testGetUserTransactionWithEJBContext() throws Exception;
}
