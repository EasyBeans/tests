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

package org.ow2.easybeans.application.context;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Test context of the other bean.
 * @author Florent Benoit
 * 
 */
@Stateless(mappedName = "AnnotationContextBean")
public class AnnotationContextBean implements IBusinessContext {

    /**
     * No interface view.
     */
    @EJB
    private XMLContextBean noInterfaceView = null;

    /**
     * Remote view.
     */
    @EJB
    private IContext remoteView = null;

    /**
     * Check invoked business interface is the remote interface
     */
    public void testRemoteBusinessInterface() {
        Class<?> interfaceView = remoteView.getInvokedBusinessInterface();
        if (!IContext.class.equals(interfaceView)) {
            throw new IllegalStateException("Expecting '" + IContext.class + "' and got '" + interfaceView + "'");
        }
    }

    /**
     * Check invoked business interface is the no interface view
     */
    public void testNoInterfaceBusinessInterface() {
        Class<?> interfaceView = noInterfaceView.getInvokedBusinessInterface();
        if (!XMLContextBean.class.equals(interfaceView)) {
            throw new IllegalStateException("Expecting '" + XMLContextBean.class + "' and got '" + interfaceView + "'");
        }
    }
    
    public void testNoInterfaceBusinessObject() {
        XMLContextBean otherInterfaceView = noInterfaceView.getBusinessObject(XMLContextBean.class);
        // try to call a method
        otherInterfaceView.testInjection();
    }

    public void testRemoteBusinessObject() {
        IContext otherInterfaceView = noInterfaceView.getBusinessObject(IContext.class);
        
        otherInterfaceView.testInjection();
        if (!otherInterfaceView.equals(remoteView)) {
            throw new IllegalStateException("Expecting equals");
        }
        
    }

}
