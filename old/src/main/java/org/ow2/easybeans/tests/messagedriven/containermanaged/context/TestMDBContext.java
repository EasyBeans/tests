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
 * $Id: TestMDBContext.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.messagedriven.containermanaged.context;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.GET_CALLER_PRINCIPAL;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.GET_ROLLBACK_ONLY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.TIMER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;

import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.context.MDBContext;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.OperationChecker;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the MessageDrivenContext is working properly.
 * @reference JSR 220-PROPOSED FINAL - Message-Driven Bean Component Contract
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.context.MDBContext</li>
 *              (Ant task: install.jar.tests.messagedrivencontext)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBContext {

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Log checker.
     */
    private OperationChecker checker;

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
     * Gets the bean logger instance and clears previous tests information.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp01() throws Exception {
        checker.deleteAll();
    }

    /**
     * Verifies if the getCallerPrincipal() is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testCallerPrincipal() throws Exception {
        jmsQueue.sendControlMessage(MDBContext.MESSAGE_TYPE, GET_CALLER_PRINCIPAL);
        checker.check(MDBContext.class.getName(), ON_MESSAGE, GET_CALLER_PRINCIPAL);
    }

    /**
     * Verifies if the getRollbackOnly()/setRollbackOnly() is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testGetRollbackOnly() throws Exception {
        jmsQueue.sendControlMessage(MDBContext.MESSAGE_TYPE, GET_ROLLBACK_ONLY);
        checker.check(MDBContext.class.getName(), ON_MESSAGE, GET_ROLLBACK_ONLY);
    }

    /**
     * Verifies if the getTimerService() is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testGetTimerService() throws Exception {
        jmsQueue.sendControlMessage(MDBContext.MESSAGE_TYPE, TIMER);
        checker.check(MDBContext.class.getName(), ON_MESSAGE, TIMER);
    }

    /**
     * Verifies if the getUserTransaction() is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testUserTransaction() throws Exception {
        jmsQueue.sendControlMessage(MDBContext.MESSAGE_TYPE, USER_TRANSACTION);
        checker.check(MDBContext.class.getName(), ON_MESSAGE, USER_TRANSACTION);
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
