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
 * $Id: SFSBInterceptorXML.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.interceptorxml;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.helper.InterceptorHelper;

/**
 * Used to verifies if the listeners can be defined by the deployment
 * descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class SFSBInterceptorXML implements ItfInterceptorXML {

    /**
     * The number of the interceptor called. Says that the
     */
    public static final Integer ORDER = new Integer(0);

    /**
     * Bean used to log the callback methods.
     */
    @EJB(beanName = "SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess clBean;

    /**
     * Used to verify if the post construct was called.
     */
    private boolean bolPostConstruct = false;

    /**
     * Creates an instance of SFSBInterceptorXML.
     */
    public SFSBInterceptorXML() {
        bolPostConstruct = false;
    }

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @return the list with the value added.
     */
    public List<Integer> insertOrder1(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @return the list with the value added.
     */
    public List<Integer> insertOrder2(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @param dummy a parmeter used to differ two methods with the same name.
     * @return the list with the value added.
     */
    public List<Integer> insertOrder2(final List<Integer> par, final int dummy) {
        par.add(ORDER);
        return par;
    }

    /**
     * Appends a value in the end of the list.
     * @param par the lists that the value will be appended.
     * @return the list with the value added.
     */
    public List<Integer> insertOrder3(final List<Integer> par) {
        par.add(ORDER);
        return par;
    }

    /**
     * Sets a variable as true.
     */
    public void postConstruct() {
        bolPostConstruct = true;
    }

    /**
     * Registers in the database that the method was called.
     */
    public void preDestroy() {
        clBean.insertCallbackLogger(this.getClass().getName(), CallbackType.PRE_DESTROY, this.getClass().getName());
    }

    /**
     * Used to verify if a prePassivate callback method can be defined by the
     * deployment descriptor.
     */
    public void prePassivate() {
        // TODO - how to test the pre-passivate?
    }

    /**
     * Used to verify if a postActivate callback method can be defined by the
     * deployment descriptor.
     */
    public void postActivate() {
        // TODO - how to test the post-activate?
    }

    /**
     * Used to remove the bean and to verifyif the preDestroy was called.
     */
    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public void remove(){

    }

    /**
     * Says if the postContruct method was called.
     * @return true if the postConstruct was called, false otherwise.
     */
    // Class and Default interceptors are Excluded by DD
    public boolean calledPostConstruct() {
        return bolPostConstruct;
    }

    /**
     * Appends a value in the end of the list.
     * @param invocationContext the  incocation context.
     * @return the list with the value added.
     * @throws Exception if an error occurs during the insertion.
     */
    public Object aroundInvoke(final InvocationContext invocationContext) throws Exception {
        return InterceptorHelper.addValue(invocationContext, ORDER, this.getClass().getName());

    }
}
