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
 * $Id: ItfSessionBeanTester01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

/**
 * Verifies if a bean that does not implemesnts the local and remote business
 * interface can be deployed and injected.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfSessionBeanTester01 {

    /**
     * Verifies if the instance of the bean injected is not null. The local bean
     * interface is defined, but the bean does not implements the interface.The bean is stateless.
     */
    void testSLLocal();

    /**
     * Verifies if the instance of the bean injected is not null. The local bean
     * interface is defined ,but the bean does not implements the interface.The bean is stateful.
     */
    void testSFLocal();

    /**
     * Verifies if the instance of the bean injected is not null. The remote bean
     * interface is defined, but the bean does not implements the interface.The bean is stateless.
     */
    void testSLRemote();

    /**
     * Verifies if the instance of the bean injected is not null. The remote bean
     * interface is defined, but the bean does not implements the interface.The bean is stateful.
     */
    void testSFRemote();

}
