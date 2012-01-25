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
 * $Id: TestPersistenceContextRefXML.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.reference.persistencecontext;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.persistencectxref.SLSBPCtxRefXML00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Verifies if the persistence context references declaration is is following the JSR 220 spec.
 * @reference JSR 220 - EJB 3.0 Core - 16.11
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.persistencectxreference.*
 *              must be deployed.
 * @setup gets the reference of the bean
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TestPersistenceContextRefXML {

    /**
     * Bean used in tests.
     */
    private ItfCheck00 bean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBPCtxRefXML00.class, ItfCheck00.class);
    }

    /**
     * Checks if the cotainer can: <li>inject a reference.</li>
     * <li>declare a reference<./li>
     * @input -
     * @output -
     */
    @Test
    public void test00(){
        bean.check();
    }
}
