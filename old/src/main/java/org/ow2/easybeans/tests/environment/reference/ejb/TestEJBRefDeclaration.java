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
 * $Id: TestEJBRefDeclaration.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.reference.ejb;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfEJBRef;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbref.SLSBEjbRefDeclaration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Verifies if the ejb references injection is following the JSR 220.
 * @reference JSR 220 - EJB 3.0 Core - 16.5
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbreference.*
 *              must be deployed.
 * @setup gets the reference of the bean
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestEJBRefDeclaration {

    /**
     * Bean used in tests.
     */
    private ItfEJBRef bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBEjbRefDeclaration.class, ItfEJBRef.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test00(){
        bean.check00();
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test01(){
        bean.check01();
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test02(){
        bean.check02();
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test03(){
        bean.check03();
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test04(){
        bean.check04();
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test05(){
        bean.check05();
    }

    /**
     * Checks if the annotation &#64;EJB is working properly.
     */
    @Test
    public void test06(){
        bean.check06();
    }

}
