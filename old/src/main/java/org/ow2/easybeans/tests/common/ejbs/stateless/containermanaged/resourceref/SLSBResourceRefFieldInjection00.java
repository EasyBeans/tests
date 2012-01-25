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
 * $Id: SLSBResourceRefFieldInjection00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.resourceref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import java.net.URL;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
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

import org.ow2.easybeans.tests.common.db.TableManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfResourceEnvRef00;

/**
 * This bean is used to test injection of resource references in fields.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBResourceRefFieldInjection00")
@Remote(ItfResourceEnvRef00.class)
public class SLSBResourceRefFieldInjection00 implements ItfResourceEnvRef00 {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jdbc/ds00")
    private DataSource dsInjection00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jdbc/ds01", mappedName = "jdbc_1")
    private DataSource dsInjection01;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jdbc/ds02", shareable = true,
            description = "Another jdbc.", authenticationType = AuthenticationType.CONTAINER)
    private DataSource dsInjection02;

    /**
     * The reference must be injected by the container.
     */
    @Resource
    private DataSource ds03;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private DataSource ds04;

    /**
     * The reference must be injected by the container using the xml descriptor,
     * so the jdbc_2 must be injected.
     */
    @Resource(name = "jdbc/ds05", mappedName = "jdbc_1")
    private DataSource ds05;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jms/cf00")
    private ConnectionFactory cfInjection00;

    /**
     * The reference must be injected by the container.
     */
    @Resource
    private ConnectionFactory conFactory01;

    /**
     * The reference must be injected by the container using
     * injection-target(XML).
     */
    private ConnectionFactory conFactory02;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jms/qcf00")
    private QueueConnectionFactory queueConFactory00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jms/tcf00")
    private TopicConnectionFactory topicConFactory00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jms/queue00")
    private Queue queue00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "jms/topic00")
    private Topic topic00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "mail/mail00")
    private Session mail00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "url/url00")
    private URL url00;

    /**
     * The reference must be injected by the container.
     */
    @Resource(name = "ejbctx/ctx00")
    private EJBContext ctx00;

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkJDBC() {
        checkResource(sessionContext, dsInjection00, "jdbc/ds00");
        checkResource(sessionContext, dsInjection01, "jdbc/ds01");
        checkResource(sessionContext, dsInjection02, "jdbc/ds02");
        checkResource(sessionContext, ds03, "org.ow2.easybeans.tests.common.ejbs."
                + "stateless.containermanaged.resourceref.SLSBResourceRefFieldInjection00/ds03");

        // The reference must be injected by the container using the xml
        // descriptor, so the jdbc_2 must be injected.
        checkResource(sessionContext, ds04, "jdbc/ds04");

        TableManager cTest00 = new TableManager(dsInjection02);
        TableManager cTest01 = new TableManager(ds05);
        try {
            // It must create twice the table, because each is in a different
            // database.
            cTest00.insertTable("ds04");
            cTest01.insertTable("ds04");

            cTest00.deleteTable("ds04");
            cTest01.deleteTable("ds04");
        } catch (Exception e) {
            throw new IllegalStateException("Exception: " + e.getMessage());
        }
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkJMSConFactory() {
        checkResource(sessionContext, cfInjection00, "jms/cf00");
        checkResource(sessionContext, conFactory01, "org.ow2.easybeans."
                + "tests.common.ejbs.stateless.containermanaged.resourceref.SLSBResourceRefFieldInjection00/conFactory01");
        checkResource(sessionContext, conFactory02, "jms/cf02");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkJMSQueueConFactory() {
        checkResource(sessionContext, queueConFactory00, "jms/qcf00");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkJMSTopicConFactory() {
        checkResource(sessionContext, topicConFactory00, "jms/tcf00");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * mail session resource.
     */
    public void checkMailSession() {
        checkResource(sessionContext, mail00, "mail/mail00");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkUrl() {
        checkResource(sessionContext, url00, "url/url00");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * ejb context resource.
     */
    public void checkEJBContext() {
        checkResource(sessionContext, ctx00, "ejbctx/ctx00");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkJMSQueue() {
        checkResource(sessionContext, queue00, "jms/queue00");
    }

    /**
     * Checks if the annotation &#64;Resource on a field is working properly to
     * url resource.
     */
    public void checkJMSTopic() {
        checkResource(sessionContext, topic00, "jms/topic00");
    }
}
