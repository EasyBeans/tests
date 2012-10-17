/*
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
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
 * $Id:$
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.managedbeans.lifecycle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.easybeans.application.managedbeans.MBeanTest;
import org.testng.Assert;

/**
 * @author Loic Albertin
 */
@Stateless(mappedName = "LifeCycleTest")
public class LifeCycleTest implements MBeanTest {


    @Resource
    LifeCycleMBean lifeCycleMBean1;

    @Resource
    LifeCycleMBean lifeCycleMBean2;

    @Resource(mappedName = "java:module/LifeCycleMBean")
    LifeCycleMBean lifeCycleMBean3;

    @Resource(lookup = "java:module/LifeCycleMBean")
    LifeCycleMBean lifeCycleMBean4;

    @EJB(name = "historyBean")
    HistoryBean historyBean;

    public void checkMBean() {
        checkLifeCycleMBean(lifeCycleMBean1);
        checkLifeCycleMBean(lifeCycleMBean2);
        checkLifeCycleMBean(lifeCycleMBean3);
        checkLifeCycleMBean(lifeCycleMBean4);
        Assert.assertNotSame(lifeCycleMBean1, lifeCycleMBean2, "Lookup on a same ManagedBean name should return different objects");
        Assert.assertNotSame(lifeCycleMBean3, lifeCycleMBean4, "Lookup on a same ManagedBean name should return different objects");

        List<String> expectedPostConstructInterceptors = new ArrayList<String>();
        expectedPostConstructInterceptors.add(MySuperSuperInterceptor.INTERCEPTOR_NAME);
        expectedPostConstructInterceptors.add(MySuperInterceptor.INTERCEPTOR_NAME);
        expectedPostConstructInterceptors.add(MyOtherInterceptor.INTERCEPTOR_NAME);
        expectedPostConstructInterceptors.add(MySuperSuperInterceptor.INTERCEPTOR_NAME);
        expectedPostConstructInterceptors.add(MySuperInterceptor.INTERCEPTOR_NAME);

        historyBean.cleanupInterceptors();
        LCInterceptorMBean lcInterceptorMBean = null;
        try {
            lcInterceptorMBean = (LCInterceptorMBean) new InitialContext().lookup("java:module/LCInterceptorMBean");
        } catch (NamingException e) {
            Assert.fail("Unable to lookup LCInterceptorMBean", e);
        }

        Assert.assertNotNull(lcInterceptorMBean, "LCInterceptorMBean was not injected");
        Assert.assertEquals(historyBean.getInterceptors(), expectedPostConstructInterceptors, "PostConstructInterceptors differs.");

    }

    private void checkLifeCycleMBean(LifeCycleMBean lifeCycleMBean) {
        Assert.assertNotNull(lifeCycleMBean, "LifeCycleMBean was not injected");
        Assert.assertTrue(lifeCycleMBean.isPostConstructCalled(), "PostConstruct callback was not called");
    }
}
