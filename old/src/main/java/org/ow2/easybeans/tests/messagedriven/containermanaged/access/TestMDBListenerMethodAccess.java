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
 * $Id: TestMDBListenerMethodAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.messagedriven.containermanaged.access;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTERPRISE_BEAN;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTITY_MANAGER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTITY_MANAGER_FACTORY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.RESOURCE_MANAGER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.TIMER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;

import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.access.MDBListenerMethodAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.OperationChecker;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if a message listener method can perform the operations allowed by the specification.
 * @reference JSR 220-PROPOSED FINAL - Message-Driven Bean Component Contract - Table 3
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.access.MDBListenerMethodAccess</li>
 *              (Ant task: install.jar.tests.messagedriven.contract)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBListenerMethodAccess {

    /**
     * Log checker.
     */
    private OperationChecker checker;

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Creates the JMS manager.
     * @throws Exception if there is a problem.
     */
   @BeforeClass
    public void startUp00() throws Exception {
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        checker = new OperationChecker();
    }

    /**
     * Verifies if a message listener method can access a resource manager.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testResourceManager() throws Exception {
        jmsQueue.sendControlMessage(MDBListenerMethodAccess.MESSAGE_TYPE, RESOURCE_MANAGER);
        checker.check(MDBListenerMethodAccess.class.getName(), ON_MESSAGE, RESOURCE_MANAGER);
    }

    /**
     * Verifies if a message listener method can access an enterprise bean.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testEnterpriseBean() throws Exception {
        jmsQueue.sendControlMessage(MDBListenerMethodAccess.MESSAGE_TYPE, ENTERPRISE_BEAN);
        checker.check(MDBListenerMethodAccess.class.getName(), ON_MESSAGE, ENTERPRISE_BEAN);
    }

    /**
     * Verifies if a message listener method can access an entity manager factory.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testEntityManagerFactory() throws Exception {
        jmsQueue.sendControlMessage(MDBListenerMethodAccess.MESSAGE_TYPE, ENTITY_MANAGER_FACTORY);
        checker.check(MDBListenerMethodAccess.class.getName(), ON_MESSAGE, ENTITY_MANAGER_FACTORY);
    }

    /**
     * Verifies if a message listener method can access an entity manager.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testEntityManager() throws Exception {
        jmsQueue.sendControlMessage(MDBListenerMethodAccess.MESSAGE_TYPE, ENTITY_MANAGER);
        checker.check(MDBListenerMethodAccess.class.getName(), ON_MESSAGE, ENTITY_MANAGER);
    }

    /**
     * Verifies if a message listener method can access the timer service.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testTimerService() throws Exception {
        jmsQueue.sendControlMessage(MDBListenerMethodAccess.MESSAGE_TYPE, TIMER);
        checker.check(MDBListenerMethodAccess.class.getName(), ON_MESSAGE, TIMER);
    }

    /**
     * Verifies if a message listener method can not access the UserTransaction.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testUserTransaction() throws Exception {
        jmsQueue.sendControlMessage(MDBListenerMethodAccess.MESSAGE_TYPE, USER_TRANSACTION);
        checker.check(MDBListenerMethodAccess.class.getName(), ON_MESSAGE, USER_TRANSACTION);
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @AfterMethod
    public void tearDown() throws Exception {
        checker.deleteAll();
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        jmsQueue.close();
    }
}
