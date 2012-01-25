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
 * $Id: SFSBOverrideTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.ow2.easybeans.tests.common.exception.TransactionException;
import org.ow2.easybeans.tests.common.helper.EJBHelper;

/**
 * Verifies if the container overrides the annotation by the deployment
 * descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfOverrideTester00.class)
public class SFSBOverrideTester00 implements ItfOverrideTester00 {

    /**
     * Bean injected by the ejb name defined in the deployment descriptor.
     */
    @EJB(beanName = "SLSBSimpleBeanOverrideLocal")
    private ItfSimpleBeanOverridedLocal bean1;

    /**
     * Bean injected by the ejb name defined in the deployment descriptor.
     */
    @EJB(beanName = "SLSBSimpleBeanOverrideLocal")
    private ItfSimpleBeanOverridedRemote bean2;

    /**
     * Verifies if the bean was injected correctly. The local interface is used.
     */
    public void testLocalInjection() {
        bean1.toString();
    }

    /**
     * Verifies if the bean was injected correctly. The remote interface is
     * used.
     */
    public void testRemoteInjection() {
        bean2.toString();
    }

    /**
     * Verifies if the bean(with the local interface) can be obtained by the
     * lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void testLookupLocal() throws Exception {
        ItfSimpleBeanOverridedLocal bean = EJBHelper.getBeanLocalInstance(SFSBSimpleBeanOverrided.class,
                ItfSimpleBeanOverridedLocal.class);
        bean.toString();
    }

    /**
     * Verifies if the bean(with the remote interface) can be obtained by the
     * lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void testLookupRemote() throws Exception {
        ItfSimpleBeanOverridedRemote bean = EJBHelper.getBeanRemoteInstance(SFSBSimpleBeanOverrided.class,
                ItfSimpleBeanOverridedRemote.class);
        bean.toString();
    }

    /**
     * Verifies if the transaction type was overriden by the container. The bean
     * has the transaction management defined as Container and the xml
     * descriptor defines the transaction management as bean.
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
    public void testTransactionType() throws IllegalStateException, SecurityException, HeuristicMixedException,
            HeuristicRollbackException, RollbackException, SystemException, NotSupportedException, NamingException {
        bean1.verifyBMT();
        bean2.verifyBMT();
    }

}
