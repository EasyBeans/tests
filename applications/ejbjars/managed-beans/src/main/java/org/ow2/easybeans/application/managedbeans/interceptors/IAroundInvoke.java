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

import java.util.List;

/**
 * @author Florent Benoit
 */
public interface IAroundInvoke {

    void dummyCallWithMethodInterceptor(List<String> interceptorsCalled);
    void dummyCallWithExcludedClassInterceptors(List<String> interceptorsCalled);
    void dummyCallWithExcludedDefaultInterceptors(List<String> interceptorsCalled);
    void dummyCallWithOverridedInterceptors(List<String> interceptorsCalled);
    
    Number testAdd(Number a, Number b);
    boolean testBoolean(boolean b);
    void testManyProceed();
    
}
