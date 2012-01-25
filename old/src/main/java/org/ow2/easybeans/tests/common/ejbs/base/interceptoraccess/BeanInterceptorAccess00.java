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
 * $Id: BeanInterceptorAccess00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.interceptoraccess;

import javax.annotation.Resource;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEJB;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEMFactory;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEntityManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessJNDI;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessResourceManager;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessSessionContext;
import org.ow2.easybeans.tests.common.interceptors.business.access.EJBAccess00Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.access.EMFactoryAccess00Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.access.EntityManagerAccess00Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.access.JCompEnvAccess00Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.access.ResourceAccess00Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.access.SessionContextAccess00Interceptor;

/**
 * Used to test the interceptors access to the following resources.<br> <li>SessionContext
 * Methods</li> <li>JNDI Access to java:comp/env</li> <li>Resource Manager</li>
 * <li>Enterprise bean</li> <li>EntityManagerFactory</li> <li>EntityManager</li>
 * <li>UserTransaction</li>
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Resource(name="jdbc/jdbc_1", mappedName="jdbc_1", type=javax.sql.DataSource.class)
public class BeanInterceptorAccess00 implements ItfAccessJNDI, ItfAccessEJB, ItfAccessResourceManager,
        ItfAccessEntityManager, ItfAccessEMFactory, ItfAccessSessionContext {

    /**
     * This has an interceptor that accesses the java:comp/env via JNDI.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({JCompEnvAccess00Interceptor.class})
    public Object accessJNDI(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses an EJB.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({EJBAccess00Interceptor.class})
    public Object accessEJB(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses a resource.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({ResourceAccess00Interceptor.class})
    public Object accessResManager(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses an entity manager.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({EntityManagerAccess00Interceptor.class})
    public Object accessEntityManager(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses an entity manager.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({EMFactoryAccess00Interceptor.class})
    public Object accessEntityManagerFactory(final Object obj) throws Exception {
        return null;
    }

    /**
     * This has an interceptor that accesses the sessionContext and tries to lookup a datasource.
     * @param obj it's not used.
     * @return null
     * @throws Exception if a problem occurs.
     */
    @Interceptors({SessionContextAccess00Interceptor.class})
    public Object accessSessionContext(final Object obj) throws Exception {
        return null;
    }

}
