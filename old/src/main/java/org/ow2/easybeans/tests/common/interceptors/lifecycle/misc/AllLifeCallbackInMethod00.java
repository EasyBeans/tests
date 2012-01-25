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
 * $Id: AllLifeCallbackInMethod00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.lifecycle.misc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This interceptor contains methods to the four lifecycle callbacks.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class AllLifeCallbackInMethod00 {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(AllLifeCallbackInMethod00.class);

    /**
     * Simple lifecyle callback interceptor method to the four life cycle
     * callback.
     * @param ic contains attributes of invocation
     */
    @PostConstruct
    @PreDestroy
    @PostActivate
    @PrePassivate
    public void intercept(final InvocationContext ic) {
        logger.debug("Lifecycle interceptor method.");
        try {
            ic.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
