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
 * $Id: TestListeners03.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity.listeners;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.ItfListenerTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.SLSBListenerTester03;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can manage listeners, when a class that overrides
 * the callback methods from the superclass use the same callback type for the
 * same method name.In the specification, in this case the methods in the
 * superclass must be overriden and they are not called. For example:
 * <br>class Father{
 * <br>    @PrePersist
 * <br>    public void prePersist(){...}
 * <br>}
 * <br>class Son extends class Father{
 * <br>     @PrePersist
 * <br>     public void prePersist(){...}
 * <br>}
 * <br>The item tested is the 3.5.4.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SLSBListenerTester03 must be deployed.
 * @setup gets the reference of SLSBListenerTester03
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestListeners03 {

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
         sfsbListenerTester = EJBHelper.getBeanRemoteInstance(SLSBListenerTester03.class,
                ItfListenerTester.class);
     }

    /**
     * Verifies if the container calls only the class method and deos not call
     * the superclass method. The callback methods verified are the PrePersist
     * and the PostPersist.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testPersistWithSupperclassListenerOverriden(){
        sfsbListenerTester.testPersistCallbackMethods();
    }

    /**
     * Verifies if the container calls only the class method and deos not call
     * the superclass method. The callback methods verified are the PreRemove
     * and the PostRemove.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testRemoveWithSupperclassListenerOverriden(){
        sfsbListenerTester.testRemoveCallbackMethods();
    }

    /**
     * Verifies if the container calls only the class method and deos not call
     * the superclass method. The callback methods verified are the PreUpdate
     * and the PostUpdate.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testUpdateWithSupperclassListenerOverriden(){
        sfsbListenerTester.testUpdateCallbackMethods();
    }

    /**
     * Verifies if the container calls only the class method and deos not call
     * the superclass method. The callback method verified is the PostLoad.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testRefreshWithSupperclassListenerOverriden(){
        sfsbListenerTester.testLoadCallbackMethods();
    }

}
