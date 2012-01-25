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
 * $Id: SLSBSecurityXMLBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.securityxml;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

/**
 * Stateless Bean with secured methods.
 * Two roles are declared : user and admin.
 * @author Florent Benoit
 */
public class SLSBSecurityXMLBean implements ItfSecurityXML {

    /**
     * SessionContext used to get current caller.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Link to run-as bean.
     */
    @EJB
    private ItfSecurityXMLRunAs other;

    /**
     * Method can be called by some roles.<br>
     * "user" and "admin" roles are authorized.
     * @return true if this is OK
     */
    public boolean someRolesAllowed() {
        return (sessionContext.isCallerInRole("user") || sessionContext.isCallerInRole("admin"));
    }

    /**
     * Method can be called by all security roles.
     */
    public void allRolesAllowed() {

    }

    /**
     * Only "admin" role can invoke this method.
     * @return true if this is OK for admin role
     */
    public boolean onlyAdminAllowed() {
        return sessionContext.isCallerInRole("admin");
    }

    /**
     * No role can invoke this method.
     */
    public void deniedForAll() {
        // nothing as it can't be called
        throw new RuntimeException("Method denied, should not be called");
    }


    /**
     * Make a call on a run-as bean which call our bean after that..
     */
    public void callRunAsBean() {
        other.callBeanWithRunAsAdmin();
    }
}
