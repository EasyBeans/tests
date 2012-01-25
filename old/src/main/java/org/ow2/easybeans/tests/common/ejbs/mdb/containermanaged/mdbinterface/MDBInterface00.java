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
 * $Id: MDBInterface00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.mdbinterface;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.UNDEFINED;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.MESSAGE_DRIVEN_CONTEXT;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;

/**
 * This bean test optional MessageDrivenBean interface.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
            "TYPE = 'org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.mdbinterface.MDBInterface00'")})
public class MDBInterface00 extends BaseInsertOperation implements MessageDrivenBean {

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs.mdb."
            + "containermanaged.mdbinterface.MDBInterface00";

    /**
     * ID.
     */
    private static final long serialVersionUID = 4108218174206348937L;

    /**
     * Context.
     */
    private MessageDrivenContext ctx;

    /**
     * Remove method.
     * @throws EJBException if a problem occurs.
     */
    public void ejbRemove() throws EJBException {
        // TODO: test
    }

    /**
     * Sets the message driven context.
     * @param ctx context
     */
    public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
        this.ctx = ctx;
    }

    /**
     * Verifies MessageDrivenContext methods.
     * @param message msg
     */
    public void onMessage(final Message message) {
        if (ctx != null) {
            super.log(MDBInterface00.class, UNDEFINED, MDBInterface00.class, MESSAGE_DRIVEN_CONTEXT);
        }
    }

}
