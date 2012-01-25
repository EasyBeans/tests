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
 * $Id: SLSBEntityManagerTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * Tests if the EntityManager is creating and deleting correctly the entity
 * beans when a persistence context transaction is used.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfEntityManagerTester00.class)
public class SLSBEntityManagerTester00 implements ItfEntityManagerTester00 {

    /**
     * The persistence context used during the test.
     */
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    /**
     * Completes the entity with the values.
     * @param id the entity primary key.
     * @param name the entity name.
     * @return the ebstore completed.
     */
    private EBStore completeEBStore(final int id, final String name) {
        EBStore ebstore = new EBStore();
        ebstore.setId(id);
        ebstore.setName(name);
        return ebstore;
    }

    /**
     * Finds the entity by primaryKey.
     * @param id the entity primary key.
     * @return the entity.
     */
    public EBStore findEBStore(final int id) {
        return em.find(EBStore.class, new Integer(id));
    }

    /**
     * Removes the entity that is in the database.
     * @param id the entity primary key.
     */
    public void removeEBStore(final int id) {
        EBStore ebstore = findEBStore(id);
        if(ebstore != null){
            em.remove(ebstore);
        }
    }

    /**
     * Creates an entity beans.
     * @param id the primary key.
     * @param name the entity name.
     */
    public void createEBStoreNew(final int id, final String name) {
        em.persist(completeEBStore(id, name));
    }

    /**
     * Tries to persist the same bean twice. In the first persist, the entity
     * status is new, in the second persist, the bean status is managed.
     * @param id the primary key.
     * @param name the entity name.
     */
    public void createEBStoreManaged(final int id, final String name) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        em.persist(ebstore);
    }

    /**
     * Creates the entity, removes it and after tries to insert other time the
     * removed entity.
     * @param id the primary key.
     * @param name the entity name.
     */
    public void createEBStoreRemoved(final int id, final String name) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        em.remove(ebstore);
        em.persist(ebstore);
    }

    /**
     * Creates the entity and tries to remove twice the same entity.
     * @param id the primary key.
     * @param name the entity name.
     */
    public void removeEBStoreRemoved(final int id, final String name) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        em.remove(ebstore);
        em.remove(ebstore);
    }

    /**
     * Creates the entity and , after that, tries remove the same entity.
     * @param id the primary key.
     * @param name the entity name.
     */
    public void removeEBStoreManaged(final int id, final String name) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        em.remove(ebstore);
    }

    /**
     * Tries remove the entity that is not persistent yet.
     * @param id the primary key.
     * @param name the entity name.
     */
    public void removeEBStoreNew(final int id, final String name) {
        EBStore ebstore = completeEBStore(id, name);
        em.remove(ebstore);
    }
}
