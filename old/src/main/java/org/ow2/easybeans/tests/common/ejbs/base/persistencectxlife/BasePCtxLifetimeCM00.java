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
 * $Id: BasePCtxLifetimeCM00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BasePCtxLifetimeCM00 implements ItfPCtxLifetime00 {

    /**
     * Manager.
     */
    private EntityManager em;

    /**
     * Hashcode.
     */
    private int id = this.hashCode();

    /**
     * Entity.
     */
    private EBStore eb;

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     * @param em instance
     */
    public void initEntityManager(final EntityManager em) {
        this.em = em;
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     * @param id entity id
     */
    public void setEntityID(final int id) {
        this.id = id;
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCheckEntity00() {
        eb = new EBStore(id);
        em.persist(eb);

        assertTrue(em.contains(eb), "The entity instance should be managed.");

        EBStore ebFind = em.find(EBStore.class, id);

        assertTrue(ebFind == eb, "The entity instances should be equals, "
                + "they are in the same persistence context.");
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void createCheckEntity01() {
        eb = new EBStore(id);
        em.persist(eb);

        assertFalse(em.contains(eb), "The entity instance should be detached.");
    }

    /**
     * Checks if an entity is contained in the entity manager.
     * @return true if exists
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public boolean containsEntity() {
        return em.contains(eb);
    }

    /**
     * Checks if an entity exists.
     * @return true if exists
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public boolean existsEntity() {
        return em.find(EBStore.class, id) != null;
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void checkManaged() {
        assertTrue(em.contains(eb), "The entity instance should be managed.");

        EBStore ebFind = em.find(EBStore.class, id);

        assertTrue(ebFind == eb, "The entity instances should be equals, "
                + "they are in the same persistence context.");
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void checkDetached() {
        assertFalse(em.contains(eb), "The entity instance should be detached.");
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    @SuppressWarnings("boxing")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void removeEntity() {
        EBStore ebFind = em.find(EBStore.class, id);
        if (ebFind != null) {
            em.remove(ebFind);
        }
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    public void initEntityManager() {
        //Must be overrided.
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00
     */
    public void persistEntity() {
        em.persist(eb);
    }
}
