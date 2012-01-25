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
 * $Id: AccessSessionContext.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.inheritance;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;


/**
 * Gets the SessionContext with the annotation &#64;Resource and verifies if the
 * value is not null.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class AccessSessionContext implements ItfAccessSessionContext {

    /**
     * The bean SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Gets a session context reference.
     * @param obj this object is not used with this implementation.
     * @return the session context reference.
     * @throws Exception if a problem occurs.
     */
    public Object accessSessionContext(final Object obj) throws Exception {
        String strContext = null;
        if (sessionContext != null){
            strContext = sessionContext.toString();
        }
        return strContext;
    }

}
