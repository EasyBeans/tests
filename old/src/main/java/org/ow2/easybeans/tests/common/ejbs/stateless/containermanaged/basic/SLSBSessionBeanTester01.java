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
 * $Id: SLSBSessionBeanTester01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimplePrintMessage;

/**
 * Verifies if a bean that does not implemesnts the local and remote business
 * interface can be deployed and injected.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote
public class SLSBSessionBeanTester01 implements ItfSessionBeanTester01 {

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest01")
    private ItfSimplePrintMessage slsbBeanRemote;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest01")
    private ItfSimplePrintMessage sfsbBeanRemote;

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest02")
    private ItfSimplePrintMessage slsbBeanLocal;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest02")
    private ItfSimplePrintMessage sfsbBeanLocal;

    /**
     * Verifies if the instance of the bean injected is not null. The local bean
     * interface is defined, but the bean does not implements the interface.The bean is stateless.
     */
    public void testSLLocal(){
        slsbBeanLocal.getMessage();
    }

    /**
     * Verifies if the instance of the bean injected is not null. The local bean
     * interface is defined ,but the bean does not implements the interface.The bean is stateful.
     */
    public void testSFLocal(){
        sfsbBeanLocal.getMessage();
    }

    /**
    * Verifies if the instance of the bean injected is not null. The remote bean
    * interface is defined, but the bean does not implements the interface.The bean is stateless.
    */
    public void testSLRemote(){
        slsbBeanRemote.getMessage();
    }

    /**
    * Verifies if the instance of the bean injected is not null. The remote bean
    * interface is defined, but the bean does not implements the interface.The bean is stateful.
    */
    public void testSFRemote(){
        sfsbBeanRemote.getMessage();
    }

}
