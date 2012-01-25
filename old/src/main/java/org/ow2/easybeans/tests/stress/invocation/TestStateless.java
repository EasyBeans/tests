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
 * $Id: TestStateless.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.stress.invocation;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic.SLSBStressBeanRemote;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic.SLSBStressFacadeBean;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Tests the support for multiple call on stateless beans.
 * @requirement Application Server must be running.<br> (Ant task:
 *              install.jar.tests.sessionbean + jar.tests.basic)
 * @author Florent Benoit
 */
public class TestStateless {

    /**
     * Number of threads for each client.
     */
    public static final int NUMBER_THREADS = 10;

    /**
     * Number of threads for each client.
     */
    public static final int NUMBER_INVOCATION = 10;


    /**
     * Facade bean.
     */
    private SLSBStressBeanRemote facadeBean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with bean initialization.
     */
    @BeforeSuite
    public void startUp() throws Exception {
        facadeBean = getBeanRemoteInstance(SLSBStressFacadeBean.class, SLSBStressBeanRemote.class);
    }

    /**
     * Tests the support for many calls in parallel on the same stateful bean.
     * @throws Exception if a problem occurs.
     */
    @Test(threadPoolSize = NUMBER_THREADS, invocationCount = NUMBER_INVOCATION)
    public void testMultipleThreadsCalls() throws Exception {
        facadeBean.dummy1();
        facadeBean.dummy2();
    }

}
