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
 * $Id: SFSBSessionBeanItf.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.sbinterface;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.BaseInsertCallbackEvent;

/**
 * This class is used as base to test the sessionbean interface.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateful(name = "SFSBSessionBeanItf")
@Remote(ItfCheck02.class)
@EJB(name = "ejb/bean00", beanName = "EJBInjectionBean", beanInterface = ItfOneMethod01.class)
public class SFSBSessionBeanItf extends BaseInsertCallbackEvent implements SessionBean, ItfCheck02 {

    /**
     * ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * SessionContext.
     */
    private SessionContext ctx;

    /**
     * ejbActivate method.
     * @throws EJBException if a problem occurs.
     * @throws RemoteException if a problem occurs.
     */
    public void ejbActivate() throws EJBException, RemoteException {
        //TODO: test case
        super.log(SFSBSessionBeanItf.class.getName(), CallbackType.POST_ACTIVATE,
                SFSBSessionBeanItf.class.getName());
    }

    /**
     * ejbPassivate method.
     * @throws EJBException if a problem occurs.
     * @throws RemoteException if a problem occurs.
     */
    public void ejbPassivate() throws EJBException, RemoteException {
        //TODO: test case
        super.log(SFSBSessionBeanItf.class.getName(), CallbackType.PRE_PASSIVATE,
                SFSBSessionBeanItf.class.getName());
    }

    /**
     * ejbRemove method.
     * @throws EJBException if a problem occurs.
     * @throws RemoteException if a problem occurs.
     */
    public void ejbRemove() throws EJBException, RemoteException {
        super.log(SFSBSessionBeanItf.class.getName(), CallbackType.PRE_DESTROY,
                SFSBSessionBeanItf.class.getName());
    }

    /**
     * Sets the SessionContext.
     * @param ctx SessionContext
     * @throws EJBException if a problem occurs.
     * @throws RemoteException if a problem occurs.
     */
    public void setSessionContext(final SessionContext ctx) throws EJBException, RemoteException {
        this.ctx = ctx;
    }

    /**
     * Check the SessionContext.
     * @throws Exception if a problem occurs.
     */
    public void check() throws Exception {
        if (ctx != null) {
            ItfOneMethod01 bean = (ItfOneMethod01) ctx.lookup("ejb/bean00");
            bean.getBool();
        } else {
            throw new Exception("SessionContext should be initialized.");
        }
    }

    /**
     * Bean remove.
     */
    @Remove
    public void remove() {
    }

}
