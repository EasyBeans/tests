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
 * $Id: MDBSimpleEntryEnv.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.simpleentry;

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
import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkSimpleEntry;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean is used to test the simple entries in the environement. Operations:
 * <li>Declaration using annotation.</li> <li>Declaration using XML.</li>
 * <li>Injection in a field using annotation.</li> <li>Injection in a method
 * using annotation.</li> <li>Injection in a field using XML.</li> <li>Injection
 * in a method using XML.</li> <li>Override an injection in a field with a
 * XML.</li><li>Override an injection in a method with a
 * XML.</li>
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "TYPE = 'org.ow2.easybeans.tests."
            + "common.ejbs.mdb.containermanaged.simpleentry.MDBSimpleEntryEnv'")})
@Resources({@Resource(name = "simple/strDeclaration00", type = java.lang.String.class)})
@Resource(name = "simple/strDeclaration01", type = java.lang.String.class)
public class MDBSimpleEntryEnv extends BaseInsertOperation {

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs.mdb."
            + "containermanaged.simpleentry.MDBSimpleEntryEnv";

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBSimpleEntryEnv.class);

    /**
     * Field.
     */
    private String strMethodInjection;

    /**
     * The value must be injected by the container.
     */
    @Resource(name = "simple/strFieldInjection")
    private String strFieldInjection;

    /**
     * The value must be injected by the container using the XML value.
     */
    private String strXMLFieldInjection;

    /**
     * The value must be injected by the container using the XML value.
     */
    private String strXMLMethodInjection;

    /**
     * The value must be injected by the container using the XML value (Override
     * test).
     */
    @Resource(name = "simple/strOverrideField")
    private String strOverrideFieldInjection;

    /**
     * The value must be injected by the container using the XML value (Override
     * test).
     */
    private String strOverrideMethodInjection;

    /**
     * The value must be injected by the container.
     * @param value information value
     */
    @SuppressWarnings("unused")
    @Resource(name = "simple/strMethodInjection")
    private void setMethodInjection(final String value) {
        strMethodInjection = value;
    }

    /**
     * The value must be injected by the container.
     * @param value information value
     */
    @SuppressWarnings("unused")
    private void setXMLMethodInjection(final String value) {
        strXMLMethodInjection = value;
    }

    /**
     * The value must be injected by the container.
     * @param value information value
     */
    @SuppressWarnings("unused")
    @Resource(name ="simple/strOverrideMethod")
    private void setOverrideMethodInjection(final String value) {
        strOverrideMethodInjection = value;
    }

    /**
     * Context.
     */
    @Resource
    private MessageDrivenContext ctx;

    /**
     * Tests declaration and injection.
     * @param message msg
     */
    public void onMessage(final Message message) {

        //Declaration using annotation.
        try {
            checkSimpleEntry(ctx, "simple/strDeclaration00", "strDeclaration00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, ANNOTATION_RESOURCES_DECLARATION);
            logger.debug("{0} is working properly.", ANNOTATION_RESOURCES_DECLARATION.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_RESOURCES_DECLARATION.toString(), e.getMessage());
        }
        try {
            checkSimpleEntry(ctx, "simple/strDeclaration01", "strDeclaration01");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, ANNOTATION_RESOURCE_DECLARATION);
            logger.debug("{0} is working properly.", ANNOTATION_RESOURCE_DECLARATION.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_RESOURCE_DECLARATION.toString(), e.getMessage());
        }


        //Declaration using XML.
        try {
            checkSimpleEntry(ctx, "simple/strXMLDeclaration", "strXMLDeclaration00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, XML_RESOURCE_DECLARATION);
            logger.debug("{0} is working properly.", XML_RESOURCE_DECLARATION.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", XML_RESOURCE_DECLARATION.toString(), e.getMessage());
        }

        //Injection in a field using annotation.
        try {

            checkSimpleEntry(ctx, "simple/strFieldInjection", strFieldInjection, "strFieldInjection00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, ANNOTATION_INJECTION_FIELD);
            logger.debug("{0} is working properly.", ANNOTATION_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_INJECTION_FIELD.toString(), e.getMessage());
        }


        //Injection in a method using annotation.
        try {
            checkSimpleEntry(ctx, "simple/strMethodInjection", strMethodInjection, "strMethodInjection00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, ANNOTATION_INJECTION_METHOD);
            logger.debug("{0} is working properly.", ANNOTATION_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_INJECTION_METHOD.toString(), e.getMessage());
        }

        //Injection in a field using XML.
        try {
            checkSimpleEntry(ctx, "simple/strXMLFieldInjection", strXMLFieldInjection, "strXMLFieldInjection00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, XML_INJECTION_FIELD);
            logger.debug("{0} is working properly.", XML_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", XML_INJECTION_FIELD.toString(), e.getMessage());
        }

        //Injection in a method using XML.
        try {
            checkSimpleEntry(ctx, "simple/strXMLMethodInjection", strXMLMethodInjection, "strXMLMethodInjection00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, XML_INJECTION_METHOD);
            logger.debug("{0} is working properly.", XML_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", XML_INJECTION_METHOD.toString(), e.getMessage());
        }

        //Override an injection in a field with a XML.
        try {
            //Attention: the "simple/strOverrideFieldInjection" is the injection declared using the XML.
            checkSimpleEntry(ctx, "simple/strOverrideFieldInjection", strOverrideFieldInjection, "strOverrideFieldInjection00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, OVERRIDE_INJECTION_FIELD);
            logger.debug("{0} is working properly.", OVERRIDE_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", OVERRIDE_INJECTION_FIELD.toString(), e.getMessage());
        }

        //Override an injection in a method with a XML.
        try {
            //Attention: the "simple/strOverrideMethodInjection" is the injection declared using the XML.
            checkSimpleEntry(ctx, "simple/strOverrideMethodInjection",
                    strOverrideMethodInjection, "strOverrideMethodInjection00");
            super.log(MDBSimpleEntryEnv.class, ON_MESSAGE, MDBSimpleEntryEnv.class, OVERRIDE_INJECTION_METHOD);
            logger.debug("{0} is working properly.", OVERRIDE_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", OVERRIDE_INJECTION_METHOD.toString(), e.getMessage());
        }

    }

}
