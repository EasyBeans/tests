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
 * $Id: TwoPrePassivateInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.lifecycle.wrongspecification;

import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;


/**
 * This interceptor has two methods annotated with PrePassivate. The specification forbids this case, only one is allowed.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TwoPrePassivateInterceptor {
    /**
     * Lifecyle callback interceptor.
     * @param ic contains attributes of invocation
     */
    @PrePassivate
    public void interceptor00(final InvocationContext ic){
        try {
            ic.proceed();
        } catch (Exception e) {
            throw new IllegalStateException("Exception in " + TwoPrePassivateInterceptor.class.getName());
        }
    }

    /**
     * Lifecyle callback interceptor.
     * @param ic contains attributes of invocation
     */
    @PrePassivate
    public void interceptor01(final InvocationContext ic){
        try {
            ic.proceed();
        } catch (Exception e) {
            throw new IllegalStateException("Exception in " + TwoPrePassivateInterceptor.class.getName());
        }
    }
}
