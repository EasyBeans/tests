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
 * $Id: SFSBInterceptorTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml;

import static org.ow2.easybeans.tests.common.asserts.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.interceptor.ExcludeDefaultInterceptors;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.interceptors.business.base.PrintOrderWithoutAnnotationInterc;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder03Interceptor;

/**
 * Verifies the definition of interceptors by deployment descriptors.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfInterceptorTester00.class)
@ExcludeDefaultInterceptors
public class SFSBInterceptorTester00 implements ItfInterceptorTester00 {

    /**
     * Bean defined by deployment descriptor used to test the interceptors.
     */
    @EJB(beanName = "SFSBInterceptorXMLRemote00")
    private ItfInterceptorXML sfsbInterceptorXML;

    /**
     * Bean used to log the callback methods.
     */
    @EJB(beanName = "SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess clBean;

    /**
     * Verifies if the default interceptor, the class interceptor and the method
     * interceptor are called.
     * @param lstResult the list that was returned by the bean method.
     */
    private void verifyMethodInterceptor(final List<Integer> lstResult) {
        List<Integer> lstExpected = new ArrayList<Integer>();

        // the default interceptor in first place
        lstExpected.add(PrintOrderWithoutAnnotationInterc.ORDER);
        // after the class interceptor
        lstExpected.add(PrintOrder02Interceptor.ORDER);
        // the method interceptor
        lstExpected.add(PrintOrder03Interceptor.ORDER);

        // Bean's aroundInvoke interceptor
        lstExpected.add(SFSBInterceptorXML.ORDER);

        // business method
        lstExpected.add(SFSBInterceptorXML.ORDER);

        assertEquals(lstExpected, lstResult,
                "The interceptors defined in the xml descriptor does not run in the correct order.");

    }

    /**
     * Verifies if the default interceptors and the class interceptors defined
     * in the deployment descriptors are called.
     */
    public void testInterceptorOrder01() {
        List<Integer> lstResult = sfsbInterceptorXML.insertOrder1(new ArrayList<Integer>());
        List<Integer> lstExpected = new ArrayList<Integer>();

        // the default interceptor in first place
        lstExpected.add(PrintOrderWithoutAnnotationInterc.ORDER);
        // after the classes interceptor
        lstExpected.add(PrintOrder02Interceptor.ORDER);

        // Around invoke of the bean
        lstExpected.add(SFSBInterceptorXML.ORDER);

        // business method of the bean
        lstExpected.add(SFSBInterceptorXML.ORDER);

        assertEquals(lstExpected, lstResult,
                "The interceptors defined in the xml descriptor does not run in the correct order.");
    }

    /**
     * Verifies if the interceptor defined for a method works correctly. The
     * interceptor is defined to the methods with the name insertOrder2, so the
     * both methods must have the same interceptors.
     */
    public void testInterceptorOrder02() {
        verifyMethodInterceptor(sfsbInterceptorXML.insertOrder2(new ArrayList<Integer>()));
        verifyMethodInterceptor(sfsbInterceptorXML.insertOrder2(new ArrayList<Integer>(), 1));
    }

    /**
     * Verifies the element exclude class interceptors.
     */
    public void testInterceptorOrder03() {
        List<Integer> lstResult = sfsbInterceptorXML.insertOrder3(new ArrayList<Integer>());
        List<Integer> lstExpected = new ArrayList<Integer>();

        // the default interceptor in first place
        lstExpected.add(PrintOrderWithoutAnnotationInterc.ORDER);

        // business method
        lstExpected.add(SFSBInterceptorXML.ORDER);

        assertEquals(lstExpected, lstResult,
                "The interceptors defined in the xml descriptor does not run in the correct order.");
    }

    /**
     * Verifies if the postConstruct was called.
     */
    public void testPostConstruct() {
        assertTrue(sfsbInterceptorXML.calledPostConstruct(), "The postConstruct defined by XML was not called.");
    }

    /**
     * Verifies if the preDestroy was called.
     */
    public void testPreDestroy() {
        sfsbInterceptorXML.remove();

        // Interceptors list
        List<String> arLife = new ArrayList<String>();

        arLife.add(SFSBInterceptorXML.class.getName());

        clBean.verifyCallbackOrder(SFSBInterceptorXML.class.getName(), CallbackType.PRE_DESTROY, arLife
                .toArray(new String[arLife.size()]));
    }

    /**
     * Verifies if the prePassivate is called.
     */
    public void testPrePassivate() {
        // TODO - how to test the prePassivate?
    }

    /**
     * Verifies if the postActivate is called.
     */
    public void testPostActivate() {
        // TODO - how to test the postActivate?
    }
}
