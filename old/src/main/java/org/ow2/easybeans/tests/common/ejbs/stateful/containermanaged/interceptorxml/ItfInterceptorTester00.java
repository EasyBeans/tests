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
 * $Id: ItfInterceptorTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml;

/**
 * Verifies if the interceptors defined in the deployment descriptor are called.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfInterceptorTester00 {

    /**
     * Verifies if the default interceptors and the class interceptors defined
     * in the deployment descriptoe are called.
     */
    void testInterceptorOrder01();

    /**
     * Verifies if the interceptor defined for a method works correctly. The
     * interceptor is defined to the methods with the name insertOrder2, so the
     * both methods must have the same interceptors.
     */
    void testInterceptorOrder02();

    /**
     * Verifies the element exclude class interceptors.
     */
    void testInterceptorOrder03();

    /**
     * Verifies if the postConstruct was called.
     */
    void testPostConstruct();

    /**
     * Verifies if the preDestroy was called.
     */
    void testPreDestroy();

    /**
     * Verifies if the prePassivate is called.
     */
    void testPrePassivate();

    /**
     * Verifies if the postActivate is called.
     */
    void testPostActivate();

}
