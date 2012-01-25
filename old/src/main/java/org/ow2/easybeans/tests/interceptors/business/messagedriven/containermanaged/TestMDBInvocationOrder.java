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
 * $Id: TestMDBInvocationOrder.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.messagedriven.containermanaged;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.AROUND_INVOKE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.UNDEFINED;

import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.interceptororder.MDBInvocationOrder00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.CallbackChecker;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Tests the interceptor invocation order for Message-Driven Beans.
 * @reference JSR 220 - EJB 3.0 Core - 12.3.1
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.interceptororder.MDBInvocationOrder00</li>
 *              (Ant task: install.jar.tests.interceptor.business)
 * @setup gets the reference of JMS manager and bean logger.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBInvocationOrder {

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Log checker.
     */
    private CallbackChecker checker;

    /**
     * Creates the JMS manager and the bean logger.
     * @throws Exception if there is a problem.
     */
    @BeforeClass
    public void startUp00() throws Exception {
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        checker = new CallbackChecker();
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
     * Verifies if business interceptors can be used with MDBs.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testQueue00() throws Exception {
        jmsQueue.sendControlMessage(MDBInvocationOrder00.MESSAGE_TYPE, UNDEFINED);
        checker.check(MDBInvocationOrder00.class.toString(), AROUND_INVOKE);
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
