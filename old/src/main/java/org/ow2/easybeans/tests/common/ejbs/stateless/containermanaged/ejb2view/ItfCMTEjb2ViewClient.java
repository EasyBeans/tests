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
 * $Id: ItfCMTEjb2ViewClient.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Verifies if a client with ejb 2.1 view receives the correctly the exceptions when
 * call the different types of transaction attribute.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfCMTEjb2ViewClient {

    /**
     * Verifies if a remote client receives the correct exception when call a
     * method with the transaction attribute Mandatory without transaction context.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if a creation error occurs.
     */
     void testRemoteMandatoryException() throws RemoteException, CreateException;

    /**
     * Verifies if a local client receives the correct exception when call a
     * method with the transaction attribute Mandatory without transaction context.
     * @throws CreateException if a creation error occurs.
     */
    void testLocalMandatoryException() throws CreateException;

    /**
     * Verifies if a remote client receives the correct exception when call a
     * method with the transaction attribute Never with a transaction context.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if a creation error occurs.
     */
    void testRemoteNeverException() throws RemoteException, CreateException;

    /**
     * Verifies if a local client receives the correct exception when call a
     * method with the transaction attribute Never with a transaction context.
     * @throws CreateException if a creation error occurs.
     */
    void testLocalNeverException() throws CreateException;

    /**
     * Verifies the identity for stateless bean.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if a creation error occurs.
     */
    void testIdentity() throws RemoteException, CreateException;


}
