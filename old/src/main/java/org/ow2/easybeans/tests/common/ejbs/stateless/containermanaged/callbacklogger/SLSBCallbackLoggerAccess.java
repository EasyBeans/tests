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
 * $Id: SLSBCallbackLoggerAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackLogger;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;

/**
 * Accesses the entity bean that is responsible for registering the callback events.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless(name="SLSBCallbackLoggerAccess")
@Remote(ItfCallbackLoggerAccess.class)
@ExcludeDefaultInterceptors
public class SLSBCallbackLoggerAccess extends CallbackLoggerAccessBase implements ItfCallbackLoggerAccess{

    /**
     * The Entity Manager Factory used during the tests.
     */
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    /**
     * Creates an instance of CallbackLogger with the parameters and the current
     * time.
     * @param className the class for which the callback event was called.
     * @param callbackEvent the callback event name.
     * @param callbackClassName the name of the class that contains the callback
     *        method.
     */
    public void insertCallbackLogger(final String className,
            final CallbackType callbackEvent, final String callbackClassName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CallbackLogger logger = new CallbackLogger();
        logger.setCallbackClassName(callbackClassName);
        logger.setCallbackEvent(callbackEvent);
        logger.setClassName(className);
        logger.setInsertionDate(getTime());
        entityManager.persist(logger);
        entityManager.flush();
    }
}
