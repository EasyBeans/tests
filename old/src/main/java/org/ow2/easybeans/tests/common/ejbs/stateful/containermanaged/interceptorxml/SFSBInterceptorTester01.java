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
 * $Id: SFSBInterceptorTester01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml;

import static org.ow2.easybeans.tests.common.asserts.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.interceptor.ExcludeDefaultInterceptors;

import org.ow2.easybeans.tests.common.interceptors.business.base.PrintOrderWithoutAnnotationInterc;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;

/**
 * Verifies the interceptors defined by deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateful
@Remote(ItfInterceptorTester01.class)
@ExcludeDefaultInterceptors
public class SFSBInterceptorTester01 implements ItfInterceptorTester01 {

    /**
     * Bean defined by deployment descriptor used to test the interceptors.
     */
    @EJB(beanName = "SFSBInterceptorXMLRemote01")
    private ItfInterceptorXML sfsbInterceptorXML;

    /**
     * Verifies if the default interceptor was called.
     * @param lstResult the list that was returned by the bean method.
     */
    private void verifyDefaultInterceptor(final List<Integer> lstResult){
          List<Integer> lstExpected = new ArrayList<Integer>();

        // the default interceptor in first place
        lstExpected.add(PrintOrderWithoutAnnotationInterc.ORDER);

        // business method of the bean
        lstExpected.add(SFSBInterceptorXML.ORDER);

        assertEquals(lstExpected, lstResult,
                "The interceptors defined in the xml descriptor does not run in the correct order.");
    }

    /**
     * Verifies if the default method is called for a bean.
     *
     */
    public void testInterceptorOrder01(){
        verifyDefaultInterceptor(sfsbInterceptorXML.insertOrder1(new ArrayList<Integer>()));
    }

    /**
     * Verifies if the interceptor defined for a method specific(defined by name
     * and parameters) is called only for this method.
     */
    public void testInterceptorOrder02(){
        List<Integer> lstResult = sfsbInterceptorXML.insertOrder2(new ArrayList<Integer>());
        List<Integer> lstExpected = new ArrayList<Integer>();

        // the default interceptor in first place
        lstExpected.add(PrintOrderWithoutAnnotationInterc.ORDER);
        // the method interceptor
        lstExpected.add(PrintOrder02Interceptor.ORDER);

        // business method of the bean
        lstExpected.add(SFSBInterceptorXML.ORDER);

        assertEquals(lstExpected, lstResult,
                "The interceptors defined in the xml descriptor does not run in the correct order.");

        verifyDefaultInterceptor(sfsbInterceptorXML.insertOrder2(new ArrayList<Integer>(), 1));
    }

    /**
     * Verifies the element exclude default interceptor.
     *
     */
    public void testInterceptorOrder03(){
        List<Integer> lstResult = sfsbInterceptorXML.insertOrder3(new ArrayList<Integer>());
        List<Integer> lstExpected = new ArrayList<Integer>();

        // business method of the bean
        lstExpected.add(SFSBInterceptorXML.ORDER);

        assertEquals(lstExpected, lstResult,
                "The interceptors defined in the xml descriptor does not run in the correct order.");
    }

}
