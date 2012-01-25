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
 * $Id: SFSBEntityManagerFlushTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.flushoperation;

import static org.testng.Assert.assertEquals;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * Verifies if the persistence context is making correctly the flush method.
 * There are two ways to make the flush: auto and commit.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfEntityManagerFlushTester.class)
public class SFSBEntityManagerFlushTester implements ItfEntityManagerFlushTester {

    /**
     * The persistence context used during the test.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Removes the entity from the database.
     *
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void removeEntity() {
        EBStore ebstore = entityManager.find(EBStore.class, ENTITY_ID);
        if (ebstore != null) {
            entityManager.remove(ebstore);
        }
    }

    /**
     * Removes the entity in the database avoiding an insertion error and
     * inserts a new entity to make the tests.
     */
    public void startup() {
        removeEntity();
        EBStore ebstore = new EBStore();
        ebstore.setId(ENTITY_ID.intValue());
        ebstore.setName(ENTITY_NAME);
        entityManager.persist(ebstore);
    }

    /**
     * Verifies if the flush mode value is auto.
     */
    public void verifyDefaultFlushMode() {
        assertEquals(entityManager.getFlushMode(), FlushModeType.AUTO,
                "The flush mode is not beginning with the default value");
    }

    /**
     * Sets the flush mode to AUTO and verifies if the container makes a flush when a query method is called.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setFlushModeAuto() {
        entityManager.setFlushMode(FlushModeType.AUTO);
        assertEquals(entityManager.getFlushMode(), FlushModeType.AUTO,
        "The flush mode was not set.");

        EBStore ebstoreBeforeChange = entityManager.find(EBStore.class, ENTITY_ID);
        ebstoreBeforeChange.setName(ENTITY_NAME_2);
        // forces a flush
        entityManager.createQuery("SELECT e FROM EBStore e");
        // verifies if the flush was made
        EBStore ebstoreAfterChange = entityManager.find(EBStore.class, ENTITY_ID);
        assertEquals(ebstoreAfterChange.getName(), ENTITY_NAME_2, "The container did not make a flush after the query");
    }

    /**
     * Sets the flush mode to COMMIT and verifies if the container does not make
     * a flush when a query method is called.The flush must be called only in
     * the commit.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setFlushModeCommit() {
        entityManager.setFlushMode(FlushModeType.COMMIT);
        assertEquals(entityManager.getFlushMode(), FlushModeType.COMMIT,
        "The flush mode was not set.");

        EBStore ebstoreBeforeChange = entityManager.find(EBStore.class, ENTITY_ID);
        ebstoreBeforeChange.setName(ENTITY_NAME_2);
        // forces a flush
        entityManager.createQuery("SELECT e FROM EBStore e");
        // verifies if the flush was not made
        EBStore ebstoreAfterChange = entityManager.find(EBStore.class, ENTITY_ID);
        assertEquals(ebstoreAfterChange.getName(), ENTITY_NAME, "The container made a flush after the query");
    }

}
