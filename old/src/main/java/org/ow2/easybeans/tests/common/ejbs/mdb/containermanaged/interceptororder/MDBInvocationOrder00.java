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
 * $Id: MDBInvocationOrder00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.interceptororder;

import static org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleBean.EMBEDDED_INTERCEPTOR;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.AROUND_INVOKE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.UNDEFINED;
import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.addValue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertCallbackEvent;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.CallbackChecker;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder01Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder02Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.order.PrintOrder06Interceptor;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean is used to test embedded interceptors.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
            "TYPE = 'org.ow2.easybeans.tests.common.ejbs."
                + "mdb.containermanaged.interceptororder.MDBInvocationOrder00'")})
@Interceptors({PrintOrder01Interceptor.class})
public class MDBInvocationOrder00 extends BaseInsertCallbackEvent {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBInvocationOrder00.class);

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common."
            + "ejbs.mdb.containermanaged.interceptororder.MDBInvocationOrder00";

    /**
     * Intercepts the method and add the ORDER value in the list that was get
     * from InvocationContext. This method has private level access.
     * @param invocationContext contains attributes of invocation, the first
     *        parameter of the intercepted method must be a list.
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @SuppressWarnings("unused")
    @AroundInvoke
    private Object addOrder(final InvocationContext invocationContext) throws Exception {
        logger.debug("Interceptor invoked.");
        return addValue(invocationContext, EMBEDDED_INTERCEPTOR, MDBInvocationOrder00.class.toString());
    }

    /**
     * Verifies the MessageDrivenContext methods.
     * @param message msg
     * @throws JMSException
     */
    @SuppressWarnings("unchecked")
    @Interceptors({PrintOrder02Interceptor.class, PrintOrder06Interceptor.class})
    public void onMessage(final Message message) {
        CallbackChecker checker = new CallbackChecker();

        List<String> lstResult = new ArrayList<String>();
        lstResult.add(PrintOrder01Interceptor.class.toString());
        lstResult.add(PrintOrder02Interceptor.class.toString());
        lstResult.add(PrintOrder06Interceptor.class.toString());
        lstResult.add(MDBInvocationOrder00.class.toString());

        // Verifies the invoked interceptors.
        try {
            checker.check(MDBInvocationOrder00.class.toString(), UNDEFINED, lstResult);
            super.log(MDBInvocationOrder00.class.toString(), AROUND_INVOKE, MDBInvocationOrder00.class.toString());
        } catch (Exception e) {
            logger.error("Error verifying interceptors order. Exception: ", e);
        }

    }
}
