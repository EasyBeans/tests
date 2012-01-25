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
 * $Id: TestSLBeanLocalInheritance00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.inheritance;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfLocalInheritanceTester00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBLocalInheritanceTester00;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if the bean inheritance is following the JSR 220 spec.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSB*Inheritance*
 *              must be deployed.
 * @setup gets the reference of SLSBLocalInheritanceTester00
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLBeanLocalInheritance00 {

    /**
     * Bean used to implement the test.
     */
    private ItfLocalInheritanceTester00 beanTester;

    /**
     * Gets bean instance used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void startUp() throws Exception {
        beanTester = getBeanRemoteInstance(SLSBLocalInheritanceTester00.class,
                ItfLocalInheritanceTester00.class);

        beanTester.startUp();
    }

    /**
     * Verifies if the bean business method is running correctly. The bean
     * business method is implemented by a extended class that implements an
     * interface. The bean doesn't have any &#64;Local or &#64;Remote.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @Test(groups = {"withInheritance"})
    public void test00() {
        beanTester.test00();
    }

    /**
     * Verifies if the bean business method is running correctly. The bean
     * business method is implemented by a extended class that implements an
     * interface. The bean has a &#64;Local is the only interface implemented.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @Test(groups = {"withInheritance"})
    public void test01() {
        beanTester.test01();
    }

}
