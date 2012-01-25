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
 * $Id: TestEntityManager02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager.ItfTransactionContextTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager.SLSBTransactionContextTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the entity manager works well among different trasnactions.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SLSBTransactionContextTester  and the entity EBStore must be deployed.
 * @setup gets the reference of SLSBTransactionContextTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEntityManager02 {

    /**
     * The stateless bean used to verify the EntityManager.
     */
    private ItfTransactionContextTester tester;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SLSBTransactionContextTester.class, ItfTransactionContextTester.class);

    }

    /**
     * The entity bean is instanciated in a method and the
     * EntityManager.persist() is made in other method. The both methods are
     * using the same transaction.
     * @input -
     * @output the bean must be persisted without error.
     */
    @Test
    public void testRequired() {
        tester.createBeanRequired();
    }

    /**
     * The entity bean is instanciated in a method and the
     * EntityManager.persist() is made in other method. The methods are not
     * using the same transaction, so the transaction of the first method is
     * resume, and other transaction is created before the entity manager makes the persist.
     * @input -
     * @output the bean must be persisted without error.
     */
    @Test
    public void testRequiresNew() {
        tester.createBeanRequiresNewWithClientTransaction();
    }

    /**
     * The entity bean is instanciated in a method and the
     * EntityManager.persist() is made in other method. The first method has not
     * transaction and the second method creates a trasnaction before the entity
     * manager makes the persist.
     * @input -
     * @output the bean must be persisted without error.
     */
    @Test
    public void testNotSupported() {
        tester.createBeanRequiresNewWithoutClientTransaction();
    }

}
