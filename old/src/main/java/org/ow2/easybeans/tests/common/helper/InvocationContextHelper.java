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
 * $Id: InvocationContextHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.interceptors.invocationcontext.BeanDescriptor;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ComplexObject00;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This helper is used to manage the invocation context.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public final class InvocationContextHelper {

    /**
     * Int value.
     */
    public static final Integer INT_VALUE_0 = new Integer(0);

    /**
     * String value.
     */
    public static final String STR_VALUE_0 = "intValue0";

    /**
     * Int value.
     */
    public static final Integer INT_VALUE_1 = new Integer(1);

    /**
     * String value.
     */
    public static final String STR_VALUE_1 = "intValue1";

    /**
     * Int value.
     */
    public static final Integer INT_VALUE_2 = new Integer(2);

    /**
     * String value.
     */
    public static final String STR_VALUE_2 = "intValue2";

    /**
     * Helper should have a private constructor.
     */
    private InvocationContextHelper() {

    }

    /**
     * Returns an array of objects with the intercepted method parameters.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object getParametersArray(final InvocationContext ic) throws Exception {
        Log logger = LogFactory.getLog(InvocationContextHelper.class);

        logger.debug("Starting method getParametersArray...");

        Object[] arNew = null;
        Object[] arParam = ic.getParameters();

        logger.debug("current parameters: {0}", arParam[0]);

        if (arParam != null) {
            arNew = new Object[arParam.length];
            for (int i = 0; i < arParam.length; i++) {
                arNew[i] = arParam[i];
            }
        }

        logger.debug("new parameters: {0}", arNew[0]);
        logger.debug("before setParameters(), {0}", ic.getParameters()[0]);

        ic.setParameters(arNew);

        logger.debug("after setParameters(), {0}", ic.getParameters()[0]);
        logger.debug("Finishing method getParametersArray...");

        return ic.proceed();
    }

    /**
     * Sets null all intercepted method parameters.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object setParametersNull(final InvocationContext ic) throws Exception {
        Log logger = LogFactory.getLog(InvocationContextHelper.class);

        logger.debug("Starting method setParametersNull...");

        Object[] arParam = ic.getParameters();

        logger.debug("current parameters: {0}", arParam[0]);

        if (arParam != null) {
            for (int i = 0; i < arParam.length; i++) {
                arParam[i] = null;
            }
        }

        logger.debug("new parameters: {0}", arParam[0]);
        logger.debug("before setParameters(), {0}", ic.getParameters()[0]);

        ic.setParameters(arParam);

        logger.debug("after setParameters(), {0}", ic.getParameters()[0]);
        logger.debug("Finishing method setParametersNull...");

        return ic.proceed();
    }

    /**
     * Modifies the objects passed as parameters.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings("boxing")
    public static Object modifyParameters(final InvocationContext ic) throws Exception {
        Object[] objParams = ic.getParameters();

        // Modifies the first attributes
        ComplexObject00 cmpObj = (ComplexObject00) objParams[0];
        cmpObj.setHashCode(INT_VALUE_0);
        cmpObj.setInterceptedMethod(STR_VALUE_0);

        // Creates a new list of bean descriptors
        List<BeanDescriptor> lstDesc = new ArrayList<BeanDescriptor>();
        lstDesc.add(new BeanDescriptor(INT_VALUE_1, STR_VALUE_1));
        lstDesc.add(new BeanDescriptor(INT_VALUE_2, STR_VALUE_2));

        cmpObj.addDescriptors(lstDesc);

        ic.setParameters(objParams);

        return ic.proceed();
    }

    /**
     * Checks if the bean descriptor is the same. <li>The first parameter of
     * the intercepted method must be a BeanDescriptor.</li>
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object checkBeanDescriptor(final InvocationContext ic) throws Exception {
        // Log logger = LogFactory.getLog(InvocationContextHelper.class);
        BeanDescriptor icBean = new BeanDescriptor(ic.getTarget().hashCode(), ic.getMethod().toString());
        BeanDescriptor bDesc = (BeanDescriptor) ic.getParameters()[0];

        if (icBean.equalsWithException(bDesc)) {
            return ic.proceed();
        }
        throw new Exception("The referenced bean is not equal as the invocation context reference.");
    }

    /**
     * Adds objects in the contextual data map. The key of the object is its
     * array position.
     * @param ic contains attributes of invocation
     * @param objs objects to add.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings({"unchecked", "boxing"})
    public static Object addContextData(final InvocationContext ic, final Object[] objs) throws Exception {
        Map mContext = ic.getContextData();
        if (objs != null) {
            for (int i = 0; i < objs.length; i++) {
                mContext.put(i, objs[i]);
            }
        }
        return ic.proceed();
    }

    /**
     * Checks if the all keys exists in the contextual data map.
     * @param ic contains attributes of invocation
     * @param keys keys to check
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object checkContextKeys(final InvocationContext ic, final Object[] keys) throws Exception {
        Map mContext = ic.getContextData();
        if (keys != null) {
            for (Object o : keys) {
                if (!mContext.containsKey(o)) {
                    throw new Exception("Object not found.");
                }
            }
        }
        return ic.proceed();
    }

    /**
     * Checks if the all objects exists in the contextual map.
     * @param ic contains attributes of invocation
     * @param objs objects to check
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object checkContextData(final InvocationContext ic, final Object[] objs) throws Exception {
        Map mContext = ic.getContextData();
        if (objs != null) {
            for (Object o : objs) {
                if (!mContext.containsValue(o)) {
                    throw new Exception("Object not found.");
                }
            }
        }
        return ic.proceed();
    }

    /**
     * Clears the contextual map.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object clearContextData(final InvocationContext ic) throws Exception {
        ic.getContextData().clear();
        return ic.proceed();
    }

    /**
     * Checks if context data is empty.
     * @param ic contains attributes of invocation
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    public static Object isEmptyContextData(final InvocationContext ic) throws Exception {
        if (ic.getContextData().isEmpty()){
            return ic.proceed();
        }
        throw new Exception("Context data should be empty.");
    }

}
