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
 * $Id: SLSBInvocationContext01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocation00;

/**
 * This bean is used to manipulate the invocation context object.
 * @author Eduardo Studzinski E. de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless
@Remote
public class SLSBInvocationContext01 implements ItfInvocation00 {

    /**
     * Information from interceptor.
     */
    private Integer interceptorInfo = null;

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

    /**
     * Interceptor that checks the invocation context object, it's compares the
     * bean descriptor. <li>The first parameter of the intercepted method must
     * be a BeanDescriptor.</li>
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings("unused")
    @AroundInvoke
    private Object checkDescriptor(final InvocationContext ic) throws Exception {
        interceptorInfo = new Integer(ic.getTarget().hashCode());
        return ic.proceed();
    }

}
