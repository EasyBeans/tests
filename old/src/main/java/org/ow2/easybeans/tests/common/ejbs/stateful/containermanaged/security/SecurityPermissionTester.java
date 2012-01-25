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
 * $Id: SecurityPermissionTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security;

import static org.testng.Assert.fail;

import javax.annotation.security.RunAs;
import javax.ejb.EJBAccessException;

import org.ow2.easybeans.tests.common.ejbs.base.security.ItfSecurityRoles;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Test if the policies work property.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@RunAs("secondaryrole")
public abstract class SecurityPermissionTester implements ItfSecurityPermissionTester {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(SecurityPermissionTester.class);


   /**
     * The bean used to test the roles.
     * @return the bean.
     */
    public abstract ItfSecurityRoles getBean();


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
     * Verifies if the allowedRoles police for one role(the role that is not
     * defined in the RunAs) works. The method must throw an exception.
     */
    public void testAllowedRolesWithOneRole() {
        try {
            getBean().permitOneRole();
            fail("The method has an allowsRoles annotation that does not contains the value "
                    + "in secondaryrole defined, so the bean cannot call this method.");
        } catch (EJBAccessException e) {
            logger.debug("The bean threw an expected exception {0}", e);
        }
    }


    /**
     * Verifies if the allowedRoles police for one role(the role that is not
     * defined in the RunAs) works. The method must throw an exception.
     */
    public void testAllowedRolesDeclaredAtBeanLevel() {
        try {
            getBean().permitRolesOnBean();
            fail("The method has an allowsRoles annotation that does not contains the value "
                    + "in secondaryrole defined, so the bean cannot call this method.");
        } catch (EJBAccessException e) {
            logger.debug("The bean threw an expected exception {0}", e);
        }
    }

}
