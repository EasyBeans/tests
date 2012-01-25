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
 * $Id: TransactionHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

/**
 * Used to get the UserTransaction.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public final class TransactionHelper {

    /**
     * Creates a new instance of TransactionHelper.
     */
    private TransactionHelper(){

    }


    /**
     * Method used to get the transaction object (Registry access)
     * It should be used by an external Component (like test case).
     * Does the lookup and returns an UserTransaction.
     * @return the userTransation instance.
     * @throws NamingException if a naming error occurs.
     */
    public static UserTransaction getInternalUserTransaction() throws NamingException {
        //sets the context
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
        //gets the initialcontext
        Context initialContext = new InitialContext();
        //gets the usertransaction
        UserTransaction utx = (UserTransaction) initialContext.lookup("javax.transaction.UserTransaction");
        return utx;
    }

    /**
     * This method use the java: naming
     * Does the lookup and returns an UserTransaction.
     * @return the userTransation instance.
     * @throws NamingException if a naming error occurs.
     */
    public static UserTransaction getUserTransaction() throws NamingException {
        //gets the initialcontext
        Context initialContext = new InitialContext();
        //gets the usertransaction
        UserTransaction utx = (UserTransaction) initialContext.lookup("java:comp/UserTransaction");
        return utx;
    }
}
