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
 * $Id: BaseInvocationParameter01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.invocationcontext;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.helper.InvocationContextHelper;
import org.ow2.easybeans.tests.common.interceptors.invocationcontext.ComplexObject00;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Used to manipulate the invocation context object.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseInvocationParameter01 implements ItfInvocationParameter00{

    /**
     * Logger.
     */
    private Log logger = LogFactory.getLog(BaseInvocationParameter01.class);

    /**
     * Intercepts the method and modifies the method parameters.
     * @param ic contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings("unused")
    @AroundInvoke
    private Object intercept(final InvocationContext ic) throws Exception{
        Method mIC = ic.getMethod();

        if (mIC.toString().contains("getObjects00")){
            //Sets all parameters to null.
            return InvocationContextHelper.setParametersNull(ic);
        } else if (mIC.toString().contains("getObjects01")) {
            //Do not modify the parameters.
            return InvocationContextHelper.getParametersArray(ic);
        } else if (mIC.toString().contains("getObjects02")) {
            //Modify the parameters.
            return InvocationContextHelper.modifyParameters(ic);
        }else{
            throw new Exception("Invalid method to intercept.");
        }
    }

    /**
     * Returns objects passed as parameters with null references. There is an interceptor
     * that sets to null the parameters reference.
     * @param objCP complex object
     * @return array with the objects passed as parameters.
     * @throws Exception if a problem occurs.
     */
    public Object[] getObjects00(ComplexObject00 objCP) throws Exception {
        Object[] arObjs = {objCP};
        logger.debug("after interceptors, parameters: {0}", objCP);
        return arObjs;
    }

    /**
     * Returns objects passed as parameters without modification. There is an interceptor
     * that creates a new array to return, but it doesn't modify the objects passed as parameters.
     * @param objCP complex object
     * @return array with the objects passed as parameters.
     * @throws Exception if a problem occurs.
     */
    public Object[] getObjects01(ComplexObject00 objCP) throws Exception {
        Object[] arObjs = {objCP};
        logger.debug("after interceptors, parameters: {0}, {1}", objCP);
        return arObjs;
    }

    /**
     * Returns objects passed as parameters with modifications.
     * There is an interceptor that modifies the parameters.
     * @param objCP complex object
     * @return array with the objects passed as parameters.
     * @throws Exception if a problem occurs.
     */
    public Object[] getObjects02(ComplexObject00 objCP) throws Exception {
        Object[] arObjs = {objCP};
        logger.debug("after interceptors, parameters: {0}", objCP);
        return arObjs;
    }

}
