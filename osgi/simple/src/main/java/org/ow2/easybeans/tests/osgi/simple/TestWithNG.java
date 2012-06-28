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
 * $Id: TestWithNG.java 6148 2012-01-25 14:51:49Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.osgi.simple;

import javax.inject.Inject;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.ow2.easybeans.tests.osgi.dummyejb.ITester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWithNG {

    @Inject
    BundleContext bundleContext;


    @Test
    public void testBundleContext() {
        Assert.assertNotNull(bundleContext);
    }

    
    @Test
    public void testSimpleAccess() {
        Bundle[] bundles = bundleContext.getBundles();

        Assert.assertNotNull(bundles);

        // At least 5 bundles
        Assert.assertTrue(bundles.length > 5);
    }
    
    @Test
    public void testEJBExposedAsOSGiService() throws InterruptedException {
        
        int i = 0;
        ServiceReference<ITester> serviceReference = null;
        while (serviceReference == null && i < 100) {
            serviceReference = bundleContext.getServiceReference(ITester.class);
            System.out.println("waiting...");
            Thread.sleep(200L);
            i++;
        }
        
        Assert.assertNotNull(serviceReference);
        
        ITester tester = bundleContext.getService(serviceReference);
        Assert.assertNotNull(tester);

        Assert.assertEquals(tester.calc(1, 2), 3);
        
        
    }
    
}
