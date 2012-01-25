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
 * $Id: TestEnvironmentBasic00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.basic;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import javax.naming.OperationNotSupportedException;

import org.ow2.easybeans.tests.common.ejbs.base.ItfModifyEnvironment;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBModifyEnvironment00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the environment is working following the JSR 220.
 * The items covered in this test are: 15.2
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running.
 * @setup gets the reference of the bean
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestEnvironmentBasic00 {

    /**
     * Bean used in tests.
     */
    private ItfModifyEnvironment bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBModifyEnvironment00.class, ItfModifyEnvironment.class);
    }

    /**
     * This method tries to modify the beans environment at runtime and it is
     * denied by the specification.
     * @input -
     * @output javax.naming.OperationNotSupportedException
     * @throws Exception if there is an enhancer exception.
     */
    @Test(groups = {"withWrongSpecification"}, expectedExceptions = OperationNotSupportedException.class)
    public void testWrongSpec00() throws Exception {
        bean.modifyEnvironment();
    }
}
