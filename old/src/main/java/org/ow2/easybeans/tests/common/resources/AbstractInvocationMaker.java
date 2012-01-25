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
 * $Id: AbstractInvocationMaker.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.ITF_REMOTE;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public abstract class AbstractInvocationMaker extends Thread {

    /**
     * Lookups n times a bean.
     * @param n number of lookups
     * @param beanClass class of the bean
     * @param beanInterface interface of the bean
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unused")
    public void test(final int n, final Class beanClass, final Class beanInterface) throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
        "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
        Context initialContext = new InitialContext();

        for (int i = 0; i < n; i++) {
            Object bean = initialContext.lookup(beanClass.getName() + "_" + beanInterface.getName() + ITF_REMOTE);
            this.invokeMethod(n, bean);
        }
    }

    /**
     * Invokes n times a method.
     * @param <E> type
     * @param n number of invocations
     * @param bean instance
     */
    public abstract <E> void invokeMethod(final int n, final E bean);
}
