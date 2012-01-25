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
 * $Id: AllLifeCallbackJEnvCompAccess00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.lifecycle.misc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;
import javax.sql.DataSource;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.resources.JEnvCompTester;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This interceptor contains methods to the four lifecycle callbacks.
 * Every method try to access the java:comp/env.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class AllLifeCallbackJEnvCompAccess00 extends JEnvCompTester{

    /**
     * Logger.
     */
    private Log logger = LogFactory.getLog(AllLifeCallbackJEnvCompAccess00.class);

    /**
     * Resource injection.
     */
    @SuppressWarnings("unused")
    @Resource(name="jdbc/jdbc_1", mappedName="jdbc_1")
    private DataSource ds;

    /**
     * SessionFacade Callback Logger.
     */
    @EJB(beanName="SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * PostActivate that accesses the java:comp/env.
     * @param ic contains attributes of invocation
     */
    @PostActivate
    public void postActivate(final InvocationContext ic){
        this.intercept(ic, CallbackType.POST_ACTIVATE);
    }

    /**
     * PostConstruct that accesses the java:comp/env.
     * @param ic contains attributes of invocation
     */
    @PostConstruct
    public void postConstruct(final InvocationContext ic){
        this.intercept(ic, CallbackType.POST_CONSTRUCT);
    }

    /**
     * PreDestroy that accesses the java:comp/env.
     * @param ic contains attributes of invocation
     */
    @PreDestroy
    public void preDestroy(final InvocationContext ic){
        this.intercept(ic, CallbackType.PRE_DESTROY);
    }

    /**
     * PrePassivate that accesses the java:comp/env.
     * @param ic contains attributes of invocation
     */
    @PrePassivate
    public void prePassivate(final InvocationContext ic){
        this.intercept(ic, CallbackType.PRE_PASSIVATE);
    }

    /**
     * Does the access.
     * @param ic context
     * @param type lifecycle interceptor type
     */
    protected void intercept(final InvocationContext ic, final CallbackType type){
        try {
            String beanClassName = ic.getTarget().getClass().getName();

            logger.debug("{0} - Testing access - Instance: {1}.", type.name(), beanClassName);
            super.access00();
            beanLogger.insertCallbackLogger(beanClassName, type, AllLifeCallbackJEnvCompAccess00.class.getName());
            logger.debug("{0} - Access is ok - Instance: {1}.", type.name(), beanClassName);

            ic.proceed();
        } catch (Exception e) {
            throw new IllegalStateException(type.name() + " Exception. ", e);
        }
    }
}
