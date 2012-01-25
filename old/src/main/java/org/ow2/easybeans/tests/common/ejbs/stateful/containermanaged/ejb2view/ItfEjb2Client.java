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
 * $Id: ItfEjb2Client.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

/**
 * Has the 2.1 client view of the ejb.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfEjb2Client {

    /**
     * The bean code.
     */
     int DEFAULT_CODE = 2;

    /**
     * The bean name.
     */
     String DEFAULT_NAME = "testClient";

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method ejbCreate() without the annotation init.
     * @throws CreateException if an error occurs during the creation.
     * @throws RemoteException if system level error occurs.
     */
    void createWithoutParameters() throws CreateException, RemoteException;

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method init() witho the annotation init.
     * @throws CreateException if an error occurs during the creation.
     * @throws RemoteException if system level error occurs.
     */
    void createWithParameters() throws CreateException, RemoteException;

    /**
     * Gets an instance of the home object by a lookup.After that, creates an
     * instance of the bean.
     * @throws NamingException if an error during the lookup occurs.
     * @throws CreateException if an error occurs during the creation.
     * @throws RemoteException if system level error occurs.
     */
    void getBeanByLookup() throws NamingException, CreateException, RemoteException;

    /**
     * Creates a bean and after that, removes it.
     * @throws RemoveException if the bean can not be deleted.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if an error occurs during the creation.
     */
    void removeObject() throws RemoteException, RemoveException, CreateException;

    /**
     * Tries to get the ejbMetaData and uses the method isStatelessSession in
     * the metadata.
     * @throws RemoteException if a system level error occurs.
     */
    void getEJBMetaData() throws RemoteException;

    /**
     * Verifies if the identity for stateful bean is correct. An instance of
     * stateful bean can not be considered identical to other instance.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if an error occurs during the creation.
     */
    void verifyIdentity() throws CreateException, RemoteException;

    /**
     * Verifies if the exception correct is thrown when the method
     * getPrimaryKey() is called.
     * @throws CreateException if a problem during the creation occurs.
     * @throws RemoteException if a system level error occurs.
     */
     void verifyGetPrimaryKey() throws RemoteException, CreateException;
}
