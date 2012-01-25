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
 * $Id: SLSBOperationLoggerAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import static java.util.Arrays.sort;
import static org.ow2.easybeans.tests.common.helper.ListHelper.convertListType;
import static org.testng.Assert.assertTrue;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackLogger;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationLogger;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;

/**
 * Accesses the entity bean that is responsible for registering operations.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless(name = "SLSBOperationLoggerAccess")
@Remote(ItfOperationLoggerAccess.class)
@ExcludeDefaultInterceptors
public class SLSBOperationLoggerAccess extends CallbackLoggerAccessBase implements ItfOperationLoggerAccess {

    /**
     * The Entity Manager Factory used during the tests.
     */
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Constant.
     */
    private static final String UNDEFINED = "Undefined.";

    /**
     * Creates an instance of OperationLogger with the parameters and the
     * current time.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the name of the class where the event is defined.
     * @param operationType the operation type.
     * @param description the operation description.
     */
    public void insertOperationLogger(final String className, final CallbackType event, final String eventClassName,
            final OperationType operationType, final String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        OperationLogger logger = new OperationLogger();
        logger.setCallbackClassName(eventClassName);
        logger.setCallbackEvent(event);
        logger.setClassName(className);
        logger.setInsertionDate(getTime());
        logger.setOperationType(operationType);
        logger.setDescription(description);
        entityManager.persist(logger);
        entityManager.flush();
    }

    /**
     * Creates an instance of OperationLogger with the parameters and the
     * current time.
     * @param className the bean class.
     * @param event the event type.
     * @param eventClassName the name of the class where the event is defined.
     * @param operationType the operation type.
     */
    public void insertOperationLogger(final String className, final CallbackType event, final String eventClassName,
            final OperationType operationType) {
        this.insertOperationLogger(className, event, eventClassName, operationType, UNDEFINED);
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
    public void verifyOperation(final String className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType) {

        this.verifyOperation(className, event, eventClassNames, operationType,
                getArrayUndefined(eventClassNames.length));
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
    public void verifyOperation(final Class className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType) {
        this.verifyOperation(className.getName(), event, eventClassNames, operationType,
                getArrayUndefined(eventClassNames.length));
    }

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
    public void verifyOperation(final Class className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType, final String[] descriptions) {
        this.verifyOperation(className.getName(), event, eventClassNames, operationType, descriptions);
    }

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
    public void verifyOperation(final String className, final CallbackType event, final String[] eventClassNames,
            final OperationType operationType, final String[] descriptions) {

        try {
            Thread.sleep(WAIT);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // Checks if the size of descriptions is the same as eventClassNames.
        assertTrue(descriptions.length == eventClassNames.length,
                "The length of descriptions and event class names must be equal.");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM OperationLogger "
                + "e WHERE e.className = :className AND e.callbackEvent= :event AND e.operationType = :operationType");
        query.setParameter("className", className);
        query.setParameter("event", event);
        query.setParameter("operationType", operationType);
        List callbackList = query.getResultList();

        assertTrue(callbackList.size() == eventClassNames.length,
                "The length of operations expected is different from the length of operations found.");

        // Sorts the events by date.
        if (callbackList.size() != 0) {
            OperationLogger[] lstManager = new OperationLogger[callbackList.size()];
            try {
                lstManager = convertListType(callbackList).toArray(lstManager);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sort(lstManager, new CallbackLoggerComparator<CallbackLogger>());
            for (int i = 0; i < lstManager.length; i++) {
                if (!(lstManager[i].getCallbackClassName().equals(eventClassNames[i]) & lstManager[i].getDescription()
                        .equals(descriptions[i]))) {
                    // The event class name and the operation description must
                    // be equal.
                    throw new IllegalStateException("The operation was not called. Expected = " + eventClassNames[i]
                            + ", Found = " + lstManager[i].toString());
                }
            }
        }
    }

    /**
     * Builds an array, which all values are equals to UNDEFINED.
     * @param length array's length
     * @return the array.
     */
    private String[] getArrayUndefined(final int length) {
        String[] list = new String[length];

        for (int i = 0; i < length; i++) {
            list[i] = UNDEFINED;
        }

        return list;
    }
}
