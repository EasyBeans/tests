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
 * $Id: EJBContextTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import javax.annotation.Resource;
import javax.ejb.EJBContext;


/**
 * EJBContext Tester.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class EJBContextTester {

    /**
     * EJBContext.
     */
    @Resource(name = "ejbctx/ctx00")
    private EJBContext context;

    /**
     * Default Constructor.
     */
    public EJBContextTester(){
    }

    /**
     * Tests an ejb context reference.
     * @throws Exception if a problem occurs.
     */
    @SuppressWarnings("unused")
    protected void access00() throws Exception{
        checkInstance(context);
    }

    /**
     * Checks if an ejb context reference is working well.
     * @param ctx reference
     * @throws Exception if a problem occurs
     */
    public static void checkInstance(final EJBContext ctx) throws Exception{
        //Checks if the reference exists.
        if (ctx == null){
            throw new Exception("EJBContext is null.");
        }

        EJBContext ctx00 = (EJBContext) ctx.lookup("java:comp/EJBContext");

        if (ctx00 == null){
            throw new Exception("EJBContext could not get using another EJBContext reference.");
        }
    }
}
