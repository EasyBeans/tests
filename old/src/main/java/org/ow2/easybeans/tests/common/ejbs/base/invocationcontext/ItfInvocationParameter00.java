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
 * $Id: ItfInvocationParameter00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.invocationcontext;

import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ComplexObject00;


/**
 * This interface is used to test if the invocation context getParameters() and setParameter() work following the specification.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public interface ItfInvocationParameter00 {

    /**
     * Constant value.
     */
    int VALUE = 5000;

    /**
     * Returns objects passed as parameters.
     * @param objCP complex object
     * @return array with the objects passed as parameters.
     * @throws Exception if a problem occurs.
     */
    Object[] getObjects00(ComplexObject00 objCP) throws Exception;

    /**
     * Returns objects passed as parameters.
     * @param objCP complex object
     * @return array with the objects passed as parameters.
     * @throws Exception if a problem occurs.
     */
    Object[] getObjects01(ComplexObject00 objCP) throws Exception;

    /**
     * Returns objects passed as parameters.
     * @param objCP complex object
     * @return array with the objects passed as parameters.
     * @throws Exception if a problem occurs.
     */
    Object[] getObjects02(ComplexObject00 objCP) throws Exception;

}
