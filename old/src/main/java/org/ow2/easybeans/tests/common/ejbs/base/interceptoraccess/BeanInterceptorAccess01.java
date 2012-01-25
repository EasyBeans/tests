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
 * $Id: BeanInterceptorAccess01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.interceptoraccess;

import static org.ow2.easybeans.tests.common.resources.EntityManagerTester.checkInstance;
import static org.testng.Assert.assertTrue;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.db.TableManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEJB;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEMFactory;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEntityManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessJNDI;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessResourceManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;
import org.ow2.easybeans.tests.common.helper.JNDIHelper;
import org.ow2.easybeans.tests.common.resources.EMFactoryTester;
import org.ow2.easybeans.tests.common.resources.EntityManagerTester;


/**
 * Used to test the interceptors access to the following resources.<br> <li>SessionContext
 * Methods</li> <li>JNDI Access to java:comp/env</li> <li>Resource Manager</li>
 * <li>Enterprise bean</li> <li>EntityManagerFactory</li> <li>EntityManage</li>
 * <li>TimerService</li> <li>UserTransaction</li>
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BeanInterceptorAccess01 implements ItfAccessJNDI, ItfAccessEJB, ItfAccessResourceManager,
        ItfAccessEntityManager, ItfAccessEMFactory, ItfAccessSessionContext{

    /**
     * Bean.
     */
    @EJB(name="ejb/bean", beanName="EJBInjectionBean")
    private ItfOneMethod01 bean;

    /**
     * Injected DataSource.
     */
    @Resource(name = "jdbc/jdbc_1", mappedName="jdbc_1")
    private DataSource ds;

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName="testEntity00")
    private EntityManager eManager = null;

    /**
     * Entity Factory.
     */
    @PersistenceUnit(unitName="testEntity00")
    private EntityManagerFactory entityFactory = null;

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * This has an interceptor that accesses the java:comp/env via JNDI.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessJNDI(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses an EJB.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessEJB(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses a resource.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessResManager(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses an entity manager.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessEntityManager(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses an entity manager.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessEntityManagerFactory(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses the sessionContext and try to lookup a datasource.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    public Object accessSessionContext(final Object obj) throws Exception {
        return null;
    }

    /**
     * Business Method Inteceptor that accesses the resource specified by the class comments.
     * @param ic contains attributes of invocation..
     * @return method's invocation result
     * @throws Exception if invocation fails
     */
    @AroundInvoke
    public Object intercept(final InvocationContext ic) throws Exception{
        if (ic.getMethod().toString().contains("accessJNDI")) {

            //JNDI Access
            DataSource dsViaJNDI = (DataSource) JNDIHelper.getJavaCompEnvResource("jdbc/jdbc_1");
            TableManager cTest = new TableManager(dsViaJNDI);
            cTest.test("tmpTable" + cTest.hashCode());

        } else if (ic.getMethod().toString().contains("accessEJB")) {

            //EJB Access
            assertTrue(bean.getBool());

        } else if (ic.getMethod().toString().contains("accessResManager")) {

            //ResourceManager Access
            TableManager cTest = new TableManager(ds);
            cTest.test("tmpTable" + cTest.hashCode());

        } else if (ic.getMethod().toString().contains("accessEntityManager")) {

            //EntityManager Access
            UserTransaction utx;
            try{
                utx = ctx.getUserTransaction();
                utx.begin();
            }catch(Exception e){
                utx = null;
            }

            checkInstance(eManager, EntityManagerTester.NAME);

            if (utx != null){
                utx.commit();
            }


        } else if (ic.getMethod().toString().contains("accessEntityManagerFactory")) {

            //EntityManagerFactory Access
            EntityManager entityManager = entityFactory.createEntityManager();
            entityManager.getTransaction().begin();
            checkInstance(entityManager, EMFactoryTester.NAME);
            entityManager.getTransaction().commit();

        } else if (ic.getMethod().toString().contains("accessSessionContext")) {

            //SessionContext Access
            if (ctx == null){
                throw new Exception("SessionContext is null.");
            }

        } else{
            throw new Exception("Invalid method to intercept.");
        }
        return ic.proceed();
    }
}
