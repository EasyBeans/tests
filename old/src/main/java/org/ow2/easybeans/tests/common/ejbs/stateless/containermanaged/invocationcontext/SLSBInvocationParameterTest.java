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
 * $Id: SLSBInvocationParameterTest.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanLocalInstance;
import static org.ow2.easybeans.tests.common.helper.InvocationContextHelper.INT_VALUE_0;
import static org.ow2.easybeans.tests.common.helper.InvocationContextHelper.INT_VALUE_1;
import static org.ow2.easybeans.tests.common.helper.InvocationContextHelper.INT_VALUE_2;
import static org.ow2.easybeans.tests.common.helper.InvocationContextHelper.STR_VALUE_0;
import static org.ow2.easybeans.tests.common.helper.InvocationContextHelper.STR_VALUE_1;
import static org.ow2.easybeans.tests.common.helper.InvocationContextHelper.STR_VALUE_2;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocationParameter00;
import org.ow2.easybeans.tests.common.ejbs.base.invocationcontext.ItfInvocationParameterTest;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.BeanDescriptor;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ComplexObject00;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean is used to perform test in the intercepted method parameters stored
 * in the invocation context.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless
@Remote
public class SLSBInvocationParameterTest implements ItfInvocationParameterTest {

    /**
     * Logger.
     */
    private Log logger = LogFactory.getLog(SLSBInvocationParameterTest.class);

    /**
     * Verifies if the intercepted method parameters can be modified to a null
     * reference.
     * @param beanClass bean class
     * @throws Exception if there is a problem with the test.
     */
    public void testNull(final Class beanClass) throws Exception {
        ItfInvocationParameter00 icBean = getBeanLocalInstance(beanClass, ItfInvocationParameter00.class);

        ComplexObject00 cmpObj = new ComplexObject00();

        Object[] arResult = icBean.getObjects00(cmpObj);

        assertNull(arResult[0], "[0]The object should be null.");
    }

    /**
     * Verifies if the intercepted method parameters can be returned WITHOUT
     * modifications.
     * @param beanClass bean class name
     * @throws Exception if there is a problem with the test.
     */
    public void testWithoutModification(final Class beanClass) throws Exception {
        ItfInvocationParameter00 icBean = getBeanLocalInstance(beanClass, ItfInvocationParameter00.class);

        ComplexObject00 cmpObj = new ComplexObject00();

        logger.debug("before bean method call, parameter: {0}", cmpObj);

        Object[] arResult = icBean.getObjects01(cmpObj);

        logger.debug("after bean method call, parameter: {0}", arResult[0]);

        assertEquals(arResult[0], cmpObj, "[0]The object should be the same. ");
    }

    /**
     * Verifies if the intercepted method parameters can be returned WITH
     * modifications.
     * @param beanClass bean class
     * @throws Exception if there is a problem with the test.
     */
    public void testWithModification(final Class beanClass) throws Exception {
        ItfInvocationParameter00 icBean = getBeanLocalInstance(beanClass, ItfInvocationParameter00.class);

        ComplexObject00 cmpObj = new ComplexObject00();

        Object[] arObjResult = icBean.getObjects02(cmpObj);

        ComplexObject00 cmpResult = (ComplexObject00) arObjResult[0];

        // Checks if the result is correct
        assertTrue(cmpResult.getHashCode() == INT_VALUE_0.intValue(), "[0]The returned object is not the expected. ");
        assertEquals(cmpResult.getInterceptedMethod(), STR_VALUE_0, "[1]The returned object is not the expected. ");

        List<BeanDescriptor> lstDescs = cmpResult.getDescriptors().get(0);
        // Checks the first bean descriptor
        assertTrue(lstDescs.get(0).getHashCode() == INT_VALUE_1.intValue(),
                "[2]The returned object is not the expected. ");
        assertEquals(lstDescs.get(0).getInterceptedMethod(), STR_VALUE_1,
                "[3]The returned object is not the expected. ");

        assertTrue(lstDescs.get(1).getHashCode() == INT_VALUE_2.intValue(),
                "[4]The returned object is not the expected. ");
        assertEquals(lstDescs.get(1).getInterceptedMethod(), STR_VALUE_2,
                "[5]The returned object is not the expected. ");
    }

}
