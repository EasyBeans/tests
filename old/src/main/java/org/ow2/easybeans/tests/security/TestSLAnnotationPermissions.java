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
 * $Id: TestSLAnnotationPermissions.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.security;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security.ItfSecurityPermissionTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security.SFSBSecurityPermissionTester01;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages the security roles defined by annotation.
 * The bean used during the tests is stateless. The chapter verified is the 17.
 * @reference JSR 220- FINAL RELEASE
 * @requirement Application Server must be running; the bean
 *              SLSBSecurityPermissionTester00 and SFSBSecurityRoles must be deployed.
 * @setup gets the reference of SLSBSecurityPermissionTester00.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestSLAnnotationPermissions {

    /**
     * Bean used during the tests.
     */
    private ItfSecurityPermissionTester tester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SFSBSecurityPermissionTester01.class, ItfSecurityPermissionTester.class);
    }

    /**
     * Verifies if it is possible to call a method with permitAll annotation.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testPermitAll(){
       tester.testPermitAll();
    }

    /**
     * Verifies if it is not possible call a method with denyAll annotation.
     * @input -
     * @output the correct method execution. The EJBAccessException is verified in the server site.
     */
    @Test
    public void testDenyAll(){
        tester.testDenyAll();
    }

    /**
     * Verifies if a method with the allowsRoles annotation can be called by a
     * bean that is defined in the annotation.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testAllowedRolesWithTwoRoles(){
        tester.testAllowedRolesWithTwoRoles();
    }

    /**
     * Verifies if the container does not permit that a bean can call a method
     * in which it has the access denied.
     * @input -
     * @output the correct method execution. The EJBAccessException is verified
     *         in the server site.
     */
    @Test
    public void testAllowedRolesWithOneRole(){
        tester.testAllowedRolesWithOneRole();
    }
}
