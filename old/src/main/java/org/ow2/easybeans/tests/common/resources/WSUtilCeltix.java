/**
 * EasyBeans
 * Copyright (C) 2006 Bull S.A.S.
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
 * $Id: WSUtilCeltix.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import java.net.URL;

import javax.xml.namespace.QName;

/**
 * Used to access web services created by Celtix.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class WSUtilCeltix implements ItfWSUtil {

    /**
     * Default constructor.
     */
    public WSUtilCeltix() {
    }

    /**
     * Gets a reference to an endpoint.
     * @param <E> Type
     * @param wsdlLocation Location of the WSDL file.
     * @param wsNamespace WebService namespace.
     * @param serviceName Service name.
     * @param serviceEndPointInterface Interface used to access the WS.
     * @param soapPortName Soap port name.
     * @return port
     * @throws Exception if a problem occurs.
     */
    public synchronized <E> E getPort(final URL wsdlLocation, final String wsNamespace, final String serviceName,
           final Class<E> serviceEndPointInterface, final String soapPortName) throws Exception {
        QName soapPortQName = new QName(wsNamespace, soapPortName);
        QName serviceQName = new QName(wsNamespace, serviceName);

        GenericService srv = new GenericService(wsdlLocation, soapPortQName);

        return srv.getPort(serviceQName, serviceEndPointInterface);
    }
}
