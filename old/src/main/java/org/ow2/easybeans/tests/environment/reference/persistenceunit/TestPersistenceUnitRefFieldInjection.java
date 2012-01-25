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
 * $Id: TestPersistenceUnitRefFieldInjection.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.reference.persistenceunit;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.persistenceunitref.SLSBPUnitRefFieldInjection00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the persistence unit references declaration is following the
 * JSR 220.
 * @reference JSR 220 - EJB 3.0 Core - 16.10
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.persistenceunitreference.*
 *              must be deployed.
 * @setup gets the reference of the bean
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestPersistenceUnitRefFieldInjection {

    /**
     * Bean used in tests.
     */
    private ItfCheck00 bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBPUnitRefFieldInjection00.class, ItfCheck00.class);
    }

    /**
     * Checks if the cotainer can: <li>inject only defined name, the unit name
     * is not used because there is only a persistent unit declared;</li> <li>inject
     * with defined name and unit name;</li> <li>inject without defined name,
     * the default name must be defined by the container.</li>
     * @input -
     * @output -
     */
    @Test
    public void test00() {
        bean.check();
    }
}
