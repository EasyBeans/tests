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
 * $Id: SLSBTestLocalAnnotation00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.local;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanLocalInstance;
import static org.testng.Assert.assertEquals;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessEJB;
import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessJNDI;
import org.ow2.easybeans.tests.common.interfaces.ItfTestLocalAnnotation00;


/**
 * Tester Bean to the "Local" annotation.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless
@Remote(ItfTestLocalAnnotation00.class)
public class SLSBTestLocalAnnotation00 implements ItfTestLocalAnnotation00{

    /**
     * @see org.ow2.easybeans.tests.annotations.TestLocalAnnotation00
     * @throws Exception if a problem occurs.
     */
    public void testRun00() throws Exception {
        ItfAccessJNDI bean = getBeanLocalInstance(SLSBLocalAnnotationTest00.class, ItfAccessJNDI.class);
        String strResult = (String) bean.accessJNDI(null);
        assertEquals(strResult, SLSBLocalAnnotationTest00.JNDI, "The bean method is not running correctly.");
    }

    /**
     * @see org.ow2.easybeans.tests.annotations.TestLocalAnnotation00
     * @throws Exception if a problem occurs.
     */
    public void testRun01() throws Exception {
        ItfAccessEJB bean = getBeanLocalInstance(SLSBLocalAnnotationTest00.class, ItfAccessEJB.class);
        String strResult = (String) bean.accessEJB(null);
        assertEquals(strResult, SLSBLocalAnnotationTest00.EJB, "The bean method is not running correctly.");
    }

    /**
     * @see org.ow2.easybeans.tests.annotations.TestLocalAnnotation00
     * @throws Exception if a problem occurs.
     */
    public void testRun02() throws Exception {
        ItfAccessEJB bean = getBeanLocalInstance(SLSBLocalAnnotationTest01.class, ItfAccessEJB.class);
        String strResult = (String) bean.accessEJB(null);
        assertEquals(strResult, SLSBLocalAnnotationTest01.EJB, "The bean method is not running correctly.");
    }

}
