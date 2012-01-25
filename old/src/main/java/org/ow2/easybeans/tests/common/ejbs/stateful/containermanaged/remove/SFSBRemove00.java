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
 * $Id: SFSBRemove00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.remove;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * It is used to test a method designated as Remove method.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateful(name="SFSBRemove00")
@Remote(ItfCheck02.class)
public class SFSBRemove00 implements ItfCheck02{

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(SFSBRemove00.class);

    /**
     * Empty method.
     * @throws Exception if a problem occurs.
     */
    public void check() throws Exception {
    }

    /**
     * Method with the annotation  &#64;Remove.
     */
    @Remove
    public void remove() {
        logger.debug("Remove method invoked.");
    }
}
