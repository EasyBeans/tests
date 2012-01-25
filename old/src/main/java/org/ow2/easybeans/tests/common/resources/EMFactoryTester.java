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
 * $Id: EMFactoryTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * EntityManagerFactory tester.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class EMFactoryTester {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(EMFactoryTester.class);

    /**
     * Name.
     */
    public static final String NAME = "Toto";

    /**
     * Entity Factory.
     */
    @PersistenceUnit(unitName = "testEntity00")
    private EntityManagerFactory entityFactory = null;

    /**
     * Default Constructor.
     */
    public EMFactoryTester() {
    }

    /**
     * Tests an EntityFactoryManager access.
     * @throws Exception if a problem occurs.
     */
    protected void access00() throws Exception {
        checkInstance(entityFactory, NAME);
    }

    /**
     * Checks if an entity manager factory reference is working well.
     * @param ref Entity Manager Factory
     * @param name row name
     * @throws Exception if a problem occurs
     */
    public static void checkInstance(final EntityManagerFactory ref, final String name) throws Exception {
        EntityManager entityManager = ref.createEntityManager();
        logger.debug("Checking an entity manager factory reference.");

        if (entityManager == null) {
            logger.debug("The method createEntityManager() returned null.");
            throw new IllegalStateException("Error checking an entity manager factory reference.");
        }

        EntityManagerTester.checkInstance(entityManager, name);

        logger.debug("Entity manager factory reference is working properly.");
    }
}
