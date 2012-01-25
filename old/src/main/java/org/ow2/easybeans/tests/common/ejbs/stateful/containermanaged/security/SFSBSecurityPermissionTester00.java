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
 * $Id: SFSBSecurityPermissionTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.security;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.base.security.ItfSecurityRoles;

/**
 * Verifies if the polices are verified for stateful bean.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateful
@Remote(ItfSecurityPermissionTester.class)
public class SFSBSecurityPermissionTester00 extends SecurityPermissionTester{

    /**
     * Bean used to test the security roles.
     */
    @EJB(beanName = "SFSBSecurityRolesAnnotation")
    private ItfSecurityRoles bean;

    /**
     * Returns the bean used in the test.
     * @return the bean.
     */
    @Override
    public ItfSecurityRoles getBean() {
        return bean;
    }

}
