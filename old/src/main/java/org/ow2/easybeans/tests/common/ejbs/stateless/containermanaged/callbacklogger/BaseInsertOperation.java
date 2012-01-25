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
 * $Id: BaseInsertOperation.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import javax.ejb.EJB;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseInsertOperation {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(BaseInsertOperation.class);

    /**
     * Bean logger.
     */
    @EJB(beanName = "SLSBOperationLoggerAccess")
    private ItfOperationLoggerAccess beanLogger;

    /**
     * Log action.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the class where the event method is defined.
     * @param operationType the operationType.
     */
    public void log(final Class className, final CallbackType event, final Class eventClassName,
            final OperationType operationType) {
        log(className.getName(), event, eventClassName.getName(), operationType);
    }

    /**
     * Log action.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the class where the event method is defined.
     * @param operationType the operationType.
     */
    public void log(final String className, final CallbackType event, final String eventClassName,
            final OperationType operationType) {
        logger.debug("Logging event in: {0}", className);
        beanLogger.insertOperationLogger(className, event, eventClassName, operationType);
    }

    /**
     * Log action.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the class where the event method is defined.
     * @param operationType the operationType.
     * @param description the operation description.
     */
    public void log(final String className, final CallbackType event, final String eventClassName,
            final OperationType operationType, final String description) {
        logger.debug("Logging event in: {0}", className);
        beanLogger.insertOperationLogger(className, event, eventClassName, operationType, description);
    }
}
