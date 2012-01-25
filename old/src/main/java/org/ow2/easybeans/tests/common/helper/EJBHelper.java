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
 * $Id: EJBHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * This helper is used to do ejb operations.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public final class EJBHelper {

    /**
     * Helper should have a private constructor.
     */
    private EJBHelper() {
    }

    /**
     * Constant used to identify a remote interface.
     */
    public static final String ITF_REMOTE = "@Remote";

    /**
     * Constant used to identify a local interface.
     */
    public static final String ITF_LOCAL = "@Local";

    /**
     * Gets a remote instance of a bean.
     * @param <E> bean element type
     * @param beanClass class of the bean
     * @param beanInterface interface of the bean
     * @return bean instance
     * @throws Exception if occurs a problem in the instance invocation
     */
    @SuppressWarnings("unchecked")
    public static synchronized <E> E getBeanRemoteInstance(final Class beanClass, final Class<E> beanInterface) throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        Context initialContext = new InitialContext();
        E clBean = (E) initialContext.lookup(beanClass.getName() + "_" + beanInterface.getName() + ITF_REMOTE);
        return clBean;
    }

    /**
     * Gets a local instance of a bean.
     * @param <E> bean element type
     * @param beanClass class of the bean
     * @param beanInterface interface of the bean
     * @return bean instance
     * @throws Exception if occurs a problem in the instance invocation.
     */
    @SuppressWarnings("unchecked")
    public static synchronized <E> E getBeanLocalInstance(final Class beanClass, final Class<E> beanInterface) throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        Context initialContext = new InitialContext();
        E clBean = (E) initialContext.lookup(beanClass.getName() + "_" + beanInterface.getName() + ITF_LOCAL);
        return clBean;
    }

    /**
     * Gets an instance of a bean by the mappedName.
     * @param <E> the bean element type.
     * @param mappedName the mappedName.
     * @return the bean.
     * @throws Exception f occurs a problem in the instance invocation.
     */
    @SuppressWarnings("unchecked")
    public static synchronized <E> E getBeanByMappedName(final String mappedName) throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        Context initialContext = new InitialContext();
        E clBean = (E) initialContext.lookup(mappedName);
        return clBean;
    }

}
