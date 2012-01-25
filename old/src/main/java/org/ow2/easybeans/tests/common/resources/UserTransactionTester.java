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
 * $Id: UserTransactionTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.exception.CustomException00;


/**
 * UserTransaction Tester.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class UserTransactionTester {

    /**
     * Injected UserTransaction.
     */
    @Resource
    private UserTransaction utx;

    /**
     * Default Constructor.
     */
    public UserTransactionTester(){
    }

    /**
     * Tests an userTransaction access.
     * @throws Exception if a problem occurs.
     */
    protected void access00() throws Exception{
        checkInstance(utx);
    }

    /**
     * Checks if an user transaction reference is working well.
     * @param utx reference
     * @throws Exception if a problem occurs
     */
    public static void checkInstance(final UserTransaction utx) throws Exception{
        if (utx == null){
            throw new CustomException00("UserTransaction reference is null.");
        }
        utx.begin();
        utx.commit();
    }

    /**
     * Checks if an user transaction reference can be obtained using the JNDI API.
     * @throws Exception if a problem occurs
     */
    public static void checkJNDI() throws Exception{
        Context ctx = new InitialContext();

        UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
        if (utx == null){
            throw new Exception("UserTransaction reference obtained using JNDI API is null.");
        }
    }
}
