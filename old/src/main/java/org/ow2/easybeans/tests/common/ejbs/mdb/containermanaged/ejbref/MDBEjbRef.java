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
 * $Id: MDBEjbRef.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.ejbref;

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
import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkBeanRef;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertOperation;
import org.ow2.easybeans.tests.common.jms.JMSManager;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * /** This bean is used to test the ejb references in the environement.
 * Operations:<li>Declaration using annotation.</li> <li>Declaration using XML.</li>
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
                + "common.ejbs.mdb.containermanaged.ejbref.MDBEjbRef'")})

@EJBs({
    @EJB(name = "ejb/beanDeclaration00", beanInterface=ItfOneMethod01.class, beanName="EJBInjectionBean"),
    @EJB(name = "ejb/beanDeclaration01", beanInterface=ItfOneMethod01.class, beanName="EJBInjectionBean")
    })
@EJB(name = "ejb/beanDeclaration02", beanInterface=ItfOneMethod01.class, beanName="EJBInjectionBean")
public class MDBEjbRef extends BaseInsertOperation{

    /**
     * Message type.
     */
    public static final String MESSAGE_TYPE = "org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.ejbref."
            + "MDBEjbRef";

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(MDBEjbRef.class);

    /**
     * Bean.
     */
    @SuppressWarnings("unused")
    @EJB(name="ejb/beanFieldInjection", beanName = "EJBInjectionBean")
    private ItfOneMethod01 beanFieldInjection;

    /**
     * Bean.
     */
    @SuppressWarnings("unused")
    @EJB(name="ejb/beanOverrideField", beanName = "EJBInjectionBean")
    private ItfOneMethod01 beanOverrideFieldInjection;

    /**
     * Bean.
     */
    private ItfOneMethod01 beanMethodInjection;

    /**
     * Bean.
     */
    private ItfOneMethod01 beanXMLMethodInjection;

    /**
     * Bean.
     */
    private ItfOneMethod01 beanXMLFieldInjection;

    /**
     * Bean.
     */
    private ItfOneMethod01 beanOverrideMethodInjection;

    /**
     * Setter method.
     * @param b bean
     */
    @EJB(name = "ejb/beanMethodInjection", beanName = "EJBInjectionBean")
    public void setMethodInjection(final ItfOneMethod01 b){
        beanMethodInjection = b;
    }

    /**
     * Setter method.
     * @param b bean
     */
    public void setXMLMethodInjection(final ItfOneMethod01 b){
        beanXMLMethodInjection = b;
    }

    /**
     * Setter method.
     * @param b bean
     */
    @EJB(name = "ejb/beanOverrideMethod", beanName = "EJBInjectionBean")
    public void setOverrideMethodInjection(final ItfOneMethod01 b){
        beanOverrideMethodInjection = b;
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
            checkBeanRef(ctx, "ejb/beanDeclaration00", ItfOneMethod01.class);
            checkBeanRef(ctx, "ejb/beanDeclaration01", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, ANNOTATION_RESOURCES_DECLARATION);
            logger.debug("{0} is working properly.", ANNOTATION_RESOURCES_DECLARATION.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_RESOURCES_DECLARATION.toString(), e.getMessage());
        }
        try {
            checkBeanRef(ctx, "ejb/beanDeclaration02", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, ANNOTATION_RESOURCE_DECLARATION);
            logger.debug("{0} is working properly.", ANNOTATION_RESOURCE_DECLARATION.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_RESOURCE_DECLARATION.toString(), e.getMessage());
        }

        //Declaration using XML.
        try {
            checkBeanRef(ctx, "ejb/beanXMLDeclaration", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, XML_RESOURCE_DECLARATION);
            logger.debug("{0} is working properly.", XML_RESOURCE_DECLARATION.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", XML_RESOURCE_DECLARATION.toString(), e.getMessage());
        }

        //Injection in a field using annotation.
        try {
            assert beanFieldInjection.getBool();
            checkBeanRef(ctx, "ejb/beanFieldInjection", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, ANNOTATION_INJECTION_FIELD);
            logger.debug("{0} is working properly.", ANNOTATION_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_INJECTION_FIELD.toString(), e.getMessage());
        }

        //Injection in a method using annotation.
        try {
            assert beanMethodInjection.getBool();
            checkBeanRef(ctx, "ejb/beanMethodInjection", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, ANNOTATION_INJECTION_METHOD);
            logger.debug("{0} is working properly.", ANNOTATION_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", ANNOTATION_INJECTION_METHOD.toString(), e.getMessage());
        }

        //Injection in a field using XML.
        try {
            assert beanXMLFieldInjection.getBool();
            checkBeanRef(ctx, "ejb/beanXMLFieldInjection", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, XML_INJECTION_FIELD);
            logger.debug("{0} is working properly.", XML_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", XML_INJECTION_FIELD.toString(), e.getMessage());
        }

        //Injection in a method using XML.
        try {
            assert beanXMLMethodInjection.getBool();
            checkBeanRef(ctx, "ejb/beanXMLMethodInjection", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, XML_INJECTION_METHOD);
            logger.debug("{0} is working properly.", XML_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", XML_INJECTION_METHOD.toString(), e.getMessage());
        }

        //Override an injection in a field with a XML.
        try {
            //Attention: the "ejb/beanOverrideFieldInjection" is the injection declared using the XML.
            assert beanOverrideFieldInjection.getBool();
            checkBeanRef(ctx, "ejb/beanOverrideFieldInjection", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, OVERRIDE_INJECTION_FIELD);
            logger.debug("{0} is working properly.", OVERRIDE_INJECTION_FIELD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", OVERRIDE_INJECTION_FIELD.toString(), e.getMessage());
        }

        //Override an injection in a method with a XML.
        try {
            //Attention: the "ejb/beanOverrideMethodInjection" is the injection declared using the XML.
            assert beanOverrideMethodInjection.getBool();
            checkBeanRef(ctx, "ejb/beanOverrideMethodInjection", ItfOneMethod01.class);
            super.log(MDBEjbRef.class, ON_MESSAGE, MDBEjbRef.class, OVERRIDE_INJECTION_METHOD);
            logger.debug("{0} is working properly.", OVERRIDE_INJECTION_METHOD.toString());
        } catch (Exception e) {
            logger.debug("Error checking {0}: {1}", OVERRIDE_INJECTION_METHOD.toString(), e.getMessage());
        }
    }
}
