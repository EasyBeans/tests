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
 * $Id: TestCMTExceptions.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.ejb2view;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejb2view.ItfCMTEjb2ViewClient;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejb2view.SLSBCMTEjb2ViewClient;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies the compatibility between the ejb 2.1 and the ejb 3.0. More
 * specifically, it verifies if the exceptions thrown when methods with
 * different transaction attributes are called incorrectly. The items are 13.6.2.5 and 13.6.2.6.
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the beans
 *              SFSBCMTEjb2ViewClient and CMTEJb2ViewBean must be deployed.
 * @setup gets a reference of the bean SFSBCMTEjb2ViewClient.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestCMTExceptions {

    /**
     * Bean that access the bean with ejb 2.1 remote view.
     */
    private ItfCMTEjb2ViewClient bean;

    /**
     * Gets an instance of the bean.
     * @throws Exception if an error occurs.
     */
    @BeforeMethod
    public void setup() throws Exception {
        // Gets a bean instance.
        bean = EJBHelper.getBeanRemoteInstance(SLSBCMTEjb2ViewClient.class, ItfCMTEjb2ViewClient.class);
    }

    /**
     * Verifies if the client with a ejb 2.1 local view receives the correct
     * exception when a method with Transaction Attribute Mandatory is called
     * without transaction context. The expected exception is
     * TransactionRequiredLocalException.
     * @input -
     * @output the correct method execution.
     * @throws Exception if some error occurs during the test.
     */
    @Test
    public void verifyMandatoryLocalException() throws Exception{
        bean.testLocalMandatoryException();
    }

    /**
     * Verifies if the client with a ejb 2.1 remote view receives the correct
     * exception when a method with Transaction Attribute Mandatory is called
     * without transaction context. The expected exception is
     * TransactionRequiredException.
     * @input -
     * @output the correct method execution.
     * @throws Exception if some error occurs during the test.
     */
    @Test
    public void verifyMandatoryRemoteException() throws Exception{
        bean.testRemoteMandatoryException();
    }

    /**
     * Verifies if the client with a ejb 2.1 local view receives the correct
     * exception when a method with Transaction Attribute Never is called
     * with transaction context. The expected exception is
     * EJBException.
     * @input -
     * @output the correct method execution.
     * @throws Exception if some error occurs during the test.
     */
    @Test
    public void verifyNeverLocalException() throws Exception{
        bean.testLocalNeverException();
    }

    /**
     * Verifies if the client with a ejb 2.1 never view receives the correct
     * exception when a method with Transaction Attribute Never is called
     * with transaction context. The expected exception is
     * RemoteException.
     * @input -
     * @output the correct method execution.
     * @throws Exception if some error occurs during the test.
     */
    @Test
    public void verifyNeverRemoteException() throws Exception{
        bean.testRemoteNeverException();
    }

    /**
     * Verifies if the method isIdentical works.
     * @input -
     * @output the correct method execution.
     * @throws Exception if some error occurs during the test.
     */
    @Test
    public void testIdentity() throws Exception{
        bean.testIdentity();
    }
}
