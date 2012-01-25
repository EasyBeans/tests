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
 * $Id: TestLocalAnnotation00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.annotations;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.local.SLSBTestLocalAnnotation00;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.interfaces.ItfTestLocalAnnotation00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the Local annotation.
 * @reference JSR 220-PROPOSED FINAL - SIMPLIFIED API - Chapter 10.2
 * @requirement Application Server must be running; the beans
 *              S*LocalAnnotation* must be deployed.
 * @setup gets the reference of S*LocalAnnotation*
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestLocalAnnotation00 implements ItfTestLocalAnnotation00{

    /**
     * Bean used in the tests.
     */
    private ItfTestLocalAnnotation00 bean;

    /**
     * Creates a bean and use a method.
     * @input List with no values inside
     * @output "JNDI" String.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testRun00() throws Exception{
        bean.testRun00();
    }

    /**
     * Creates a bean and use a method.
     * @input List with no values inside
     * @output "EJB" String.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testRun01() throws Exception{
        bean.testRun01();
    }

    /**
     * Creates a bean and use a method.
     * @input List with no values inside
     * @output "EJB" String.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testRun02() throws Exception{
        bean.testRun02();
    }

    /**
     * Gets bean instance used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception{
        bean = EJBHelper.getBeanRemoteInstance(SLSBTestLocalAnnotation00.class, ItfTestLocalAnnotation00.class);
    }
}
