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
 * $Id: SLSBResourceRefDeclaration00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.resourceref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfResourceEnvRef00;

/**
 * This bean is used to test the "resource" annotation on the bean class. Resources will be declared in the
 *           environment, but they will not be injected.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBResourceRefDeclaration00")
@Remote(ItfResourceEnvRef00.class)
@Resources({@Resource(name = "jdbc/ds00", type = javax.sql.DataSource.class),
        @Resource(name = "jdbc/ds01", type = javax.sql.DataSource.class, mappedName="jdbc_1"),
        @Resource(name = "jms/cf00", type = javax.jms.ConnectionFactory.class),
        @Resource(name = "jms/qcf00", type = javax.jms.QueueConnectionFactory.class),
        @Resource(name = "jms/tcf00", type = javax.jms.TopicConnectionFactory.class),
        @Resource(name = "mail/mail00", type = javax.mail.Session.class),
        @Resource(name = "url/url00", type = java.net.URL.class),
        @Resource(name = "ejbctx/ctx00", type = javax.ejb.EJBContext.class),
        @Resource(name = "jms/queue00", type = javax.jms.Queue.class),
        @Resource(name = "jms/topic00", type = javax.jms.Topic.class)})
@Resource(name = "url/url01", type = java.net.URL.class)
public class SLSBResourceRefDeclaration00 implements ItfResourceEnvRef00{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Checks if the annotation &#64;Resource is working properly. The resource was declared on the bean class.
     */
    public void checkJDBC() {
        checkResource(sessionContext, "jdbc/ds00");
        checkResource(sessionContext, "jdbc/ds01");
    }

    /**
     * Checks if the annotation &#64;Resource is working properly. The resource was declared on the bean class.
     */
    public void checkJMSConFactory() {
        checkResource(sessionContext, "jms/cf00");
    }

    /**
     * Checks if the annotation &#64;Resource is working properly. The resource was declared on the bean class.
     */
    public void checkJMSQueueConFactory() {
        checkResource(sessionContext, "jms/qcf00");
    }

    /**
     * Checks if the annotation &#64;Resource is working properly. The resource was declared on the bean class.
     */
    public void checkJMSTopicConFactory() {
        checkResource(sessionContext, "jms/tcf00");
    }

    /**
     * Checks if the annotation &#64;Resource is working properly. The resource was declared on the bean class.
     */
    public void checkMailSession() {
        checkResource(sessionContext, "mail/mail00");
    }

    /**
     * Checks if the annotations &#64;Resource and &#64;Resources
     *  are working properly. The resource was declared on the bean class.
     */
    public void checkUrl() {
        checkResource(sessionContext, "url/url00");
        checkResource(sessionContext, "url/url01");
    }

    /**
     * Checks if the annotations &#64;Resource and &#64;Resources
     *  are working properly. The resource was declared on the bean class.
     */
    public void checkEJBContext() {
        checkResource(sessionContext, "ejbctx/ctx00");
    }

    /**
     * Checks if the annotations &#64;Resource and &#64;Resources
     *  are working properly. The resource was declared on the bean class.
     */
    public void checkJMSQueue() {
        checkResource(sessionContext, "jms/queue00");
    }

    /**
     * Checks if the annotations &#64;Resource and &#64;Resources
     *  are working properly. The resource was declared on the bean class.
     */
    public void checkJMSTopic() {
        checkResource(sessionContext, "jms/topic00");
    }

}
