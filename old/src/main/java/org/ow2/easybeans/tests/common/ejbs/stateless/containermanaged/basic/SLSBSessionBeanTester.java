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
 * $Id: SLSBSessionBeanTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

import static org.testng.Assert.assertEquals;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimplePrintMessage;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.basic.SFSBDeployTest;
import org.ow2.easybeans.tests.common.helper.EJBHelper;

/**
 * Verfifies if a bean that was defined only with the annotation remote without
 * parameters works well.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote
public class SLSBSessionBeanTester implements ItfSessionBeanTester {

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest")
    private ItfSimplePrintMessage slsbBean1;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest")
    private ItfSimplePrintMessage sfsbBean1;

    /**
     * Calls the method getDefaultMessage() of the stateless bean that was
     * injected.
     */
    public void testSLSBByInjection() {
        assertEquals(slsbBean1.getDefaultMessage(), ItfSimplePrintMessage.DEFAULT_MESSAGE,
                "The bean with the remote interface did not returned the default message.");
    }

    /**
     * Calls the method getDefaultMessage() of the stateless bean that was goten
     * by lookup.
     * @throws Exception if a lokkup error occurs.
     */
    public void testSLSBByLookup() throws Exception {
        ItfSimplePrintMessage slsbBean2 = EJBHelper.getBeanRemoteInstance(SLSBDeployTest.class,
                ItfSimplePrintMessage.class);
        assertEquals(slsbBean2.getDefaultMessage(), ItfSimplePrintMessage.DEFAULT_MESSAGE,
                "The bean with the remote interface did not returned the default message.");
    }

    /**
     * Calls the method getDefaultMessage() of the stateful bean that was
     * injected.
     */
    public void testSFSBByInjection() {
        assertEquals(sfsbBean1.getDefaultMessage(), ItfSimplePrintMessage.DEFAULT_MESSAGE,
                "The bean with the remote interface did not returned the default message.");
    }

    /**
     * Calls the method getDefaultMessage() of the stateful bean that was goten
     * by lookup.
     * @throws Exception if a lokkup error occurs.
     */
    public void testSFSBByLookup() throws Exception {
        ItfSimplePrintMessage sfsbBean2 = EJBHelper.getBeanRemoteInstance(SFSBDeployTest.class,
                ItfSimplePrintMessage.class);
        assertEquals(sfsbBean2.getDefaultMessage(), ItfSimplePrintMessage.DEFAULT_MESSAGE,
                "The bean with the remote interface did not returned the default message.");
    }

}
