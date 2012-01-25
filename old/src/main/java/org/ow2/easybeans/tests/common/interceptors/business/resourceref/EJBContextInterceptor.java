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
 * $Id: EJBContextInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.business.resourceref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.interceptor.InvocationContext;

/**
 * Verifies if the interceptor access a resource defined in the deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public class EJBContextInterceptor {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private EJBContext ctx00;

    /**
     * Checks the ejb context reference injected by xml descriptor.
     * @param invocationContext the bean invocation context.
     * @throws Exception if an error occurs during the proceed.
     * @return the result from the bean.
     */
    public Object aroundInvoke(final InvocationContext invocationContext) throws Exception {
        checkResource(sessionContext, ctx00, "ejbctx/ctx00");
        return invocationContext.proceed();
    }
}
