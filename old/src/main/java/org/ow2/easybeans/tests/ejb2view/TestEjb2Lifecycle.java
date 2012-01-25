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
 * $Id: TestEjb2Lifecycle.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.ejb2view;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view.ItfEjb2ClientLifecycle;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view.SFSBEjb2ClientLifecycle;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies the compatibility between the ejb 2.1 and the ejb 3.0. More
 * specifically, it verifies if the callback methods are called when the bean
 * implements the interface sessionBeans.
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the beans
 *              SFSBEjb2ClientLifecycle and SimpleEjb2LifecyleBean must be
 *              deployed.
 * @setup gets a reference of the bean SFSBEjb2ClientLifecycle.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEjb2Lifecycle {

    /**
     * Bean that access the bean with ejb 2.1 remote view.
     */
    private ItfEjb2ClientLifecycle bean;

    /**
     * Gets an instance of the bean.
     * @throws Exception if an error occurs.
     */
    @BeforeMethod
    public void setup() throws Exception {
        // Gets a bean instance.
        bean = EJBHelper.getBeanRemoteInstance(SFSBEjb2ClientLifecycle.class, ItfEjb2ClientLifecycle.class);
    }

    /**
     * Verifies if the ejbPassivate method is called. The test was not
     * implemented yet.
     * @input -
     * @output -
     */
    @Test
    public void testEjbPassivate() {
        bean.verifyPassivate();
    }

    /**
     * Verifies if the ejbActivate method is called. The test was not
     * implemented yet.
     * @input -
     * @output -
     */
    @Test
    public void testEjbActivate() {
        bean.verifyActivate();
    }

    /**
     * Verifies if the ejbRemove is called. The method remove creates log in the
     * database to register the call.
     * @input -
     * @output the correct method execution. The log in the database is
     *         verified.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testEjbRemove() throws Exception {
        bean.verifyRemove();
    }
}
