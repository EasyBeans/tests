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
 * $Id: SLSBSecurityXMLRunAsBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.securityxml;

import javax.ejb.EJB;

/**
 * Stateless Bean with secured methods. Two roles are declared : user and admin.<br>
 * The Bean should take as run-as role the admin role
 * @author Florent Benoit
 */
public class SLSBSecurityXMLRunAsBean implements ItfSecurityXMLRunAs {

    /**
     * Link to bean.
     */
    @EJB
    private ItfSecurityXML other;

    /**
     * Call method that is allowed only for admin. (but use run-as role).
     */
    public void callBeanWithRunAsAdmin() {
        if (!other.onlyAdminAllowed()) {
            throw new IllegalStateException("The admin role has not been propagated when calling onlyAdminAllowed() method");
        }

        // Also call a method that permit two roles
        if (!other.someRolesAllowed()) {
            throw new IllegalStateException(
                    "The admin or user roles has not been propagated when calling onlyAdminAllowed() method");
        }

    }

}
