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

package org.ow2.easybeans.application.aroundinvoke;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * This interceptor will try to set parameters on a given method and check if all is authorized.
 * @author Florent Benoit
 */
public class InterceptorSetParameters {

    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {
        Method m = invocationContext.getMethod();
        if ("testAdd".equals(m.getName())) {
            checkSetTestAdd(invocationContext);
        } else if ("testBoolean".equals(m.getName())) {
            checkSetTestBoolean(invocationContext);
        }
        
        return invocationContext.proceed();
        
    }
    
    protected void checkSetTestBoolean(InvocationContext invocationContext) throws Exception {
        
        // fails
        try {
            invocationContext.setParameters(null);
            throw new Exception("Null shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }


        // fails
        try {
            invocationContext.setParameters(new String[] {"test"});
            throw new Exception("Invalid type shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }

        // fails
        try {
            invocationContext.setParameters(new String[] {"1", "2", "3"});
            throw new Exception("Invalid type/size shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }

        // OK
        invocationContext.setParameters(new Boolean[] {true});
    }
    
    protected void checkSetTestAdd(InvocationContext invocationContext) throws Exception {
        
        // fails
        try {
            invocationContext.setParameters(null);
            throw new Exception("Null shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }


        // fails
        try {
            invocationContext.setParameters(new Boolean[] {Boolean.TRUE, Boolean.FALSE});
            throw new Exception("Invalid type shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }

        // fails
        try {
            invocationContext.setParameters(new Integer[] {1, 2, 3});
            throw new Exception("Invalid size shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }

        // fails
        try {
            invocationContext.setParameters(new String[] {"1", "2", "3"});
            throw new Exception("Invalid type/size shouldnt be accepted");
        } catch (IllegalArgumentException e) {
            // expecting this exception
        }

        // OK
        invocationContext.setParameters(new Integer[] {2, 1});
    }
}
