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
 * $Id: ItfTestPCtxLifeCM00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interfaces;

/**
 * Interface for persistence context lifetime tests.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public interface ItfTestPCtxLifeCM00 {

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    void startUp() throws Exception;

    /**
     * Tests if an entity manager with a
     * transaction-scoped persistence context is working properly.
     * @throws Exception if a problem occurs.
     */
    void test00() throws Exception;

    /**
     * Tests if an entity manager with a
     * transaction-scoped persistence context is working properly.
     * @throws Exception if a problem occurs.
     */
    void test01() throws Exception;

    /**
     * Tests if an entity manager with a
     * transaction-scoped persistence context is working properly.
     * @throws Exception if a problem occurs.
     */
    void test02() throws Exception;

    /**
     * Tests if an entity manager with a
     * transaction-scoped persistence context is working properly.
     * @throws Exception if a problem occurs.
     */
    void test03() throws Exception;

    /**
     * Tests if an entity manager with a
     * transaction-scoped persistence context is working properly.
     * @throws Exception if a problem occurs.
     */
    void test04() throws Exception;

    /**
     * Removes old entity.
     * @throws Exception if a problem occurs
     */
    void tearDown() throws Exception;

}
