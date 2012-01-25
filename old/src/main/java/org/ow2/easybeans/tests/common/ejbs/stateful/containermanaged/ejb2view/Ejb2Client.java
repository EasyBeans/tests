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
 * $Id: Ejb2Client.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBMetaData;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Client that has a 2.1 view of the ejb.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class Ejb2Client implements ItfEjb2Client {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(Ejb2Client.class);

    /**
     * Returns the bean home object.
     * @return the home object.
     */
    public abstract SimpleEjb2Home getBeanHome();

    /**
     * Returns the bean JNDI address.
     * @return the JNDI address.
     */
    public abstract String getBeanName();

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method ejbCreate() without the annotation init.
     * @throws CreateException if an error occurs during the creation.
     * @throws RemoteException if system level error occurs.
     */
    public void createWithoutParameters() throws CreateException, RemoteException {
        SimpleEjb2Home beanHome = getBeanHome();
        SimpleEjb2 bean = beanHome.create();
        assertEquals(bean.getCode(), SimpleEjb2.DEFAULT_CODE_REMOTE, "The bean was not created with the correct value.");

    }

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method init() witho the annotation init.
     * @throws CreateException if an error occurs during the creation.
     * @throws RemoteException if system level error occurs.
     */
    public void createWithParameters() throws CreateException, RemoteException {
        SimpleEjb2Home beanHome = getBeanHome();
        SimpleEjb2 bean = beanHome.create(DEFAULT_CODE, DEFAULT_NAME);
        assertEquals(bean.getCode(), DEFAULT_CODE, "The bean was not created with the correct value.");
    }

    /**
     * Gets an instance of the home object by a lookup.After that, creates an
     * instance of the bean.
     * @throws NamingException if an error during the lookup occurs.
     * @throws CreateException if an error occurs during the creation.
     * @throws RemoteException if system level error occurs.
     */
    public void getBeanByLookup() throws NamingException, CreateException, RemoteException {
        // Obtain the default initial JNDI context.
        Context initCtx = new InitialContext();

        Object result = initCtx.lookup(getBeanName());

        SimpleEjb2Home beanHomeLookup = (SimpleEjb2Home) javax.rmi.PortableRemoteObject.narrow(result,
                SimpleEjb2Home.class);

        SimpleEjb2 beanLookup = beanHomeLookup.create(DEFAULT_CODE, DEFAULT_NAME);
        assertEquals(beanLookup.getCode(), DEFAULT_CODE, "The bean was not created with the correct value.");

    }

    /**
     * Creates a bean and after that, removes it.
     * @throws RemoveException if the bean can not be deleted.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if an error occurs during the creation.
     */
    public void removeObject() throws RemoteException, RemoveException, CreateException {
        SimpleEjb2Home beanHome = getBeanHome();
        SimpleEjb2 bean = beanHome.create();
        beanHome.remove(bean.getHandle());

        try {
            bean.toString();
            fail("The bean was not discarded");
        } catch (Exception e) {
            logger.debug("The bean threw an expected exception :{0}", e);
        }
    }

    /**
     * Tries to get the ejbMetaData and uses the method isStatelessSession in
     * the metadata.
     * @throws RemoteException if a system level error occurs.
     */
    public void getEJBMetaData() throws RemoteException {
        SimpleEjb2Home beanHome = getBeanHome();
        EJBMetaData data = beanHome.getEJBMetaData();

        assertFalse(data.isStatelessSession(),
                "The metadata interface is doing an incorrect value for the isStatelessSession method.");
    }

    /**
     * Verifies if the identity for stateful bean is correct. An instance of
     * stateful bean can not be considered identical to other instance.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if an error occurs during the creation.
     */
    public void verifyIdentity() throws CreateException, RemoteException {
        SimpleEjb2Home beanHome = getBeanHome();
        SimpleEjb2 bean1 = beanHome.create();
        SimpleEjb2 bean2 = beanHome.create();

        assertTrue(bean1.isIdentical(bean1), "The bean is not considered identical to itself.");
        assertFalse(bean1.isIdentical(bean2), "The two different beans are considered identical.");
    }

    /**
     * Verifies if the exception correct is thrown when the method
     * getPrimaryKey() is called.
     * @throws CreateException if a problem during the creation occurs.
     * @throws RemoteException if a system level error occurs.
     */
    public void verifyGetPrimaryKey() throws RemoteException, CreateException{
        SimpleEjb2Home beanHome = getBeanHome();
        SimpleEjb2 bean1 = beanHome.create();
        try{
            bean1.getPrimaryKey();
            fail("The client cannot call the getPrimaryKey method.");
        }catch (RemoteException e){
            logger.debug("The bean threw an expected exception :{0}", e);
        }
    }
}
