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
 * $Id: TestListeners04.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity.listeners;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.ItfListenerTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.SLSBListenerTester04;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can manage listeners, when a class that overrides
 * the callback methods from the superclass use different callback type for the
 * same method name.In the specification, in this case the methods in the
 * superclass must be called. for example:
 * <br>class Father{
 * <br>    @PrePersist
 * <br>    public void prePersist(){...}
 * <br>
 * <br>    @PostPersist
 * <br>    public void postPersist(){..}
 * <br>}
 * <br>class Son extends class Father{
 * <br>     @PrePersist
 * <br>     @Override
 * <br>     public void postPersist(){...}
 * <br>
 * <br>    @PostPersist
 * <br>    public void prePersist(){..}
 * <br>}
 * <br>The item tested is the 3.5.4, note 22.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SLSBListenerTester04 must be deployed.
 * @setup gets the reference of SLSBListenerTester4
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestListeners04 {

    /**
     * The bean used during the tests.
     */
    private ItfListenerTester sfsbListenerTester;

    /**
     * Creates the stateless bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        sfsbListenerTester = EJBHelper.getBeanRemoteInstance(SLSBListenerTester04.class, ItfListenerTester.class);
    }

    /**
     * Verifies if the container calls the overriden methods for the callback methods PrePersist and PostPersist.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testPersistWithOverridenListerners() {
        sfsbListenerTester.testPersistCallbackMethods();
    }

    /**
     * Verifies if the container calls the overriden methods for the callback methods PreRemove and PostRemove.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testRemoveWithOverridenListerners() {
        sfsbListenerTester.testRemoveCallbackMethods();
    }

    /**
     * Verifies if the container calls the overriden methods for the callback methods PreUpdate and PostUpdate.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testUpdateWithOverridenListerners() {
        sfsbListenerTester.testUpdateCallbackMethods();
    }

    /**
     * Verifies if the container calls the overriden method for the callback method PostLoad.
     * @input -
     * @output the correct method execution
     *
     */
    @Test
    public void testRefreshWithOverridenListerners() {
        sfsbListenerTester.testLoadCallbackMethods();
    }
}
