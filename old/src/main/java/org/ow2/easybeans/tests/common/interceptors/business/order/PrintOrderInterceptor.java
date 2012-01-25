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
 * $Id: PrintOrderInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.business.order;

import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.addValue;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.helper.InterceptorHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
/**
 * This generic interceptor is used to test the execution of interceptors.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 * @param <E> Type of order value
 */
public class PrintOrderInterceptor<E> {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(InterceptorHelper.class);

    /**
     * Value to add into a list.
     */
    private E order;

    /**
     * Constructor that receives the order value.
     * @param order value
     */
    public PrintOrderInterceptor(final E order) {
        this.order = order;
    }

    /**
     * Intercepts the method and adds the ORDER value in the array that was get from
     * InvocationContext.
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @AroundInvoke
    public Object addOrder(final InvocationContext invocationContext) throws Exception {
        logger.info("In the addOrder of PrinterOrderInterceptor: order={0}", order);
        return addValue(invocationContext, order, this.getClass().toString());
    }
}
