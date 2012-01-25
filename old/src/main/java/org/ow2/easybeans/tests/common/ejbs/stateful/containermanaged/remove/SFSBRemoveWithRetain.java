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
 * $Id: SFSBRemoveWithRetain.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.remove;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck03;
import org.ow2.easybeans.tests.common.exception.AppException;

/**
 * It is used to test a method designated as Remove method.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateful(name="SFSBRemoveWithRetain")
@Remote(ItfCheck03.class)
public class SFSBRemoveWithRetain implements ItfCheck03{

    /**
     * This method does nothing.
     * @throws Exception it is always thrown.
     */
    public void check() throws Exception {

    }

    /**
     * Method with the annotation &#64;Remove.
     * @throws Exception it is always thrown.
     */
    @Remove(retainIfException=true)
    public void remove() throws Exception{
        throw new AppException(null);
    }
}
