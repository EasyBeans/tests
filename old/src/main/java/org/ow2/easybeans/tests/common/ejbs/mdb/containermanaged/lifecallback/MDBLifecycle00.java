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
 * $Id: MDBLifecycle00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.lifecallback;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertCallbackEvent;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.predestroy.PreDestroyLogger00;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean is used to test the PostConstruct and PreDestroy lifecycle event
 * callback methods.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
            "TYPE = 'org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.lifecallback.MDBLifecycle00'")})
@Interceptors({PostConstructMDB.class, PreDestroyLogger00.class})
public class MDBLifecycle00 extends BaseInsertCallbackEvent {

    /**
     * External PostConstruct.
     */
    private boolean externalInvoked;

    /**
     * Internal PostConstruct.
     */
    private boolean internalInvoked;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBLifecycle00.class);

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests."
        + "common.ejbs.mdb.containermanaged.lifecallback.MDBLifecycle00";

    /**
     * PostConstruct method.
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void postConstruct00() {
        internalInvoked = true;
    }

    /**
     * Sets the external postconstruct invocation result.
     * @param b true if ok, otherwise false.
     */
    public void setStatusPostConstruct(final boolean b) {
        externalInvoked = b;
    }

    /**
     * PreDestroy method.
     */
    @SuppressWarnings("unused")
    @PreDestroy
    private void preDestroy00() {
        super.log(MDBLifecycle00.class, CallbackType.PRE_DESTROY, MDBLifecycle00.class);
        logger.debug("{0} was invoked.", CallbackType.PRE_DESTROY.toString());
    }

    /**
     * Receives a message.
     * @param message msg
     */
    public void onMessage(final Message message) {
        if (externalInvoked) {
            super.log(MDBLifecycle00.class, CallbackType.POST_CONSTRUCT, PostConstructMDB.class);
            logger.debug("Internal PostConstruct was invoked.");
        }
        if (internalInvoked) {
            super.log(MDBLifecycle00.class, CallbackType.POST_CONSTRUCT, MDBLifecycle00.class);
            logger.debug("Internal PostConstruct was invoked.");
        }
    }

}
