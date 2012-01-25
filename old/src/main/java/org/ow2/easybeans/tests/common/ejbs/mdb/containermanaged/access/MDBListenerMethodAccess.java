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
 * $Id: MDBListenerMethodAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.access;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTERPRISE_BEAN;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTITY_MANAGER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTITY_MANAGER_FACTORY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.RESOURCE_MANAGER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.TIMER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.isEqual;
import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TimerService;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.easybeans.tests.common.jms.MessageProperty;
import org.ow2.easybeans.tests.common.resources.EMFactoryTester;
import org.ow2.easybeans.tests.common.resources.EntityManagerTester;
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
            "TYPE ='org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.access.MDBListenerMethodAccess'")
        })
@EJB(name = "ejb/bean01", beanInterface = ItfOneMethod01.class, beanName = "EJBInjectionBean")
@Resource(name = "jdbc/ds01", type = javax.sql.DataSource.class, mappedName = "jdbc_1")
@PersistenceUnit(name = "persistence/pu01")
@PersistenceContext(name = "persistence/pctx01")
public class MDBListenerMethodAccess extends BaseInsertOperation {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(MDBListenerMethodAccess.class);

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs."
            + "mdb.containermanaged.access.MDBListenerMethodAccess";

    /**
     * ID.
     */
    private static final long serialVersionUID = 4108218174206348937L;

    /**
     * Context.
     */
    @Resource
    private MessageDrivenContext ctx;

    /**
     * Timer.
     */
    @Resource
    private TimerService timer;

    /**
     * UserTransaction must not be injected.
     */
    @Resource
    private UserTransaction utx;

    /**
     * Verifies if the operations allowed for Message Driven listener methods are working properly.
     * @param message msg
     */
    public void onMessage(final Message message) {
        String op = null;

        try {
            op = message.getStringProperty(MessageProperty.OPERATION.toString());
        } catch (JMSException e) {
            logger.debug("Error getting operation type: {0}", e);
        }

        if (isEqual(RESOURCE_MANAGER, op)) {
            DataSource ds = (DataSource) ctx.lookup("jdbc/ds01");
            checkResource(ds);
            log(MDBListenerMethodAccess.class, ON_MESSAGE, MDBListenerMethodAccess.class, RESOURCE_MANAGER);

        } else if (isEqual(ENTERPRISE_BEAN, op)) {
            ItfOneMethod01 bean = (ItfOneMethod01) ctx.lookup("ejb/bean01");
            bean.getBool();
            log(MDBListenerMethodAccess.class, ON_MESSAGE, MDBListenerMethodAccess.class, ENTERPRISE_BEAN);

        } else if (isEqual(ENTITY_MANAGER_FACTORY, op)) {
            EntityManagerFactory emf = (EntityManagerFactory) ctx.lookup("persistence/pu01");
            try {
                EMFactoryTester.checkInstance(emf, "tmpTable" + this.hashCode());
                log(MDBListenerMethodAccess.class, ON_MESSAGE, MDBListenerMethodAccess.class, ENTITY_MANAGER_FACTORY);
            } catch (Exception e) {
                logger.debug("Error in EntityManagerFactory use: {0}", e);
                e.printStackTrace();
            }

        } else if (isEqual(ENTITY_MANAGER, op)) {
            EntityManager em = (EntityManager) ctx.lookup("persistence/pctx01");
            try {
                EntityManagerTester.checkInstance(em, "tmpTable" + this.hashCode());
                log(MDBListenerMethodAccess.class, ON_MESSAGE, MDBListenerMethodAccess.class, ENTITY_MANAGER);
            } catch (Exception e) {
                logger.debug("Error in EntityManagerFactory use: {0}", e);
                e.printStackTrace();
            }

        } else if (isEqual(TIMER, op)) {
            timer.hashCode();
            log(MDBListenerMethodAccess.class, ON_MESSAGE, MDBListenerMethodAccess.class, TIMER);

        } else if (isEqual(USER_TRANSACTION, op)){
            if (utx == null){
                log(MDBListenerMethodAccess.class, ON_MESSAGE, MDBListenerMethodAccess.class, USER_TRANSACTION);
            }
        }

    }

}
