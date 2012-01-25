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
 * $Id: EmbeddedHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import org.ow2.easybeans.server.EmbeddedException;
import org.ow2.easybeans.tests.common.core.EmbeddedTest;

/**
 * Is used to makes bind and unbind in the databases.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public final class EmbeddedHelper {

    /**
     * Creates a new instance of EmbeddedHelper.
     *
     */
    private EmbeddedHelper(){

    }
    /**
     * Publishes all databases.
     * @throws EmbeddedException if an error during database publication occurs.
     */
    public static void bindDatasource() throws EmbeddedException{
        EmbeddedTest embTest = new EmbeddedTest();
        embTest.bindDatabases();
    }

    /**
     * Makes the unbind in all databases.
     * @throws EmbeddedException
     */
    public static void unbindDatasource() throws EmbeddedException{
        EmbeddedTest embTest = new EmbeddedTest();
        embTest.unbindDataBases();
    }

}
