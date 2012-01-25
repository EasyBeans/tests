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
 * $Id: TestInvocation.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.stress.invocation;

import org.ow2.easybeans.tests.common.resources.EJBInjectionInvocationMaker;
import org.testng.annotations.Test;

/**
 * Tests the support for multiple lookups and method invocations.
 * @requirement Application Server must be running.<br> (Ant task:
 *              install.jar.tests.sessionbean)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestInvocation {

    /**
     * Number of clients to be simulated.
     */
    public static final int NUMBER_CLIENTS = 10;

    /**
     * Number of lookup for each client.
     */
    public static final int NUMBER_LOOKUPS = 10;

    /**
     * Tests the support for multiple lookups and method invocations.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testLookup() throws Exception {
        for (int i = 0; i < NUMBER_CLIENTS; i++) {
            new EJBInjectionInvocationMaker(NUMBER_LOOKUPS).start();
        }
    }

}
