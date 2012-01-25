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
 * $Id: TestSimpleEnvMessageDrivenBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.reference.simpleentry;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.ON_MESSAGE;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ANNOTATION_INJECTION_FIELD;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ANNOTATION_INJECTION_METHOD;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ANNOTATION_RESOURCES_DECLARATION;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ANNOTATION_RESOURCE_DECLARATION;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.OVERRIDE_INJECTION_FIELD;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.OVERRIDE_INJECTION_METHOD;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.XML_INJECTION_FIELD;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.XML_INJECTION_METHOD;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.XML_RESOURCE_DECLARATION;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.simpleentry.MDBSimpleEntryEnv;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.OperationChecker;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Tests simple environment declaration and injection in Message-Driven beans.
 * @reference JSR 220-PROPOSED FINAL - Enterprise Bean Environment - 16.4
 * @requirement MDBs:<li>org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.simpleentry.MDBSimpleEntryEnv</li>
 * (Ant task: install.jar.tests.simple.environment.messagedriven)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSimpleEnvMessageDrivenBean {

    /**
     * JMS Manager.
     */
    private JMSManager jmsQueue;

    /**
     * Log checker.
     */
    private OperationChecker checker;

    /**
     * Configure the test.
     * @throws Exception if there is a problem.
     */
    @BeforeClass
    public void startUp00() throws Exception {
        //Creates the queue
        jmsQueue = new JMSManager(JMSManager.DEFAULT_QUEUE_CONNECTION_FACTORY, JMSManager.DEFAULT_QUEUE);
        //Sends a message to run the test
        jmsQueue.sendControlMessage(MDBSimpleEntryEnv.MESSAGE_TYPE, OperationType.UNDEFINED);
    }

    /**
     * Gets a bean logger reference.
     * @throws Exception if there is a problem.
     */
    @BeforeMethod
    public void startUp01() throws Exception {
        checker = new OperationChecker();
    }

    /**
     * Verifies declaration using annotation.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test00() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, ANNOTATION_RESOURCES_DECLARATION);
    }

    /**
     * Verifies declaration using annotation.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test01() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, ANNOTATION_RESOURCE_DECLARATION);
    }

    /**
     * Verifies declaration using XML.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test02() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, XML_RESOURCE_DECLARATION);
    }

    /**
     * Verifies injection in a field using annotation.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test03() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, ANNOTATION_INJECTION_FIELD);
    }

    /**
     * Verifies injection in a method using annotation.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test04() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, ANNOTATION_INJECTION_METHOD);
    }

    /**
     * Verifies injection in a field using XML.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test05() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, XML_INJECTION_FIELD);
    }

    /**
     * Verifies injection in a method using XML.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test06() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, XML_INJECTION_METHOD);
    }

    /**
     * Verifies an injection override in a field with a XML.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test07() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, OVERRIDE_INJECTION_FIELD);
    }

    /**
     * Verifies an injection override in a method with a XML.
     * @input a message
     * @output a logged onMessage() event
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test08() throws Exception {
        checker.check(MDBSimpleEntryEnv.class.getName(), ON_MESSAGE, OVERRIDE_INJECTION_METHOD);
    }

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        jmsQueue.close();
        checker.deleteAll();
    }
}
