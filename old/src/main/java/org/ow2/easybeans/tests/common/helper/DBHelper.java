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
 * $Id: DBHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Gets the datasource reference.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public final class DBHelper {

    /**
     * Creates a new instance of DBHelper.
     *
     */
    private DBHelper(){

    }

    /**
     * Does the lookup and returns the database.
     * @param dsName the database wanted.
     * @return the database in the registry with the dsName.
     * @throws NamingException if a naming error occurs.
     */
    public static DataSource getDataSource(final String dsName) throws NamingException {
        // sets the context
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

        // gets the initialcontext
        Context initialContext = new InitialContext();

        // gets the database
       return (DataSource) initialContext.lookup(dsName);
    }
}
