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
 * $Id: PrintOrderWithAllCallbackMethods.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.business.base;

import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.addValue;

import javax.ejb.EJB;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;

/**
 * Has the definition for all lifecycle callback methods, but is has not
 * annotations. The methods are defined as callback methods by deployment
 * descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class PrintOrderWithAllCallbackMethods {

    /**
     * Bean used to log the callback loger.
     */
    @EJB(beanName = "SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess clBean;

    /**
     * Constant to add into list.
     */
    public static final Integer ORDER = new Integer(5);

    /**
     * Intercepts the method and adds the ORDER value in the list that was get
     * from InvocationContext.
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public Object addOrder(final InvocationContext invocationContext) throws Exception {
        return addValue(invocationContext, ORDER, this.getClass().toString());
    }

    /**
     * Sets a variable as true.
     * @param invocationContext contains attributes of invocation.
     * @throws Exception if an error in the interceptor chain occurs.
     */
    public void postConstruct(final InvocationContext invocationContext) throws Exception {
        clBean.insertCallbackLogger(invocationContext.getTarget().getClass().getName(), CallbackType.POST_CONSTRUCT,
                this.getClass().getName());
        invocationContext.proceed();
    }

    /**
     * Registers in the database that the method was called.
     * @param invocationContext contains attributes of invocation.
     * @throws Exception if an error in the interceptor chain occurs.
     */
    public void preDestroy(final InvocationContext invocationContext) throws Exception {
        clBean.insertCallbackLogger(invocationContext.getTarget().getClass().getName(), CallbackType.PRE_DESTROY, this
                .getClass().getName());
        invocationContext.proceed();
    }

    /**
     * Used to verify if a prePassivate callback method can be defined by the
     * deployment descriptor.
     *   * @param invocationContext contains attributes of invocation.
     * @throws Exception if an error in the interceptor chain occurs.
     */
    public void prePassivate(final InvocationContext invocationContext) throws Exception {
        // TODO - how to test the pre-passivate?
        invocationContext.proceed();
    }

    /**
     * Used to verify if a postActivate callback method can be defined by the
     * deployment descriptor.
     *   * @param invocationContext contains attributes of invocation.
     * @throws Exception if an error in the interceptor chain occurs.
     */
    public void postActivate(final InvocationContext invocationContext) throws Exception {
        // TODO - how to test the post-activate?
        invocationContext.proceed();
    }

}
