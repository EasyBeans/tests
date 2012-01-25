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
 * $Id: ItfOperationLoggerAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;

/**
 * Accesses the entity that registers the operations made by a method.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfOperationLoggerAccess {

    /**
     * Creates an instance of OperationLogger with the parameters and the
     * current time.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the name of the class where the event is defined.
     * @param operationType the operationType.
     */
    void insertOperationLogger(final String className, final CallbackType event, final String eventClassName,
            final OperationType operationType);

    /**
     * Creates an instance of OperationLogger with the parameters and the
     * current time.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the name of the class where the event is defined.
     * @param operationType the operation type.
     * @param operationDescription the operation description.
     */
    void insertOperationLogger(final String className, final CallbackType event, final String eventClassName,
            final OperationType operationType, final String operationDescription);

    /**
     * Deletes all operations from the database.
     */
    void deleteAll();

    /**
     * Verifies if the events for an operation were called and if the invocation
     * order is correct.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassNames the list of where the event methods are defined in
     *        the correct invocation order.
     * @param operationType the operation type.
     */
    void verifyOperation(final String className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType);

    /**
     * Verifies if the events for an operation were called and if the invocation
     * order is correct.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassNames the list of where the event methods are defined in
     *        the correct invocation order.
     * @param operationType the operation type.
     */
    void verifyOperation(final Class className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType);

    /**
     * Verifies if the events for an operation were called and if the invocation
     * order is correct.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassNames the list of where the event methods are defined in
     *        the correct invocation order.
     * @param operationType the operation type.
     * @param descriptions the operations description.
     */
    void verifyOperation(final Class className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType, final String[] descriptions);

    /**
     * Verifies if the events for an operation were called and if the invocation
     * order is correct.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassNames the list of where the event methods are defined in
     *        the correct invocation order.
     * @param operationType the operation type.
     * @param descriptions the operations description.
     */
    void verifyOperation(final String className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType, final String[] descriptions);

}
