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
 * $Id: ItfCascadeTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.cascadeoperation;

/**
 * Verifies if the container can manage the operations in cascade.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfCascadeTester {

    /**
     * Inserts the entities in the database.
     *
     */
    void startup();

    /**
     * Verifies if the container manages a refresh in cascade.
     *
     */
    void verifyCascadeTypeRefresh();

    /**
     * Verifies if the container manages a remove in cascade.
     *
     */
    void verifyCascadeTypeRemove();

    /**
     * Verifies if the container manages a merge in cascade.
     *
     */
    void verifyCascadeTypeMerge();

    /**
     * Verifies if the container manages a persist in cascade.
     *
     */
    void verifyCascadeTypePersist();

}
