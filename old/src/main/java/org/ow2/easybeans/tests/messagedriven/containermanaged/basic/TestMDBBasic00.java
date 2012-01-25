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
 * $Id: TestMDBBasic00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.messagedriven.containermanaged.basic;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.UNDEFINED;

import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBQueue00;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBQueue01;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBTopic00;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBTopic01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.CallbackChecker;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if simple message-driven beans can be deployed.
 * @reference JSR 220-PROPOSED FINAL - Message-Driven Bean Component Contract
 * @requirement Application Server must be running.<br>
 *              MDBs:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBQueue00</li>
 *              <li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBQueue01</li>
 *              <li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBTopic00</li>
 *              <li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.basic.MDBTopic01</li>
*               (Ant task: install.jar.tests.messagedriven.contract)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBBasic00 {

    /**
     * JMS Manager.
     */
    private JMSManager jmsTopic;

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Log checker.
     */
    private CallbackChecker checker;

    /**
     * Creates the JMS manager.
     * @throws Exception if there is a problem.
     */
    @BeforeClass
    public void startUp00() throws Exception {
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        jmsTopic = new JMSManager(JMSManager.DEFAULT_TOPIC_CONNECTION_FACTORY, JMSManager.DEFAULT_TOPIC);
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
     * Verifies if a message-driven bean is invoked when a message is sent to a
     * topic.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testTopic00() throws Exception {
        jmsTopic.sendControlMessage(MDBTopic00.MESSAGE_TYPE, UNDEFINED);
        checker.check(MDBTopic00.class.getName(), ON_MESSAGE);
    }

    /**
     * Verifies if a message-driven bean is invoked when a message is sent to a
     * topic.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testTopic01() throws Exception {
        jmsTopic.sendControlMessage(MDBTopic01.MESSAGE_TYPE, UNDEFINED);
        checker.check(MDBTopic01.class.getName(), ON_MESSAGE);
    }

    /**
     * Verifies if a message-driven bean is invoked when a message is sent to a
     * queue.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testQueue00() throws Exception {
        jmsQueue.sendControlMessage(MDBQueue00.MESSAGE_TYPE, UNDEFINED);
        checker.check(MDBQueue00.class.getName(), ON_MESSAGE);
    }

    /**
     * Verifies if a message-driven bean is invoked when a message is sent to a
     * queue.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testQueue01() throws Exception {
        jmsQueue.sendControlMessage(MDBQueue01.MESSAGE_TYPE, UNDEFINED);
        checker.check(MDBQueue01.class.getName(), ON_MESSAGE);
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @AfterMethod
    public void tearDown() throws Exception {
        //checker.deleteAll();
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        jmsQueue.close();
        jmsTopic.close();
    }
}
