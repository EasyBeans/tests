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
 * $Id: Ejb2LocalClient.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Is a bean with ejb 2.1 client local view.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public abstract class Ejb2LocalClient implements ItfEjb2LocalClient {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(Ejb2Client.class);

    /**
     * Returns the bean home object.
     * @return the home object.
     */
    public abstract SimpleEjb2LocalHome getBeanHome();

    /**
     * Returns the bean JNDI address.
     * @return the JNDI address.
     */
    public abstract String getBeanName();

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method ejbCreate() without the annotation init.
     * @throws CreateException if an error occurs during the creation.
     */
    public void createWithIntParameter() throws CreateException{
        SimpleEjb2LocalHome beanHome = getBeanHome();
        SimpleEjb2Local bean = beanHome.create(DEFAULT_CODE);
        assertEquals(bean.getNameLocal(), SimpleEjb2Local.DEFAULT_NAME_LOCAL, "The bean was not created with the correct value.");

    }

    /**
     * Creates a bean using the home object that was injected. The bean is
     * created with the method init() with the annotation init.
     * @throws CreateException if an error occurs during the creation.
      */
    public void createWithStrParameter() throws CreateException{
        SimpleEjb2LocalHome beanHome = getBeanHome();
        SimpleEjb2Local bean = beanHome.create(DEFAULT_NAME);
        assertEquals(bean.getCodeLocal(), DEFAULT_CODE, "The bean was not created with the correct value.");
    }

    /**
     * Gets an instance of the home object by a lookup.After that, creates an
     * instance of the bean.
     * @throws NamingException if an error during the lookup occurs.
     * @throws CreateException if an error occurs during the creation.
     */
    public void getBeanByLookup() throws NamingException, CreateException {
        // Obtain the default initial JNDI context.
        Context initCtx = new InitialContext();

        Object result = initCtx.lookup(getBeanName());

        SimpleEjb2LocalHome beanHomeLookup = (SimpleEjb2LocalHome) javax.rmi.PortableRemoteObject.narrow(result,
                SimpleEjb2LocalHome.class);

        SimpleEjb2Local beanLookup = beanHomeLookup.create(DEFAULT_NAME);
        assertEquals(beanLookup.getCodeLocal(), DEFAULT_CODE, "The bean was not created with the correct value.");

    }

    /**
     * Creates a bean and after that, removes it.
     * @throws RemoveException if the bean can not be deleted.
     * @throws CreateException if an error occurs during the creation.
     */
    public void removeObject() throws RemoveException, CreateException {
        SimpleEjb2LocalHome beanHome = getBeanHome();
        SimpleEjb2Local bean = beanHome.create(DEFAULT_CODE);
        bean.remove();

        try {
            bean.toString();
            fail("The bean was not discarded");
        } catch (Exception e) {
            logger.debug("The bean threw an expected exception :{0}", e);
        }
    }

    /**
     * Verifies if the identity for stateful bean is correct. An instance of
     * stateful bean can not be considered identical to other instance.
     * @throws CreateException if an error occurs during the creation.
     */
    public void verifyIdentity() throws CreateException{
        SimpleEjb2LocalHome beanHome = getBeanHome();
        SimpleEjb2Local bean1 = beanHome.create(DEFAULT_NAME);
        SimpleEjb2Local bean2 = beanHome.create(DEFAULT_NAME);

        assertTrue(bean1.isIdentical(bean1), "The bean is not considered identical to itself.");
        assertFalse(bean1.isIdentical(bean2), "The two different beans are considered identical.");
    }

    /**
     * Verifies if the exception correct is thrown when the method
     * getPrimaryKey() is called.
     * @throws CreateException if a problem during the creation occurs.
     */
    public void verifyGetPrimaryKey() throws CreateException{
        SimpleEjb2LocalHome beanHome = getBeanHome();
        SimpleEjb2Local bean1 = beanHome.create(DEFAULT_NAME);
        try{
            bean1.getPrimaryKey();
            fail("The client cannot call the getPrimaryKey method.");
        }catch(EJBException e){
            logger.debug("The bean threw an expected exception :{0}", e);
        }
    }
}
