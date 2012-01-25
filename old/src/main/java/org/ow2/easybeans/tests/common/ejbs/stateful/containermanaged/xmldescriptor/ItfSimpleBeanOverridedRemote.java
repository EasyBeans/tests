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
 * $Id: ItfSimpleBeanOverridedRemote.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.ow2.easybeans.tests.common.exception.TransactionException;

/**
 * Verifies if the deployment descriptor overrides the annotations.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfSimpleBeanOverridedRemote {

    /**
     * Opens a transaction without close. If the bean is stateless, this method
     * call will throw an EJBException.However, for a stateful the method will
     * be executed without problem.
     * @throws NamingException if a problem to get the transaction occurs.
     * @throws NotSupportedException if the bean is already associated with a transaction.
     * @throws SystemException if an unexpected error occurs during the transaction.
     */
    void openTransaction() throws NamingException, NotSupportedException, SystemException;

    /**
     * Verifies if the bean has a bean-managed transaction. If the transaction
     * type is defined as container managed transaction, this method must throw
     * an exception.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws TransactionException if a rollback was made.
     */
    void verifyBMT() throws IllegalStateException, SecurityException, HeuristicMixedException,
            HeuristicRollbackException, RollbackException, SystemException, NotSupportedException, NamingException;

}
