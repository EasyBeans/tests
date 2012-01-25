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
 * $Id: EBaseInternalCallbackOrder00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.lifecallback;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheckPostConstruct;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;


/**
 * Tests the PostConstruct invocation order.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class EBaseInternalCallbackOrder00 implements ItfCheckPostConstruct{

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(EBaseInternalCallbackOrder00.class);

    /**
     * Shows if a post construct method was executed.
     */
    private boolean okPostConstruct;

    /**
     * PostConstruct status.
     */
    private boolean okAllPostConstruct;

    /**
     * Session Context.
     */
    @Resource
    private SessionContext ctx;

    /**
     * PostConstruct callback.
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void postConstruct00(){
        logger.debug("PostConstruct Method.");
        if (ctx == null){
            throw new IllegalStateException("PostConstruct callback must be invoked after the dependency injection.");
        }
        okPostConstruct = true;
    }

    /**
     * Checks the PostConstruct invocation.
     */
    public void check(){
        if (!okAllPostConstruct){
            throw new IllegalStateException("Some PostConstruct was not invoked.");
        }

    }

    /**
     * Gets the Postconstruct Invocation status.
     * @return true if the postconstruct callback was invoked.
     */
    protected boolean isOKPostConstruct(){
        return okPostConstruct;
    }

    /**
     * Sets the result of interceptor invocation.
     * @param b true if ok, otherwise false.
     */
    public void setStatus(final boolean b) {
        okAllPostConstruct = b;
    }

}
