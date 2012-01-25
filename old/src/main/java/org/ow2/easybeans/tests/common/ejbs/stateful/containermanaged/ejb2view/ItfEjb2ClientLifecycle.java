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
 * $Id: ItfEjb2ClientLifecycle.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;

/**
 * Verifies if the lifecycle are called.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfEjb2ClientLifecycle {

    /**
     * Verifies if the method ejbPassivate is called.
     */
    void verifyPassivate();

    /**
     * Verifies if the method ejbActivate is called.
     */
    void verifyActivate();

    /**
     * Verifies if the method ejbRemove is called.
     * @throws RemoveException if an error during the remove occurs.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if an error during the creation occurs.
     */
    void verifyRemove() throws RemoteException, CreateException, RemoveException;

}
