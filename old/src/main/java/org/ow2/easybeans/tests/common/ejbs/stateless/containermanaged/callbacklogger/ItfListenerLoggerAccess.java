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
 * $Id: ItfListenerLoggerAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;

/**
 * Accesses the entity that registers the callback events.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfListenerLoggerAccess {

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
    void insertCallbackLogger(final String className, final CallbackType callbackEvent, final String callbackClassName,
            final int entityId);


    /**
     * Deletes a callback event from the database.
     * @param id the callback identifier.
     */
    void deleteCallbackEvent(int id);

    /**
     * Finds a callback event for a class.
     * @param className the class for each the callback was called.
     * @param callbackEvent the callback method that was called.
     * @return the list of results.
     */
    List findCallbackEvent(String className, CallbackType callbackEvent);

    /**
     * Finds a callback event that was called for the class in the className
     * parameter and is defined in the class callbackClassName.
     * @param className the class for each the callback was called.
     * @param callbackEvent the callback method that was called.
     * @param callbackClassName the class taht the callback method is defined.
     * @return the list of results.
     */
    List findCallbackEventByCallbackMethod(String className, CallbackType callbackEvent, String callbackClassName);

    /**
     * Deletes all callback events from the database.
     */
    void deleteAll();

}
