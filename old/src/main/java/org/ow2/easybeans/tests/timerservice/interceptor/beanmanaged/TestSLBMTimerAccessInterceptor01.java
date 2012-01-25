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
 * $Id: TestSLBMTimerAccessInterceptor01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.timerservice.interceptor.beanmanaged;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import org.ow2.easybeans.tests.common.ejbs.base.ItfAccessTimerService;
import org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.interceptoraccess.SLSBBMTAccessTimerInterceptor01;
import org.ow2.easybeans.tests.interceptors.business.base.access.BaseTimerAccessInterceptor00;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Verifies if interceptors in the bean class can use the timer service specified by the JSR-220.
 * @reference JSR 220-PROPOSED FINAL - Chapter 4 - Table 2
 * @requirement Application Server must be running; the beans
 *              org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSB*AccessInterceptor*
 *              must be deployed.
 * @setup gets the reference of SLSBBMTAccessTimerInterceptor01
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSLBMTimerAccessInterceptor01 extends BaseTimerAccessInterceptor00{

    /**
     * Gets bean instance used in the tests.
     * @throws Exception if there is a problem with the bean initialization.
     */
    @BeforeMethod
    public void startUp() throws Exception {
        ItfAccessTimerService bean = getBeanRemoteInstance(SLSBBMTAccessTimerInterceptor01.class,
                ItfAccessTimerService.class);
        super.setTimerBean(bean);
    }


    /**
     * Verifies if the interceptor can access the timer service. This
     * operation is allowed, so there isn't an expected
     * exception.
     * @input -
     * @output no exceptions.
     * @throws Exception if a problem occurs.
     */
    @Override
    @Test(groups = {"TimerService access"})
    public void testTimerService00() throws Exception {
        super.testTimerService00();
    }

}
