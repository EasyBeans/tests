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
 * $Id: MDBContext.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.context;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.GET_CALLER_PRINCIPAL;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.GET_ROLLBACK_ONLY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.SET_ROLLBACK_ONLY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.TIMER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.isEqual;
import static org.ow2.easybeans.tests.common.resources.TimerServiceTester.checkInstance;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.easybeans.tests.common.jms.MessageProperty;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean tests the MessageDrivenContext.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "TYPE = 'org.ow2.easybeans.tests."
            + "common.ejbs.mdb.containermanaged.context.MDBContext'")})
public class MDBContext extends BaseInsertOperation {

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.context.MDBContext";

    /**
     * Context.
     */
    @Resource
    private MessageDrivenContext ctx;

    /**
     * Logger.
     */
    private Log logger = LogFactory.getLog(MDBContext.class);

    /**
     * Verifies the MessageDrivenContext methods.
     * @param msg message
     */
    public void onMessage(final Message msg) {
        String op = null;

        try {
            op = msg.getStringProperty(MessageProperty.OPERATION.toString());
        } catch (JMSException e) {
            logger.debug("Error getting operation type: {0}", e);
        }

        if(ctx == null){
            logger.debug("The SessionContext reference is null.");
        }else if (isEqual(GET_CALLER_PRINCIPAL, op)) {

            // TODO: test case

        } else if (isEqual(GET_ROLLBACK_ONLY, op) | isEqual(SET_ROLLBACK_ONLY, op)) {
            ctx.setRollbackOnly();
            if (ctx.getRollbackOnly()) {
                try {
                    log(MDBContext.class, ON_MESSAGE, MDBContext.class, GET_ROLLBACK_ONLY);
                } catch (Exception e) {
                    logger.error("Cannot log for RollBackOnly", e);
                }
            }

        } else if (isEqual(TIMER, op)) {
            try {
                checkInstance(ctx.getTimerService());
                log(MDBContext.class, ON_MESSAGE, MDBContext.class, TIMER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (isEqual(USER_TRANSACTION, op)) {
            try {
                ctx.getUserTransaction();
            } catch (IllegalStateException e) {
                try {
                    log(MDBContext.class, ON_MESSAGE, MDBContext.class, USER_TRANSACTION);
                } catch (Exception ee) {
                    logger.error("Cannot log for getUserTransaction()", ee);
                }
            }
        }

    }

}
