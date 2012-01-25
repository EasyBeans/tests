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
 * $Id: ItfSecurityXML.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.securityxml;


/**
 * Remote interface of the Stateless Bean.
 * @author Florent Benoit
 */
public interface ItfSecurityXML {

    /**
     * Method can be called by some roles.
     * @return true if this is OK
     */
    boolean someRolesAllowed();

    /**
     * Method can be called by all security roles.
     */
    void allRolesAllowed();

    /**
     * Only "admin" role can invoke this method.
     * @return true if this is OK for admin role
     */
    boolean onlyAdminAllowed();

    /**
     * No role can invoke this method.
     */
    void deniedForAll();

    /**
     * Make a call on a run-as bean which call our bean after that..
     */
    void callRunAsBean();
}
