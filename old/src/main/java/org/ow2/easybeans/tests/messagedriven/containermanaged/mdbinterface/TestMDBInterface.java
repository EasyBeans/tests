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
 * $Id: TestMDBInterface.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.messagedriven.containermanaged.mdbinterface;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.UNDEFINED;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.MESSAGE_DRIVEN_CONTEXT;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.mdbinterface.MDBInterface00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfOperationLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBOperationLoggerAccess;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if a mdb that implements the MessageDrivenBean interface works
 * properly.
 * @reference JSR 220 - EJB 3.0 Core - 5.4.6
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.mdbinterface.MDBInterface00</li>
 *              (Ant task: install.jar.tests.messagedriven.interface)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBInterface {

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Logger bean.
     */
    private ItfOperationLoggerAccess beanLogger;

    /**
     * Creates the JMS manager.
     * @throws Exception if there is a problem.
     */
    @BeforeClass
    public void startUp00() throws Exception {
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        beanLogger = getBeanRemoteInstance(SLSBOperationLoggerAccess.class, ItfOperationLoggerAccess.class);
    }

    /**
     * Verifies if the container initiliazes the MessageDrivenContext object.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testInterface00() throws Exception {
        jmsQueue.sendControlMessage(MDBInterface00.MESSAGE_TYPE, OperationType.MESSAGE_DRIVEN_CONTEXT);

        // Verifies if the message was received
        List<String> arEvent = new ArrayList<String>();

        arEvent.add(MDBInterface00.class.getName());

        beanLogger.verifyOperation(MDBInterface00.class, UNDEFINED, arEvent
                .toArray(new String[0]), MESSAGE_DRIVEN_CONTEXT);
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
    }
}
