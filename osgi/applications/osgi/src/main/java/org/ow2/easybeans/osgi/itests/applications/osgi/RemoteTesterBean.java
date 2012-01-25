/**
 * EasyBeans
 * Copyright (C) 2011 Bull S.A.S.
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
 * $Id: RemoteTesterBean.java 6148 2012-01-25 14:51:49Z benoitf $
 * --------------------------------------------------------------------------
 */


package org.ow2.easybeans.osgi.itests.applications.osgi;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Call local bean and export a remote interface too.
 * @author Florent Benoit
 */
@Stateless(mappedName="RemoteTesterBean")
public class RemoteTesterBean implements IRemoteTester {

    /**
     * Local instance.
     */
    @EJB
    private ITester localTestBean;

    /**
     * Method used to reproduce the bug http://jira.easybeans.org/browse/EZB-484 .
     * @return an int
     */
    public int create() {
        return localTestBean.create();
    }
}
