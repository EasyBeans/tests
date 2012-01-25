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
 * $Id: CallbackLoggerAccessBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import static java.util.Arrays.sort;
import static org.ow2.easybeans.tests.common.helper.ListHelper.convertListType;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackLogger;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;


/**
 * The base that is used for manipulating CallbackLoggers.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public class CallbackLoggerAccessBase{

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(CallbackLoggerAccessBase.class);

    /**
     * The Entity Manager Factory used during the tests.
     */
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Sleep time to wait callback operations.
     */
    public static final int WAIT = 1000;

    /**
     * Waits 2 milliseconds and returns the current time.
     * @return the time.
     */
    protected long getTime(){
        try {
            // assures that the time diference between two insertion is at least
            // 2 miliseconds, and, consequently the test can registry the call
            // order.
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Date date = new Date();
        return date.getTime();
    }

   /**
     * Deletes a callback event from the database.
     * @param id the callback identifier.
     */
    public void deleteCallbackEvent(final int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CallbackLogger callbackLogger = entityManager.find(CallbackLogger.class, new Integer(id));
        if (callbackLogger != null) {
            entityManager.remove(callbackLogger);
        }
    }

    /**
     * Finds a callback event for a class.
     * @param className the class for each the callback was called.
     * @param callbackEvent the callback method that was called.
     * @return the list of results.
     */
    public List findCallbackEvent(final String className, final CallbackType callbackEvent) {
        //All logs
        List arAll = findAll();
        for(int i = 0; i < arAll.size(); i++){
            logger.debug("Callback: {0}", arAll.get(i).toString());
        }

        logger.debug("Finding: className={0}, callbackEvent={1}", className, callbackEvent);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findLifecycleEvent");
        query.setParameter("className", className);
        query.setParameter("event", callbackEvent);


        List arFound = query.getResultList();
        for(int i = 0; i <  arFound.size(); i++){
            logger.debug("Found callback: {0}", arFound.get(i).toString());
        }

        return arFound;
    }

    /**
     * Finds all callback events for a class.
     * @param className the class for each the callback was called.
     * @return the list of results.
     */
    public List findCallbackEvent(final String className) {
        logger.debug("Finding: className={0}", className);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findLifecycleEventByClass");
        query.setParameter("className", className);
        return query.getResultList();
    }

    /**
     * Finds a callback event that was called for the class in the className
     * parameter and is defined in the class callbackClassName.
     * @param className the class for each the callback was called.
     * @param callbackEvent the callback method that was called.
     * @param callbackClassName the class taht the callback method is defined.
     * @return the list of results.
     */
    public List findCallbackEventByCallbackMethod(final String className, final CallbackType callbackEvent,
            final String callbackClassName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findLifecycleEventByCallbackMethod");
        query.setParameter("className", className);
        query.setParameter("event", callbackEvent);
        query.setParameter("callbackClassName", callbackClassName);
        return query.getResultList();
    }

    /**
     * Finds all callback events.
     * @return events
     */
    public List findAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAll");
        return query.getResultList();
    }

    /**
     * Deletes all callback events from the database.
     */
    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("findAll");
        List lstEvent = query.getResultList();
        for (Object obj : lstEvent) {
            CallbackLogger callbackLogger = (CallbackLogger) obj;
            if (callbackLogger != null) {
                entityManager.remove(callbackLogger);
            }
        }
    }

    /**
     * Verifies if the callback interceptor methods for a life cycle callback event are executed in
     * the correct order.
     * @param className the class where the interceptor must executes.
     * @param callbackEvent the lifecycle callback interceptor method type.
     * @param callbackClassNames the list of interceptors that must be called organized in
     *        the correct order.
     */
    public void verifyCallbackOrder(final String className, final CallbackType callbackEvent,
            final String[] callbackClassNames) {

        try {
            Thread.sleep(WAIT);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        List callbackList = findCallbackEvent(className, callbackEvent);
        logger.debug("Callback list: {0}", callbackList);

        assertTrue(callbackList.size() == callbackClassNames.length, "There is an error in callback interceptor invocation.");

        // sorts the events by date.
        if (callbackList.size() != 0) {
            CallbackLogger[] lstManager = new CallbackLogger[callbackList.size()];
            try {
                lstManager = convertListType(callbackList).toArray(lstManager);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sort(lstManager, new CallbackLoggerComparator<CallbackLogger>());
            for (int i = 0; i < lstManager.length; i++) {
                assertEquals(lstManager[i].getCallbackClassName(), callbackClassNames[i],
                        "The callback methods were not called in the correct order. Expected = "
                        + callbackClassNames[i] + ", Found = " + lstManager[i].toString());
            }
        }
    }

    /**
     * Verifies if the callback interceptor methods for a life cycle callback event are executed in
     * the correct order.
     * @param className the class where the interceptor must executes.
     * @param callbackEvent the lifecycle callback interceptor method type.
     * @param callbackClassNames the list of interceptors that must be called organized in
     *        the correct order.
     */
    public void verifyCallbackOrder(final Class className, final CallbackType callbackEvent, final String[] callbackClassNames) {
        this.verifyCallbackOrder(className.getName(), callbackEvent, callbackClassNames);
    }
}
