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
 * $Id: SLSBSessionCtxMethods.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.sessioncontext;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextBMT;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextEJB3;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextTimer;
import org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextWS;

/**
 * This class is used to test SessionContext methods.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBSessionCtxMethods")
@Remote({ItfSessionContextBMT.class, ItfSessionContextCMT.class, ItfSessionContextEJB3.class,
    ItfSessionContextTimer.class, ItfSessionContextWS.class})
public class SLSBSessionCtxMethods implements ItfSessionContextBMT, ItfSessionContextCMT, ItfSessionContextEJB3,
         ItfSessionContextTimer, ItfSessionContextWS{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT
     */
    public void verifySetRollbackOnly() {
        ctx.setRollbackOnly();
        assertTrue(ctx.getRollbackOnly(), "The transaction should be marked to rollback.");
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextCMT
     */
    public void verifyGetRollbackOnly() {
        this.verifySetRollbackOnly();
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
        assertNotNull(ctx.getTimerService(), "The Timer should not be null.");
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextBMT
     */
    public void verifyGetUserTransaction() {
        //Must throw an IllegalStateException
       ctx.getUserTransaction();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.sessioncontext.ItfSessionContextWS
     */
    public void verifyGetMessageContext() {
        // TODO: Code

    }

}
