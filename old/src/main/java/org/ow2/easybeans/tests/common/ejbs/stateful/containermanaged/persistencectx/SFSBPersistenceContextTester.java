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
 * $Id: SFSBPersistenceContextTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectx;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * Used to test the reference in the persistence context.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Remote(ItfPersistenceContextTester.class)
public class SFSBPersistenceContextTester implements ItfPersistenceContextTester {

    /**
     * EntityManager with the persistence context type = transaction.
     */
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManagerTransaction;

    /**
     * EntityManager with the persistence context type = extended.
     */
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManagerExtended;

    /**
     * Bean that is created in a transaction-scoped persistence context.
     */
    private EBStore ebstoreTransaction;

    /**
     * Bean that is created in a extended persistence context.
     */
    private EBStore ebstoreExtended;

    /**
     * Completes a EBStore entity with the values in the parameters.
     * @param id the primary key.
     * @param name the entity name.
     * @return a new entity with the values.
     */
    private EBStore createBean(final Integer id, final String name) {
        EBStore ebstore = new EBStore();
        ebstore.setId(id.intValue());
        ebstore.setName(name);
        return ebstore;
    }

    /**
     * Removes the beans of the database.
     *
     */
    private void removeEBStore(){
        EBStore ebstoreTransaction = entityManagerTransaction.find(EBStore.class, ID_TRANSACTION);
        if(ebstoreTransaction != null){
            entityManagerTransaction.remove(ebstoreTransaction);
        }

        EBStore ebstoreExtended = entityManagerExtended.find(EBStore.class, ID_EXTENDED);
        if(ebstoreExtended != null){
            entityManagerExtended.remove(ebstoreExtended);
        }

    }

    /**
     * Creates a entity bean using transaction-scoped persistence context and a
     * bean with extended persistence context.
     */
    public void startup() {
        //clean the db
        removeEBStore();
        // creates an entity in the transaction-scoped persistence context
        ebstoreTransaction = createBean(ID_TRANSACTION, NAME_TRANSACTION);
        entityManagerTransaction.persist(ebstoreTransaction);
        ebstoreTransaction = entityManagerTransaction.find(EBStore.class, ID_TRANSACTION);

        // creates an entity in the extended persistence context
        ebstoreExtended = createBean(ID_EXTENDED, NAME_EXTENDED);
        entityManagerExtended.persist(ebstoreExtended);
        ebstoreExtended = entityManagerExtended.find(EBStore.class, ID_EXTENDED);
    }

    /**
     * Verifies if the bean created in transaction-scoped persistence context in
     * the startup method, and, a bean that was goten using the find method have
     * different reference.
     */
    public void testTransactionPersistenceContext() {
        EBStore ebstoreResult = entityManagerTransaction.find(EBStore.class, ID_TRANSACTION);
        assertFalse(ebstoreResult == ebstoreTransaction,
                "The entities were goten in diferents persistence context, so they should not have the same reference.");
    }

    /**
     * Verifies if the bean created in extended persistence context in the
     * startup method, and, a bean that was goten using the find method have the
     * same reference.
     */
    public void testExtendedPersistenceContext() {
        EBStore ebstoreResult = entityManagerExtended.find(EBStore.class, ID_EXTENDED);
        assertTrue(ebstoreResult == ebstoreExtended,
                "The entities were goten in the same persistence context, so they should have the same reference.");
    }

}
