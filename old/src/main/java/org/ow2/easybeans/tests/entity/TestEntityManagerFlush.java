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
 * $Id: TestEntityManagerFlush.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.flushoperation.ItfEntityManagerFlushTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.flushoperation.SFSBEntityManagerFlushTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the methods related with flush(flush(), getFlushMode(),
 * setFlushMode()) from EntityManager are working properly. The items 3.2.3
 * (Persistence doc)
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SFSBEntityManagerFlushTester must be deployed.
 * @setup gets the reference of SFSBEntityManagerFlushTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEntityManagerFlush {

    /**
     * Bean used during the tests.
     */
    private ItfEntityManagerFlushTester sfsbFlushTester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        sfsbFlushTester = EJBHelper.getBeanRemoteInstance(SFSBEntityManagerFlushTester.class,
                ItfEntityManagerFlushTester.class);
        sfsbFlushTester.startup();
    }

    /**
     * Verifies when the persistence context default value is AUTO.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testVerifyDefaultValue() {
        sfsbFlushTester.verifyDefaultFlushMode();
    }

    /**
     * Verifies if the container manages the synchronization to the database
     * when the flush mode is AUTO. In this case, the flush must be made after
     * query methods.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testVerifyFlushAuto() {
        sfsbFlushTester.setFlushModeAuto();
    }

    /**
     * Verifies if the container manages the synchronization to the database
     * when the flush mode is COMMIT. In this case, the flush must be made when
     * the container makes the commit.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testVerifyFlushCommit() {
        sfsbFlushTester.setFlushModeCommit();
    }
}
