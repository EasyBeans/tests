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
 * $Id: ItfMethodInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

import java.util.List;

/**
 * This interface is used to test if the container call the method interceptors in order.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski E. de Castro
 * @param <T> list type
 */
public interface ItfMethodInterceptor<T> {

    /**
     * The number that each method will add in the array.
     */
    Integer ORDER = new Integer(0);

    /**
     * Appends an element in the par.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withoutInterceptor(List<T> par);

    /**
     * Appends an element in the par. Also,the interceptor appends
     * an element with its value.
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> withOneMethodInterceptor(List<T> par);

    /**
     * Appends an element in the par. Also,each interceptor
     * appends an element with its value.
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> withTwoMethodInterceptors(List<T> par);

    /**
     * Apends an element in the par. Also,each interceptor
     * appends an element with its value.
     * @param par list used to apend the value
     * @return list updated
     */
    List<T> withFiveMethodInterceptors(List<T> par);

    /**
     * Appends an element in the par. Also,each interceptor
     * appends an element with its value.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withFiveMethodInterceptorsInverse(List<T> par);

    /**
     * Verifies the list status.
     * @return true if the list is properly configured to use.
     */
    boolean checkPostbackInterceptors();

    /**
     * Appends an element in the par. Also,each interceptor
     * appends an element with its value.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withPrivateProtectedPublicInterceptors(List<T> par);

    /**
     * Appends an element in the par. Also, each interceptor
     * appends an element with its value.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withPrivateInterceptors(List<T> par);

    /**
     * Appends an element in the par. Also,each interceptor
     * appends an element with its value.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withProtectedInterceptors(List<T> par);

    /**
     * Appends an element in the par. Also,each interceptor
     * appends an element with its value.
     * @param par list used to append the value
     * @return list updated
     */
    List<T> withPackageInterceptors(final List<Integer> par);
}
