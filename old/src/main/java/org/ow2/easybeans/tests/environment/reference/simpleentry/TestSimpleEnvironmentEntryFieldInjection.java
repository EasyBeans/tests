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
 * $Id: TestSimpleEnvironmentEntryFieldInjection.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.reference.simpleentry;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry;
import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntryByDescriptor;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.simpleentry.SLSBSimpleEnvEntryByDescriptor;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.simpleentry.SLSBSimpleEnvEntryFieldInjection00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the simple environment entries injection is following the JSR 220.
 * The items covered in this test are: 16.4
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.simpleentry.*
 *              must be deployed. (Ant task: install.jar.tests.simple.environment.session)
 * @setup gets the reference of the bean
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSimpleEnvironmentEntryFieldInjection {

    /**
     * Bean used in tests.
     */
    private ItfSimpleEnvEntry bean;

    /**
     * Bean used in tests.
     */
    private ItfSimpleEnvEntryByDescriptor beanInjectionByDescriptor;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBSimpleEnvEntryFieldInjection00.class, ItfSimpleEnvEntry.class);
        beanInjectionByDescriptor = getBeanRemoteInstance(SLSBSimpleEnvEntryByDescriptor.class,
                ItfSimpleEnvEntryByDescriptor.class);
    }

    /**
     * Tests if a string value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testString00() {
        bean.checkString00();
    }

    /**
     * Tests if a character value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testCharacter00() {
        bean.checkCharacter00();
    }

    /**
     * Tests if an integer value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testInteger00() {
        bean.checkInteger00();
    }

    /**
     * Tests if a boolean value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testBoolean00() {
        bean.checkBoolean00();
    }

    /**
     * Tests if a double value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testDouble00() {
        bean.checkDouble00();
    }

    /**
     * Tests if a byte value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testByte00() {
        bean.checkByte00();
    }

    /**
     * Tests if a short value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testShort00() {
        bean.checkByte00();
    }

    /**
     * Tests if a byte value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testLong00() {
        bean.checkLong00();
    }

    /**
     * Tests if a float value was correctly injected.
     * @input -
     * @output -
     */
    @Test
    public void testFloat00() {
        bean.checkFloat00();
    }

    /**
     * Tests if a variable continues with its default value when the
     * evn-entry-value is not specified.
     * @input -
     * @output -
     */
    @Test
    public void testNotOverride00() {
        beanInjectionByDescriptor.checkNotOverride00();
    }

    /**
     * Tests if injection by deployment descriptor using injection-target tag is working properly.
     * @input -
     * @output -
     */
    @Test
    public void testInjectionByDeploymentDescriptor00() {
        beanInjectionByDescriptor.checkStringInjection00();
    }



}
