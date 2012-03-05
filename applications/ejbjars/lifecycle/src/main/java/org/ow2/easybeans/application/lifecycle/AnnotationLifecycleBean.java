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
 * $Id: MetadataMerge.java 4697 2009-02-23 17:03:23Z sauthieg $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.lifecycle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.Interceptors;



/**
 * Interceptor Bean.
 * @author Florent Benoit
 */
@Singleton(mappedName="AnnotationLifeCycleBean")
@Remote(ILifeCycle.class)
@Interceptors({MyLifeCycleInterceptor.class, MySuperOverridedInterceptor.class})
@Startup
public class AnnotationLifecycleBean extends CommonLifecycleBean implements ILifeCycle {
    
    private List<String> postConstructInterceptorsCalled = null;
    
    public AnnotationLifecycleBean() {
        this.postConstructInterceptorsCalled = new ArrayList<String>();
    }
    
    public void addPostConstructCall(Class<?> clazz) {
        postConstructInterceptorsCalled.add(clazz.getName());
    }
    
    /**
     * PostConstruct lifecycle method that is in the bean.
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void postConstruct() {
        addPostConstructCall(AnnotationLifecycleBean.class);
    }
    
    
    @Interceptors(MyLifeCycleInterceptor.class)
    public void dummyCallWithMethodInterceptor(List<String> interceptorsCalled) {
        
        List<String> expected = new ArrayList<String>();
        // MyOtherBusinessInterceptor and its super classes (Default Interceptor)
        expected.add(MySuperSuperLifecycleInterceptor.class.getName());
        expected.add(MySuperLifecycleInterceptor.class.getName());
        expected.add(MyLifeCycleInterceptor.class.getName());
        
        // Bean aroundInvoke
        expected.add(AnnotationLifecycleBean.class.getName());
        
        if (!expected.equals(interceptorsCalled)) {
            throw new IllegalStateException("Not the expected list. Got '" + interceptorsCalled + "' and we were expecting '" + expected + "'");
        }
    }


}
