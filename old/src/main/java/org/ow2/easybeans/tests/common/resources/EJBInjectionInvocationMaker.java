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
 * $Id: EJBInjectionInvocationMaker.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import org.ow2.easybeans.tests.common.ejbs.base.EJBInjectionBean;
import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;

/**
 * Lookups and invokes the EJBInjectionBean for n times.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class EJBInjectionInvocationMaker extends AbstractInvocationMaker {

    /**
     * Bean class.
     */
    private static final Class BEAN_CLASS = EJBInjectionBean.class;

    /**
     * Bean interface.
     */
    private static final Class BEAN_INTERFACE = ItfOneMethod01.class;

    /**
     * Number of times.
     */
    private int timesInvocation;

    /**
     * Number of times to invoke.
     * @param timesInvocation number
     */
    public EJBInjectionInvocationMaker(final int timesInvocation) {
        super();
        this.timesInvocation = timesInvocation;
    }

    /**
     * Invokes a bean method.
     * @param n number of times
     * @param bean bean instance
     * @param <E> type
     */
    @Override
    public <E> void invokeMethod(final int n, final E bean) {
        ItfOneMethod01 b = (ItfOneMethod01) bean;
        b.getBool();
    }

    /**
     * Starts the test.
     */
    @Override
    public void run() {
        try {
            this.test(timesInvocation, BEAN_CLASS, BEAN_INTERFACE);
        } catch (Exception e) {
            throw new IllegalStateException("Error.", e);
        }
    }
}
