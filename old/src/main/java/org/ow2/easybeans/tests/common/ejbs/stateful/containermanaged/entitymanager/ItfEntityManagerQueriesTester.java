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
 * $Id: ItfEntityManagerQueriesTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.entitymanager;

/**
 * Verifies if the EntityManager methods that are related with query(native or
 * EJB QL) work properly.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfEntityManagerQueriesTester {

    /**
     * The number of entities that are created in the test.
     */
    int MAX_ENTITIES = 10;

    /**
     * The beginning of the entity name.
     */
    String ENTITY_NAME_ROOT = "test";

    /**
     * Cleans the database and inserts the entities used in the test. *
     */
    void startup();

    /**
     * Calls a EJBQL named query created by annotation.
     */
    void callNamedQuery();

    /**
     * Calls a named native query created by annotation.
     */
    void callNamedNativeQuery();

    /**
     * Creates an EJBQL query using the createQuery method.
     */
    void callCreateQuery();

    /**
     * Creates a native query using the method createNativeQuery with only the
     * query as parameter.
     */
    void callCreateNativeQuery00();

    /**
     * Creates a native query using the method createNativeQuery with the query
     * and the resultClass as parameters.
     */
    void callCreateNativeQuery01();

    /**
     * Creates a native query using the method createNativeQuery with the query
     * and the resultSetMapping as parameters.
     */
    void callCreateNativeQuery02();

}
