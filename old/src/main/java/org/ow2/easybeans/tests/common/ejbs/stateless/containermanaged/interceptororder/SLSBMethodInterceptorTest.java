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
 * $Id: SLSBMethodInterceptorTest.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfMethodInterceptor;
import org.ow2.easybeans.tests.common.ejbs.base.interceptororder.MethodInterceptorTest;

/**
 * Implements a bean that all methods apend an Integer with value 0. The
 * difference between each method is the number of method interceptors used in
 * each one. There are not class interceptor and default interceptor specified.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski E. de Castro
 */
@Stateless(name="SLSBMethodInterceptorTest")
@Remote(ItfMethodInterceptor.class)
public class SLSBMethodInterceptorTest extends MethodInterceptorTest{
}
