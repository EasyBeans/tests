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
 * $Id: TestListeners02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity.listeners;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.ItfListenerTester;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners.SLSBListenerTester03;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can manage listeners, when a class that has a
 * listener defined in the superclass and has the annotation ExcludeSuperclassListeners. The item tested is the 3.5.4.
 * @reference JSR 220-PROPOSED FINAL
 * @requirement Application Server must be running; the bean
 *              SLSBListenerTester02 must be deployed.
 * @setup gets the reference of SLSBListenerTester02
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestListeners02 {

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
     * This test verifies if does not call the PrePersist and the PostPersist
     * callback methods when the annotation ExcludeSuperclassListeners is used.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testPersistWithoutListeners(){
        sfsbListenerTester.testPersistCallbackMethods();
    }

    /**
     * This test verifies if does not call the PreRemove and the PostRemove
     * callback methods when the annotation ExcludeSuperclassListeners is used.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testRemoveWithoutListeners(){
        sfsbListenerTester.testRemoveCallbackMethods();
    }

    /**
     * This test verifies if does not call the PreUpdate and the PostUpdate
     * callback methods when the annotation ExcludeSuperclassListeners is used.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testUpdateWithoutListeners(){
        sfsbListenerTester.testUpdateCallbackMethods();
    }

    /**
     * This test verifies if does not call the PostLoad callback method when the
     * annotation ExcludeSuperclassListeners is used.
     * @input -
     * @output the correct method execution
     */
    @Test
    public void testRefreshWithoutListeners(){
        sfsbListenerTester.testLoadCallbackMethods();
    }
}
