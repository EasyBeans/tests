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
 * $Id: AllLifeCallback00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.lifecycle.misc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * This interceptor contains methods to the four lifecycle callbacks.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class AllLifeCallback00 {

    /**
     * Logger.
     */
    private Log logger = LogFactory.getLog(AllLifeCallback00.class);

    /**
     * Simple lifecyle callback interceptor method to PostConstruct.
     * @param ic contains attributes of invocation
     */
    @PostConstruct
    public void postConstruct(final InvocationContext ic){
        logger.debug("PostConstruct method.");
        try {
            ic.proceed();
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Simple lifecyle callback interceptor method to PreDestroy.
     * @param ic contains attributes of invocation
     */
    @PreDestroy
    public void preDestroy(final InvocationContext ic){
        logger.debug("PreDestroy method.");
        try {
            ic.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Simple lifecyle callback interceptor method to PostActivate.
     * @param ic contains attributes of invocation
     */
    @PostActivate
    public void postActivate(final InvocationContext ic){
        logger.debug("PostActivate method.");
        try {
            ic.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Simple lifecyle callback interceptor method to PrePassivate.
     * @param ic contains attributes of invocation
     */
    @PrePassivate
    public void prePassivate(final InvocationContext ic){
        logger.debug("PrePassivate method.");
        try {
            ic.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
