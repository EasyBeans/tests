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
 * $Id: SLSBCMTEjb2ViewClient.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejb2view;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertTrue;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionRequiredLocalException;
import javax.transaction.TransactionRequiredException;

/**
 * Verifies if a client with ejb 2.1 view receives the correctly the exceptions when
 * call the different types of transaction attribute.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfCMTEjb2ViewClient.class)
public class SLSBCMTEjb2ViewClient implements ItfCMTEjb2ViewClient {

    /**
     * Instance of the ejb home.
     */
    @EJB
    private CMTEjb2ViewHome beanHome;

    /**
     * Instance of ejb local home.
     */
    @EJB
    private CMTEjb2ViewLocalHome beanLocalHome;

    /**
     * Verifies if a remote client receives the correct exception when call a
     * method with the transaction attribute Mandatory without transaction context.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if a creation error occurs.
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void testRemoteMandatoryException() throws RemoteException, CreateException {
        CMTEjb2View bean = beanHome.create();
        try {
            bean.attributeMandatory();
            fail("The container did not throw an exception after a mandatory method had been "
                    + "called without a transaction context.");
        } catch (Exception e) {
            if (!(e instanceof TransactionRequiredException)) {
                fail("The bean has 2.1 remote client view and did not receive the correct exception after a mandatory "
                        + "method had been called without a transaction context. The excepted exception expected was "
                        + "TransactionRequiredException, but the exception received was " + e.getClass().getName());
            }
        }
    }

    /**
     * Verifies if a local client receives the correct exception when call a
     * method with the transaction attribute Mandatory without transaction context.
     * @throws CreateException if a creation error occurs.
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void testLocalMandatoryException() throws CreateException {
        CMTEjb2LocalView bean = beanLocalHome.create();
        try {
            bean.attributeMandatoryLocal();
            fail("The container did not throw an exception after a mandatory method had been called "
                    + "without a transaction context.");
        } catch (Exception e) {
            if (!(e instanceof TransactionRequiredLocalException)) {
                fail("The bean has 2.1 local client view and did not receive the correct exception after a mandatory "
                        + "method had been called without a transaction context. The excepted exception expected was "
                        + "TransactionRequiredLocalException, but the exception received was " + e.getClass().getName());
            }
        }
    }

    /**
     * Verifies if a remote client receives the correct exception when call a
     * method with the transaction attribute Never with a transaction context.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if a creation error occurs.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void testRemoteNeverException() throws RemoteException, CreateException {
        CMTEjb2View bean = beanHome.create();
        try {
            bean.attributeNever();
            fail("The container did not throw an exception after a never method had been called "
                    + "with a transaction context.");
        } catch (Exception e) {
            if (!(e instanceof RemoteException)) {
                fail("The bean has 2.1 remote client view and did not receive the correct exception after "
                        + "a never method had been called with a transaction context. The excepted exception expected "
                        + "was RemoteException, but the exception received was " + e.getClass().getName());
            }
        }
    }

    /**
     * Verifies if a local client receives the correct exception when call a
     * method with the transaction attribute Never with a transaction context.
     * @throws CreateException if a creation error occurs.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void testLocalNeverException() throws CreateException {
        CMTEjb2LocalView bean = beanLocalHome.create();
        try {
            bean.attributeNeverLocal();
            fail("The container did not throw an exception after a never method had been called "
                    + "with a transaction context.");
        } catch (Exception e) {
            if (!(e instanceof EJBException)) {
                fail("The bean has 2.1 local client view and did not receive the correct exception after a never method "
                        + "had been called with a transaction context. The excepted exception expected was EJBException, "
                        + "but the exception received was " + e.getClass().getName());
            }
        }
    }

    /**
     * Verifies the identity for stateless bean.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if a creation error occurs.
     */
    public void testIdentity() throws RemoteException, CreateException {
        CMTEjb2View bean1 = beanHome.create();
        CMTEjb2View bean2 = beanHome.create();

        assertTrue(bean1.isIdentical(bean1), "The isIdentical method is not working for stateless beans");
        assertTrue(bean1.isIdentical(bean2), "The isIdentical method is not working for stateless beans");
    }
}
