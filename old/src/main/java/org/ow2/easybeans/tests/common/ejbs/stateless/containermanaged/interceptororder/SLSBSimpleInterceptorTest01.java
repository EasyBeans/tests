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
 * $Id: SLSBSimpleInterceptorTest01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder;

import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.addValue;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleBean;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder06Interceptor;

/**
 * This bean is used to test embedded interceptors.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless
@Remote(ItfSimpleBean.class)
@Interceptors({PrintOrder06Interceptor.class})
public class SLSBSimpleInterceptorTest01 implements ItfSimpleBean<Integer>{

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has interceptors that must be call in order.
     * @param par list used to append the value
     * @return the list with modified
     */
    @Interceptors({PrintOrder01Interceptor.class})
    public List<Integer> withInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Intercepts the method and add the ORDER value in the list that was get from
     * InvocationContext. This method has protected level access.
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @AroundInvoke
    protected Object addOrder(final InvocationContext invocationContext) throws Exception{
        return addValue(invocationContext, EMBEDDED_INTERCEPTOR, this.getClass().toString());
    }

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has interceptors that must be call in order. There
     * are interceptors that use inheritance.
     * @param par list used to append the value
     * @return the list with modified
     */
    @Interceptors({PrintOrder03Interceptor.class})
    public List<Integer> withInterceptorsInheritance(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }
}
