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
 * $Id: SLSBClassInterceptorTest02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfClassInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder04Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder06Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder07Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder08Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder09Interceptor;

/**
 * Is used to test if the container call the class interceptors in order.Each method
 * in appends 0 in the array, the difference amog them is the way that the interceptors
 * are called.
 * This class has four class interceptors.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski E. de Castro
 */
@Stateless(name="SLSBClassInterceptorTest02")
@Remote(ItfClassInterceptor.class)
@Interceptors({PrintOrder06Interceptor.class, PrintOrder07Interceptor.class, PrintOrder08Interceptor.class,
        PrintOrder09Interceptor.class})
public class SLSBClassInterceptorTest02 implements ItfClassInterceptor<Integer>{

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has only class interceptors that must be call in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    public List<Integer> withoutMethodInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has only
     * class interceptors that must be call in order and the default
     * interceptor(defined in the xml file) must not be executed
     * @param par array used to append the value
     * @return the array with modified
     */
    @ExcludeDefaultInterceptors
    public List<Integer> withExcludeDefaultInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has the annotation ExcludeClassInterceptor, so the interceptors
     * must not be executed.
     * @param par array used to append the value
     * @return the array with modified
     */
    @ExcludeClassInterceptors
    public List<Integer> withExcludeClassInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has the annotation ExcludeClassInterceptor, so only the method
     * interceptor must not be executed.
     * @param par array used to append the value
     * @return the array with modified
     */
    @ExcludeClassInterceptors
    @Interceptors({PrintOrder01Interceptor.class})
    public List<Integer> excludeClassAndOneMtd(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par. This method has the
     * annotations ExcludeClassInterceptor and ExcludeDefaultInterceptor , so
     * only the method interceptor must not be executed.
     * @param par array used to append the value
     * @return the array with modified
     */
    @ExcludeClassInterceptors
    @ExcludeDefaultInterceptors
    @Interceptors({PrintOrder01Interceptor.class, PrintOrder02Interceptor.class, PrintOrder03Interceptor.class,
            PrintOrder04Interceptor.class})
    public List<Integer> excludeClassDefAndFourMtd(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has the class interceptors and the method interceptor,
     * so all interceptors must not be executed in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrintOrder01Interceptor.class})
    public List<Integer> withOneMethodInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends an Integer with the value 0 in the par.
     * This method has the class interceptors and the method interceptors,
     * so all interceptors must not be executed in order.
     * @param par array used to append the value
     * @return the array with modified
     */
    @Interceptors({PrintOrder01Interceptor.class, PrintOrder02Interceptor.class, PrintOrder03Interceptor.class})
    public List<Integer> withThreeMethodInterceptor(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

}
