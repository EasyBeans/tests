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
 * $Id: SFSBEjb2ClientLifecycle.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import static org.testng.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackLogger;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;

/**
 * Bean used to verify the callback methods chen the interface sessionBean is
 * used.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
public class SFSBEjb2ClientLifecycle implements ItfEjb2ClientLifecycle {

    /**
     * The bean home interface.
     */
    @EJB(beanName = "SimpleEjb2LifecycleBean")
    private SimpleEjb2Home beanHome;

    /**
     * Bean used to log the callback methods.
     */
    @EJB(beanName = "SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess clBean;

    /**
     * Verifies if the method ejbPassivate is called.
     */
    public void verifyPassivate() {
        // TODO - How to test the EJB passivate?
        throw new RuntimeException("Test not implemented yet.");
    }

    /**
     * Verifies if the method ejbActivate is called.
     */
    public void verifyActivate() {
        // TODO - How to test the EJB passivate?
        throw new RuntimeException("Test not implemented yet.");
    }

    /**
     * Verifies if the method ejbRemove is called.
     * @throws RemoveException if an error during the remove occurs.
     * @throws RemoteException if a system level error occurs.
     * @throws CreateException if an error during the creation occurs.
     */
    public void verifyRemove() throws RemoteException, CreateException, RemoveException {
        // cleans the log
        List lstResultDelete = clBean.findCallbackEventByCallbackMethod(SimpleEjb2LifecycleBean.class.getName(),
                CallbackType.PRE_DESTROY, SimpleEjb2LifecycleBean.class.getName());
        for (Object obj : lstResultDelete) {
            CallbackLogger log = (CallbackLogger) obj;
            clBean.deleteCallbackEvent(log.getId());
        }
        // creates the bean
        SimpleEjb2 bean = beanHome.create();

        // deletes the bean
        beanHome.remove(bean);

        // verifies the callback
        List lstResult = clBean.findCallbackEventByCallbackMethod(SimpleEjb2LifecycleBean.class.getName(),
                CallbackType.PRE_DESTROY, SimpleEjb2LifecycleBean.class.getName());
        assertEquals(1, lstResult.size(), "The lifecycle callback was not called or it was called more then once.");
    }

}
