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
 * $Id: TestEntityManager01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager.ItfEntityManagerTester01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager.SLSBEntityManagerTester01;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the methods remove and persist from EntityManager are working
 * properly. The items 3.2.1 e 3.2.2 (Persistence doc)
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SLSBEntityManagerTester must be deployed.
 * @setup gets the reference of SLSBEntityManagerTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEntityManager01 {

    /**
     * The entity bean primary key that is used during the tests.
     */
    public static final int PRIMARY_KEY = 1;

    /**
     * The entity bean name that is used during the tests.
     */
    public static final String ENTITY_NAME = "test";

    /**
     * The entity bean alternative name that is used during the tests.
     */
    public static final String ALTERNATIVE_ENTITY_NAME = "test2";

    /**
     * The stateless bean used to verify the EntityManager.
     */
    private ItfEntityManagerTester01 slsbEntityManagerTester01;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        slsbEntityManagerTester01 = EJBHelper.getBeanRemoteInstance(SLSBEntityManagerTester01.class,
                ItfEntityManagerTester01.class);
        slsbEntityManagerTester01.removeEBStore(PRIMARY_KEY);
    }

    /**
     * Tests if the EntityManager can make a merge with a detached entity.
     * @input PRIMARY_KEY,ENTITY_NAME and ALTERNATIVE_ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void testMerge() {
        slsbEntityManagerTester01.mergeEBStore(PRIMARY_KEY, ENTITY_NAME, ALTERNATIVE_ENTITY_NAME);
    }

    /**
     * Tests if the EntityManager can make a refresh.
     * @input PRIMARY_KEY,ENTITY_NAME and ALTERNATIVE_ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void testRefresh() {
        slsbEntityManagerTester01.refreshEBStore(PRIMARY_KEY, ENTITY_NAME, ALTERNATIVE_ENTITY_NAME);
    }

    /**
     * Tests if the contains method in the EntityManager works well.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void testContains() {
        slsbEntityManagerTester01.containsEBStore(PRIMARY_KEY, ENTITY_NAME);
    }

}
