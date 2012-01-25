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
 * $Id: SimpleEjb2Home.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * The bean home interface.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface SimpleEjb2Home extends EJBHome {

    /**
     * Creates an instance of the bean with the default values.
     * @return the bean.
     * @throws CreateException if an error during teh creation occurs.
     * @throws RemoteException if a system level error occurs.
     */
    SimpleEjb2 create() throws CreateException, RemoteException;

    /**
     * Creates an instance of the bean with the valus in the parameters.
     * @param code the bean code.
     * @param name the bean name.
     * @return the bean.
     * @throws CreateException if an error during teh creation occurs.
     * @throws RemoteException if a system level error occurs.
     */
    SimpleEjb2 create(final int code, final String name) throws CreateException, RemoteException;
}
