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
 * $Id: MDBResourceRef.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.resourceref;

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
import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.sql.DataSource;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This bean is used to test the "resource" annotation on the bean class.
 * Resources will be declared in the environment, but they will not be injected.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@MessageDriven(name = "MDBResourceRef", messageListenerInterface = MessageListener.class, activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = JMSManager.DEFAULT_QUEUE),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "TYPE = 'org.ow2.easybeans.tests."
                + "common.ejbs.mdb.containermanaged.resourceref.MDBResourceRef'")})
@Resources({@Resource(name = "jdbc/ds00", type = javax.sql.DataSource.class)})
@Resource(name = "jdbc/ds01", type = javax.sql.DataSource.class)
public class MDBResourceRef extends BaseInsertOperation {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBResourceRef.class);

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.resourceref."
            + "MDBResourceRef";

    /**
     * Context.
     */
    @Resource
    private MessageDrivenContext ctx;

    /**
     * Field.
     */
    private DataSource dsMethodInjection;

    /**
     * The value must be injected by the container.
     */
    @Resource(name = "jdbc/dsFieldInjection", mappedName="jdbc_1")
    private DataSource dsFieldInjection;

    /**
     * The value must be injected by the container using the XML value.
     */
    private DataSource dsXMLFieldInjection;

    /**
     * The value must be injected by the container using the XML value.
     */
    private DataSource dsXMLMethodInjection;

    /**
     * The value must be injected by the container using the XML value (Override
     * test).
     */
    @Resource(name = "jdbc/dsOverrideField")
    private DataSource dsOverrideFieldInjection;

    /**
     * The value must be injected by the container using the XML value (Override
     * test).
     */
    private DataSource dsOverrideMethodInjection;

    /**
     * The value must be injected by the container.
     * @param value information value
     */
    @SuppressWarnings("unused")
    @Resource(name = "jdbc/dsMethodInjection", mappedName="jdbc_1")
    private void setMethodInjection(final DataSource value) {
        dsMethodInjection = value;
    }

    /**
     * The value must be injected by the container.
     * @param value information value
     */
    @SuppressWarnings("unused")
    private void setXMLMethodInjection(final DataSource value) {
        dsXMLMethodInjection = value;
    }

    /**
     * The value must be injected by the container.
     * @param value information value
     */
    @SuppressWarnings("unused")
    @Resource(name = "jdbc/dsOverrideMethod")
    private void setOverrideMethodInjection(final DataSource value) {
        dsOverrideMethodInjection = value;
    }

    /**
     * Tests declaration and injection.
     * @param message msg
     */
    public void onMessage(final Message message) {

        // Declaration using annotation.
        try {
            checkResource(ctx, "jdbc/dsDeclaration00");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, ANNOTATION_RESOURCES_DECLARATION);
            logger.debug("{0} is working properly.", ANNOTATION_RESOURCES_DECLARATION.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", ANNOTATION_RESOURCES_DECLARATION.toString(), e.getMessage());
        }
        try {
            checkResource(ctx, "jdbc/dsDeclaration01");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, ANNOTATION_RESOURCE_DECLARATION);
            logger.debug("{0} is working properly.", ANNOTATION_RESOURCE_DECLARATION.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", ANNOTATION_RESOURCE_DECLARATION.toString(), e.getMessage());
        }

        // Declaration using XML.
        try {
            checkResource(ctx, "jdbc/dsXMLDeclaration");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, XML_RESOURCE_DECLARATION);
            logger.debug("{0} is working properly.", XML_RESOURCE_DECLARATION.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", XML_RESOURCE_DECLARATION.toString(), e.getMessage());
        }

        // Injection in a field using annotation.
        try {

            checkResource(ctx, dsFieldInjection, "jdbc/dsFieldInjection");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, ANNOTATION_INJECTION_FIELD);
            logger.debug("{0} is working properly.", ANNOTATION_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", ANNOTATION_INJECTION_FIELD.toString(), e.getMessage());
        }

        // Injection in a method using annotation.
        try {
            checkResource(ctx, dsMethodInjection, "jdbc/dsMethodInjection");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, ANNOTATION_INJECTION_METHOD);
            logger.debug("{0} is working properly.", ANNOTATION_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", ANNOTATION_INJECTION_METHOD.toString(), e.getMessage());
        }

        // Injection in a field using XML.
        try {
            checkResource(ctx, dsXMLFieldInjection, "jdbc/dsXMLFieldInjection");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, XML_INJECTION_FIELD);
            logger.debug("{0} is working properly.", XML_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", XML_INJECTION_FIELD.toString(), e.getMessage());
        }

        // Injection in a method using XML.
        try {
            checkResource(ctx, dsXMLMethodInjection, "jdbc/dsXMLMethodInjection");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, XML_INJECTION_METHOD);
            logger.debug("{0} is working properly.", XML_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", XML_INJECTION_METHOD.toString(), e.getMessage());
        }

        // Override an injection in a field with a XML.
        try {
            // Warning: the "jdbc/dsOverrideFieldInjection" is the injection
            // declared using the XML.
            checkResource(ctx, dsOverrideFieldInjection, "jdbc/dsOverrideFieldInjection");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, OVERRIDE_INJECTION_FIELD);
            logger.debug("{0} is working properly.", OVERRIDE_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", OVERRIDE_INJECTION_FIELD.toString(), e.getMessage());
        }

        // Override an injection in a method with a XML.
        try {
            // Warning: the "jdbc/dsOverrideMethodInjection" is the injection
            // declared using the XML.
            checkResource(ctx, dsOverrideMethodInjection, "jdbc/dsOverrideMethodInjection");
            super.log(MDBResourceRef.class, ON_MESSAGE, MDBResourceRef.class, OVERRIDE_INJECTION_METHOD);
            logger.debug("{0} is working properly.", OVERRIDE_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.error("Error checking {0}: {1}", OVERRIDE_INJECTION_METHOD.toString(), e.getMessage());
        }
    }

}
