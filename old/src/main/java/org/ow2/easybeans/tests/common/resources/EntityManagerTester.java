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
 * $Id: EntityManagerTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;


/**
 * EntityManager Tester.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class EntityManagerTester {

    /**
     * Name.
     */
    public static final String NAME = "Toto2";

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName="testEntity00")
    private EntityManager entityManager = null;

    /**
     * Default Constructor.
     */
    public EntityManagerTester(){

    }

    /**
     * Tests an EntityFactoryManager access.
     * @throws Exception if a problem occurs.
     */
    protected void access00() throws Exception{
        checkInstance(entityManager, NAME);
    }

    /**
     * Checks if an entity manager reference is working well.
     * @param entityManager Entity Manager
     * @param name row name
     * @throws Exception if a problem occurs
     */
    public static void checkInstance(final EntityManager entityManager, final String name) throws Exception{
        EBStore store = new EBStore();
        store.setId(store.hashCode());
        store.setName(name);
        entityManager.persist(store);

        // flush only if there is a transaction
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        if (utx != null) {
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                entityManager.flush();
            }
        }

        if (entityManager.find(EBStore.class, Integer.valueOf(store.hashCode())) == null){
            throw new Exception("EBStore was created, but it wasn't found after the commit.");
        }
    }
}
