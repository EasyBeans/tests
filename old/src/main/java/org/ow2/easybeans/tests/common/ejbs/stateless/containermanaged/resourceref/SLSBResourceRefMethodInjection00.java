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
 * $Id: SLSBResourceRefMethodInjection00.java 5369 2010-02-24 14:58:19Z benoitf $
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
 * This bean is used to test injection of resource references in setter methods.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBResourceRefMethodInjection00")
@Remote(ItfResourceEnvRef00.class)
public class SLSBResourceRefMethodInjection00 implements ItfResourceEnvRef00{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The reference must be injected by the container.
     */
    private DataSource dsInjection00;

    /**
     * Setter method. It is an injection target.
     * @param ds Data Source
     */
    @Resource(name = "jdbc/ds00")
    public void setJDBC00(final DataSource ds){
        dsInjection00 = ds;
    }

    /**
     * The reference must be injected by the container.
     */
    private DataSource dsInjection01;

    /**
     * Setter method. It is an injection target.
     * @param ds Data Source
     */
    @Resource(name = "jdbc/ds01", mappedName = "jdbc_1")
    public void setJDBC01(final DataSource ds){
        dsInjection01 = ds;
    }

    /**
     * The reference must be injected by the container.
     */
    private DataSource dsInjection02;

    /**
     * Setter method. It is an injection target.
     * @param ds Data Source
     */
    @Resource(name = "jdbc/ds02", shareable = true)
    public void setJDBC02(final DataSource ds){
        dsInjection02 = ds;
    }

    /**
     * The reference must be injected by the container.
     */
    private DataSource ds03;

    /**
     * Setter method. It is an injection target.
     * @param ds Data Source
     */
    @Resource
    public void setDs03(final DataSource ds){
        ds03 = ds;
    }

    /**
     * The reference must be injected by the container.
     */
    private ConnectionFactory cfInjection00;

    /**
     * Setter method. It is an injection target.
     * @param con Connection Factory
     */
    @Resource(name = "jms/cf00")
    public void setConFactory00(final ConnectionFactory con){
        cfInjection00 = con;
    }

    /**
     * The reference must be injected by the container.
     */
    private ConnectionFactory conFactory01;

    /**
     * Setter method. It is an injection target.
     * @param con Connection Factory
     */
    @Resource(type=ConnectionFactory.class)
    public void setConFactory01(final ConnectionFactory con){
        conFactory01 = con;
    }

    /**
     * The reference must be injected by the container.
     */

    private QueueConnectionFactory queueConFactory00;

    /**
     * Setter method. It is an injection target.
     * @param con Queue Connection Factory
     */
    @Resource(name = "jms/qcf00")
    public void setQueueConFactory00(final QueueConnectionFactory con){
        queueConFactory00 = con;
    }

    /**
     * The reference must be injected by the container.
     */

    private TopicConnectionFactory topicConFactory00;

    /**
     * Setter method. It is an injection target.
     * @param con Topic Connection Factory
     */
    @Resource(name = "jms/tcf00")
    public void setTopicConFactory00(final TopicConnectionFactory con){
        topicConFactory00 = con;
    }

    /**
     * The reference must be injected by the container.
     */

    private Queue queue00;

    /**
     * Setter method. It is an injection target.
     * @param q queueu
     */
    @Resource(name = "jms/queue00")
    public void setQueue00(final Queue q){
        queue00 = q;
    }

    /**
     * The reference must be injected by the container.
     */

    private Topic topic00;

    /**
     * Setter method. It is an injection target.
     * @param t topic
     */
    @Resource(name = "jms/topic00")
    public void setQueue00(final Topic t){
        topic00 = t;
    }

    /**
     * The reference must be injected by the container.
     */
    private Session mail00;

    /**
     * Setter method. It is an injection target.
     * @param session Mail Session
     */
    @Resource(name = "mail/mail00")
    public void setTopicConFactory00(final Session session){
        mail00 = session;
    }

    /**
     * The reference must be injected by the container.
     */
    private URL url00;

    /**
     * Setter method. It is an injection target.
     * @param url url
     */
    @Resource(name = "url/url00")
    public void setUrl00(final URL url){
        url00 = url;
    }

    /**
     * The reference must be injected by the container.
     */
    private EJBContext ctx00;

    /**
     * Setter method. It is an injection target.
     * @param ctx reference
     */
    @Resource(name = "ejbctx/ctx00")
    public void setCtx00(final EJBContext ctx){
        ctx00 = ctx;
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkJDBC() {
        checkResource(sessionContext, dsInjection00, "jdbc/ds00");
        checkResource(sessionContext, dsInjection01, "jdbc/ds01");
        checkResource(sessionContext, dsInjection02, "jdbc/ds02");
        checkResource(sessionContext, ds03, "org.ow2.easybeans.tests.common."
                + "ejbs.stateless.containermanaged.resourceref.SLSBResourceRefMethodInjection00/ds03");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkJMSConFactory() {
        checkResource(sessionContext, cfInjection00, "jms/cf00");
        checkResource(sessionContext, conFactory01, "org.ow2.easybeans.tests."
                + "common.ejbs.stateless.containermanaged.resourceref.SLSBResourceRefMethodInjection00/conFactory01");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkJMSQueueConFactory() {
        checkResource(sessionContext, queueConFactory00, "jms/qcf00");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkJMSTopicConFactory() {
        checkResource(sessionContext, topicConFactory00, "jms/tcf00");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkMailSession() {
        checkResource(sessionContext, mail00, "mail/mail00");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkUrl() {
        checkResource(sessionContext, url00, "url/url00");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkEJBContext() {
        checkResource(sessionContext, ctx00, "ejbctx/ctx00");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkJMSQueue() {
        checkResource(sessionContext, queue00, "jms/queue00");
    }

    /**
     * Checks if the annotation &#64;Resource on a method is working properly to ejb context resource.
     */
    public void checkJMSTopic() {
        checkResource(sessionContext, topic00, "jms/topic00");
    }
}
