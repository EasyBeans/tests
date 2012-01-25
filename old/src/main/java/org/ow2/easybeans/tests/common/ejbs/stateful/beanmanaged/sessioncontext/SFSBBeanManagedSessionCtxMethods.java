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
 * $Id: SFSBBeanManagedSessionCtxMethods.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.sessioncontext;

import static org.testng.Assert.assertEquals;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextBMT;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer;

/**
 * This class is used to test SessionContext methods.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateful(name = "SFSBBeanManagedSessionCtxMethods")
@Remote({ItfSessionContextBMT.class, ItfSessionContextCMT.class, ItfSessionContextEJB3.class,
        ItfSessionContextTimer.class})
@TransactionManagement(value = TransactionManagementType.BEAN)
public class SFSBBeanManagedSessionCtxMethods implements ItfSessionContextBMT, ItfSessionContextCMT,
        ItfSessionContextEJB3, ItfSessionContextTimer {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT
     */
    public void verifySetRollbackOnly() {
        // Must throw an IllegalStateException
        ctx.setRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT
     */
    public void verifyGetRollbackOnly() {
        // Must throw an IllegalStateException
        ctx.getRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3
     */
    public void verifyGetBusinessObject() {
        ItfSessionContextCMT bean = ctx.getBusinessObject(ItfSessionContextCMT.class);
        bean.verifyGetRollbackOnly();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3
     */
    public void verifyGetInvokedBusinessInterface() {
        Class cl = ctx.getInvokedBusinessInterface();
        assertEquals(cl, ItfSessionContextEJB3.class, "This method should return the "
                + "interface ItfSessionContextEJB3, but returned " + cl.getName());
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer
     */
    public void verifyGetTimerService() {
        // Must throw an IllegalStateException
        ctx.getTimerService();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextBMT
     */
    public void verifyGetUserTransaction() {
        ctx.getUserTransaction();
    }

}
