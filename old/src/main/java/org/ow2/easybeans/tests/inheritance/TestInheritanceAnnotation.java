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
 * $Id: TestInheritanceAnnotation.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.inheritance;

import static org.testng.Assert.assertNotNull;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.inheritance.SFSBAccessResource;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBExtAccessSessionContext;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if the bean inheritance is following the JSR 220 spec.The item 15.2.2
 * says that the injection must be available in the inheritance hierarchy.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean that implements the
 *              interface
 *              org.ow2.easybeans.tests.common.inheritance.ItfAcessSessionContext
 *              must be deployed.
 * @setup gets the reference of bean SFSBAccessResource.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestInheritanceAnnotation {

    /**
     * The bean used during the test.
     */
    private ItfAccessSessionContext sfsbAccessResource;

    /**
     * The bean used during the test.
     */
    private ItfAccessSessionContext sfsbExtendedAccessResource;

    /**
     * Creates a bean to be used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void setup() throws Exception {
        // gets a bean
        sfsbAccessResource = EJBHelper.getBeanRemoteInstance(SFSBAccessResource.class, ItfAccessSessionContext.class);

        // gets a bean
        sfsbExtendedAccessResource = EJBHelper.getBeanRemoteInstance(SLSBExtAccessSessionContext.class,
                ItfAccessSessionContext.class);
    }

    /**
     * Verifies if a subclass can use the SessionContext injected in the
     * superclass.
     * @input -
     * @output the return SessionContext variable is not egual a null.
     * @throws Exception if an error during the test occurs.
     */
    @Test
    public void testSessionContextInheritance00() throws Exception {
        Object obj = sfsbAccessResource.accessSessionContext(null);
        assertNotNull(obj);
    }

    /**
     * Verifies if a subclass can use a SessionContext injected in the superclas
     * that is not direct ancestor.
     * @input -
     * @output the return SessionContext variable is not egual a null.
     * @throws Exception if an error during the test occurs.
     */
    @Test
    public void testSessionContextInheritance01() throws Exception {
        Object obj = sfsbExtendedAccessResource.accessSessionContext(null);
        assertNotNull(obj);
    }
}
