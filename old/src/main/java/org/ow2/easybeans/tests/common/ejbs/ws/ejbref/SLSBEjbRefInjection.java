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
 * $Id: SLSBEjbRefInjection.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.ws.ejbref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkBeanRef;
import static org.testng.Assert.assertTrue;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;
import org.ow2.easybeans.tests.common.ws.ejbref.gen.ItfChecker;
import org.ow2.easybeans.tests.common.ws.ejbref.gen.WSException;

/**
 * This bean is used to test injection of enterprise beans.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBEjbRefInjection")
// Defines that instance as a WebService
@WebService(name="Checker",
            serviceName="EjbRefService",
            targetNamespace="http://objectweb.org/easybeans/tests/common/ws/ejbref/gen",
            wsdlLocation="META-INF/wsdl/ejbref.wsdl")
@Remote
@EJBs({
    @EJB(name = "ejb/beanDeclaration", beanName="EJBInjectionBean", beanInterface=ItfOneMethod01.class)})
public class SLSBEjbRefInjection implements ItfChecker{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Bean.
     */
    private ItfOneMethod01 beanMethodInjection;

    /**
     * Bean.
     */
    @EJB(name="ejb/beanField", beanName="EJBInjectionBean")
    private ItfOneMethod01 beanFieldInjection;

    /**
     * Setter method.
     * @param b bean
     */
    @EJB(name="ejb/beanMethod", beanName = "EJBInjectionBean")
    public void setBean(final ItfOneMethod01 b){
        beanMethodInjection = b;
    }

    /**
     * Verifies if the annotation &#64;EJB is working properly.
     * @throws WSException if a problem occurs.
     */
    public void check() throws WSException {

        //Field injection
        checkBeanRef(ctx, "ejb/beanField", ItfOneMethod01.class);
        assertTrue(beanFieldInjection.getBool(), "The value must be true.");

        //Method injection
        checkBeanRef(ctx, "ejb/beanMethod", ItfOneMethod01.class);
        assertTrue(beanMethodInjection.getBool(), "The value must be true.");

        //Declaration
        checkBeanRef(ctx, "ejb/beanDeclaration", ItfOneMethod01.class);
    }
}
