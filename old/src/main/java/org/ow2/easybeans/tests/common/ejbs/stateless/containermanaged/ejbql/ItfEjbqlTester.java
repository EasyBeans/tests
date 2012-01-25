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
 * $Id: ItfEjbqlTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbql;

/**
 * Verifies the EJB QL main function.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfEjbqlTester {

    /**
     * Verifies if the container manages a path expression.
     */
    void testPathExpression();

    /**
     * Verifies if the inner join works.
     */
    void testInnerJoin();

    /**
     * Verifies if the container can manage the query with is empty.
     */
    void testIsEmpty();

    /**
     * Verifies if the container can make a bulk operation delete.
     */
    void testDelete();

    /**
     * Verifies if the container can make a bulk operation update.
     */
    void testUpdate();

    /**
     * Verifies if the clause having works properly.
     */
    void testHaving();

}
