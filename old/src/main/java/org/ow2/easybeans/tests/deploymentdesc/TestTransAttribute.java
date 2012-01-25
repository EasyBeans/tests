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
 * $Id: TestTransAttribute.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.ItfTransAttributeTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.SFSBTransAttributeTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can deploy an bean with the transaction attributes
 * defined in the deployment description. Item 13.3.7
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SFSBCTransAttributeTester, the SLSBDatabaseManager and the SFSBCTransAttributeDefinition must be
 *              deployed to make the tests, and, the deployment descriptor must
 *              be used too.
 * @setup gets an instance of the SFSBCTransAttributeTester.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestTransAttribute {

    /**
     * Bean used to verify the local access.
     */
    private ItfTransAttributeTester tester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SFSBTransAttributeTester.class, ItfTransAttributeTester.class);
    }

    /**
     * Verifies if the transaction attribute mandatory defined in the deployment
     * descriptor is read by the container.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testMandatory() throws Exception {
        tester.testMandatory();
    }

    /**
     * Verifies if the transaction attribute required defined in the deployment
     * descriptor is read by the container. *
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testRequired() throws Exception {
        tester.testRequired();
    }

    /**
     * Verifies if the transaction attribute requires new defined in the
     * deployment descriptor is read by the container. *
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testRequiresNew() throws Exception {
        tester.testRequiresNew();
    }

    /**
     * Verifies if the transaction attribute supports defined in the deployment
     * descriptor is read by the container. *
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testSupports() throws Exception {
        tester.testSupports();
    }

    /**
     * Verifies if the transaction attribute not supported defined in the
     * deployment descriptor is read by the container. *
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testNotSupported() throws Exception {
        tester.testNotSupported();
    }

    /**
     * Verifies if the transaction attribute never defined in the deployment
     * descriptor is read by the container. *
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testNever() throws Exception {
        tester.testNever();
    }
}
