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
 * $Id: TestInterceptor03.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptorxml.ItfIntercResourceRef;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptorxml.SLSBIntercResourceRef;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container inject resources for an interceptor qhen the resources are define in the deployment descriptor.
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SLSBIntercResourceRef must be deployed to make the tests,
 *              and, the deployment descriptors must be used too.
 * @setup gets an instance of the SLSBIntercResourceRef.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestInterceptor03 {

    /**
     * Bean used to verify the interceptor definition..
     */
    private ItfIntercResourceRef bean;

    /**
     * Creates the beans used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        bean = EJBHelper.getBeanRemoteInstance(SLSBIntercResourceRef.class, ItfIntercResourceRef.class);
    }

    /**
     * Verifies if a datasource is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesDataSource(){
        bean.checkJDBC();
    }

    /**
     * Verifies if a queue connection factory is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesJMSQueue(){
        bean.checkJMSQueueConFactory();
    }

    /**
     * Verifies if a connection factory is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesJMSConFactory(){
        bean.checkJMSConFactory();
    }

    /**
     * Verifies if a topic connection factory is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesTopicConFactory(){
        bean.checkJMSTopicConFactory();
    }

    /**
     * Verifies if the EJBContext connection factory is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesEJBContext(){
        bean.checkEJBContext();
    }

    /**
     * Verifies if a mail session is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesMailSession(){
        bean.checkMailSession();
    }

    /**
     * Verifies if an URL is injected in an interceptor when it is
     * described in the deployment descriptor.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifiesURL(){
        bean.checkUrl();
    }
}
