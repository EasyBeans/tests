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
 * $Id: PrintOrderWithoutAnnotationInterc.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.business.base;

import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.addValue;

import javax.interceptor.InvocationContext;



/**
 * Interceptor without annotation where the interceptor is defined in the
 * deployment descriptor.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class PrintOrderWithoutAnnotationInterc {

    /**
     * Constant to add into list.
     */
    public static final Integer ORDER = new Integer(1);

    /**
     * Intercepts the method and adds the ORDER value in the list that was get from
     * InvocationContext.
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
     public Object addOrder(final InvocationContext invocationContext) throws Exception {
        return addValue(invocationContext, ORDER, this.getClass().toString());
    }
}
