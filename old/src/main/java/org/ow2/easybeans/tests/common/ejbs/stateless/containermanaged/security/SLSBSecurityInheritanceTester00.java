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
 * $Id: SLSBSecurityInheritanceTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.security;

import static org.testng.Assert.fail;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Verifies the inheritance for security polices.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@RunAs("mainrole")
@Stateless
@Remote
public class SLSBSecurityInheritanceTester00 implements ItfSecurityInheritanceTester {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(SLSBSecurityInheritanceTester00.class);

    /**
     * The bean used in the tests.
     */
    @EJB
    private ItfSecurityInheritance bean;

    /**
     * Verifies if a method that has the deny all police defined cannot be
     * called.
     */
    public void callMethodDenyAll() {
        try {
            bean.dummyMethod1();
            fail("The method has a denyAll annotation, so the bean cannot call this method.");
        } catch (EJBAccessException e) {
            logger.debug("The bean threw an expected exception {0}", e);
        }
    }

    /**
     * Verifies if method that allows the role mainrole can be called by a bean
     * with the annotation RunAs("mainrole").
     */
    public void callMethodMainRole() {
        bean.dummyMethod2();
    }

    /**
     * Verifies if a method with permitAll annotation can be called by everyone.
     */
    public void callMethodPermitAll() {
        bean.dummyMethod3();
    }
}
