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
 * $Id: TestSFCallbackAccess00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.lifecycle.stateful.containermanaged;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.POST_CONSTRUCT;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.PRE_DESTROY;
import static org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess.SLEEP;
import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import java.util.ArrayList;
import java.util.List;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.SFSBExternalCallbackAccess00;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBCallbackLoggerAccess;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackEJBAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackEMFactoryAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackEntityManagerAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackJEnvCompAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackResourceManagerAccess00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.misc.AllLifeCallbackSessionContextAccess00;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Verifies if interceptors methods for lifecycle callbacks can perform the operations
 * allowed by the specification.
 * @reference JSR 220-PROPOSED FINAL - Table 2
 * @requirement Application Server must be running; the beans
 *              org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback.*
 *              must be deployed.
 *              (Ant task: install.jar.tests.interceptor.lifecycle)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFCallbackAccess00 {

    /**
     * Bean.
     */
    private ItfCheck02 bean;

    /**
     * Logger bean.
     */
    private ItfCallbackLoggerAccess beanLogger;

    /**
     * Gets bean instances used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        bean = getBeanRemoteInstance(SFSBExternalCallbackAccess00.class, ItfCheck02.class);
        beanLogger = getBeanRemoteInstance(SLSBCallbackLoggerAccess.class, ItfCallbackLoggerAccess.class);
        beanLogger.deleteAll();
    }

    /**
     * Verifies PostConstruct lifecycle callback interceptor method operations.
     * The following operations are tested:
     * <li>EJB</li>
     * <li>SessionContext</li>
     * <li>JNDI</li>
     * <li>ResourceManager</li>
     * <li>EntityManagerFactory</li>
     * <li>EntityManager</li>
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test00() throws Exception{
       bean.check();

       /*TODO: review after PostConstruct implementation.*/

       //Interceptors list
       List<String> arLife = new ArrayList<String>();

       arLife.add(AllLifeCallbackEJBAccess00.class.getName());
       arLife.add(AllLifeCallbackSessionContextAccess00.class.getName());
       arLife.add(AllLifeCallbackJEnvCompAccess00.class.getName());
       arLife.add(AllLifeCallbackResourceManagerAccess00.class.getName());
       arLife.add(AllLifeCallbackEMFactoryAccess00.class.getName());
       arLife.add(AllLifeCallbackEntityManagerAccess00.class.getName());


       beanLogger.verifyCallbackOrder(SFSBExternalCallbackAccess00.class, POST_CONSTRUCT,
               arLife.toArray(new String[0]));
    }

    /**
     * Verifies PreDestroy lifecycle callback interceptor method operations.
     * The following operations are tested:
     * <li>EJB</li>
     * <li>SessionContext</li>
     * <li>JNDI</li>
     * <li>ResourceManager</li>
     * <li>EntityManagerFactory</li>
     * <li>EntityManager</li>
     * @input -
     * @output -
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test01() throws Exception{
       bean.check();
       bean.remove();

       //Sleep used to wait all interceptors execution
       Thread.sleep(SLEEP);

       /*TODO: review after PreDestroy implementation.*/

       //Interceptors list
       List<String> arLife = new ArrayList<String>();

       arLife.add(AllLifeCallbackEJBAccess00.class.getName());
       arLife.add(AllLifeCallbackSessionContextAccess00.class.getName());
       arLife.add(AllLifeCallbackJEnvCompAccess00.class.getName());
       arLife.add(AllLifeCallbackResourceManagerAccess00.class.getName());
       arLife.add(AllLifeCallbackEMFactoryAccess00.class.getName());
       arLife.add(AllLifeCallbackEntityManagerAccess00.class.getName());

       beanLogger.verifyCallbackOrder(SFSBExternalCallbackAccess00.class, PRE_DESTROY,
               arLife.toArray(new String[0]));
    }

    /**
     * Clears callback event log.
     */
    @AfterMethod
    public void tearDown(){
        beanLogger.deleteAll();
    }
}
