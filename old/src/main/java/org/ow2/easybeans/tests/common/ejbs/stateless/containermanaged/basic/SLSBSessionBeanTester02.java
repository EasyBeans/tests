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
 * $Id: SLSBSessionBeanTester02.java 5369 2010-02-24 14:58:19Z benoitf $
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
public class SLSBSessionBeanTester02 implements ItfSessionBeanTester01 {

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest03")
    private ItfSimplePrintMessage slsbBeanRemote;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest03")
    private ItfSimplePrintMessage sfsbBeanRemote;

    /**
     * Simple stateless bean.
     */
    @EJB(beanName = "SLSBDeployTest04")
    private ItfSimplePrintMessage slsbBeanLocal;

    /**
     * Simple stateful bean.
     */
    @EJB(beanName = "SFSBDeployTest04")
    private ItfSimplePrintMessage sfsbBeanLocal;

    /**
     * Verifies if the local instance of the stateless bean injected is not
     * null. The local bean interface as well as the annotation are defined in
     * the superclass.
     */
    public void testSLLocal(){
        slsbBeanLocal.getMessage();
    }

    /**
     * Verifies if the local instance of the stateful bean injected is not
     * null. The local bean interface as well as the annotation are defined in
     * the superclass.
     */
    public void testSFLocal(){
        sfsbBeanLocal.getMessage();
    }

    /**
     * Verifies if the remtoe instance of the stateless bean injected is not
     * null. The remote bean interface as well as the annotation are defined in
     * the superclass.
     */
    public void testSLRemote(){
        slsbBeanRemote.getMessage();
    }

    /**
     * Verifies if the remote instance of the stateful bean injected is not
     * null. The remote bean interface as well as the annotation are defined in
     * the superclass.
     */
    public void testSFRemote(){
        sfsbBeanRemote.getMessage();
    }

}
