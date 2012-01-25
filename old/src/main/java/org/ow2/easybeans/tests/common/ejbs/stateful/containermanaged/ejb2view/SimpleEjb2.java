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
 * $Id: SimpleEjb2.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * The remote interface of a bean that provides a 2.1 view.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface SimpleEjb2 extends EJBObject {

    /**
     * The default bean code.
     */
    int DEFAULT_CODE_REMOTE = 1;

    /**
     * The default bean name.
     */
    String DEFAULT_NAME_REMOTE = "test";

    /**
     * Returns the message in the parameter.
     * @param message the message.
     * @return the message.
     * @throws RemoteException if a system level error occurs.
     */
    String sayHello(final String message) throws RemoteException;

    /**
     * Gets the bean code.
     * @return the code.
     * @throws RemoteException if a system level error occurs.
     */
    int getCode() throws RemoteException;

    /**
     * Sets the bean code.
     * @param intCode the code.
     * @throws RemoteException if a system level error occurs.
     */
    void setCode(int intCode) throws RemoteException;

    /**
     * Gets the bean name.
     * @return the bean name.
     * @throws RemoteException if a system level error occurs.
     */
    String getName() throws RemoteException;

    /**
     * Sets the bean name.
     * @param strName the bean name.
     * @throws RemoteException if a system level error occurs.
     */
    void setName(String strName) throws RemoteException;
}
