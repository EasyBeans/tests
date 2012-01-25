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
 * $Id: TestEjbql.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbql.ItfEjbqlTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbql.SLSBEjbqlTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages the most used EJB QL statement. The item 4
 * (Persistence doc)
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the bean SLSBEjbqlTester and
 *              the entity package Customer must be deployed.
 * @setup gets the reference of SLSBEjbqlTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEjbql {

    /**
     * Bean used during the tests.
     */
    private ItfEjbqlTester slsbEjbqlTester;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        slsbEjbqlTester = EJBHelper.getBeanRemoteInstance(SLSBEjbqlTester.class, ItfEjbqlTester.class);
    }

    /**
     * Verifies if the container manages a path expression.
     * @input -
     * @output the correct method execution.
     *
     */
    @Test
    public void testPathExpression() {
        slsbEjbqlTester.testPathExpression();
    }

    /**
     * Verifies if the container manages an inner join.
     * @input -
     * @output the correct method execution.
     *
     */
    @Test
    public void testInnerJoin() {
        slsbEjbqlTester.testInnerJoin();
    }

    /**
     * Verifies if the is empty statement work properly.
     * @input -
     * @output the correct method execution.
     *
     */
    @Test
    public void testIsEmpty() {
        slsbEjbqlTester.testIsEmpty();
    }

    /**
     * Verifies if the container manages a group by and having statements.
     * @input -
     * @output the correct method execution.
     *
     */
    @Test
    public void testGroupByHaving() {
        slsbEjbqlTester.testHaving();
    }

    /**
     * Verifies if the container manages a bulk operation delete.
     * @input -
     * @output the correct method execution.
     *
     */
    @Test
    public void testDelete() {
        slsbEjbqlTester.testDelete();
    }

    /**
     * Verifies if the container manages a bulk operation update.
     * @input -
     * @output the correct method execution.
     *
     */
    @Test
    public void testUpdate() {
        slsbEjbqlTester.testUpdate();
    }

}
