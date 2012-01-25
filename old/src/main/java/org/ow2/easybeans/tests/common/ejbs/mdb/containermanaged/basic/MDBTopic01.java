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
 * $Id: MDBTopic01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertCallbackEvent;
import org.ow2.easybeans.tests.common.jms.JMSManager;

/**
 * This class is a simple message-driven bean that configures the listener interface using an annotation.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(
        messageListenerInterface = MessageListener.class,
        activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_TOPIC),
        @ActivationConfigProperty(propertyName = "messageSelector",
                propertyValue = "TYPE ='org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBTopic01'")})
public class MDBTopic01 extends BaseInsertCallbackEvent{

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBTopic01";

    /**
     * Manages a message.
     * @param message msg
     */
    public void onMessage(final Message message) {
        super.log(MDBTopic01.class, CallbackType.ON_MESSAGE, MDBTopic01.class);
    }

}
