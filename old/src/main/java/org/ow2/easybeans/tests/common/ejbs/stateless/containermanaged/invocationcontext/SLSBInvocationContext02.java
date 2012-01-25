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
 * $Id: SLSBInvocationContext02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocationParameter01;
import org.ow2.easybeans.tests.common.helper.InvocationContextHelper;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.BeanDescriptor;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ComplexObject00;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ICDataAddInterceptor;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ICDataCheckEmptyInterceptor;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ICDataClearInterceptor;

/**
 * This bean is used to manipulate the invocation context object.
 * @author Eduardo Studzinski E. de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless
@Remote
@Interceptors({ICDataAddInterceptor.class, ICDataClearInterceptor.class, ICDataCheckEmptyInterceptor.class})
public class SLSBInvocationContext02 implements ItfInvocationParameter01 {

    /**
     * Returns objects passed as parameters.
     * @param objCP complex object
     * @param beanDescriptor a bean descriptor
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({ICDataCheckEmptyInterceptor.class, ICDataAddInterceptor.class, ICDataClearInterceptor.class,
            ICDataCheckEmptyInterceptor.class, ICDataAddInterceptor.class, ICDataClearInterceptor.class})
    public Object[] getObjects(final ComplexObject00 objCP, final BeanDescriptor beanDescriptor) throws Exception {
        return null;
    }

    /**
     * Interceptor that checks the invocation context object.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @AroundInvoke
    public Object intercept(final InvocationContext ic) throws Exception {
        return InvocationContextHelper.isEmptyContextData(ic);
    }
}
