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
 * $Id: TestRequiredNewExceptionRemoteSFSB.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.stateful;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction.SFSBContainerTransacRequiredNewRuntime;
import org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNewException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container-managed transaction in the session bean is
 * following the JSR 220. The container must handle the different types of
 * exception in the transaction context. The items covered in this test are:
 * 13.3
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean .
 *              SFSBContainerTransactionRequiredNewApp,
 *              SFSBContainerTransactionRequiredNewRollback and
 *              SFSBContainerTransactionRequiredNewRuntime must be deployed for
 *              testing stateful session bean.
 * @setup gets the reference of the bean and binds the databases specified in
 *        the EmbeddedTest.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestRequiredNewExceptionRemoteSFSB extends TestRequiredNewException {

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNewException
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"runtime exception tests"})
    @Override
    public void testCallOtherBeanNotSup() throws Exception {
        super.testCallOtherBeanNotSup();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNewException
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"runtime exception tests"})
    @Override
    public void testUsingClientTrans() throws Exception {
        super.testUsingClientTrans();
    }

    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestRequiredNewException
     * @throws Exception if an error during the tests occurs.
     */
    @Test(groups = {"runtime exception tests"})
    @Override
    public void testCallOtherBeanReq() throws Exception {
        super.testCallOtherBeanReq();
    }

    /**
     * Create a bean used during the test beafore each method.
     * @throws Exception if a lookup error occurs.
     */
   @Override
   public void createBeanRuntime() throws Exception {
        super.createBeanRuntime(SFSBContainerTransacRequiredNewRuntime.class);
    }


    /**
     * @see org.ow2.easybeans.tests.transaction.containermanaged.base.TestContainerTransactionException
     * @throws Exception if an error during the initialization occurs.
     */
    @BeforeMethod
    @Override
    public void setup() throws Exception {
        super.setup();
    }

}
