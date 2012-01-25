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
 * $Id: ItfClassInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

import java.util.List;

/**
 * Is used to test if the container call the class interceptors in order.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski E. de Castro
 * @param <T> list type
 */
public interface ItfClassInterceptor<T> {

    /**
     * The number that each method will add in the array.
     */
    Integer ORDER = new Integer(0);

    /**
     * Appends a integer in the par with the value 0.
     * Has only the class interceptors.
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> withoutMethodInterceptor(List<T> par);

    /**
     * Appends a integer in the par with the value 0.
     * Has only the class interceptors.
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> withExcludeDefaultInterceptor(List<T> par);

    /**
     * Appends a integer in the par with the value 0.
     * Has only the class interceptors and has the annotation ExcludeClassInterceptor
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> withExcludeClassInterceptor(List<T> par);

    /**
     * Appends a integer in the par with the value 0.
     * Has the class interceptors, one method interceptor and has the
     * annotation ExcludeClassInterceptor
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> excludeClassAndOneMtd(List<T> par);

    /**
     * Appends a integer in the par with the value 0.
     * Has the class interceptors, four method interceptor and has the
     * annotation ExcludeClassInterceptor
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> excludeClassDefAndFourMtd(List<T> par);

    /**
     * Appends a integer in the par with the value 0.
     * Has the class interceptors and one method interceptor.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withOneMethodInterceptor(List<T> par);

    /**
     * Appends a integer in the par with the value 0.
     * Has the class interceptors and three method interceptor.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withThreeMethodInterceptor(List<T> par);

}
