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
 * $Id: TestSecurityInheritance00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.security;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.security.ItfSecurityInheritanceTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.security.SLSBSecurityInheritanceTester00;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages the security police and inheritance. The
 * item is 17.3.2.1.
 * @reference JSR 220- FINAL RELEASE
 * @requirement Application Server must be running; the bean
 *              SLSBSecurityInheritanceTester00 and SLSBSecurityInheritance must be
 *              deployed.
 * @setup gets the reference of SLSBSecurityInheritanceTester00.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestSecurityInheritance00 {

    /**
     * Bean used during the tests.
     */
    private ItfSecurityInheritanceTester tester;

    /**
     * Creates the bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SLSBSecurityInheritanceTester00.class,
                ItfSecurityInheritanceTester.class);
    }

    /**
     * Verifies if a the annotation denyAll in a subclass method overrides the
     * others polices definition.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void callMethodDenyAll() {
        tester.callMethodDenyAll();
    }

    /**
     * Verifies if the police defined in the superclass is not overrided by the
     * subclass.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void callMethodMainRole() {
        tester.callMethodMainRole();
    }

    /**
     * Verifies if the police defined in the class is valid for all methods.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void callMethodPermitAll() {
        tester.callMethodPermitAll();
    }
}
