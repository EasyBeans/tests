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
 * $Id: TestMDBTimeoutAccess.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.timerservice.timeout.mdb.containermanaged;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.timer.MDBTimeoutCallbackAccess00;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.easybeans.tests.timerservice.timeout.base.BaseTestTimeout;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Verifies if the timeout callback can perform the operations defined by the specification.
 * @reference JSR 220 - EJB 3.0 Core - Chapter 5 - Table 3
 * @requirement Application Server must be running.<br>
 *              MDBs:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.timer.MDBTimeoutCallbackAccess00</li>
*               (Ant task: install.jar.tests.timerservice)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBTimeoutAccess extends BaseTestTimeout{

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * MDB which must perform the access.
     * @return name
     */
    @Override
    public String getBeanName() {
        return MDBTimeoutCallbackAccess00.class.getName();
    }

    /**
     * Creates the JMS manager.
     * @throws Exception if there is a problem.
     */
    @Override
    @BeforeClass
    public void startUp() throws Exception {
        super.startUp();
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
    }

    /**
     * Tests if a timeout callback method is allowed to access an EJB.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test
    public void testAccessEJB00() throws Exception {
        super.testAccessEJB00();
    }

    /**
     * Tests if a timeout callback method is allowed to access the Resource
     * Manager.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test
    public void testAccessResourceManager00() throws Exception {
        super.testAccessResourceManager00();
    }

    /**
     * Tests if a timeout callback method is allowed to access the Entity
     * Manager.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test
    public void testAccessEntityManager00() throws Exception {
        super.testAccessEntityManager00();
    }

    /**
     * Tests if a timeout callback method is allowed to access the Entity
     * Manager Factory.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test
    public void testAccessEntityManagerFactory00() throws Exception {
        super.testAccessEntityManagerFactory00();
    }

    /**
     * Tests if a timeout callback method is allowed to access the Timer
     * Service.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test
    public void testAccessTimerService00() throws Exception {
        super.testAccessTimerService00();
    }

    /**
     * Tests if a timeout callback method is allowed to access the
     * UserTransaction.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test
    public void testAccessUserTransaction00() throws Exception {
        super.testAccessUserTransaction00();
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @Override
    @AfterMethod
    public void tearDownMethod() throws Exception {
        super.tearDownMethod();
    }

    /**
     * Closes the JMS Queue.
     * @throws Exception if a problem occurs.
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        jmsQueue.close();
    }

    /**
     * Starts the timer.
     * @param type operation type
     * @throws Exception if a problem occurs.
     */
    @Override
    public void requestStartTimer(final OperationType type) throws Exception{
        jmsQueue.sendControlMessage(getBeanName(), type);
    }

}
