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
 * $Id: TestMDBElements.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.UNDEFINED;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Verifies if the container can deploy a message-driven bean with the parameters defined in
 * the deployment descriptor.
 * @reference JSR 220 - FINAL RELEASE - 5.4.1
 * @requirement The BasicMDBBean with the deployment
 *              descriptors must be deployed.
 *              (Ant task: install.jar.tests.xmldescriptor.ejb-jar.mdb)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TestMDBElements {

    /**
     * Expected MDB Topic name.
     */
    private static final String MDB_TOPIC_MESSAGE_TYPE =
        "org.ow2.easybeans.tests.common.ejbs.base.xmldescriptor.MDBBasicTopicXML";

    /**
     * Expected MDB Queue name.
     */
    private static final String MDB_QUEUE_MESSAGE_TYPE =
        "org.ow2.easybeans.tests.common.ejbs.base.xmldescriptor.MDBBasicQueueXML";

    /**
     * JMS Manager.
     */
    private JMSManager jmsTopic;

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Logger bean.
     */
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * Creates the JMS manager.
     * @throws Exception if there is a problem.
     */
    @BeforeClass
    public void startUp00() throws Exception {
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        jmsTopic = new JMSManager(JMSManager.DEFAULT_TOPIC_CONNECTION_FACTORY, JMSManager.DEFAULT_TOPIC);
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
    }

    /**
     * Gets the bean logger instance and clears previous tests information.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp01() throws Exception {
        beanLogger.deleteAll();
    }

    /**
     * Verifies if a message-driven bean defined using XML is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testTopic00() throws Exception {
        jmsTopic.sendControlMessage(MDB_TOPIC_MESSAGE_TYPE, UNDEFINED);

        // Verifies if the message was received
        List<String> arEvent = new ArrayList<String>();

        arEvent.add(MDB_TOPIC_MESSAGE_TYPE);

        beanLogger.verifyCallbackOrder(MDB_TOPIC_MESSAGE_TYPE, ON_MESSAGE, arEvent.toArray(new String[0]));
    }

    /**
     * Verifies if a message-driven bean defined using XML is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testQueue00() throws Exception {
        jmsQueue.sendControlMessage(MDB_QUEUE_MESSAGE_TYPE, UNDEFINED);

        // Verifies if the message was received
        List<String> arEvent = new ArrayList<String>();

        arEvent.add(MDB_QUEUE_MESSAGE_TYPE);

        beanLogger.verifyCallbackOrder(MDB_QUEUE_MESSAGE_TYPE, ON_MESSAGE, arEvent.toArray(new String[0]));
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @AfterMethod
    public void tearDown() throws Exception {
        beanLogger.deleteAll();
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
