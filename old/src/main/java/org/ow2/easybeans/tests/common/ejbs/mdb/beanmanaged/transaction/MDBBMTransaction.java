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
 * $Id: MDBBMTransaction.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.beanmanaged.transaction;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.transaction.UserTransaction;

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
            "TYPE = 'org.ow2.easybeans.tests.common.ejbs.mdb.beanmanaged.transaction.MDBBMTransaction'")})
@TransactionManagement(value = TransactionManagementType.BEAN)
public class MDBBMTransaction extends BaseInsertCallbackEvent implements MessageListener {

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs."
        + "mdb.beanmanaged.transaction.MDBBMTransaction";

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBBMTransaction.class);

    /**
     * Bean.
     */
    @EJB(beanName = "SLSBCMTInheritance")
    private ItfCMTInheritance bean;

    /**
     * UserTransaction.
     */
    @Resource
    private UserTransaction utx;

    /**
     * Manages a message.
     * @param message msg
     */
    public void onMessage(final Message message) {
        try {
            // The dummyMethod1 is NEVER, so an exception must be thrown.
            logger.debug("Invoking dummyMethod1.");
            utx.begin();
            bean.dummyMethod1();
            utx.commit();
            logger.debug("Invoking dummyMethod1 should throw an EJBException.");
        } catch (EJBException e) {
            logger.debug("MDB is OK.");
            super.log(MDBBMTransaction.class, CallbackType.ON_MESSAGE, MDBBMTransaction.class);
        } catch (Exception e) {
            logger.error("Error. Exception = {0}", e.getCause());
        }
    }

}
