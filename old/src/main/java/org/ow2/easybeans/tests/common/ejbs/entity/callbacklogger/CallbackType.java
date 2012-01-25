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
 * $Id: CallbackType.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger;

import java.io.Serializable;

/**
 * The callback methods that are registered in the database.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public enum CallbackType implements Serializable {

    /**
     * Indicates that is an interceptor method invocation.
     */
    AROUND_INVOKE,

    /**
     * Indicates that the callback method is PrePersist.
     */
    PRE_PERSIST,

    /**
     * Indicates that the callback method is PostPersist.
     */
    POST_PERSIST,

    /**
     * Indicates that the callback method is PreRemove.
     */
    PRE_REMOVE,

    /**
     * Indicates that the callback method is PostRemove.
     */
    POST_REMOVE,

    /**
     * Indicates that the callback method is PreUpdate.
     */
    PRE_UPDATE,

    /**
     * Indicates that the callback method is PostUpdate.
     */
    POST_UPDATE,

    /**
     * Indicates that the callback method is PostPLoad.
     */
    POST_LOAD,

    /**
     * Indicates that the callback method is PreDestroy.
     */
    PRE_DESTROY,

    /**
     * Indicates that the callback method is PostConstruct.
     */
    POST_CONSTRUCT,

    /**
     * Indicates that the callback method is PrePassivate.
     */
    PRE_PASSIVATE,

    /**
     * Indicates that the callback method is PostActivate.
     */
    POST_ACTIVATE,

    /**
     * Indicates that the callback method is a message-driven bean onMethod.
     */
    ON_MESSAGE,

    /**
     * Indicates that the callback method is a timeout.
     */
    TIMEOUT,

    /**
     * This is used to allow the use with other kinds of methods.
     */
    UNDEFINED;
}
