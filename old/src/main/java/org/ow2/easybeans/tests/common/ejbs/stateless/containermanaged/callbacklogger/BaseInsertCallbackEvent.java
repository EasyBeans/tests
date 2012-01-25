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
 * $Id: BaseInsertCallbackEvent.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import javax.ejb.EJB;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;


/**
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class BaseInsertCallbackEvent {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(BaseInsertCallbackEvent.class);

    /**
     * Bean logger.
     */
    @EJB(beanName="SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * Log action.
     * @param ic context
     * @param type lifecycle interceptor type
     * @param interceptorClass class that contains the interceptor method.
     */
    public void log(final InvocationContext ic, final CallbackType type, final Class interceptorClass){
        log(ic.getTarget().getClass().getName(), type, interceptorClass.getName());
    }

    /**
     * Log action.
     * @param interceptedClass intercepted class
     * @param type lifecycle interceptor type
     * @param interceptorClass class that contains the interceptor method.
     */
    public void log(final Class interceptedClass, final CallbackType type, final Class interceptorClass){
        log(interceptedClass.getName(), type, interceptorClass.getName());
    }

    /**
     * Log action.
     * @param interceptedClassName intercepted class name
     * @param type lifecycle interceptor type
     * @param interceptorClassName class that contains the interceptor method name.
     */
    public void log(final String interceptedClassName, final CallbackType type, final String interceptorClassName){
        logger.debug("Logging event: {0}", interceptorClassName);
        beanLogger.insertCallbackLogger(interceptedClassName, type, interceptorClassName);
    }
}
