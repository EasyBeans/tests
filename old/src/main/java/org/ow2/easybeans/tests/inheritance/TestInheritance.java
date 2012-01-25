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
package org.ow2.easybeans.tests.inheritance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimplePrintMessage;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.inheritance.SFSBInheritanceTest00;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Verifies if the bean inheritance is following the JSR 220 spec.And verifies
 * also if the container accepts two methods with the same signature.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean that implements the
 *              interface
 *              org.ow2.easybeans.tests.common.ejbs.stateful.ItfSimplePrintMessage
 *              must be deployed.
 * @setup gets the reference of bean that implements ItfSimplePrintMessage.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestInheritance {

    /**
     * The bean used during the tests.
     */
    private ItfSimplePrintMessage sfsbMessage;

    /**
     * The message used to compare with the bean value.
     */
    private static final String MESSAGE_1 = "Test says Hello World";

    /**
     * The message used to compare with the bean value.
     */
    private static final String MESSAGE_2 = "The bean works well";

    /**
     * Creates a bean to be used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void setup() throws Exception {
        // gets a bean
        ItfSimplePrintMessage sfsbPrint = EJBHelper.getBeanRemoteInstance(SFSBInheritanceTest00.class,
                ItfSimplePrintMessage.class);
        sfsbMessage = sfsbPrint;
    }

    /**
     * Verifies if the bean returned call correctly the startup method.
     * @input the message to be stored.
     * @output -
     */
    @Test(groups = {"startup"})
    public void callStartupWithOneParameterTest() {
        sfsbMessage.startup(MESSAGE_1);
    }

    /**
     * Verifies if the bean returned call correctly the startup method.
     * @input the message to be stored.
     * @output -
     */
    @Test(groups = {"startup"})
    public void callStartupWithTwoParametersTest() {
        sfsbMessage.startup(MESSAGE_1, MESSAGE_2);
    }

    /**
     * Verifies if the startup was made correctly.
     * @input -
     * @output the same message value inserted in the
     *         callStartupWithOneParameterTest.
     */
    @Test(groups = {"callMesssage"}, dependsOnMethods = "callStartupWithOneParameterTest")
    public void callMessageWithOneParameterTest() {
        assert sfsbMessage.getMessage().equals(MESSAGE_1);
    }

    /**
     * Verifies if the startup was made correctly.
     * @input -
     * @output the same message value inserted in the
     *         callStartupWithTwoParametersTest.
     */
    @Test(groups = {"callMesssage"}, dependsOnMethods = "callStartupWithTwoParametersTest")
    public void callMessageWithTwoParametersTest() {
        assert sfsbMessage.getMessage().equals(MESSAGE_1 + MESSAGE_2);
    }

}
