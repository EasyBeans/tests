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
 * $Id: BaseMethodInterceptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.base.invocationorder;

import static org.ow2.easybeans.tests.common.asserts.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfMethodInterceptor;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBMethodInterceptorTest;
import org.ow2.easybeans.tests.common.interceptors.business.basic.PackageInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.basic.PrivateInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.basic.ProtectedInterceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder04Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder05Interceptor;
import org.testng.annotations.Test;

/**
 * Verifies if the order to execute the method interceptors is
 * correct.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseMethodInterceptor {

    /**
     * Bean used to implement the test.
     */
    private ItfMethodInterceptor<Integer> mtBean;

    /**
     * Verifies if the interceptors are not executed.
     * @input List with no values inside
     * @output List with only one value, the value inserted by the method
     */
    @Test(groups = {"withoutInterceptor"})
    public void interceptorMethodTest00() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withoutInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "");
    }

    /**
     * Verifies if the interceptor is executed.
     * @input List with no values inside
     * @output List with two values, the value inserted by the method and the
     *         value inserted by the interceptor
     */
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest02() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withOneMethodInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptor is not running or it is running in the incorrect order.");
    }

    /**
     * Verifies if two interceptors are executed in order.
     * @input List with no values inside
     * @output List with three values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest03() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder02Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withTwoMethodInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptors are not called in the correct order.");
    }

    /**
     * Verifies if many interceptors are executed in order.
     * @input List with no values inside
     * @output List with six values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest04() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder02Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder04Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder05Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withFiveMethodInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptors are not running in the correct order."
                + " Maybe there is a problem with the interceptors inheritance.");
    }

    /**
     * Verifies if many interceptors are executed in order. This must repect the
     * declaration order.
     * @input List with no values inside
     * @output List with six values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest05() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrintOrder05Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder04Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder02Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withFiveMethodInterceptorsInverse(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptors are not running in the correct order."
                + " Maybe there is a problem with the interceptors inheritance.");
    }

    /**
     * Verifies if interceptors are executed in order. All interceptors have a
     * private method that intercepts.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest06() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrivateInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrivateInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrivateInterceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withPrivateInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptors with private method are not running in the correct order.");
    }

    /**
     * Verifies if interceptors are executed in order. All interceptors have a
     * protected method that intercepts.
     * @input List with no values inside
     * @output List with 3 values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void interceptorMethodTest07() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(ProtectedInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(ProtectedInterceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withProtectedInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The interceptors with protected method are not running in the correct order.");
    }

    /**
     * Verifies if interceptors are executed in order. There is interceptors
     * with private, protected, and public method, also there is inheritance in
     * one interceptor(PrintOrder03Interceptor.class).
     * @input List with no values inside
     * @output List with 9 values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest08() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrivateInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(ProtectedInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrivateInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrivateInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(ProtectedInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(ProtectedInterceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withPrivateProtectedPublicInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptors are not running in the correct order.");
    }

    /**
     * Verifies if interceptors are executed in order. There is interceptors
     * with package method modifier.
     * @input List with no values inside
     * @output List with 5 values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void interceptorMethodTest09() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PackageInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PackageInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PackageInterceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PackageInterceptor.ORDER);

        // insert the value of the method
        arExpected.add(SLSBMethodInterceptorTest.ORDER);

        // gets the result
        arResult = mtBean.withPackageInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The interceptors are not running in the correct order.");
    }

    /**
     * Sets bean used in the tests.
     * @param bean The bean to set.
     */
    public void setBean(final ItfMethodInterceptor<Integer> bean){
        this.mtBean = bean;
    }
}
