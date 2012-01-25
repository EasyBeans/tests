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
 * $Id: ItfInterceptorXML.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml;

import java.util.List;

/**
 * Used to verify if the listeners can be defined by deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfInterceptorXML {

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @return the list with the value added.
     */
    List<Integer> insertOrder1(final List<Integer> par);

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @return the list with the value added.
     */
    List<Integer> insertOrder2(final List<Integer> par);

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @param dummy a parmeter used to differ two methods with the same name.
     * @return the list with the value added.
     */
    List<Integer> insertOrder2(final List<Integer> par, final int dummy);

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @return the list with the value added.
     */
    List<Integer> insertOrder3(final List<Integer> par);

    /**
     * Says if the postContruct method was called.
     * @return true if the postConstruct was called, false otherwise.
     */
    boolean calledPostConstruct();

    /**
     * Used to remove the bean and to verifyif the preDestroy was called.
     */
    void remove();
}
