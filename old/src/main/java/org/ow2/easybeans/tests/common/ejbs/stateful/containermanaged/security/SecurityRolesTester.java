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
 * $Id: SecurityRolesTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;

import org.ow2.easybeans.tests.common.ejbs.base.security.ItfSecurityRoles;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Test the differents roles types and also the methods related with security
 * in the EJBContext.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@RunAs("mainrole")
public abstract class SecurityRolesTester implements ItfSecurityRolesTester {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(SecurityRolesTester.class);

    /**
     * The bean used to test the roles.
     * @return the bean.
     */
    public abstract ItfSecurityRoles getBean();

    /**
     * The session context.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Verifies if the caller is propagated among the beans.
     */
    public void testGetCallerPrincipalSameCaller() {
        assertTrue(getBean().testCallerPrincipal(), "The method getCallerPrincipal is not working properly. Two bean"
                + " with the same caller returned different values.");
    }

    /**
     * Verifies if the annotation RunAs is setting the role only for the callee
     * and not for the caller.
     */
    public void testGetCallerPrincipalDifferentCaller() {
        assertFalse(getBean().getCallerPrincipal().equals(sessionContext.getCallerPrincipal()),
                "The method getCallerPrincipal is not working properly. The bean has a RunAs "
                        + "definition in the class, but this is valid only for the callee.Consequently, "
                        + "the getCallerPrincipal in this method and in the callee must be different.");
    }

    /**
     * Verifies if the method is caller in role returns false for the incorrect
     * role.
     */
    public void testIsCallerInRoleIncorrect() {
        assertFalse(getBean().isCallerinRole("secondaryrole"),
                "The caller has the runAs = secondaryrole and the method isCallerInRole in the callee returns true");
    }

    /**
     * Verifies if the method is caller in role returns true for the correct
     * role.
     */
    public void testIsCallerInRoleCorrect() {
        assertTrue(getBean().isCallerinRole("mainrole"),
                "The caller has the runAs = mainrole and the method isCallerInRole in the callee returns false");
    }

    /**
     * Verifies if the method that is not implemented throws the correct
     * exception.
     */
    @SuppressWarnings("deprecation")
    public void testGetCallerIdentity() {
        try {
            sessionContext.getCallerIdentity();
            fail("The container did not throw an exception when the method getCallerIdentity was called.");
        } catch (RuntimeException e) {
            logger.debug("The bean threw an expected exception {0}", e);
        }
    }

    /**
     * Verifies if the method that is not implemented throws the correct
     * exception.
     */
    @SuppressWarnings("deprecation")
    public void testIsCallerInRoleDeprecated() {
        try {
            sessionContext.isCallerInRole(new DummyIdentity());
            fail("The container did not throw an exception when the method isCallerInRole(Identity arg) was called.");
        } catch (RuntimeException e) {
            logger.debug("The bean threw an expected exception {0}", e);
        }
    }

    /**
     * Verifies if the permitAll police works.
     */
    public void testPermitAll() {
        getBean().permitAllAttribute();
    }

    /**
     * Verifies if the denyAll police works.
     */
    public void testDenyAll() {
        try{
            getBean().denyAllAttribute();
            fail("The method has a denyAll annotation, so the bean cannot call this method.");
        }catch(EJBAccessException e){
            logger.debug("The bean threw an expected exception {0}", e);
        }

    }

    /**
     * Verifies if the allowedRoles police for two roles(the role specified in
     * the RunAs and other role) works.
     */
    public void testAllowedRolesWithTwoRoles() {
        getBean().permitTwoRoles();
    }

    /**
     * Verifies if the allowedRoles police for one role(the role specified in
     * the RunAs) works.
     */
    public void testAllowedRolesWithOneRole() {
        getBean().permitOneRole();
    }

    /**
     * Verifies if the allowedRoles police for one role(the role that is not
     * defined in the RunAs) works. The method must throw an exception.
     */
    public void testAllowedRolesDeclaredAtBeanLevel() {
        getBean().permitRolesOnBean();
    }

}
