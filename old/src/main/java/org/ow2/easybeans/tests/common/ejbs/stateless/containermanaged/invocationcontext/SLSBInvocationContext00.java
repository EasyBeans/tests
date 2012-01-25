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
 * $Id: SLSBInvocationContext00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocation00;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.InvocationInterceptor00;

/**
 * This bean is used to manipulate the invocation context object.
 * @author Eduardo Studzinski E. de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless
@Remote
@Interceptors({InvocationInterceptor00.class})
public class SLSBInvocationContext00 implements ItfInvocation00 {

    /**
     * Information from interceptor.
     */
    private Integer interceptorInfo = new Integer(0);

    /**
     * Verifies if the Interceptor.getTarget() returns an object with the same
     * hashcode of the bean instance.
     */
    public void check() {
        if (interceptorInfo.intValue() != this.hashCode()) {
            throw new IllegalStateException("The referenced bean is not equal as the invocation context reference.");
        }
    }

    /**
     * Allows the interceptor to store an information in the bean.
     * @param o information
     */
    @ExcludeClassInterceptors
    public void setInterceptorInfo(final Object o) {
        interceptorInfo = (Integer) o;
    }
}
