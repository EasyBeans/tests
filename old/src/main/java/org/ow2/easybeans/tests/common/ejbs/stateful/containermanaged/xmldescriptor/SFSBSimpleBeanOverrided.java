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
 * $Id: SFSBSimpleBeanOverrided.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;

/**
 * Used to verify if the deployment descriptor overrides the annotation. The
 * deployment descriptor defines: <ul>
 * <li>The session bean as stateful. The session type cannot be overrided (page 491-FINAL REALEASE);</li>
 * <li>The bean name: SLSBSimpleBeanOverrideLocal;</li>
 * <li>The transaction
 * type as Bean;</li>
 * <li>The local interface: ItfSimpleBeanOverridedLocal;</li>
 * <li>The remote interface: ItfSimpleBeanOverridedRemote.</li> </ul>
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful(name = "SFSBSimpleBeanByAnnotation")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ItfSimpleBeanOverridedRemote.class)
@Remote(ItfSimpleBeanOverridedLocal.class)
public class SFSBSimpleBeanOverrided implements ItfSimpleBeanOverridedLocal, ItfSimpleBeanOverridedRemote {

    /**
     * Opens a transaction without close. If the bean is stateless, this method
     * call will throw an EJBException.However, for a stateful the method will
     * be executed without problem.
     * @throws NamingException if a problem to get the transaction occurs.
     * @throws NotSupportedException if the bean is already associated with a transaction.
     * @throws SystemException if an unexpected error occurs during the transaction.
     */
    public void openTransaction() throws NamingException, NotSupportedException, SystemException {
        UserTransaction utx = TransactionHelper.getUserTransaction();
        utx.begin();
    }

    /**
     * Verifies if the bean has a bean-managed transaction. If the transaction
     * type is defined as container managed transaction, this method must throw
     * an exception.
     * @throws IllegalStateException if begin or commit is not permit
     * @throws SecurityException if an unexpected error occurs.
     * @throws HeuristicMixedException if an unexpected error occurs.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     */
    public void verifyBMT() throws IllegalStateException, SecurityException, HeuristicMixedException,
            HeuristicRollbackException, RollbackException, SystemException, NotSupportedException, NamingException {
        UserTransaction utx = TransactionHelper.getUserTransaction();
        utx.begin();
        utx.commit();
    }
}
