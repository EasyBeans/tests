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
 * $Id: SLSBListenerLoggerAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.ListenerLogger;

/**
 * Accesses the entity bean that is responsible for registering the callback events.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless
@Remote(ItfListenerLoggerAccess.class)
public class SLSBListenerLoggerAccess extends CallbackLoggerAccessBase implements ItfListenerLoggerAccess{

    /**
     * The Entity Manager Factory used during the tests.
     */
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    /**
     * Creates an instance of ListenerLogger with the parameters and the current
     * time.
     * @param className the class for which the callback event was called.
     * @param callbackEvent the callback event name.
     * @param callbackClassName the name of the class that contains the callback
     *        method.
     * @param entityId the identifier of the entity that the listenr methdos was
     *        called.
     */
    public void insertCallbackLogger(final String className, final CallbackType callbackEvent,
            final String callbackClassName, final int entityId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ListenerLogger logger = new ListenerLogger();
        logger.setCallbackClassName(callbackClassName);
        logger.setCallbackEvent(callbackEvent);
        logger.setClassName(className);
        logger.setInsertionDate(getTime());
        logger.setEntityId(entityId);
        entityManager.persist(logger);
        entityManager.flush();
    }

    /**
     * Finds a callback event that was called for the class in the className
     * parameter and is defined in the class callbackClassName.
     * @param className the class for each the callback was called.
     * @param callbackEvent the callback method that was called.
     * @param entityId the the entity for which the listener was called.
     * @return the list of results.
     */
    public List findLifecycleEventByEntity(final String className, final CallbackType callbackEvent,
            final int entityId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM ListenerManager "
                + "e WHERE e.className = :className AND e.callbackEvent= :event AND e.entityId = :entityId");
        query.setParameter("className", className);
        query.setParameter("event", callbackEvent);
        query.setParameter("entityId", new Integer(entityId));
        return query.getResultList();
    }
}
