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
 * $Id: SimpleEjb2LifecycleBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Init;
import javax.ejb.Local;
import javax.ejb.LocalHome;
import javax.ejb.Remote;
import javax.ejb.RemoteHome;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;

/**
 * Bean that provides a ejb 2.1 view.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateful(name = "SimpleEjb2LifecycleBean")
@Remote(SimpleEjb2.class)
@Local(SimpleEjb2Local.class)
@RemoteHome(SimpleEjb2Home.class)
@LocalHome(SimpleEjb2LocalHome.class)
public class SimpleEjb2LifecycleBean extends SimpleEjb2BeanBase implements SessionBean {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 3594789527580608168L;

    /**
     * Bean used to log the callback loger.
     */
    @EJB(beanName = "SLSBCallbackLoggerAccess")
    private ItfCallbackLoggerAccess clBean;

    /**
     * Creates an instance of the bean with the values in the parameters.
     * @param code the bean code.
     * @param name the bean name.
     * @throws CreateException if an error during the creation occurs
     * @throws RemoteException if a system level error occurs.
     */
    @Override
    @Init
    public void init(final int code, final String name) throws CreateException, RemoteException {
        super.init(code, name);
    }

    /**
     * Creates an instance of the bean with the default code and the name
     * defined in the parameter.
     * @param name the bean name.
     * @throws CreateException if a creation error occurs.
     */
    @Override
    @Init
    void init(final String name) throws CreateException {
        super.init(name);
    }

    /**
     * Called when the bean is activated.
     * @throws EJBException if a system level error occurs.
     * @throws RemoteException defined to provive compatibility among the versions.
     */
    public void ejbActivate() throws EJBException, RemoteException {
        // TODO How to test the ejbActivate?
    }

    /**
     * Called when the bean goes to passivate state.
     *    @throws EJBException if a system level error occurs.
     * @throws RemoteException defined to provive compatibility among the versions.
     */
    public void ejbPassivate() throws EJBException, RemoteException {
        // TODO How to test the ejbPassivate?

    }

    /**
     * Inserts the call in the log.
     * @throws EJBException if a system level error occurs.
     * @throws RemoteException defined to provive compatibility among the versions.
     */
    public void ejbRemove() throws EJBException, RemoteException {
        clBean.insertCallbackLogger(this.getClass().getName(), CallbackType.PRE_DESTROY, this.getClass().getName());
    }

    /**
     * Sets the session context.
     * @param arg0 the session context.
     * @throws EJBException if a system level error occurs.
     * @throws RemoteException defined to provive compatibility among the versions.
     */
    public void setSessionContext(final SessionContext arg0) throws EJBException, RemoteException {

    }
}
