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
 * $Id: TestStatefulThreadSafe.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.stress.invocation;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.basic.SFSBStressBean;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.basic.SFSBStressBeanRemote;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Tests the support for invoking stateful.
 * @requirement Application Server must be running.<br> (Ant task:
 *              install.jar.tests.sessionbean + jar.tests.basic)
 * @author Florent Benoit
 */
public class TestStatefulThreadSafe {

    /**
     * Number of threads for each client.
     */
    public static final int NUMBER_LOOKUPS = 3;


    /**
     * Bean 1.
     */
    private SFSBStressBeanRemote bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with bean initialization.
     */
    @BeforeSuite
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SFSBStressBean.class, SFSBStressBeanRemote.class);
        bean.initValue();
    }

    /**
     * Tests the support for many calls in parallel on the same stateful bean.
     * @throws Exception if a problem occurs.
     */
    @Test(threadPoolSize = NUMBER_LOOKUPS, invocationCount = NUMBER_LOOKUPS)
    public void testMultipleThreadsCalls() throws Exception {
        bean.testThreadSafe();
    }

}
