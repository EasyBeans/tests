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
 * $Id: ItfPCtxLifetime00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife;


/**
 * Interface used to test the persistence context lifetime.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public interface ItfPCtxLifetime00 {

    /**
     * Initialize the entity manager.
     */
    void initEntityManager();

    /**
     * Creates an entity, persist and check if it is still managed.
     */
    void createCheckEntity00();

    /**
     * Creates an entity and check contains, it should not be managed.
     * The method has a "not supported" transaction attribute.
     */
    void createCheckEntity01();

    /**
     * Checks if an entity is contained in the entity manager.
     * @return true if exists
     */
    boolean containsEntity();

    /**
     * Checks if an entity exists.
     * @return true if exists
     */
    boolean existsEntity();

    /**
     * Checks the persistence context with contains and find methods.
     */
    void checkManaged();

    /**
     * Checks the persistence context with contains and find methods.
     */
    void checkDetached();

    /**
     * Removes the entity.
     */
    void removeEntity();

    /**
     * Persist the entity.
     */
    void persistEntity();
}
