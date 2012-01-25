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
 * $Id: SFSBStressBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.basic;

import javax.ejb.Stateful;

/**
 * Bean used for testing concurrency on stateful.
 * Calls should be thread safe on a bean.
 * @author Florent Benoit
 */
@Stateful
public class SFSBStressBean implements SFSBStressBeanRemote {

    /**
     * Default value.
     */
    private static int value = 0;

    /**
     * Sleep time (so synchro can be seen more quickly).
     */
    private static final int SLEEP = 100;


    /**
     * Init the value.
     */
    public void initValue() {
        value = 0;
    }

    /**
     * Test if a method is thread safe.
     * It should be ensured by the container (only 1 call on an instance of this bean at once)
     */
    public void testThreadSafe() {
        value++;
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (value > 1) {
            throw new IllegalStateException("Not thread safe");
        }
        value--;
    }
}
