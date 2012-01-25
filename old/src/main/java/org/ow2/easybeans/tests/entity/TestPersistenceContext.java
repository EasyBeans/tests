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
 * $Id: TestPersistenceContext.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectx.ItfPersistenceContextTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectx.SFSBPersistenceContextTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the transaction-scope persistence context and the extended
 * persistence context are making the correct management of the references. The
 * items 5.6(Persistence doc)
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SFSBPersistenceContextTester must be deployed.
 * @setup gets the reference of SFSBPersistenceContextTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestPersistenceContext {

    /**
     * Bean used during the tests.
     */
    private ItfPersistenceContextTester sfsbPersistenceContextTester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        sfsbPersistenceContextTester = EJBHelper.getBeanRemoteInstance(SFSBPersistenceContextTester.class,
                ItfPersistenceContextTester.class);
        sfsbPersistenceContextTester.startup();
    }

    /**
     * Tests if two beans created in the same transaction-scoped persistence
     * context have different references.
     * @input -
     * @output the test execution without error.
     */
    @Test
    public void testTransactionPersistenceContext() {
         sfsbPersistenceContextTester.testTransactionPersistenceContext();
    }

    /**
     * Tests if two beans created in the same extended persistence context have
     * the same references.
     * @input -
     * @output the test execution without error.
     */
    @Test
    public void testExtendedPersistenceContext() {
         sfsbPersistenceContextTester.testExtendedPersistenceContext();
    }

}
