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
 * $Id: TestEjb2DeployDescRemote.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.ejb2view;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view.ItfEjb2Client;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view.SFSBEjb2ClientDeployDesc;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies the compatibility between the ejb 2.1 and the ejb 3.0. More
 * specifically, it verifies if the access to the remote home works properly.
 * All interfaces are defined by deployemnt descriptor.
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the beans SFSBEjb2ClientDeployDesc
 *              and SimpleEjb2BeanBase with the xml file (that is in the same package where the class is) must be deployed.
 * @setup gets a reference of the bean SFSBEjb2ClientDeployDesc.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEjb2DeployDescRemote {

    /**
     * Bean that access the bean with ejb 2.1 remote view.
     */
    private ItfEjb2Client bean;

    /**
     * Gets an instance of the bean.
     * @throws Exception if an error occurs.
     */
    @BeforeMethod
    public void setup() throws Exception {
        // Gets a bean instance.
        bean = EJBHelper.getBeanRemoteInstance(SFSBEjb2ClientDeployDesc.class, ItfEjb2Client.class);
    }

    /**
     * Tests if the definition of the create method as ejbCreate in the bean
     * class works. The interface home here is goten by the annotation ejb.
     * @input -
     * @output the correct method execution.
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testCreateWithoutParameters() throws Exception {
        bean.createWithoutParameters();
    }

    /**
     * Tests if the definition of the create method with the annotation init in
     * the bean class works. The interface home here is goten by the annotation
     * ejb.
     * @input -
     * @output the correct method execution.
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testCreateWithParameters() throws Exception {
        bean.createWithParameters();
    }

    /**
     * Verifies if theclient can get a bean by lookup.
     * @input -
     * @output the correct method execution.
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testGetBeanByLookup() throws Exception {
        bean.getBeanByLookup();
    }

    /**
     * Verifies if the method isIdentical that is defined in the EJBObject
     * interface.
     * @input -
     * @output the correct method execution.
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testIsIdentical() throws Exception {
        bean.verifyIdentity();
    }

    /**
     * Verifies if the method remove from the interface EJBHome works.
     * @input -
     * @output the correct method execution.
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testRemove() throws Exception {
        bean.removeObject();
    }

    /**
     * Verifies if the method getMetaData from the interface EJBHome works.
     * @input -
     * @output the correct method execution.
     * @throws Exception If an error occurs during the test.
     */
    @Test
    public void testMetadata() throws Exception {
        bean.getEJBMetaData();
    }
}
