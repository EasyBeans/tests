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
 * $Id: SLSBBMTTimeoutCallbackAccess00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.timer;

import static org.ow2.easybeans.tests.common.resources.UserTransactionTester.checkInstance;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.timer.BaseTimeoutCallbackAccess;
import org.ow2.easybeans.tests.common.ejbs.base.timer.ItfCreateTimer;



/**
 * Performs timeout operations allowed by the specification.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless(name="SLSBBMTTimeoutCallbackAccess00")
@Remote(ItfCreateTimer.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class SLSBBMTTimeoutCallbackAccess00 extends BaseTimeoutCallbackAccess{

    /**
     * Gets the bean name.
     * @return name
     */
    @Override
    public String getName() {
        return SLSBBMTTimeoutCallbackAccess00.class.getName();
    }

    /**
     * Tests the UserTransaction.
     * @param utx instance
     * @return true if the instance is working properly, otherwise false.
     */
    @Override
    public boolean testUserTransaction(final UserTransaction utx) {
        try{
            checkInstance(utx);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
