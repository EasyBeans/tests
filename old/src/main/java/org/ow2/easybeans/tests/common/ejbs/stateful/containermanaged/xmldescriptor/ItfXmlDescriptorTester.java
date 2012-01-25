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
 * $Id: ItfXmlDescriptorTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

/**
 * Bean used to test if the container verifies the deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfXmlDescriptorTester {

    /**
     * Verifies if the bean that is defined in the deployment descriptor has
     * actually the same type. This method must throw a EJBException if the bean
     * is stateless.And, The method must run correctly if the bean is stateful
     * @throws NamingException if a problem to get the transaction occurs.
     * @throws NotSupportedException if the bean is already associated with a
     *         transaction.
     * @throws SystemException if an unexpected error occurs during the
     *         transaction.
     */
    void verifyBeanTypeElement() throws NamingException, NotSupportedException, SystemException;

    /**
     * Verifies if the bean has actually a BMT when it has the transaction type
     * defined as BMT in the deployment descriptor.
     * @throws Exception if an error occurs.
     */
    void verifyBMT() throws Exception;

    /**
     * Verifies if it id possible to get a bean by the mappedName defined in the
     * deployment descriptor.
     * @throws Exception if an error during the lookup occurs.
     */
    void verifyBeanMappedName() throws Exception;

}
