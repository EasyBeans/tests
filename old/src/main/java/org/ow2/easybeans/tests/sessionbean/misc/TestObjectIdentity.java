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
 * $Id: TestObjectIdentity.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.sessionbean.misc;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic.ItfSessionObjectIdentity;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic.SLSBSessionObjectIdentity;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies the method equals in a seesion beans works as the specified.
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the beans
 *              SLSBSessionObjectIdentity, SLSBDeployTest and SFSBDeployTest
 *              must be deployed.
 * @setup gets a reference of the bean SLSBSessionObjectIdentity.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestObjectIdentity {

    /**
     * Bean used during the test.
     */
    private ItfSessionObjectIdentity bean;

    /**
     * Gets an instance of the bean.
     * @throws Exception if an error occurs.
     */
    @BeforeMethod
    public void setup() throws Exception {
        // Gets a bean instance.
        bean = EJBHelper.getBeanRemoteInstance(SLSBSessionObjectIdentity.class, ItfSessionObjectIdentity.class);
    }

    /**
     * Verifies the identity of the same stateful bean(example:
     * bean1.equals(bean1)). The bean was goten by injection.
     * @input a bean injected
     * @output the correct method execution, the result of the comparison must
     *         be true
     */
    @Test
    public void testSFSameInstanceInjected() {
        bean.testSFSameInstanceIdentityInjected();
    }

    /**
     * Verifies the identity of the same stateful bean(example:
     * bean1.equals(bean1)). The bean was goten by lookup.
     * @input a bean injected
     * @output the correct method execution, the result of the comparison must
     *         be true
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testSFSameInstanceLookup() throws Exception {
        bean.testSFSameInstanceIdentityLookup();
    }

    /**
     * Verifies the identity of the same stateless bean(example:
     * bean1.equals(bean1)). The bean was goten by injection.
     * @input a bean injected
     * @output the correct method execution, the result of the comparison must
     *         be true
     */
    @Test
    public void testSLSameInstanceInjected() {
        bean.testSLSameInstanceIdentityInjected();
    }

    /**
     * Verifies the identity of the same stateless bean(example:
     * bean1.equals(bean1)). The bean was goten by lookup.
     * @input a bean injected
     * @output the correct method execution, the result of the comparison must
     *         be true
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testSLSameInstanceLookup() throws Exception {
        bean.testSLSameInstanceIdentityLookup();
    }

    /**
     * Verifies the identity of two different stateful beans(example:
     * bean1.equals(bean2)). The beans were goten by injection.
     * @input the beans injected
     * @output the correct method execution, the result of the comparison must
     *         be false
     */
    @Test
    public void testSFDifferentInstanceInjected() {
        bean.testSFDifferentInstanceIdentityInjected();
    }

    /**
     * Verifies the identity of two different stateful beans(example:
     * bean1.equals(bean2)). The beans were goten by lookup.
     * @input the beans injected
     * @output the correct method execution, the result of the comparison must
     *         be false
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testSFDifferentInstanceLookup() throws Exception {
        bean.testSFDifferentInstanceIdentityLookup();
    }

    /**
     * Verifies the identity of two different stateless beans(example:
     * bean1.equals(bean2)). The beans were goten by injection.
     * @input the beans injected
     * @output the correct method execution, the result of the comparison must
     *         be true
     */
    @Test
    public void testSLDifferentInstanceInjected() {
        bean.testSLDifferentInstanceIdentityInjected();
    }

    /**
     * Verifies the identity of two different stateless beans(example:
     * bean1.equals(bean2)). The beans were goten by lookup.
     * @input the beans injected
     * @output the correct method execution, the result of the comparison must
     *         be true
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testSLDifferentInstanceLookup() throws Exception {
        bean.testSLDifferentInstanceIdentityLookup();
    }
}
