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

package org.ow2.easybeans.application.managedbeans;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.testng.Assert;

/**
 * @author Loic Albertin
 */
@Stateless(mappedName = "LifeCycleTest")
public class LifeCycleSB implements MBeanTest {

    @Resource
    LifeCycleMBean lifeCycleMBean1;

    @Resource
    LifeCycleMBean lifeCycleMBean2;

    @Resource(mappedName = "java:module/LifeCycleMBean")
    LifeCycleMBean lifeCycleMBean3;

    @Resource(lookup = "java:module/LifeCycleMBean")
    LifeCycleMBean lifeCycleMBean4;

    public void checkMBean() {
        checkLifeCycleMBean(lifeCycleMBean1);
        checkLifeCycleMBean(lifeCycleMBean2);
        checkLifeCycleMBean(lifeCycleMBean3);
        checkLifeCycleMBean(lifeCycleMBean4);
        Assert.assertNotSame(lifeCycleMBean1, lifeCycleMBean2, "Lookup on a same ManagedBean name should return different objects");
        Assert.assertNotSame(lifeCycleMBean3, lifeCycleMBean4, "Lookup on a same ManagedBean name should return different objects");
    }

    private void checkLifeCycleMBean(LifeCycleMBean lifeCycleMBean) {
        Assert.assertNotNull(lifeCycleMBean, "LifeCycleMBean was not injected");
        Assert.assertTrue(lifeCycleMBean.isPostConstructCalled(), "PostConstruct callback was not called");
    }
}
