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
 * $Id: SLSBTwoPostConstructIntInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.wrongspecification;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod00;


/**
 * This bean has two annotations(PostConstruct).
 * It isn't in agreement with the JSR 220 - EJB 3.0 Spec.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateful
public class SLSBTwoPostConstructIntInterceptor implements ItfOneMethod00{

    /**
     * This method does nothing.
     * @param i integer
     * @return null
     */
    public Object doOne(final Integer i) {
        return null;
    }

    /**
     * Lifecyle callback interceptor.
     * @param ic contains attributes of invocation
     */
    @PostConstruct
    public void interceptor00(final InvocationContext ic){
        try {
            ic.proceed();
        } catch (Exception e) {
            throw new IllegalStateException("Exception in " + SLSBTwoPostConstructIntInterceptor.class.getName());
        }
    }

    /**
     * Lifecyle callback interceptor.
     * @param ic contains attributes of invocation
     */
    @PostConstruct
    public void interceptor01(final InvocationContext ic){
        try {
            ic.proceed();
        } catch (Exception e) {
            throw new IllegalStateException("Exception in " + SLSBTwoPostConstructIntInterceptor.class.getName());
        }
    }
}
