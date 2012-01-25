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
 * $Id: ItfIntercResourceRef.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.interceptorxml;

/**
 * Verifies if the definition of resources gfor an interceptor in the deployment
 * descriptor works. Each method has an interceptor defined in the deployment
 * discriptor that access different types of resources.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfIntercResourceRef {

    /**
     * Checks the jdbc reference injected by xml descriptor.
     */
    void checkJDBC();

    /**
     * Checks the jms reference injected by xml descriptor.
     */
    void checkJMSConFactory();

    /**
     * Checks the jms reference injected by xml descriptor.
     */
    void checkJMSQueueConFactory();

    /**
     * Checks the jms reference injected by xml descriptor.
     */
    void checkJMSTopicConFactory();

    /**
     * Checks the mail session reference injected by xml descriptor.
     */
    void checkMailSession();

    /**
     * Checks the url reference injected by xml descriptor.
     */
    void checkUrl();

    /**
     * Checks the ejb context reference injected by xml descriptor.
     */
    void checkEJBContext();

}
