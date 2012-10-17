/*
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
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
 * $Id:$
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.managedbeans.interceptors;

import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MySuperOverridedInterceptor extends MySimpleInterceptor {

    @SuppressWarnings("unchecked")
    @AroundInvoke
    public Object interceptNew(InvocationContext invocationContext) throws Exception {
        ((List<String>) invocationContext.getParameters()[0]).add(MySuperOverridedInterceptor.class.getName());
        return invocationContext.proceed();
    }
    
    @Override
    public Object intercept(InvocationContext invocationContext) throws Exception {
        throw new IllegalArgumentException("Not an aroundInvoke anymore");
    }

}
