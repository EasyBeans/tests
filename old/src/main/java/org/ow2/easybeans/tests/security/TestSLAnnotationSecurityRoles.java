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
 * $Id: TestSLAnnotationSecurityRoles.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.security;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security.ItfSecurityRolesTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security.SFSBSecurityRolesTester01;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages the security roles defined by annotation,
 * as well as verifies the methods in the session context related with security.
 * The bean used during the tests is stateless. The chapter verified is the 17.
 * @reference JSR 220- FINAL RELEASE
 * @requirement Application Server must be running; the bean
 *              SLSBSecurityRolesTester and SLSBSecurityRoles must be deployed.
 * @setup gets the reference of SLSBSecurityRolesTester.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestSLAnnotationSecurityRoles {

    /**
     * Bean used during the tests.
     */
    private ItfSecurityRolesTester tester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SFSBSecurityRolesTester01.class, ItfSecurityRolesTester.class);
    }

    /**
     * Test if the permit all role works. The bean call a method with the
     * annotation permitAll.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testPermitAll() {
        tester.testPermitAll();
    }

    /**
     * Test if the deny all role works. The bean call a method with the
     * annotation denyAll.
     * @input -
     * @output the correct method execution. The EJBAccessException is verified
     *         in the server site.
     */
    @Test
    public void testDenyAll() {
        tester.testDenyAll();
    }


    /**
     * Verifies if the role defined in the roles allowed can access the method. The annotation has only the role defined.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testAllowedRolesWithOneRole() {
        tester.testAllowedRolesWithOneRole();
    }

    /**
     * Verifies if the role defined in the roles allowed at bean level can access the method.
     * The annotation has only the role defined.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testAllowedRolesDeclaredAtBeanLevel() {
        tester.testAllowedRolesDeclaredAtBeanLevel();
    }

    /**
     * Verifies if the role defined in the roles allowed can access the method. The annotation has two roles defined.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testAllowedRolesWithTwoRoles() {
        tester.testAllowedRolesWithTwoRoles();
    }

    /**
     * Verifies if the correct exception(Runtime or subclass) is thrown when the deprecated method getCallerIdentity is called.
     * @input -
     * @output the correct method execution, the exception is verified in the server side.
     *
     */
    @Test
    public void testGetCallerIdentity() {
        tester.testGetCallerIdentity();
    }

    /**
     * Verifies if the annotation RunAs does not change the current caller for a
     * class the has the RunAs(bean1). The bean1 call a method in other bean
     * that returns the caller principal, this caller must be different of the
     * current bean1 caller.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testGetCallerPrincipalDifferentCaller() {
        tester.testGetCallerPrincipalDifferentCaller();
    }

    /**
     * Verifies if the caller is propagated among the beans. The bean1(that has
     * not the annotation RunAs) call the bean2, the both caller principal must
     * be the same.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testGetCallerPrincipalSameCaller() {
        tester.testGetCallerPrincipalSameCaller();
    }

    /**
     * Verifies if the isCallerInRole returns true when the correct role name is
     * used. The bean1 has the annotation RunAs(role1) and call the bean2 that
     * verifies if the isCallerInRoleMethod(role1) returns true.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testIsCallerInRoleCorrect() {
        tester.testIsCallerInRoleCorrect();
    }

    /**
     * Verifies if the correct exception(Runtime or subclass) is thrown when the
     * deprecated method getCallerInRole(Identity identity) is called.
     * @input -
     * @output the correct method execution, the exception is verified in the
     *         server side.
     */
    @Test
    public void testIsCallerInRoleDeprecated() {
        tester.testIsCallerInRoleDeprecated();
    }

    /**
     * Verifies if the isCallerInRole returns false when the incorrect role name is
     * used. The bean1 has the annotation RunAs(role1) and call the bean2 that
     * verifies if the isCallerInRoleMethod(roleX) returns false.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testIsCallerInRoleIncorrect() {
        tester.testIsCallerInRoleIncorrect();
    }
}
