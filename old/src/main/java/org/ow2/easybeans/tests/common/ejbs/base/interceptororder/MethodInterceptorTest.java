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
 * $Id: MethodInterceptorTest.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.interceptororder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfMethodInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.basic.PackageInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.basic.PrivateInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.basic.ProtectedInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder04Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder05Interceptor;

/**
 * Implements an interface that all methods append an Integer with value 0. The
 * difference between each method is the number of method interceptors used in
 * each one. There are not class interceptor and default interceptor specified.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski E. de Castro
 */
public class MethodInterceptorTest implements ItfMethodInterceptor<Integer> {

    /**
     * List used to test postconstruct callback.
     */
    private List<Integer> lstPostconstruct;

    /**
     * Appends an Integer with the value 0 in the par. This method has 5
     * interceptors that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrintOrder01Interceptor.class, PrintOrder02Interceptor.class, PrintOrder03Interceptor.class,
            PrintOrder04Interceptor.class, PrintOrder05Interceptor.class})
    public List<Integer> withFiveMethodInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 5
     * interceptors that must be call in order. The interceptor order is
     * inverse.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrintOrder05Interceptor.class, PrintOrder04Interceptor.class, PrintOrder03Interceptor.class,
            PrintOrder02Interceptor.class, PrintOrder01Interceptor.class})
    public List<Integer> withFiveMethodInterceptorsInverse(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 1
     * interceptor that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrintOrder01Interceptor.class})
    public List<Integer> withOneMethodInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has no
     * interceptors.
     * @param par array used to append the value
     * @return the array with modified
     */
    public List<Integer> withoutInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 2
     * interceptors that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrintOrder01Interceptor.class, PrintOrder02Interceptor.class})
    public List<Integer> withTwoMethodInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Verifies the list status. The list must be not null, because the PostConstruct callback will initializate this variable.
     * @return true if the list is properly configured to use.
     */
    public boolean checkPostbackInterceptors(){
        return (lstPostconstruct != null);
    }

    /**
     * Creates a list that will be used.
     */
    @PostConstruct
    public void postConstruct(){
        lstPostconstruct = new ArrayList<Integer>();
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 8
     * interceptors that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrivateInterceptor.class, ProtectedInterceptor.class, PrintOrder01Interceptor.class,
            PrivateInterceptor.class, PrintOrder03Interceptor.class, PrivateInterceptor.class, ProtectedInterceptor.class,
            ProtectedInterceptor.class})
    public List<Integer> withPrivateProtectedPublicInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 3
     * interceptors with a private method that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrivateInterceptor.class, PrivateInterceptor.class, PrivateInterceptor.class})
    public List<Integer> withPrivateInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 2
     * interceptors with a protected method that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({ProtectedInterceptor.class, ProtectedInterceptor.class})
    public List<Integer> withProtectedInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has 4
     * interceptors with a package method that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PackageInterceptor.class, PackageInterceptor.class, PackageInterceptor.class, PackageInterceptor.class})
    public List<Integer> withPackageInterceptors(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }
}
