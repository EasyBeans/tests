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
 * $Id: BaseClassInterceptor02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.base.invocationorder;

import static org.ow2.easybeans.tests.common.asserts.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfClassInterceptor;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBClassInterceptorTest00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptororder.SLSBClassInterceptorTest02;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder04Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder06Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder07Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder08Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder09Interceptor;
import org.testng.annotations.Test;

/**
 * Verifies if the order to execute the class and method interceptors is
 * correct.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseClassInterceptor02 {

    /**
     * Bean used to implement the test.
     */
    private ItfClassInterceptor<Integer> clBean;

    /**
     * Verifies if the interceptors are not executed.
     * @input List with no values inside.
     * @output List with five values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"onlyClassInterceptor", "withInheritance"})
    public void testClassInterCallOrder00() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor class
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder07Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder08Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder09Interceptor.ORDER);
        // insert the value of method
        arExpected.add(SLSBClassInterceptorTest02.ORDER);

        // gets the result
        arResult = clBean.withoutMethodInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The class interceptors are not called or they are called in the incorrect order.");
    }

    /**
     * Verifies if the interceptors are not executed.
     * @input List with no values inside.
     * @output List with five values, the value inserted by the method and the
     *         value inserted by the interceptors.
     */
    @Test(groups = {"excludeDefaultInterceptor", "onlyClassInterceptor", "withInheritance"})
    public void testClassInterCallOrder01() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor class
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder07Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder08Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder09Interceptor.ORDER);
        // insert the value of method
        arExpected.add(SLSBClassInterceptorTest02.ORDER);
        // gets the result
        arResult = clBean.withExcludeDefaultInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The class interceptors are not called or they are called in the incorrect order.");
    }

    /**
     * Verifies if the interceptor classes are not executed with the annotation
     * excludeClassInterceptor.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @Test(groups = {"excludeClassInterceptor", "onlyClassInterceptor"})
    public void testClassInterCallOrder02() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the method
        arExpected.add(SLSBClassInterceptorTest02.ORDER);

        // gets the result
        arResult = clBean.withExcludeClassInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The method has excludeClassInterceptor annotation, but it is calling the interceptor.");
    }

    /**
     * Verifies if the interceptor classes are not executed with the annotation
     * excludeClassInterceptor. Also, this test verifies if the annotation doesn't
     * modify the interceptor method execution.
     * @input List with no values inside.
     * @output List with two values, the value inserted by the method
     *         interceptor and the value inserted by the method.
     */
    @Test(groups = {"methodInterceptor", "excludeClassInterceptor"})
    public void testClassInterCallOrder03() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBClassInterceptorTest02.ORDER);

        // gets the result
        arResult = clBean.excludeClassAndOneMtd(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "This method has the excludeClassInterceptor, but it is not executing the method interceptor correctly.");
    }

    /**
     * Verifies if the interceptor classes are not executed with the annotation
     * excludeClassInterceptor. Also, this test verifies if the annotation doesn't
     * modify many interceptor method executions.
     * @input List with no values inside.
     * @output List with two values, the value inserted by the method
     *         interceptor and the value inserted by the method.
     */
    @Test(groups = {"excludeDefaultInterceptor", "excludeClassInterceptor", "methodInterceptor", "withInheritance"})
    public void testClassInterCallOrder04() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder02Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder04Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBClassInterceptorTest02.ORDER);

        // gets the result
        arResult = clBean.excludeClassDefAndFourMtd(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "This method has the excludeClassInterceptor, but it is not executing the method interceptors correctly.");
    }

    /**
     * Verifies if the interceptor classes and the interceptor methods work well
     * together, i.e., if they respect the order.
     * @input List with no values inside.
     * @output List with eight values, the values inserted by the method
     *         interceptor and the value inserted by the method.
     */
    @Test(groups = {"methodInterceptor", "withInheritance"})
    public void testClassInterCallOrder05() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor class
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder07Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder08Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder09Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder02Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder03Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBClassInterceptorTest02.ORDER);

        // gets the result
        arResult = clBean.withThreeMethodInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult,
                "The class and the method interceptors are not running in the correct order.");
    }

    /**
     * Verifies if the interceptor classes and the interceptor methods work well
     * together, i.e., if they respect the order.
     * @input List with no values inside.
     * @output List with two values, the values inserted by the method
     *         interceptor and the value inserted by the method.
     */
    @Test(groups = {"methodInterceptor", "withInheritance"})
    public void testClassInterCallOrder06() {
        // The arrays used to know what is the interceptor order
        List<Integer> arResult = new ArrayList<Integer>();
        List<Integer> arExpected = new ArrayList<Integer>();

        // insert the value of interceptor class
        arExpected.add(PrintOrder06Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder07Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder08Interceptor.ORDER);
        // insert the value of interceptor class
        arExpected.add(PrintOrder09Interceptor.ORDER);
        // insert the value of the interceptor
        arExpected.add(PrintOrder01Interceptor.ORDER);
        // insert the value of the method
        arExpected.add(SLSBClassInterceptorTest00.ORDER);

        // gets the result
        arResult = clBean.withOneMethodInterceptor(arResult);

        // compares the two values
        assertEquals(arExpected, arResult, "The method interceptor are running in the incorrect order.");
    }

    /**
     * Sets bean used in the tests.
     * @param bean The bean to set.
     */
    public void setBean(final ItfClassInterceptor<Integer> bean){
        this.clBean = bean;
    }
}
