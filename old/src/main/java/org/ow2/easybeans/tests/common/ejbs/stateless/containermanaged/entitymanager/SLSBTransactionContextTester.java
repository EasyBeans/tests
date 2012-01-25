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
 * $Id: SLSBTransactionContextTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * Creates a bean using different transaction context.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfTransactionContextTester.class)
public class SLSBTransactionContextTester implements ItfTransactionContextTester {

    /**
     * The entity manager used during the test.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * The entity manager used to delete the bean, if the bean is already in the
     * database.
     */
    @PersistenceContext
    private EntityManager emDelete;

    /**
     * Deletes the bean with the default value.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBean() {
        EBStore ebstore = emDelete.find(EBStore.class, new Integer(ID));
        if (ebstore != null) {
            emDelete.remove(ebstore);
        }
        emDelete.flush();
    }

    /**
     * Creates a entity bean in a transaction context and call other method that has a different
     * transaction to persist the bean.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createBeanRequiresNewWithClientTransaction() {
        deleteBean();
        EBStore ebstore = new EBStore();
        ebstore.setId(ID);
        ebstore.setName(NAME);
        persistRequiresNew(ebstore);
    }

    /**
     * Creates a entity bean without a transaction context and call other method
     * that has a transaction to persist the bean.
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void createBeanRequiresNewWithoutClientTransaction() {
        deleteBean();
        EBStore ebstore = new EBStore();
        ebstore.setId(ID);
        ebstore.setName(NAME);
        persistRequiresNew(ebstore);
    }
    /**
     * Creates a entity bean and call other method that has the same transaction
     * to persist the bean.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createBeanRequired() {
        deleteBean();
        EBStore ebstore = new EBStore();
        ebstore.setId(ID);
        ebstore.setName(NAME);
        persistRequired(ebstore);
    }

    /**
     * Makes the persist and does not use the client transaction.
     * @param entity the entity class to be stored.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void persistRequiresNew(final EBStore entity) {
        em.persist(entity);
    }

    /**
     * Makes the persist and use the client transaction.
     * @param entity the entity class to be stored.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persistRequired(final EBStore entity) {
        em.persist(entity);
    }

}
