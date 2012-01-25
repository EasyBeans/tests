/**
 * EasyBeans
 * Copyright (C) 2007 Bull S.A.S.
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
 * $Id: TestSecurity.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import javax.ejb.EJBAccessException;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.securityxml.ItfSecurityXML;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.securityxml.SLSBSecurityXMLBean;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can deploy an bean with the security attributes
 * defined in the deployment description. Item 17.
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SLSBSecurityXMLBean and the SLSBSecurityXMLRunAsBean must be
 *              deployed to make the tests, and, the deployment descriptor must
 *              be used too.
 * @setup gets an instance of the SLSBSecurityXMLBean.
 * @author Florent Benoit
 */
public class TestSecurity {

    /**
     * Bean used to verify the local access.
     */
    private ItfSecurityXML tester;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SLSBSecurityXMLBean.class, ItfSecurityXML.class);
    }

    /**
     * Verifies if the client can access to a method that everybody can call
     * descriptor is read by the container.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testAllRolesAllowed() throws Exception {
        tester.allRolesAllowed();
    }

    /**
     * Verifies if the client is denied when accessing a method that nobody can call
     * descriptor is read by the container.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test(expectedExceptions={EJBAccessException.class})
    public void testCallDeniedForCall() throws Exception {
        tester.deniedForAll();
    }


    /**
     * Verifies if the client is using the run-as identity when calling it
     * descriptor is read by the container.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testCallWithRunAs() throws Exception {
        tester.callRunAsBean();
    }

}
