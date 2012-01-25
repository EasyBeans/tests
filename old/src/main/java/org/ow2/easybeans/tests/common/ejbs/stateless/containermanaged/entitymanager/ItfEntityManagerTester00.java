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
 * $Id: ItfEntityManagerTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * Tests if the EntityManager is creating and removing the entities properly.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfEntityManagerTester00 {

    /**
     * Finds the entity by primaryKey.
     * @param id the entity primary key.
     * @return the entity.
     */
    EBStore findEBStore(final int id);

    /**
     * Removes the entity that is in the database.
     * @param id the entity primary key.
     */
    void removeEBStore(final int id);

    /**
     * Creates an entity beans.
     * @param id the primary key.
     * @param name the entity name.
     */
    void createEBStoreNew(final int id, final String name);

    /**
     * Tries to persist the same bean twice. In the first persist, the entity
     * status is new, in the second persist, the bean status is managed.
     * @param id the primary key.
     * @param name the entity name.
     */
    void createEBStoreManaged(final int id, final String name);

    /**
     * Creates the entity, removes it and after tries to insert other time the removed entity.
     * @param id the primary key.
     * @param name the entity name.
     */
    void createEBStoreRemoved(final int id, final String name);

    /**
     * Creates the entity and tries to remove twice the same entity.
     * @param id the primary key.
     * @param name the entity name.
     */
    void removeEBStoreRemoved(final int id, final String name);

    /**
     * Creates the entity and , after that, tries remove the same entity.
     * @param id the primary key.
     * @param name the entity name.
     */
    void removeEBStoreManaged(final int id, final String name);

    /**
     * Tries remove the entity taht is not persistent yet.
     * @param id the primary key.
     * @param name the entity name.
     */
    void removeEBStoreNew(final int id, final String name);

}
