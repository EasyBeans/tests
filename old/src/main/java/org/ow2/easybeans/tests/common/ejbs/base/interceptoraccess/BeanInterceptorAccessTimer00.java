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
 * $Id: BeanInterceptorAccessTimer00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.interceptoraccess;


import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessTimerService;
import org.ow2.easybeans.tests.common.interceptors.business.access.TimerServiceAccess00Interceptor;


/**
 * Used to test the interceptors access to the TimerService.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class BeanInterceptorAccessTimer00 implements ItfAccessTimerService{

    /**
     * This has an interceptor that access a TimerService.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({TimerServiceAccess00Interceptor.class})
    public Object accessTimerService(final Object obj) throws Exception {
        return null;
    }

}
