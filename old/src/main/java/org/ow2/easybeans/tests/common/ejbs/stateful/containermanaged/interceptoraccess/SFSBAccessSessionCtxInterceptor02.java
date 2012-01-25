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
 * $Id: SFSBAccessSessionCtxInterceptor02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptoraccess;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.resources.SessionContextTester;

/**
 * This bean tries to call "getUserTransaction()" of a SessionContext instance. It
 * can't invoke this method and it must throw an IllegalStateException.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateful(name = "SFSBAccessSessionCtxInterceptor02")
@Remote({ItfAccessSessionContext.class})
public class SFSBAccessSessionCtxInterceptor02 extends SessionContextTester implements ItfAccessSessionContext {

    /**
     * This has an interceptor that calls "getUserTransaction()".
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessSessionContext(final Object obj) throws Exception {
        return null;
    }

    /**
     * Tries to call the method "getUserTransaction()". It must throw IllegalStateException.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @AroundInvoke
    public Object intercept(final InvocationContext ic) throws Exception{
        super.access02();
        return ic.proceed();
    }

}
