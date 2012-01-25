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
 * $Id: SecurityRolesBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.security;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security.ItfEJBContextMethods;

/**
 * Containes different types of polices. In this class, the methods do nothing,
 * but has different types of polices.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@DeclareRoles({"mainrole", "secondaryrole"})
@RolesAllowed({"mainrole"})
public class SecurityRolesBase implements ItfSecurityRoles {

    /**
     * The context used to test the security methods in the EJBContext
     * interface.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Bean used to test the getCallerPrincipal.
     */
    @EJB
    private ItfEJBContextMethods bean;

    /**
     * Method with the police permiteAll.
     */
    @PermitAll
    public void permitAllAttribute() {

    }

    /**
     * Method with the polcie denyAll.
     */
    @DenyAll
    public void denyAllAttribute() {

    }

    /**
     * Method that can be accessed only by the mainrole and the secondaryrole.
     */
    @RolesAllowed(value = {"mainrole", "secondaryrole"})
    public void permitTwoRoles() {

    }

    /**
     * Method that can be accessed only by the mainrole.
     */
    @RolesAllowed(value = {"mainrole"})
    public void permitOneRole() {

    }

    /**
     * Test the Roles declared on the bean class.
     * This has to be inherited by the method if nothing is set.
     */
    public void permitRolesOnBean() {

    }


    /**
     * Calls a method in other bean that returns the callerPrincipal. It
     * compares its caller with the caller returned. The caller for the two
     * methods must be the same.
     * @return true if the bean caller and the bean callee are called by the
     *         same role, false otherwise.
     */
    @PermitAll
    public boolean testCallerPrincipal() {
        Principal principalCaller = sessionContext.getCallerPrincipal();
        return principalCaller.equals(bean.getCallerPrincipal());
    }

    /**
     * Returns the bean caller principal.
     * @return the caller principal.
     */
    @PermitAll
    public Principal getCallerPrincipal() {
        return sessionContext.getCallerPrincipal();
    }

    /**
     * Compares the role in the parameter with the caller in role.
     * @param role the role name.
     * @return true if the caller has the role in the parameter, false
     *         otherwise.
     */
    @PermitAll
    public boolean isCallerinRole(final String role) {
        return sessionContext.isCallerInRole(role);
    }

}
