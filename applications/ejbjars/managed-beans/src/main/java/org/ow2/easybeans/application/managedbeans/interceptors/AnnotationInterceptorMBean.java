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

package org.ow2.easybeans.application.managedbeans.interceptors;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;


/**
 * Interceptor Bean.
 * @author Florent Benoit
 */
@ManagedBean("AnnotationInterceptorMBean")
@Interceptors({MyClassBusinessInterceptor.class})
public class AnnotationInterceptorMBean implements IAroundInvoke {
    

    
    @SuppressWarnings("unchecked")
    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {
        if (invocationContext.getMethod().getName().startsWith("dummy")) {
            ((List<String>) invocationContext.getParameters()[0]).add(AnnotationInterceptorMBean.class.getName());
        }
        return invocationContext.proceed();
    }
    
    @Interceptors(MyBusinessInterceptor.class)
    public void dummyCallWithMethodInterceptor(List<String> interceptorsCalled) {
       
        List<String> expected = new ArrayList<String>();
        // MyOtherBusinessInterceptor and its super classes (Default Interceptor)
//        expected.add(MySuperSuperBusinessInterceptor.class.getName());
//        expected.add(MySuperBusinessInterceptor.class.getName());
//        expected.add(MyOtherBusinessInterceptor.class.getName());
        
        // MyClassBusinessInterceptor and its super classes  (Class Interceptor)
        expected.add(MySuperSuperBusinessInterceptor.class.getName());
        expected.add(MySuperBusinessInterceptor.class.getName());
        expected.add(MyClassBusinessInterceptor.class.getName());        

        // MyBusinessInterceptor and its super classes
        expected.add(MySuperSuperBusinessInterceptor.class.getName());
        expected.add(MySuperBusinessInterceptor.class.getName());
        expected.add(MyBusinessInterceptor.class.getName());
        
        // Bean aroundInvoke
        expected.add(AnnotationInterceptorMBean.class.getName());
        
        if (!expected.equals(interceptorsCalled)) {
            throw new IllegalStateException("Not the expected list. Got '" + interceptorsCalled + "' and we were expecting '" + expected + "'");
        }
    }                               
                                                   
    @Interceptors(MyBusinessInterceptor.class)
    @ExcludeClassInterceptors
    public void dummyCallWithExcludedClassInterceptors(List<String> interceptorsCalled) {
        
        List<String> expected = new ArrayList<String>();
        // MyOtherBusinessInterceptor and its super classes (Default Interceptor)
//        expected.add(MySuperSuperBusinessInterceptor.class.getName());
//        expected.add(MySuperBusinessInterceptor.class.getName());
//        expected.add(MyOtherBusinessInterceptor.class.getName());
        
        // MyBusinessInterceptor and its super classes
        expected.add(MySuperSuperBusinessInterceptor.class.getName());
        expected.add(MySuperBusinessInterceptor.class.getName());
        expected.add(MyBusinessInterceptor.class.getName());
        
        // Bean aroundInvoke
        expected.add(AnnotationInterceptorMBean.class.getName());
        
        if (!expected.equals(interceptorsCalled)) {
            throw new IllegalStateException("Not the expected list. Got '" + interceptorsCalled + "' and we were expecting '" + expected + "'");
        }
    }
    
    
    @Interceptors(MyBusinessInterceptor.class)
    @ExcludeDefaultInterceptors
    public void dummyCallWithExcludedDefaultInterceptors(List<String> interceptorsCalled) {

        List<String> expected = new ArrayList<String>();
        // MyClassBusinessInterceptor and its super classes  (Class Interceptor)
        expected.add(MySuperSuperBusinessInterceptor.class.getName());
        expected.add(MySuperBusinessInterceptor.class.getName());
        expected.add(MyClassBusinessInterceptor.class.getName());   
        
        // MyBusinessInterceptor and its super classes
        expected.add(MySuperSuperBusinessInterceptor.class.getName());
        expected.add(MySuperBusinessInterceptor.class.getName());
        expected.add(MyBusinessInterceptor.class.getName());
        
        // Bean aroundInvoke
        expected.add(AnnotationInterceptorMBean.class.getName());
        
        if (!expected.equals(interceptorsCalled)) {
            throw new IllegalStateException("Not the expected list. Got '" + interceptorsCalled + "' and we were expecting '" + expected + "'");
        }        
    }
    
    @Interceptors(MyOverridedInterceptor.class)
    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public void dummyCallWithOverridedInterceptors(List<String> interceptorsCalled) {

        List<String> expected = new ArrayList<String>();
        // MyClassBusinessInterceptor and its super classes  (Class Interceptor)
        expected.add(MyOverridedInterceptor.class.getName());
        // Bean aroundInvoke
        expected.add(AnnotationInterceptorMBean.class.getName());
        
        if (!expected.equals(interceptorsCalled)) {
            throw new IllegalStateException("Not the expected list. Got '" + interceptorsCalled + "' and we were expecting '" + expected + "'");
        }        
    }
    
    @Interceptors(InterceptorSetParameters.class)
    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public Number testAdd(Number a, Number b) {
        return a.intValue() + b.intValue();
    }
    
    @Interceptors(InterceptorSetParameters.class)
    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public boolean testBoolean(boolean b) {
        return b;
    }
    
    @Interceptors(InterceptorProceed.class)
    @ExcludeDefaultInterceptors
    @ExcludeClassInterceptors
    public void testManyProceed() {
        
    }
    
        
    

}
