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
 * $Id: TestBasicElements.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import org.ow2.easybeans.tests.common.ejbs.base.xmldescriptor.ItfBasicBeanRemote;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.ItfXmlDescriptorTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.SFSBXmlDescriptorTester00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.SFSBXmlDescriptorTester01;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can deploy an bean with the parameters defined in
 * the deployment descriptor. The elements that are tested are the mappedName,
 * ejb-name, local, remote and session type.
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SFSBXmlDescriptorTester must be deployed to make the
 *              tests, and, the BasicBean with the different deployment
 *              descriptors must be deployed too.
 * @setup gets an instance of the SFSBXmlDescriptorTester.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestBasicElements {

    /**
     * Bean used to verify the local access.
     */
    private ItfXmlDescriptorTester sfTester;

    /**
     * Bean used to verify the local access.
     */
    private ItfXmlDescriptorTester slTester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        slTester = EJBHelper.getBeanRemoteInstance(SFSBXmlDescriptorTester00.class, ItfXmlDescriptorTester.class);
        sfTester = EJBHelper.getBeanRemoteInstance(SFSBXmlDescriptorTester01.class, ItfXmlDescriptorTester.class);
    }

    /**
     * Verifies if the bean can get an instance of a stateless bean by the
     * mappedName.This test verifies the local interface.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void verifySLMappedName() throws Exception {
        ItfBasicBeanRemote bean = EJBHelper.getBeanByMappedName("SLSBBasicBeanByXMLMappedRemote");
        bean.toString();
    }

    /**
     * Verifies if the bean can get an instance of a stateful bean by the
     * mappedName.This test verifies the remote interface.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void verifySFMappedName() throws Exception {
        ItfBasicBeanRemote bean = EJBHelper.getBeanByMappedName("SFSBBasicBeanByXMLMappedRemote");
        bean.toString();
    }

    /**
     * Verifies if the bean defined as stateless is deployed as a stateless. The
     * bean class begins an userTransaction, but does not make the commit. So,
     * if the bean is stateless the container must throw an EJBException.
     * @input -
     * @output an EJBException.
     * @throws Exception if an error occurs.
     */
    @Test(dependsOnMethods = {"verifySLMappedName"}, expectedExceptions = javax.ejb.EJBException.class)
    public void verifySLBeanTypeElement() throws Exception {
        ItfBasicBeanRemote bean = EJBHelper.getBeanByMappedName("SLSBBasicBeanByXMLMappedRemote");
        bean.openTransaction();
    }

    /**
     * Verifies if the bean defined as stateful is deployed as a stateless. The
     * bean class begins an userTransaction, but does not make the commit. So,
     * if the bean is stateful the container must NOT throw any exception.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test(dependsOnMethods = {"verifySFMappedName"})
    public void verifySFBeanTypeElement() throws Exception {
        ItfBasicBeanRemote bean = EJBHelper.getBeanByMappedName("SFSBBasicBeanByXMLMappedRemote");
        bean.openTransaction();
    }

    /**
     * Verifies if a bean can make a lookup in other bean(stateless) by the
     * mappedName. This test verifies the local interface.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void verifySLMappedNameLocal() throws Exception {
        slTester.verifyBeanMappedName();
    }

    /**
     * Verifies if a bean can make a lookup in other bean(stateful) by the
     * mappedName. This test verifies the local interface.
     * @throws Exception
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void verifySFMappedNameLocal() throws Exception {
        sfTester.verifyBeanMappedName();
    }

    /**
     * Verifies if the stateless bean is injected by the ejb-name.
     * @input -
     * @output an EJBException because the stateless bean begins a transaction
     *         without making a commit.
     * @throws Exception if an error occurs.
     */
    @Test(expectedExceptions = javax.ejb.EJBException.class)
    public void verifySLInjectionByEjbName() throws Exception{
        slTester.verifyBeanTypeElement();
    }

    /**
     * Verifies if the stateful bean is injected by the ejb-name.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    public void verifySFInjectionByEjbName() throws Exception{
        sfTester.verifyBeanTypeElement();
    }

}
