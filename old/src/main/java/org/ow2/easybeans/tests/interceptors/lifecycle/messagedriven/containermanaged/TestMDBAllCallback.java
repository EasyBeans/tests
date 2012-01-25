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
 * $Id: TestMDBAllCallback.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.lifecycle.messagedriven.containermanaged;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.POST_CONSTRUCT;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.PRE_DESTROY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.UNDEFINED;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.lifecallback.MDBLifecycle00;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.lifecallback.PostConstructMDB;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.predestroy.PreDestroyLogger00;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Verifies if lifecycle callback interceptors are invoked.
 * @reference JSR 220 - EJB 3.0 Core - 12.4
 * @requirement Application Server must be running.<br>
 *              MDB:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.lifecallback.MDBLifecycle00</li>
 *              (Ant task: install.jar.tests.interceptor.lifecycle)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestMDBAllCallback {

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Logger bean.
     */
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * Creates the JMS manager and the bean logger.
     * @throws Exception if there is a problem.
     */
    @BeforeClass
    public void startUp00() throws Exception {
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
        jmsQueue.sendControlMessage(MDBLifecycle00.MESSAGE_TYPE, UNDEFINED);
    }

    /**
     * Verifies if the PostConstruct callback in invoked in the correct order.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testPostConstruct() throws Exception {
        //Interceptors list
        List<String> arLife = new ArrayList<String>();

        arLife.add(PostConstructMDB.class.getName());
        arLife.add(MDBLifecycle00.class.getName());

        beanLogger.verifyCallbackOrder(MDBLifecycle00.class, POST_CONSTRUCT,
                arLife.toArray(new String[0]));
    }

    /**
     * Verifies if the PreDestroy callback in invoked in the correct order.
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    //@Test
    public void testPreDestroy() throws Exception {
        /*TODO: test, it's necessary to force a mdb destruction.*/

        //Interceptors list
        List<String> arLife = new ArrayList<String>();

        arLife.add(PreDestroyLogger00.class.getName());
        arLife.add(MDBLifecycle00.class.getName());

        beanLogger.verifyCallbackOrder(MDBLifecycle00.class, PRE_DESTROY,
                arLife.toArray(new String[0]));
    }

    /**
     * Clears callback event log.
     * @throws Exception if a problem occurs.
     */
    @AfterClass
    public void tearDown() throws Exception{
        beanLogger.deleteAll();
        jmsQueue.close();
    }
}
