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
 * $Id: InterceptorHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.UNDEFINED;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import javax.interceptor.InvocationContext;
import javax.jms.Message;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This helper is used to do operation with InvocationContext and interceptors.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public final class InterceptorHelper {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(InterceptorHelper.class);

    /**
     * Helper should have a private constructor.
     */
    private InterceptorHelper() {
    }

    /**
     * Intercepts the method and adds a value in the list that was get from
     * InvocationContext.
     * @param <E> Element Type
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list or a message.
     * @param value value to add
     * @param className class that is invoking this method.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings("unchecked")
    public static <E> Object addValue(final InvocationContext invocationContext, final E value, final String className)
            throws Exception {
        ItfCallbackLoggerAccess beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class,
                ItfCallbackLoggerAccess.class);
        boolean added = false;

        Object[] arObj = invocationContext.getParameters();

        List<E> arOrder = null;

        if (arObj[0] instanceof List) {
            //Standard interceptor
            arOrder = (List<E>) arObj[0];
            added = arOrder.add(value);

        }else if (arObj[0] instanceof Message){
            //Log event
            beanLogger.insertCallbackLogger(invocationContext.getTarget().getClass().toString(), UNDEFINED, className);
            added = true;
        }

        // If can't add, throw an exception to avoid cascade errors.
        if (!added) {
            throw new IllegalStateException(className + " can not add the value " + value + ".");
        }
        logger.debug("Added: target={0}, interceptorName={1}, order={2}",
                invocationContext.getTarget().toString(), className, value);
        return invocationContext.proceed();
    }

    /**
     * Gets the string representation of a list.
     * @param list object to get the string representation.
     * @return string representation of the list.
     */
    public static String getString(final List list){
        StringBuffer result = new StringBuffer("");
        if (list != null){
            for(Object o : list){
                result.append(",");
                result.append(o.toString());
            }
            result.replace(0, 0, "");
        }
        logger.debug("Result string = {0}", result.toString());
        return result.toString();
    }

    /**
     * Gets the array from string representation.
     * @param str representation of the list.
     * @return list obtained using the string representation.
     */
    @SuppressWarnings({"boxing", "unchecked"})
    public static List getArray(final String str){
        List lst = new ArrayList<Integer>();

        if (str != null){
            String[] values = str.split(",");
            for(int i = 0; i < values.length; i++){
                lst.add(Integer.parseInt(values[i]));
            }
        }
        return lst;
    }

    /**
     * Gets the error msg formatted.
     * @param expected values to a correct answer
     * @param result result values after an execution
     * @param seedMessage standard message to show
     * @return error message
     */
    public static String getPrintOrderErrorMsg(final List expected, final List result, final String seedMessage) {
        String strMessage = seedMessage;

        if (expected == null & result == null) {
            strMessage += " Result=[null], Expected=[null].";
        } else if (expected == null) {
            strMessage += " Result=" + result.toString() + ", Expected=[null].";
        } else if (result == null) {
            strMessage += " Result=[null], Expected=" + expected.toString() + ".";
        } else {
            strMessage += " Actual=" + result.toString() + ", Expected=" + expected.toString() + ".";
        }
        return strMessage;
    }
}
