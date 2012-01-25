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
 * $Id: PostConstruct01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct;

import javax.annotation.PostConstruct;
import javax.interceptor.InvocationContext;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Interceptor for lifecycle callback event.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class PostConstruct01 {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(PostConstruct01.class);

    /**
     * PostConstruct lifecyle callback interceptor.
     * @param ic contains attributes of invocation
     */
    @PostConstruct
    public void interceptor(final InvocationContext ic){
        try {
            if (ic.proceed() != null) {
                throw new Exception("The previous interceptor method is void and the proceed should return null.");
            }
        } catch (Exception e) {
            logger.debug("Exception: {0}", e.toString());
            throw new IllegalStateException("Exception in " + PostConstruct01.class.getName());
        }
        logger.debug("PostConstruct executed.");
    }
}
