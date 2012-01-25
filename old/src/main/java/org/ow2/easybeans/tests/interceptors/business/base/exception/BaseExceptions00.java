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
 * $Id: BaseExceptions00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.interceptors.business.base.exception;

import org.ow2.easybeans.tests.common.ejbs.base.ItfExceptions;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.common.exception.CustomException01;
import org.testng.annotations.Test;

/**
 * Verifies if the interceptors and exceptions are working well.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BaseExceptions00 {

    /**
     * Bean used to implement the test.
     */
    private ItfExceptions clBean;

    /**
     * Verifies if the interceptor can throw a customized exception.
     * @throws Exception if there is an enhancer exception.
     */
    @Test(groups = {"Exceptions"}, expectedExceptions = CustomException00.class)
    public void testException00() throws Exception {
        clBean.throwsOneException00();
    }

    /**
     * Verifies if the interceptor can throw a customized exception.
     * @throws Exception if there is an enhancer exception.
     */
    @Test(groups = {"Exceptions"}, expectedExceptions = CustomException01.class)
    public void testException01() throws Exception {
        clBean.throwsOneException01();
    }

    /**
     * Verifies if the interceptor can throw a customized exception.
     * @throws Exception if there is an enhancer exception.
     */
    @Test(groups = {"Exceptions"}, expectedExceptions = CustomException01.class)
    public void testException02() throws Exception {
        try {
            clBean.throwsOneException00();
        } catch (CustomException00 e) {
            clBean.throwsOneException01();
        }
    }

    /**
     * Verifies if the interceptor can throw a customized exception.
     * @throws Exception if there is an enhancer exception.
     */
    @Test(groups = {"Exceptions"}, expectedExceptions =  CustomException00.class)
    public void testException03() throws Exception {
        clBean.throwsTwoExceptions();
    }

    /**
     * Sets bean used in the tests.
     * @param bean The bean to set.
     */
    public void setBean(final ItfExceptions bean){
        this.clBean = bean;
    }
}
