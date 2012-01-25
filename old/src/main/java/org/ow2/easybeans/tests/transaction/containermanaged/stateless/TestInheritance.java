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
 * $Id: TestInheritance.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.stateless;

import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.ItfCMTInheritance;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance.SLSBCMTInheritance;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the inheritance in container-managed transaction is
 * following the JSR 220.The item covered in this test are: 13.37.1.
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the bean
 *              SLSBCMTInheritance
 *              must be deployed.
 * @setup gets the reference of the bean
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestInheritance {

    /**
     * Bean used during the tests.
     */
    private ItfCMTInheritance slsbCMTInheritance;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        slsbCMTInheritance = EJBHelper.getBeanRemoteInstance(SLSBCMTInheritance.class, ItfCMTInheritance.class);
    }

    /**
     * Verifies if the transaction attribute define for the class is the same
     * for all class methods. The class transaction attribute is never and the
     * method is called in a transaction context, so the method must throw an
     * exception.
     * @input -
     * @output an EJBException.
     * @throws Exception if an error occurs.
     */
    @Test(expectedExceptions = javax.ejb.EJBException.class)
    public void testClassDefinition() throws Exception {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try {
            utx.begin();
            slsbCMTInheritance.dummyMethod1();
        } finally {
            utx.rollback();
        }
    }

    /**
     * Verifies if the transaction attribute defined in a method overrides the
     * class transaction attribute. The method transaction attribute is
     * mandatory and the method is called without a transaction context, so the
     * method must throw an exception.
     * @input -
     * @output an EJBException.
     * @throws Exception if an error occurs.
     */
    @Test(expectedExceptions = javax.ejb.EJBException.class)
    public void testMethodDefinition() throws Exception {
        slsbCMTInheritance.dummyMethod2();

    }

    /**
     * Verifies if the transaction attribute defined in the class method overrides the
     * transaction attribute defined in the superclass. The method transaction
     * attribute is mandatory and the method is called without a transaction
     * context, so the method must throw an exception.
     * @input -
     * @output an EJBException.
     * @throws Exception if an error occurs.
     */
    @Test(expectedExceptions = javax.ejb.EJBException.class)
    public void testOverrideSuperclassDefinition1() throws Exception {
        slsbCMTInheritance.dummyMethod3();

    }

    /**
     * Verifies if the transaction attribute defined in the class method overrides the
     * transaction attribute defined in the superclass. The method transaction
     * attribute is required, so the method must not throw an exception.
     * @input -
     * @output a correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void testOverrideSuperclassDefinition2() throws Exception {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try {
            utx.begin();
            slsbCMTInheritance.dummyMethod4();
        } finally {
            utx.rollback();
        }
    }
}
