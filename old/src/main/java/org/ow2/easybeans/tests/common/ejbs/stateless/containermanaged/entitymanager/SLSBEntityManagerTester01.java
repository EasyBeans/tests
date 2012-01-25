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
 * $Id: SLSBEntityManagerTester01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
@Remote(ItfEntityManagerTester01.class)
public class SLSBEntityManagerTester01 implements ItfEntityManagerTester01 {

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
     * Creates a entity and makes it detached. After that, changes the entity
     * detached and tries to make a merge.
     * @param id the entity primary key.
     * @param name the entity name.
     * @param newName the name that the entity receives after to be detached.
     */
    public void mergeEBStore(final int id, final String name, final String newName) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        //makes the entity detached
        em.clear();
        //changes the entity name
        ebstore.setName(newName);
        EBStore ebstoreMerged = em.merge(ebstore);
        assertEquals(newName, ebstoreMerged.getName(), "The entity was not merged.");
    }

    /**
     * Test if the method contains verifies if the entity is in the db.
     * @param id the entity primary key.
     * @param name the entity name.
     */
    public void containsEBStore(final int id, final String name) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        assertTrue(em.contains(ebstore), "The contains methos does not work properly");
    }

    /**
     * Verifies if the method refresh is working properly.
     * @param id the entity primary key.
     * @param name the entity name.
     * @param newName the name that the entity receives after to be detached.
     */
    public void refreshEBStore(final int id, final String name, final String newName) {
        EBStore ebstore = completeEBStore(id, name);
        em.persist(ebstore);
        //makes the entity detached
        em.clear();
        //changes the entity name
        ebstore.setName(newName);
        //makes a refresh
        em.refresh(ebstore);
        //verifies if the container got the values from the database.
        assertEquals(ebstore.getName(), name, "The entity was not refreshed");
    }

    /**
     * Removes the entity that is in the database.
     * @param id the entity primary key.
     */
    public void removeEBStore(final int id) {
        EBStore ebstore = em.find(EBStore.class, new Integer(id));
        if(ebstore != null){
            em.remove(ebstore);
        }
    }
}
