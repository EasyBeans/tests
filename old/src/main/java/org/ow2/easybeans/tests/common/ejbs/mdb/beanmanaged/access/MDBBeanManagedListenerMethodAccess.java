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
 * $Id: MDBBeanManagedListenerMethodAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.beanmanaged.access;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.isEqual;
import static org.ow2.easybeans.tests.common.resources.UserTransactionTester.checkInstance;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.easybeans.tests.common.jms.MessageProperty;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean test operations allowed in message listener methods.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
            "TYPE ='org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.access.MDBBeanManagedListenerMethodAccess'")})
@TransactionManagement(TransactionManagementType.BEAN)
public class MDBBeanManagedListenerMethodAccess extends BaseInsertOperation {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(MDBBeanManagedListenerMethodAccess.class);

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs."
            + "mdb.containermanaged.access.MDBBeanManagedListenerMethodAccess";

    /**
     * ID.
     */
    private static final long serialVersionUID = 4108218174206348937L;

    /**
     * UserTransaction must not be injected.
     */
    @Resource
    private UserTransaction utx;

    /**
     * Verifies MessageDrivenContext methods.
     * @param message msg
     * @throws JMSException
     */
    public void onMessage(final Message message) {
        String op = null;

        try {
            op = message.getStringProperty(MessageProperty.OPERATION.toString());
        } catch (JMSException e) {
            logger.debug("Error getting operation type: {0}", e);
        }

        if (isEqual(USER_TRANSACTION, op)) {
            if (utx != null) {

                try {
                    checkInstance(utx);

                    log(MDBBeanManagedListenerMethodAccess.class, ON_MESSAGE, MDBBeanManagedListenerMethodAccess.class,
                            USER_TRANSACTION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
