/**
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
 * $Id$
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.beaninheritance;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.testng.Assert;

/**
 * Test Bean
 * @author Florent Benoit
 */
@Singleton(mappedName="BeanInheritanceTestBean")
@Startup
public class TestBean implements ITestBeanInheritance {

    @EJB
    private StatelessBean statelessBean1;

    @EJB
    private StatelessBean statelessBean2;

    @EJB(beanName = "StatelessBean")
    private IBusiness statelessBeanItf1;

    @EJB
    private StatefulBean statefulBean1;

    @EJB
    private StatefulBean statefulBean2;

    @EJB(beanName = "StatefulBean")
    private IBusiness statefulBeanItf1;

    @EJB
    private SingletonBean singletonBean1;

    @EJB
    private SingletonBean singletonBean2;

    @EJB(beanName = "SingletonBean")
    private IBusiness singletonBeanItf1;
    
    
    public void checkStateless() {
        // No interface view
        Assert.assertEquals(statelessBean1, statelessBean2);
        Assert.assertEquals(statelessBean1.getBusinessInterface(), StatelessBean.class);
        Assert.assertEquals(statelessBean1.compute(-1, 1), 0);
        
        // IBusiness interface
        Assert.assertEquals(statelessBeanItf1.getBusinessInterface(), IBusiness.class);
        Assert.assertEquals(statelessBeanItf1.compute(-1, 1), 0);
        
    }

    public void checkStateful() {
        // No interface view
        Assert.assertNotSame(statefulBean1, statefulBean2);
        Assert.assertEquals(statefulBean1.getBusinessInterface(), StatefulBean.class);
        Assert.assertEquals(statefulBean1.compute(-1, 1), 0);
        
        // IBusiness interface
        Assert.assertEquals(statefulBeanItf1.getBusinessInterface(), IBusiness.class);
        Assert.assertEquals(statefulBeanItf1.compute(-1, 1), 0);
        
    }
    
    public void checkSingleton() {
        // No interface view
        Assert.assertEquals(singletonBean1, singletonBean2);
        Assert.assertEquals(singletonBean1.getBusinessInterface(), SingletonBean.class);
        Assert.assertEquals(singletonBean1.compute(-1, 1), 0);
        
        // IBusiness interface
        Assert.assertEquals(singletonBeanItf1.getBusinessInterface(), IBusiness.class);
        Assert.assertEquals(singletonBeanItf1.compute(-1, 1), 0);
        
    }

    
}
