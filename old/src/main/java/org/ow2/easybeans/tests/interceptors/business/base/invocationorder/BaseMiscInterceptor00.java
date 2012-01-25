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
 * $Id: BaseMiscInterceptor00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.base.invocationorder;

import static org.ow2.easybeans.tests.common.asserts.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleBean;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest02;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBSimpleInterceptorTest03;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder06Interceptor;
import org.testng.annotations.Test;

/**
 * Verifies if the order to execute the method and callbacks interceptors is
 * correct, also verifies the interceptors inheritance.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseMiscInterceptor00 {

    /**
     * Bean used to implement the test.
     */
    private ItfSimpleBean<Integer> mtBean00;

    /**
     * Bean used to implement the test.
     */
    private ItfSimpleBean<Integer> mtBean01;

    /**
     * Bean used to implement the test.
     */
    private ItfSimpleBean<Integer> mtBean02;

    /**
     * Bean used to implement the test.
     */
    private ItfSimpleBean<Integer> mtBean03;

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has public modifier
     * access.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void testMiscPublicInterCallOrder() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest00.ORDER);

        // gets the result
        arResult = mtBean00.withInterceptors(arResult);

        assertEquals(arExpected, arResult,
                "The method has class interceptors, a method interceptor and a public bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has protected modifier
     * access.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void testMiscProtectedInterCallOrder() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest01.ORDER);

        // gets the result
        arResult = mtBean01.withInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has class interceptors, a method interceptor and a protected bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has private modifier
     * access.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void testMiscPrivateInterCallOrder() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest02.ORDER);

        // gets the result
        arResult = mtBean02.withInterceptors(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has class interceptors, a method interceptor and a protected bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has public modifier
     * access.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void testMiscInheritanceInterCallOrder00() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest00.ORDER);

        // gets the result
        arResult = mtBean00.withInterceptorsInheritance(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has class interceptors, a method interceptor with inheritance and a public bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has protected modifier
     * access.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void testMiscInheritanceInterCallOrder01() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest01.ORDER);

        // gets the result
        arResult = mtBean01.withInterceptorsInheritance(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has class interceptors, a method interceptor with inheritance and a protected bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has private modifier
     * access.
     * @input List with no values inside
     * @output List with 4 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor", "withInheritance"})
    public void testMiscInheritanceInterCallOrder02() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest02.ORDER);

        // gets the result
        arResult = mtBean02.withInterceptorsInheritance(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has class interceptors, a method interceptor with inheritance and a private bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Verifies if the interceptors are following the order of declaration.
     * There is an interceptor inside the bean class that has package modifier
     * access.
     * @input List with no values inside
     * @output List with 3 values, the value inserted by the method and value
     *         inserted by the interceptors.
     */
    @Test(groups = {"withInterceptor"})
    public void testMiscPackageInterCallOrder() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of interceptor
        arExpected.add(ItfSimpleBean.EMBEDDED_INTERCEPTOR);
        // insert the value of the method
        arExpected.add(SLSBSimpleInterceptorTest03.ORDER);

        // gets the result
        arResult = mtBean03.withInterceptorsInheritance(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has a method interceptor and a package level bean interceptor method"
                        + " declared inside the bean; however it isn't running in the correct order.");
    }

    /**
     * Sets bean used in the tests.
     * @param bean00 The bean to set.
     * @param bean01 The bean to set.
     * @param bean02 The bean to set.
     * @param bean03 The bean to set.
     */
    public void setBeans(final ItfSimpleBean<Integer> bean00, final ItfSimpleBean<Integer> bean01,
            final ItfSimpleBean<Integer> bean02, final ItfSimpleBean<Integer> bean03) {
        this.mtBean00 = bean00;
        this.mtBean01 = bean01;
        this.mtBean02 = bean02;
        this.mtBean03 = bean03;
    }
}
