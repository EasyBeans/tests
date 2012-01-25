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
 * $Id: TestEJBRef.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.webservice.environment;

import java.net.URL;

import org.ow2.easybeans.tests.common.resources.ItfWSUtil;
import org.ow2.easybeans.tests.common.resources.WSUtilCeltix;
import org.ow2.easybeans.tests.common.ws.ejbref.gen.ItfChecker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the EJB references injection is following the JSR 220.
 * @reference JSR 220-PROPOSED FINAL - 16.5
 * @requirement Tomcat running in the port 8080 with embedded EasyBeans;
 *              (Ant task: install.jar.tests.ejb.reference.webservice)
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestEJBRef {

    /**
     * WS instance.
     */
    private ItfChecker instance;

    /**
     * WS Util.
     */
    private ItfWSUtil ws = new WSUtilCeltix();

    /**
     * Gets the instance used in the test.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        instance = ws.getPort(new URL("http://localhost:8080/ow_easybeans/services/EjbRefService/"),
                "http://objectweb.org/easybeans/tests/common/ws/ejbref/gen",
                "SOAPService", ItfChecker.class, "SoapPort");
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. A webservice is used to access the bean.
     * @throws Exception if a problem occurs.
     */
    @Test
    public void test00() throws Exception{
        instance.check();
    }
}
