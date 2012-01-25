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
 * $Id: TestRequiredRemoteMDB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.mdb;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.transaction.MDBCMTRequired;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.CallbackChecker;
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
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.transaction.MDBCMTRequired</li>
 *              SLSB: <li>org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBCMTInheritance</li>
 *              (Ant task: install.jar.tests.transaction)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TestRequiredRemoteMDB {

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
        checker = new CallbackChecker();
    }

    /**
     * Verifies if the container-managed transaction in the
     * message-driven bean with transaction attribute REQUIRED is working properly.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testRequired00() throws Exception {
        jmsQueue.sendControlMessage(MDBCMTRequired.MESSAGE_TYPE, OperationType.UNDEFINED);
        checker.check(MDBCMTRequired.class.getName(), ON_MESSAGE);
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
