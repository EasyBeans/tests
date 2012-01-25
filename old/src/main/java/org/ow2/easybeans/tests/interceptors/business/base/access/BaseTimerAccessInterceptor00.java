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
 * $Id: BaseTimerAccessInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.base.access;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessTimerService;


/**
 * Verifies the interceptor access to the TimerService.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseTimerAccessInterceptor00 {

    /**
     * Bean used to implement the test.
     */
    private ItfAccessTimerService beanAcessTimerService;

    /**
     * Verifies if the interceptor can access a TimerService.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unchecked")
    public void testTimerService00() throws Exception {
        beanAcessTimerService.accessTimerService(null);
    }

    /**
     * Sets the bean used to test the timer service.
     * @param bean The bean to set.
     */
    public void setTimerBean(final ItfAccessTimerService bean){
        this.beanAcessTimerService = bean;
    }
}
