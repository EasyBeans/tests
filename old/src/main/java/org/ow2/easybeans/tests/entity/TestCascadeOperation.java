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
 * $Id: TestCascadeOperation.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.entity;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.cascadeoperation.ItfCascadeTester;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.cascadeoperation.SFSBCascadeTester;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container manages the different cascade types in the relationships mapping.
 * The items 3.2.1 e 3.2.2 (Persistence doc)
 * @reference JSR 220-FINAL RELEASE
 * @requirement Application Server must be running; the bean
 *              SFSBCascadeTester must be deployed.
 * @setup gets the reference of SFSBCascadeTester
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestCascadeOperation {

    /**
     * Bean used during the tests.
     */
    private ItfCascadeTester sfsbCascadeTester;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        sfsbCascadeTester = EJBHelper.getBeanRemoteInstance(SFSBCascadeTester.class,
                ItfCascadeTester.class);
        sfsbCascadeTester.startup();
    }

    /**
     * Verifies if the container manages the cascade refresh properly.
     * @input -
     * @output the method execution without error.
     */
    @Test
    public void verifyCascadeTypeRefresh(){
        sfsbCascadeTester.verifyCascadeTypeRefresh();
    }


    /**
     * Verifies if the container manages the cascade remove properly.
     * @input -
     * @output the method execution without error.
     */
    @Test
    public void verifyCascadeTypeRemove(){
        sfsbCascadeTester.verifyCascadeTypeRemove();
    }

    /**
     * Verifies if the container manages the cascade merge properly.
     * @input -
     * @output the method execution without error.
     */
    @Test
    public void verifyCascadeTypeMerge(){
        sfsbCascadeTester.verifyCascadeTypeMerge();
    }

    /**
     * Verifies if the container manages the cascade persist properly.
     * @input -
     * @output the method execution without error.
     */
    @Test
    public void verifyCascadeTypePersist(){
        sfsbCascadeTester.verifyCascadeTypePersist();
    }
}
