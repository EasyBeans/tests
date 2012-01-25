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
 * $Id: EBaseExternalCallbackAccess00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.lifecallback;

import javax.ejb.Remove;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackEJBAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackEMFactoryAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackEntityManagerAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackJEnvCompAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackResourceManagerAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackSessionContextAccess00;


/**
 * This bean is used to verify all operations allowed in interceptor
 * methods for lifecycle callback methods. The first interceptor must be the
 * EJB Access Tester because it verifies if the interceptor bean
 * logger is ok. This logger is used in all interceptors,
 * so it must be verifired first.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Interceptors({
    AllLifeCallbackEJBAccess00.class,
    AllLifeCallbackSessionContextAccess00.class,
    AllLifeCallbackJEnvCompAccess00.class,
    AllLifeCallbackResourceManagerAccess00.class,
    AllLifeCallbackEMFactoryAccess00.class,
    AllLifeCallbackEntityManagerAccess00.class
})
public class EBaseExternalCallbackAccess00 implements ItfCheck02{

    /**
     * Empty method.
     * @throws Exception if a problem occurs.
     */
    public void check() throws Exception{
    }

    /**
     * Bean with @Remove annotation.
     */
    @Remove
    public void remove() {
    }

}
