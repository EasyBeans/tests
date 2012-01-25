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
 * $Id: SLSBModifyEnvironment00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.ejbs.base.ItfModifyEnvironment;


/**
 * This bean tries to modify the runtime environment. It is denied by the specification.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless
@Remote(ItfModifyEnvironment.class)
public class SLSBModifyEnvironment00 implements ItfModifyEnvironment{

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void modifyEnvironment(){
        try {
            Context initCtx = new InitialContext();
            Context myEnv = (Context) initCtx.lookup("java:comp/env");

            myEnv.addToEnvironment("flt00", new Float(INJECTED_FLOAT));
            myEnv.removeFromEnvironment("flt00");

        } catch (NamingException e) {
            throw new IllegalStateException("The context could not be obtained.");
        }
    }
}
