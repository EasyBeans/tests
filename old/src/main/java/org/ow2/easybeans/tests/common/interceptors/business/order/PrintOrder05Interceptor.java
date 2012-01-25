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
 * $Id: PrintOrder05Interceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.business.order;

import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.addValue;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


/**
 * This interceptor is used to test the order execution of interceptors.
 * This interceptor has a method that overrides the super method.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class PrintOrder05Interceptor extends PrintOrderInterceptor<Integer>{

    /**
     * Constant to add into array.
     *
     */
    public static final Integer ORDER = new Integer(5);

    /**
     * This value is used to set the super order value, <b>it will be used to test if the override is working.</b>
     */
    private static final Integer FALSE_ORDER = new Integer(-1);

    /**
     * Constructor standard.
     */
    public PrintOrder05Interceptor() {
        //The value is -1 to test the method that overrides the super method.
        super(FALSE_ORDER);
    }

    /**
     * Intercepts the method and adds the ORDER value in the array that was get from
     * InvocationContext.
     * <b>Overrides the super method.</b>
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @Override
    @AroundInvoke
    public Object addOrder(final InvocationContext invocationContext) throws Exception {
        return addValue(invocationContext, ORDER, this.getClass().toString());
    }
}
