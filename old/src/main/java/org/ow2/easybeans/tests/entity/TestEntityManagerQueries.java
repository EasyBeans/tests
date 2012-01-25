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
 * $Id: TestEntityManagerQueries.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.entitymanager.ItfEntityManagerQueriesTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.entitymanager.SFSBEntityManagerQueriesTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the methods related with queries of the Entitymanager are working
 * properly. The items 3.1.1, 3.5.4 e 3.5.6 (Persistence doc)
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SLSBEntityManagerQueriesTester must be deployed.
 * @setup gets the reference of SLSBEntityManagerQueriesTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEntityManagerQueries {

    /**
     * The bean used during the tests.
     */
    private ItfEntityManagerQueriesTester sfsbQueryTester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        sfsbQueryTester = EJBHelper.getBeanRemoteInstance(SFSBEntityManagerQueriesTester.class,
                ItfEntityManagerQueriesTester.class);
        sfsbQueryTester.startup();
    }

    /**
     * Tests if the EntityManager can call a named query that is write in EJB
     * QL.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testCallNamedQueryEJBQL() {
        sfsbQueryTester.callNamedQuery();
    }

    /**
     * Tests if the EntityManager can call a named query that is write in native
     * SQL.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testCallNamedQueryNative() {
        sfsbQueryTester.callNamedNativeQuery();
    }

    /**
     * Tests if the EntityManager can create a EJB QL query.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testCreateQueryEJBQL() {
        sfsbQueryTester.callCreateQuery();
    }

    /**
     * Tests if the EntityManager can create a native query without choosing the
     * result class.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testCreateQueryNative00() {
        sfsbQueryTester.callCreateNativeQuery00();
    }

    /**
     * Tests if the EntityManager can create a native query when the result
     * class is choosen.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testCreateQueryNative01() {
        sfsbQueryTester.callCreateNativeQuery01();
    }

    /**
     * Tests if the EntityManager can create a native query when the
     * resultSetMapping is choosen.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testCreateQueryNative02() {
        sfsbQueryTester.callCreateNativeQuery02();
    }
}
