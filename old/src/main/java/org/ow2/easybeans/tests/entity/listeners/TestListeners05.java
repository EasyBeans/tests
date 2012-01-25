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
 * $Id: TestListeners05.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity.listeners;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.ItfListenerTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.SLSBListenerTester05;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can manage listeners, when a class that overrides
 * the callback methods from the superclass and does not define as callback
 * methods the methods overriden. In the specification, in this case the methods
 * in the superclass must be called. for example:
 * <br>class Father{
 * <br> @PrePersist
 * <br> public void prePersist(){...} <br>
 * <br>
 * <br>}
 * <br>class Son extends class Father{
 * <br>@Override
 * <br> public void prePersist(){...} <br>
 * <br>
 * <br>}
 * <br>The item tested isthe 3.5.4, note 22.
 */
  public class TestListeners05 {

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
         sfsbListenerTester = EJBHelper.getBeanRemoteInstance(SLSBListenerTester05.class,
                ItfListenerTester.class);
     }

    /**
     * Verifies if the container call the superclass methods for the PrePersist
     * and PostPersist callback methods.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testPersistWithOverridenListeners(){
        sfsbListenerTester.testPersistCallbackMethods();
    }

    /**
     * Verifies if the container call the superclass methods for the PreRemove
     * and PostRemove callback methods.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testRemoveWithOverridenListeners(){
        sfsbListenerTester.testRemoveCallbackMethods();
    }

    /**
     * Verifies if the container call the superclass methods for the PreUpdate
     * and PostUpdate callback methods.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testUpdateWithOverridenListeners(){
        sfsbListenerTester.testUpdateCallbackMethods();
    }

    /**
     * Verifies if the container call the superclass methods for the PostLoad callback method.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testRefreshWithOverridenListeners(){
        sfsbListenerTester.testLoadCallbackMethods();
    }
}
