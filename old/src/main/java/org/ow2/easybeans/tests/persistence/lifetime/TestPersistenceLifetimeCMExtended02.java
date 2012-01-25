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
 * $Id: TestPersistenceLifetimeCMExtended02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.persistence.lifetime;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifeCMETest00;
import org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.persistencectxlife.SFSBBeanManagedPCtxLifeCMETest00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests container-managed extended-scoped persistence context. It uses a Stateful as client and a
 * Stateful with extendended-scoped persistence context.
 * @reference JSR 220 - Persistence API - FINAL DRAFT - 5.6.2
 * @requirement Application Server must be running; the package
 *              org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.persistencectxlife
 *              must be deployed
 * @setup gets the reference of the bean.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestPersistenceLifetimeCMExtended02 {

    /**
     * Bean.
     */
    private ItfPCtxLifeCMETest00 bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SFSBBeanManagedPCtxLifeCMETest00.class, ItfPCtxLifeCMETest00.class);
    }

    /**
     * Tests if an entity manager with a Stateful bean managed can manage an
     * extended-scoped persistence context. The Stateful creates a transaction
     * that must be propagated to another Stateful which has an extended persistence context. After the commit,
     * it is check if the context is still managed. In the second step,
     * the test verifies using another bean method if the entity is still managed.
     * @input With providing a client(stateful bean manged) transaction,
     * invocation of a bean method which creates an entity and persists it.
     * @output A managed entity.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test00() throws Exception {
        bean.create();
        bean.check();
    }
}
