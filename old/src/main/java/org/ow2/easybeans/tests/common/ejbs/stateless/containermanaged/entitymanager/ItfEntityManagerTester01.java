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
 * $Id: ItfEntityManagerTester01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.entitymanager;

/**
 * Verifies if the container provides a correct implementation of the methods:
 * merge, contains and refresh.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfEntityManagerTester01 {

    /**
     * Verifies if the container can make a merge in a detached entity.
     * @param id the bean primary key.
     * @param name the bean name.
     * @param newName the bean name after it became dectached.
     */
    void mergeEBStore(final int id, final String name, final String newName);

    /**
     * Verifies if the conatins method return the correct bean.
     * @param id the bean primary key.
     * @param name the bean name.
     */
    void containsEBStore(final int id, final String name);

    /**
     * Verifies if the container makes a refresh of a modified bean.
     * @param id the entity primary key.
     * @param name the bean name.
     * @param newName the bean name after it became detached.
     */
    void refreshEBStore(final int id, final String name, final String newName);

    /**
     * Removes the entity that is in the database.
     * @param id the entity primary key.
     */
    void removeEBStore(final int id);

}
