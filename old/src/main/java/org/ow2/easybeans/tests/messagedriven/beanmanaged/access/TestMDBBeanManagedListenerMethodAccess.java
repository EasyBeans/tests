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
 * $Id: TestMDBBeanManagedListenerMethodAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.messagedriven.beanmanaged.access;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;

import org.ow2.easybeans.tests.common.ejbs.mdb.beanmanaged.access.MDBBeanManagedListenerMethodAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.OperationChecker;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if a message listener method can perform the operations allowed by the specification(Table 3).
 * @reference JSR 220-PROPOSED FINAL - Message-Driven Bean Component Contract
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.beanmanaged.access.MDBBeanManagedListenerMethodAccess</li>
 *              (Ant task: install.jar.tests.messagedriven.contract)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBBeanManagedListenerMethodAccess {

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
     * Verifies if a message listener method can access the UserTransaction.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testUserTransaction() throws Exception {
        jmsQueue.sendControlMessage(MDBBeanManagedListenerMethodAccess.MESSAGE_TYPE, USER_TRANSACTION);
        checker.check(MDBBeanManagedListenerMethodAccess.class.getName(), ON_MESSAGE, USER_TRANSACTION);
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
