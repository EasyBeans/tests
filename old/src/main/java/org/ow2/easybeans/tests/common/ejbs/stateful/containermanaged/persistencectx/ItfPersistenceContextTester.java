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
 * $Id: ItfPersistenceContextTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectx;

/**
 * Tests if the bean reference are following the behaviour described in the
 * specification.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfPersistenceContextTester {

    /**
     * The primary key of the entity that is created in a transaction-scoped
     * persistence context.
     */
    Integer ID_TRANSACTION = new Integer(1);

    /**
     * The primary key of the entity that is created in an extended persistence
     * context.
     */
    Integer ID_EXTENDED = new Integer(2);

    /**
     * The name of the entity that is created in a transaction-scoped
     * persistence context.
     */
    String NAME_TRANSACTION = "bean1";

    /**
     * The name of the entity that is created in an extended persistence
     * context.
     */
    String NAME_EXTENDED = "bean2";

    /**
     * Creates the beans used during the test. A bean is created with a
     * transaction-scoped persistence context and other bean is created with a
     * extended persistence context.
     */
    void startup();

    /**
     * Makes a find with the primary key(ID_TRANSACTION), compares the result
     * with the bean created in the method setup.The two beans should not have
     * the same reference.
     */
    void testTransactionPersistenceContext();

    /**
     * Makes a find with the primary key(ID_EXTENDED), compares the result
     * with the bean created in the method setup.The two beans should have
     * the same reference.
     */
    void testExtendedPersistenceContext();

}
