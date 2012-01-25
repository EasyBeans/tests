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
 * $Id: SLSBResourceRefXMLInjection00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.resourceref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import java.net.URL;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.mail.Session;
import javax.sql.DataSource;

import org.ow2.easybeans.tests.common.ejbs.base.ItfResourceEnvRef00;

/**
 * This bean is used to test injection of resource references by xml descriptors with the injection-target tag.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBResourceRefXMLInjection00")
@Remote(ItfResourceEnvRef00.class)
public class SLSBResourceRefXMLInjection00 implements ItfResourceEnvRef00 {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private DataSource ds00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private ConnectionFactory cf00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private QueueConnectionFactory qcf00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private TopicConnectionFactory tcf00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private Queue queue00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private Topic topic00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private Session mail00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private URL url00;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private EJBContext ctx00;

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkJDBC() {
        checkResource(sessionContext, ds00, "jdbc/ds00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkJMSConFactory() {
        checkResource(sessionContext, cf00, "jms/cf00");

    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkJMSQueueConFactory() {
        checkResource(sessionContext, qcf00, "jms/qcf00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkJMSTopicConFactory() {
        checkResource(sessionContext, tcf00, "jms/tcf00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkMailSession() {
        checkResource(sessionContext, mail00, "mail/mail00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkUrl() {
        checkResource(sessionContext, url00, "url/url00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkEJBContext() {
        checkResource(sessionContext, ctx00, "ejbctx/ctx00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkJMSQueue() {
        checkResource(sessionContext, queue00, "jms/queue00");
    }

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    public void checkJMSTopic() {
        checkResource(sessionContext, topic00, "jms/topic00");
    }

}
