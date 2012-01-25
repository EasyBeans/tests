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
 * $Id: EJBContextHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import javax.ejb.EJBContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Used to gets the EJBContext.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public final class EJBContextHelper {

    /**
     * Creates a new instance of EJBContextHelper.
     *
     */
    private EJBContextHelper() {

    }

    /**
     * Gets the EJBContext.
     * @return the bean EJBContext.
     * @throws NamingException if a naming error occurs.
     */
    public static EJBContext getEJBContext() throws NamingException {
        // sets the context
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        Context initialContext = new InitialContext();
        EJBContext ejbContext = (EJBContext) initialContext.lookup("java:comp/EJBContext");
        return ejbContext;
    }
}
