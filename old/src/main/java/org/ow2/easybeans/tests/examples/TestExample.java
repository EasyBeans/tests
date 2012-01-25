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
 * $Id: TestExample.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.examples;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;
import static org.testng.Assert.assertEquals;

import org.ow2.easybeans.tests.common.ejbs.base.ItfExample;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBExample;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This is an example of an EasyBeans Test Suite Class.
 * @reference It is used to specify the document that the tests cover. Example:
 *            JSR 220-PROPOSED FINAL - Stateless - Method Call
 * @requirement It is used to specify the classes and files needed to run the
 *              tests. Exampe: EasyBeans must be running and the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SFSBExample
 *              must be deployed.
 * @setup It is used to specify the classes needed to run the test(setup methods).
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestExample {

    /**
     * Constant.
     */
    private static final Integer INPUT = new Integer(1);

    /**
     * Bean used in tests.
     */
    private ItfExample<Integer> bean;

    /**
     * Gets a new bean instance used during the tests.
     * @throws Exception if an error occurs during the setup.
     */
    @SuppressWarnings("unchecked")
    @BeforeMethod
    public void setup() throws Exception {
        // Gets a bean instance.
        bean = getBeanRemoteInstance(SLSBExample.class, ItfExample.class);
    }

    /**
     * Indicates the test description. Example: Tests if the bean can return a
     * value without modifications.
     * @input It is used to specify the classes and files needed to run the
     *        test. Example: Integer value.
     * @output It is used to specify the classes and files needed to run the
     *         test. Example: The same input integer.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void test00() throws Exception {
        // Output value, it must be the same as the input.
        Integer output = bean.getValue(INPUT);

        // Test if input and output are equal.
        assertEquals(INPUT, output, "The input and output values should be equal.");
    }
}
