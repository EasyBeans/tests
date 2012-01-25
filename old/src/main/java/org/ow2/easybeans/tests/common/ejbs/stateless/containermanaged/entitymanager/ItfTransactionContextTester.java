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
 * $Id: ItfTransactionContextTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * Verifies the use of the entity manager in different transaction context.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfTransactionContextTester {

    /**
     * The bean id.
     */
    int ID = 1;

    /**
     * The bean name.
     */
    String NAME = "test";

    /**
     * Creates a entity bean in a transaction context and call other method that has a different
     * transaction to persist the bean.
     */
    void createBeanRequiresNewWithClientTransaction();

    /**
     * Creates a entity bean without a transaction context and call other method
     * that has a transaction to persist the bean.
     */
     void createBeanRequiresNewWithoutClientTransaction();

    /**
     * Creates a entity bean and call other method that has the same transaction
     * to persist the bean.
     */
    void createBeanRequired();

    /**
     * Makes the persist and does not use the client transaction.
     * @param entity the entity class to be stored.
     */
    void persistRequiresNew(final EBStore entity);

    /**
     * Makes the persist and use the client transaction.
     * @param entity the entity class to be stored.
     */
    void persistRequired(final EBStore entity);

    /**
     * Deletes the bean with the default value.
     */
    void deleteBean();

}
