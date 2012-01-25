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
 * $Id: PostConstruct02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct;

import javax.annotation.PostConstruct;
import javax.interceptor.InvocationContext;

import org.ow2.easybeans.tests.common.exception.IllegalException;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;


/**
 * Interceptor for lifecycle callback event.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class PostConstruct02 extends PostConstructCatchException00 {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(PostConstruct02.class);

    /**
     * PostConstruct lifecyle callback interceptor.
     * @param ic context
     */
    @PostConstruct
    public void interceptor(final InvocationContext ic){
        logger.debug("PostConstruct invoked.");
        throw new IllegalException("Exception in " + PostConstruct02.class.getName());
    }
}
