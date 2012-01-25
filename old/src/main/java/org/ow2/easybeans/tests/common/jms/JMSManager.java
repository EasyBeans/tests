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
 * $Id: JMSManager.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.jms;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;

/**
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class JMSManager {

    /**
     * Queue connection factory.
     */
    private static final String DEFAULT_TEXT_MESSAGE = "d";

    /**
     * Queue connection factory.
     */
    public static final String DEFAULT_QUEUE_CONNECTION_FACTORY = "JQCF";

    /**
     * Topic connection factory.
     */
    public static final String DEFAULT_TOPIC_CONNECTION_FACTORY = "JTCF";

    /**
     * Default queue name.
     */
    public static final String DEFAULT_QUEUE = "dummyQueue";

    /**
     * Default topic name.
     */
    public static final String DEFAULT_TOPIC = "dummyTopic";

    /**
     * Sleep time.
     */
    public static final int SLEEP = 1000;

    /**
     * Topic or Queue.
     */
    private Connection cnn = null;

    /**
     * Sender, Topic or Queue.
     */
    private MessageProducer sender = null;

    /**
     * Topic or Queue.
     */
    private Session session = null;

    /**
     * Topic or Queue.
     */
    private Destination destination = null;

    /**
     * Topic or Queue.
     */
    private ConnectionFactory factory;

    /**
     * Creates a new instance.
     * @param jndiConnectionFactoryName JNDI name of the connection factory.
     * @param jndiDestinationName JNDI name of the destination.
     * @throws Exception if a problem occurs.
     */
    public JMSManager(final String jndiConnectionFactoryName, final String jndiDestinationName) throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        InitialContext ctx = new InitialContext();
        factory = (ConnectionFactory) ctx.lookup(jndiConnectionFactoryName);
        destination = (Destination) ctx.lookup(jndiDestinationName);
        cnn = factory.createConnection();
        session = cnn.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    /**
     * Sends a text message.
     * @param text message text.
     * @throws Exception if a problem occurs.
     */
    public void sendTextMessage(final String text) throws Exception {
        TextMessage msg = session.createTextMessage(text);
        this.sendMessage(msg);
    }

    /**
     * Sends a default text message.
     * @param bean name of the destination bean.
     * @param operation operation to be performed by the bean.
     * @throws Exception if a problem occurs.
     */
    public void sendControlMessage(final String bean, final OperationType operation) throws Exception {
        TextMessage msg = session.createTextMessage(DEFAULT_TEXT_MESSAGE);
        msg.setStringProperty(MessageProperty.TYPE.toString(), bean);
        msg.setStringProperty(MessageProperty.OPERATION.toString(), operation.toString());

        this.sendMessage(msg);
    }

    /**
     * Sends a message.
     * @param msg Message
     * @throws Exception if a problem occurs.
     */
    public void sendMessage(final Message msg) throws Exception{
        sender = session.createProducer(destination);
        sender.send(msg);

        sender.close();
        Thread.sleep(SLEEP);
    }

    /**
     * Sends object message.
     * @param type message type.
     * @param message contains the message to be sent.
     * @throws Exception if a problem occurs.
     */
    public void sendObjectMessage(final String type, final Serializable message) throws Exception {
        ObjectMessage msg = session.createObjectMessage(message);
        msg.setStringProperty(MessageProperty.TYPE.toString(), type);

        this.sendMessage(msg);
    }

    /**
     * Closes JMS connections.
     * @throws Exception if a problem occurs.
     */
    public void close() throws Exception{
        session.close();
        cnn.close();
    }

}
