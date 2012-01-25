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
 * $Id: EMFactoryAccess00Interceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.business.access;


import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.resources.EMFactoryTester;

/**
 * Interceptor used to access an EntityManager.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class EMFactoryAccess00Interceptor extends EMFactoryTester{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Intercepts the method and tries to access an entity bean using the entity manager.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings("unused")
    @AroundInvoke
    protected Object intercept(final InvocationContext ic) throws Exception {
        UserTransaction utx;
        try{
            utx = ctx.getUserTransaction();
            utx.begin();
        }catch(Exception e){
            utx = null;
        }

        super.access00();

        if (utx != null){
            utx.commit();
        }
        return ic.proceed();
    }
}
