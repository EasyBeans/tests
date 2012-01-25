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
 * $Id: ItfSecurityPermissionTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security;

/**
 * Test if the policies work property.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfSecurityPermissionTester {

    /**
     * Verifies if the permitAll police works.
     */
    void testPermitAll();

    /**
     * Verifies if the denyAll police works.
     */
    void testDenyAll();

    /**
     * Verifies if the allowedRoles police for two roles(the role specified in
     * the RunAs and other role) works.
     */
    void testAllowedRolesWithTwoRoles();

    /**
     * Verifies if the allowedRoles police for one role(the role that is not
     * defined in the RunAs) works. The method must throw an exception.
     */
    void testAllowedRolesWithOneRole();

}
