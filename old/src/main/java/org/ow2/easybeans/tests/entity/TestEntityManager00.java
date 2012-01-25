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
 * $Id: TestEntityManager00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager.ItfEntityManagerTester00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager.SLSBEntityManagerTester00;
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
public class TestEntityManager00 {

    /**
     * The entity bean primary key that is used during the tests.
     */
    private static final int PRIMARY_KEY = 1;

    /**
     * The entity bean name that is used during the tests.
     */
    private static final String ENTITY_NAME = "test";

    /**
     * The statelles bean used to verify the EntityManager.
     */
    private ItfEntityManagerTester00 slsbEntityManagerTester;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        slsbEntityManagerTester = EJBHelper.getBeanRemoteInstance(SLSBEntityManagerTester00.class,
                ItfEntityManagerTester00.class);
        slsbEntityManagerTester.removeEBStore(PRIMARY_KEY);
    }

    /**
     * Tests if the EntityManager can persist a new entity.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void createNewEntity() {
        slsbEntityManagerTester.createEBStoreNew(PRIMARY_KEY, ENTITY_NAME);
        EBStore ebstore = slsbEntityManagerTester.findEBStore(PRIMARY_KEY);
        assertNotNull(ebstore, "The entity was not inserted in the database");
    }

    /**
     * Tests if the EntityManager can persist a removed entity. The
     * specification says that the bean must become managed.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void createRemoved() {
        slsbEntityManagerTester.createEBStoreRemoved(PRIMARY_KEY, ENTITY_NAME);
        EBStore ebstore = slsbEntityManagerTester.findEBStore(PRIMARY_KEY);
        assertNotNull(ebstore, "The entity was not re-inserted in the database");
    }

    /**
     * Tests if the EntityManager can persist a managed entity. The
     * specification says that the persist operation must be ignored.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void createManaged() {
        slsbEntityManagerTester.createEBStoreManaged(PRIMARY_KEY, ENTITY_NAME);
        EBStore ebstore = slsbEntityManagerTester.findEBStore(PRIMARY_KEY);
        assertNotNull(ebstore, "The persist operation was not ignored.");
    }

    /**
     * Tests if the EntityManager can remove a new entity. The specification
     * says that the remove operation must be ignored.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void removeNew() {
        slsbEntityManagerTester.removeEBStoreNew(PRIMARY_KEY, ENTITY_NAME);
        EBStore ebstore = slsbEntityManagerTester.findEBStore(PRIMARY_KEY);
        assertNull(ebstore, "The remove operation was not ignored.");
    }

    /**
     * Tests if the EntityManager can remove a managed entity.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void removeManaged() {
        slsbEntityManagerTester.removeEBStoreManaged(PRIMARY_KEY, ENTITY_NAME);
        EBStore ebstore = slsbEntityManagerTester.findEBStore(PRIMARY_KEY);
        assertNull(ebstore, "The remove operation failed.");
    }

    /**
     * Tests if the EntityManager can remove a removed entity. The specification
     * says that the remove operation must be ignored.
     * @input PRIMARY_KEY and ENTITY_NAME.
     * @output the method execution without error.
     */
    @Test
    public void removeRemoved() {
        slsbEntityManagerTester.removeEBStoreManaged(PRIMARY_KEY, ENTITY_NAME);
        EBStore ebstore = slsbEntityManagerTester.findEBStore(PRIMARY_KEY);
        assertNull(ebstore, "The remove operation was not ignored.");
    }
}
