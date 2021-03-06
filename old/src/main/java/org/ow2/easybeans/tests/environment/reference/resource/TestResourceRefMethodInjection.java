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
 * $Id: TestResourceRefMethodInjection.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.environment.reference.resource;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;
import org.ow2.easybeans.tests.common.ejbs.base.ItfResourceEnvRef00;
import org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.usertxref.SLSBBeanManagedUtxRefMethodInjection00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.resourceref.SLSBResourceRefMethodInjection00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.timersrvref.SLSBTimerSrvRefMethodInjection00;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Verifies if the resource references injection is following the JSR 220.
 * The items covered in this test are: 16.7, 16.12, 16.14, 16.15 and 16.19.
 * @reference JSR 220 - EJB 3.0 Core
 * @requirement Application Server must be running; the bean
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.resourcereference.*
 *              must be deployed.
 * @setup gets the reference of the bean
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TestResourceRefMethodInjection {

    /**
     * Bean used in tests.
     */
    private ItfResourceEnvRef00 bean;

    /**
     * Bean used in timer service tests.
     */
    private ItfCheck00 timerBean;

    /**
     * Bean used in user transaction tests.
     */
    private ItfCheck00 utxBean;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SLSBResourceRefMethodInjection00.class, ItfResourceEnvRef00.class);
    }

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeClass
    public void startUp01() throws Exception {
        timerBean = getBeanRemoteInstance(SLSBTimerSrvRefMethodInjection00.class, ItfCheck00.class);
        utxBean = getBeanRemoteInstance(SLSBBeanManagedUtxRefMethodInjection00.class, ItfCheck00.class);
    }

    /**
     * Checks if the cotainer can:<li>inject with defined name</li>
     * <li>inject with defined name and mapped name</li>
     * <li>inject with defined name and shareable = true</li>
     * <li>inject without defined name, the default name must be defined by the container.</li>
     */
    @Test
    public void testJDBC00(){
        bean.checkJDBC();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name</li>
     * <li>inject without defined name, the default name must be defined by the container.</li>
     */
    @Test
    public void testJMS00(){
        bean.checkJMSConFactory();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name.</li>
     */
    @Test
    public void testJMS01(){
        bean.checkJMSQueueConFactory();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name.</li>
     */
    @Test
    public void testJMSQueue(){
        bean.checkJMSQueue();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name.</li>
     */
    @Test
    public void testJMSTopic(){
        bean.checkJMSTopic();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name.</li>
     */
    @Test
    public void testJMS02(){
        bean.checkJMSTopicConFactory();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name.</li>
     */
    @Test
    public void testMail00(){
        bean.checkMailSession();
    }

    /**
     * Checks if the cotainer can: <li>inject with defined name.</li>
     */
    @Test
    public void testUrl00(){
        bean.checkUrl();
    }

    /**
     * Checks if the cotainer can inject a reference.
     */
    @Test
    public void testEJBContext00(){
        bean.checkEJBContext();
    }

    /**
     * Checks if the cotainer can:<li>inject with defined name</li>
     * <li>inject without defined name, the default name must be defined by the container.</li>
     */
    @Test
    public void testTimerService00(){
        timerBean.check();
    }

    /**
     * Checks if the cotainer can:<li>inject with defined name</li>
     * <li>inject without defined name, the default name must be defined by the container.</li>
     */
    @Test
    public void testUserTransaction00(){
        utxBean.check();
    }
}
