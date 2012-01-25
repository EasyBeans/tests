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
 * $Id: TestResourceLevelTrans.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.resourceleveltrans.ItfResourceLevelTransTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.resourceleveltrans.SFSBResourceLevelTransTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the bean can use a resource level transaction.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SFSBResourceLevelTransTester and the entity Book must be
 *              deployed.
 * @setup gets the reference of SSFSBResourceLevelTransTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestResourceLevelTrans {

    /**
     * Bean used during the tests.
     */
    private ItfResourceLevelTransTester sfsbResourceLevelTransTester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        sfsbResourceLevelTransTester = EJBHelper.getBeanRemoteInstance(SFSBResourceLevelTransTester.class,
                ItfResourceLevelTransTester.class);
        sfsbResourceLevelTransTester.startup();
    }

    /**
     * Verifies if the method rollback in the EnityTransaction interface works
     * well.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testResourceLevelRollback() {
        sfsbResourceLevelTransTester.resourceLevelRollback();
    }

    /**
     * Verifies if the method commit in the EnityTransaction interface works
     * well.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testResourceLevelCommit() {
        sfsbResourceLevelTransTester.resourceLevelCommit();
    }

    /**
     * Verifies if the method setRollbackOnly in the EnityTransaction interface
     * works well.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testResourceLevelSetRollbackOnly() {
        sfsbResourceLevelTransTester.setRollbackOnly();
    }
}
