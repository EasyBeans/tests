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
 * $Id: ItfInvocationParameterTest.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.invocationcontext;

/**
 * This interface is used to test if the invocation context getParameters() and setParameter() work following the specification.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public interface ItfInvocationParameterTest {

    /**
     * Verifies if the intercepted method parameters can be modified to a null
     * reference. The interceptor method is on the bean class.
     * @param beanClass the bean used to test
     * @throws Exception if there is a problem with the test.
     */
    void testNull(Class beanClass) throws Exception;

    /**
     * Verifies if the intercepted method parameters can be returned WITHOUT
     * modifications. The interceptor is a external class.
     * @param beanClass the bean used to test
     * @throws Exception if there is a problem with the test.
     */
    void testWithoutModification(Class beanClass) throws Exception;

    /**
     * Verifies if the intercepted method parameters can be returned WITH
     * modifications. The interceptor is a external class.
     * @param beanClass the bean used to test
     * @throws Exception if there is a problem with the test.
     */
    void testWithModification(Class beanClass) throws Exception;
}
