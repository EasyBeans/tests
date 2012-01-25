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
 * $Id: EBaseLocalInheritanceTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.inheritance;

import static org.ow2.easybeans.tests.common.asserts.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfLocalInheritanceTester00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBInheritance00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBInheritance01;
import org.ow2.easybeans.tests.common.inheritance.ItfAddElement;
import org.testng.annotations.Test;

/**
 * Verifies if the bean inheritance is following the JSR 220 spec.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class EBaseLocalInheritanceTester00 implements ItfLocalInheritanceTester00 {

    /**
     * Used to configure the environment used by the tests.
     * @throws Exception if a problem occurs.
     */
    public void startUp() throws Exception{
    }

    /**
     * Bean used to implement the test.
     */
    private ItfAddElement mtBean00;

    /**
     * Bean used to implement the test.
     */
    private ItfAddElement mtBean01;

    /**
     * Verifies if the bean business method is running correctly. The bean
     * business method is implemented by a extended class that implements an
     * interface. The bean doesn't have any &#64;Local or &#64;Remote.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @SuppressWarnings("unchecked")
    @Test(groups = {"withInheritance"})
    public void test00() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of method
        // there are not interceptors in this class, so only
        // the method will be executed
        arExpected.add(SLSBInheritance00.ELEMENT);

        // gets the result
        arResult = mtBean00.addElement(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The business method is not running correctly.");
    }

    /**
     * Verifies if the bean business method is running correctly. The bean
     * business method is implemented by a extended class that implements an
     * interface. The bean has a &#64;Local as unique interface implemented.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @SuppressWarnings("unchecked")
    @Test(groups = {"withInheritance"})
    public void test01() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of method
        // there are not interceptors in this class, so only
        // the method will be executed
        arExpected.add(SLSBInheritance01.ELEMENT);

        // gets the result
        arResult = mtBean01.addElement(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The business method is not running correctly.");
    }


    /**
     * Sets bean(s) used in the tests.
     * @param bean00 The bean to set.
     * @param bean01 The bean to set.
     */
    public void setBeans(final ItfAddElement bean00, final ItfAddElement bean01) {
        this.mtBean00 = bean00;
        this.mtBean01 = bean01;
    }
}
