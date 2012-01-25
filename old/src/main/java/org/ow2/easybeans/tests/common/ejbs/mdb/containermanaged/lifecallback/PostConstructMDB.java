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
 * $Id: PostConstructMDB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.mdb.containermanaged.lifecallback;

import javax.annotation.PostConstruct;
import javax.interceptor.InvocationContext;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;


/**
 * PostConstruct interceptor.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class PostConstructMDB {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(PostConstructMDB.class);

    /**
     * PostConstruct lifecyle callback interceptor method.
     * @param ic context
     */
    @PostConstruct
    public void intercept(final InvocationContext ic) {
        try {
            logger.debug("External interceptor was invoked.");
            if (ic.getTarget() instanceof MDBLifecycle00){
               ((MDBLifecycle00) ic.getTarget()).setStatusPostConstruct(true);
            }
            ic.proceed();
        } catch (Exception e) {
            throw new IllegalStateException("Exception in interceptor.");
        }
    }

}
