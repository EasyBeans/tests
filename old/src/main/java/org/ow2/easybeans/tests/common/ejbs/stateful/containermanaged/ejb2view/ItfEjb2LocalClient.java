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
 * $Id: ItfEjb2LocalClient.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

/**
 * Is a client with ejb 2.1 view.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfEjb2LocalClient {

    /**
     * The default bean code.
     */
    int DEFAULT_CODE = 1;

    /**
     * The default bean name.
     */
    String DEFAULT_NAME = "test";

    /**
     * Returns the bean home object.
     * @return the home object.
     */
    SimpleEjb2LocalHome getBeanHome();

    /**
     * Returns the bean JNDI address.
     * @return the JNDI address.
     */
    String getBeanName();

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method ejbCreate() without the annotation init.
     * @throws CreateException if an error occurs during the creation.
     */
    void createWithIntParameter() throws CreateException;

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method init() with the annotation init.
     * @throws CreateException if an error occurs during the creation.
     */
    void createWithStrParameter() throws CreateException;

    /**
     * Gets an instance of the home object by a lookup.After that, creates an
     * instance of the bean.
     * @throws NamingException if an error during the lookup occurs.
     * @throws CreateException if an error occurs during the creation.
     */
    void getBeanByLookup() throws NamingException, CreateException;

    /**
     * Creates a bean and after that, removes it.
     * @throws RemoveException if the bean can not be deleted.
     * @throws CreateException if an error occurs during the creation.
     */
    void removeObject() throws RemoveException, CreateException;

    /**
     * Verifies if the identity for stateful bean is correct. An instance of
     * stateful bean can not be considered identical to other instance.
     * @throws CreateException if an error occurs during the creation.
     */
    void verifyIdentity() throws CreateException;

    /**
     * Verifies if the exception correct is thrown when the method
     * getPrimaryKey() is called.
     * @throws CreateException if a problem during the creation occurs.
     */
    void verifyGetPrimaryKey() throws CreateException;

}
