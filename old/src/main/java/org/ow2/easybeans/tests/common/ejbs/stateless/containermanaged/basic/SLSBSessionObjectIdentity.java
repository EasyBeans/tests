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
 * $Id: SLSBSessionObjectIdentity.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimplePrintMessage;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.basic.SFSBDeployTest;
import org.ow2.easybeans.tests.common.helper.EJBHelper;


/**
 * Verifies the identity test for stateful and stateless beans.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless
@Remote
public class SLSBSessionObjectIdentity implements ItfSessionObjectIdentity {

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest")
    private ItfSimplePrintMessage slsbBean1;

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest")
    private ItfSimplePrintMessage slsbBean2;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest")
    private ItfSimplePrintMessage sfsbBean1;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest")
    private ItfSimplePrintMessage sfsbBean2;

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were injected by annotation.
     */
    public void testSLSameInstanceIdentityInjected() {
        assertTrue(slsbBean1.equals(slsbBean1), "The stateless bean is not equal to itself.");
    }

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with other bean that has the same name. Example
     * bean1.equals(bean2).The beans in this test were injected by annotation.
     */
    public void testSLDifferentInstanceIdentityInjected() {
        assertTrue(slsbBean1.equals(slsbBean2),
                "The stateless bean is not equal to other stateless with the same name.");
    }

    /**
     * Verifies if the method equals of a stateful bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were injected by annotation.
     */
    public void testSFSameInstanceIdentityInjected() {
        assertTrue(sfsbBean1.equals(sfsbBean1), "The stateful bean is not equal to itself.");
    }

    /**
     * Verifies if the method equals of a stateful bean returns false when the
     * bean is compared with other bean that has the same name. Example bean1.equals(bean2). The beans in
     * this test were injected by annotation.
     */
    public void testSFDifferentInstanceIdentityInjected() {
        assertFalse(sfsbBean1.equals(sfsbBean2), "The stateful bean is equal to other stateful with the same name.");
    }

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void testSLSameInstanceIdentityLookup() throws Exception {
        ItfSimplePrintMessage bean1 = EJBHelper
                .getBeanRemoteInstance(SLSBDeployTest.class, ItfSimplePrintMessage.class);
        assertTrue(bean1.equals(bean1), "The stateless bean is not equal to itself.");
    }

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with other bean that has the same name. Example bean1.equals(bean2). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void testSLDifferentInstanceIdentityLookup() throws Exception {
        ItfSimplePrintMessage bean1 = EJBHelper
                .getBeanRemoteInstance(SLSBDeployTest.class, ItfSimplePrintMessage.class);
        ItfSimplePrintMessage bean2 = EJBHelper
                .getBeanRemoteInstance(SLSBDeployTest.class, ItfSimplePrintMessage.class);
        assertTrue(bean1.equals(bean2), "The stateless bean is not equal to other stateless with the same name.");
    }

    /**
     * Verifies if the method equals of a stateful bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void testSFSameInstanceIdentityLookup() throws Exception {
        ItfSimplePrintMessage bean1 = EJBHelper
                .getBeanRemoteInstance(SFSBDeployTest.class, ItfSimplePrintMessage.class);
        assertTrue(bean1.equals(bean1), "The stateful bean is not equal to itself.");
    }

    /**
     * Verifies if the method equals of a stateful bean returns false when the
     * bean is compared with other bean that has the same name. Example bean1.equals(bean2). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void testSFDifferentInstanceIdentityLookup() throws Exception {
        ItfSimplePrintMessage bean1 = EJBHelper
                .getBeanRemoteInstance(SFSBDeployTest.class, ItfSimplePrintMessage.class);
        ItfSimplePrintMessage bean2 = EJBHelper
                .getBeanRemoteInstance(SFSBDeployTest.class, ItfSimplePrintMessage.class);
        assertFalse(bean1.equals(bean2), "The stateful bean is equal to other stateful with the same name.");
    }

}
