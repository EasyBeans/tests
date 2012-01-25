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
 * $Id: OperationChecker.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;

/**
 * This class encapsulates the verification using the bean logger.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class OperationChecker {

    /**
     * Logger bean.
     */
    private ItfOperationLoggerAccess opLog;


    /**
     * Creates a logger checker.
     * @throws Exception if a problem occurs.
     */
    public OperationChecker() throws Exception{
        opLog = getBeanRemoteInstance(SLSBOperationLoggerAccess.class, ItfOperationLoggerAccess.class);
    }

    /**
     * Verifies if the specified operation type occured.
     * @param className the bean class.
     * @param event the event type.
     * @param operationType the operation type.
     */
    public void check(final String className, final CallbackType event, final OperationType operationType) {

        // Verifies if the message was received
        List<String> arEvent = new ArrayList<String>();

        arEvent.add(className);

        this.check(className, event, arEvent, operationType);
    }

    /**
     * Verifies if the events for an operation were called and if the invocation
     * order is correct.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassNames the list of where the event methods are defined in
     *        the correct invocation order.
     * @param operationType the operation type.
     */
    public void check(final String className, final CallbackType event, final List<String> eventClassNames,
            final OperationType operationType) {
        opLog.verifyOperation(className, event, eventClassNames.toArray(new String[0]), operationType);
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    public void deleteAll() throws Exception {
        opLog.deleteAll();
    }
}
