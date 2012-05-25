/**
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
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
 * $Id: MetadataMerge.java 4697 2009-02-23 17:03:23Z sauthieg $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.statefulsync;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;

/**
 * Session Synchronization for a stateful session bean implementing the given interface
 * @author Florent Benoit
 */
@Stateful
@Local(IStatefulSessionBean.class)
public class SessionSynchronizationBean extends AbsStatefulBean implements SessionSynchronization {

    /**
     * SessionSynchronization interface method.
     * The afterBegin method notifies a session Bean instance that a new
     * transaction has started, and that the subsequent business methods on the
     * instance will be invoked in the context of the transaction. The instance
     * can use this method, for example, to read data from a database and cache
     * the data in the instance fields. This method executes in the proper
     * transaction context.
     */
    public void afterBegin() throws EJBException, RemoteException {
        afterBeginCall();
    }
    
    /**
     * SessionSynchronization interface method.
     * The beforeCompletion method notifies a session Bean instance that a
     * transaction is about to be committed. The instance can use this method,
     * for example, to write any cached data to a database. This method executes
     * in the proper transaction context. Note: The instance may still cause the
     * container to rollback the transaction by invoking the setRollbackOnly()
     * method on the instance context, or by throwing an exception.
     */
    public void beforeCompletion() throws EJBException, RemoteException {
        beforeCompletionCall();
    }

    /**
     * SessionSynchronization interface method.
     * The afterCompletion method notifies a session Bean instance that a
     * transaction commit protocol has completed, and tells the instance whether
     * the transaction has been committed or rolled back. This method executes
     * with no transaction context. This method executes with no transaction
     * context.
     * @param committed True if the transaction has been committed, false if is
     *        has been rolled back.
     */
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        afterCompletionCall(committed);
    }


}
