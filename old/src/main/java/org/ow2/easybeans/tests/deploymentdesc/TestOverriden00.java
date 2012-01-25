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
 * $Id: TestOverriden00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.ItfOverrideTester00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.SFSBOverrideTester00;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the deployment descriptor overrides the bean annotation.
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SFSBOverrideTester must be deployed to make the tests,
 *              and, the SFSBSimpleBeanOverrided with the deployment descriptor
 *              must be deployed too.
 * @setup gets an instance of the SFSBOverrideTester.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestOverriden00 {

    /**
     * Bean used to verify the local access.
     */
    private ItfOverrideTester00 tester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        tester = EJBHelper.getBeanRemoteInstance(SFSBOverrideTester00.class, ItfOverrideTester00.class);
    }

    /**
     * Verifies if the injection works when the local interface and the bean
     * name are used. The bean has a different name defined in the annotation
     * and in the deployment descriptor,and the local interface defined in the
     * deployment descriptor is different too. The test uses the values defined
     * in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testLocalInjection() {
        tester.testLocalInjection();
    }

    /**
     * Verifies if the injection works when the remotel interface and the bean
     * name are used. The bean has a different name defined in the annotation
     * and in the deployment descriptor,and the remote interface defined in the
     * deployment descriptor is different too. The test uses the values defined
     * in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void testRemoteInjection() {
        tester.testRemoteInjection();
    }

    /**
     * Verififes if the lookup works when a local interface is used. The
     * interface defined as local in the deployment descriptor is different of
     * the interface defined in the bean class. The test uses the values defined
     * in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testLookupLocal() throws Exception {
        tester.testLookupLocal();
    }

    /**
     * Verififes if the lookup works when a local interface is used. The
     * interface defined as remote in the deployment descriptor is different of
     * the interface defined in the bean class. The test uses the values defined
     * in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     * @throws Exception if a lookup error occurs.
     */
    @Test
    public void testLookupRemote() throws Exception {
        tester.testLookupRemote();
    }



    /**
     * Verifies if the container overrides the transaction type when a
     * deployment descriptor is used. The transaction type defined in the bean
     * is container managed, but the transaction type defined in the deployment
     * descriptor is bean managed.The bean gets an instance of an user
     * transaction. If the transaction is bean managed, the operation must not
     * throw an exception.
     * @input -
     * @output the correct method execution.
     * @throws NamingException if a lookup error occurs.
     * @throws SystemException if an unexpected error occurs.
     * @throws NotSupportedException if the resquest cannot be made.
     * @throws HeuristicRollbackException if a heuristic decision was made and
     *         some relevant update was rolled back.
     * @throws RollbackException if the transaction was rolled back instead of
     *         committed.
     * @throws HeuristicMixedException if a heuristic decision was made and some
     *         relevant update was commited and others rolled back.
     * @throws IllegalStateException if there is an illegal state
     * @throws SecurityException if there is a security exception
     */
    @Test
    public void testTransactionType() throws IllegalStateException, SecurityException, HeuristicMixedException,
            HeuristicRollbackException, RollbackException, SystemException, NotSupportedException, NamingException {
        tester.testTransactionType();
    }

}
