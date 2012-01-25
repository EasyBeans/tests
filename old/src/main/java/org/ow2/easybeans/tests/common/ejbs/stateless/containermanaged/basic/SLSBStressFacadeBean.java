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
 * $Id: SLSBStressFacadeBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Facade bean used by heavy clients.
 * @author Florent Benoit
 */
@Stateless
public class SLSBStressFacadeBean implements SLSBStressBeanRemote {

    /**
     * Reference to a local stateless.
     */
    @EJB
    private SLSBStressBeanLocal bean1;

    /**
     * Second reference to a local stateless.
     */
    @EJB
    private SLSBStressBeanLocal bean2;


    /**
     * @return dummy value.
     */
    public int dummy1() {
        return bean1.dummy1() + bean2.dummy1();
    }


    /**
     * Do some stuff.
     */
    public void dummy2() {
        bean2.dummy2();
        bean1.dummy2();
    }
}
