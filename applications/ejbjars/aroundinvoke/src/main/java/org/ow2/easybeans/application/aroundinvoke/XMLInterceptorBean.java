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

import java.util.ArrayList;
import java.util.List;


/**
 * Interceptor Bean.
 * @author Florent Benoit
 */
public class XMLInterceptorBean implements IXMLAroundInvoke {
    
    public void dummyCallOverrided(List<String> interceptorsCalled) {

        List<String> expected = new ArrayList<String>();
        // MyOtherBusinessInterceptor and its super classes (Default Interceptor)
        expected.add(MySuperSuperBusinessInterceptor.class.getName());
        expected.add(MySuperBusinessInterceptor.class.getName());
        expected.add(MyOtherBusinessInterceptor.class.getName());
        
        
       if (!expected.equals(interceptorsCalled)) {
            throw new IllegalStateException("Not the expected list. Got '" + interceptorsCalled + "' and we were expecting '" + expected + "'");
       }      
    }
    
    
    

}
