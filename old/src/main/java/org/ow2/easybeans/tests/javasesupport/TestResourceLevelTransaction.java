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
 * $Id:TestResourceLevelTransaction.java 505 2006-05-24 14:28:38Z pinheirg $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.javasesupport;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the bean can use a resource level transaction.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the entity Book must be
 *              deployed.
 * @setup gets the reference of ResourceLevelTransTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestResourceLevelTransaction {

    /**
     * Common class used during the tests.
     */
    private ResourceLevelTransTester resourceLevelTransTester;

    /**
     * Creates an instance of the esourceLevelTransTester.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        resourceLevelTransTester = new ResourceLevelTransTester();
    }

    /**
     * Verifies if the EnitityTransaction.rollback() works.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testResourceLevelRollback(){
        resourceLevelTransTester.resourceLevelRollback();
    }

    /**
     * Verifies if the EnitityTransaction.commit() works.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testResourceLevelCommit(){
        resourceLevelTransTester.resourceLevelCommit();
    }

    /**
     * Verifies if the EnitityTransaction.setRollbackOnly() works.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testResourceLevelSetRollbackOnly(){
        resourceLevelTransTester.setRollbackOnly();
    }

}
