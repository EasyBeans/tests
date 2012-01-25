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
 * $Id: MDBCMTNotSupported.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.transaction;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertCallbackEvent;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.ItfCMTInheritance;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This message-driven bean is used to test the container-managed transaction
 * demarcation with transaction attribute NOT_SUPPORTED.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
            "TYPE = 'org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.transaction.MDBCMTNotSupported'")})
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MDBCMTNotSupported extends BaseInsertCallbackEvent implements MessageListener {

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs."
            + "mdb.containermanaged.transaction.MDBCMTNotSupported";

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBCMTNotSupported.class);

    /**
     * Bean.
     */
    @EJB(beanName = "SLSBCMTInheritance")
    private ItfCMTInheritance bean;

    /**
     * Manages a message.
     * @param message msg
     */
    public void onMessage(final Message message) {
        try {
            // This method is NOT_SUPPORTED and the dummyMethod1 is NEVER, so everything must work.
            logger.debug("Invoking dummyMethod1.");
            bean.dummyMethod1();
            super.log(MDBCMTNotSupported.class, CallbackType.ON_MESSAGE, MDBCMTNotSupported.class);
            logger.debug("Invoking dummyMethod1 finished.");
        }catch (Exception e) {
            logger.error("Error invoking dummyMethod1. Exception = {0}", e.getCause());
        }
    }

}
