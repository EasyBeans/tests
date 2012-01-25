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
 * $Id: TestNotSupportedRemoteMDB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.mdb;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.transaction.MDBCMTNotSupported;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Verifies if message-driven bean with container-managed transaction is
 * following the JSR 220. The items covered in this test are: 5.4.12
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.transaction.MDBCMTNotSupported</li>
 *              SLSB: <li>org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBCMTInheritance</li>
 *              (Ant task: install.jar.tests.transaction)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TestNotSupportedRemoteMDB {

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
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
    }

    /**
     * Verifies if the container-managed transaction in the
     * message-driven bean with transaction attribute NOT_SUPPORTED is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testNotSupported00() throws Exception {
        jmsQueue.sendControlMessage(MDBCMTNotSupported.MESSAGE_TYPE, OperationType.UNDEFINED);

        // Verifies if the message was received
        List<String> arEvent = new ArrayList<String>();

        arEvent.add(MDBCMTNotSupported.class.getName());

        beanLogger.verifyCallbackOrder(MDBCMTNotSupported.class, ON_MESSAGE, arEvent
                .toArray(new String[0]));
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
