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
 * $Id: ItfLocalInheritanceTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

import org.testng.annotations.Test;

/**
 * Interface used to local inheritance tests.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public interface ItfLocalInheritanceTester00 {

    /**
     * Gets a new reference of the beans.
     * @throws Exception if a problem occurs.
     */
    void startUp() throws Exception;

    /**
     * Verifies if the bean business method is running correctly. The bean
     * business method is implemented by a extended class that implements an
     * interface. The bean doesn't have any &#64;Local or &#64;Remote.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @SuppressWarnings("unchecked")
    @Test(groups = {"withInheritance"})
    void test00();

    /**
     * Verifies if the bean business method is running correctly. The bean
     * business method is implemented by a extended class that implements an
     * interface. The bean has a &#64;Local as unique interface implemented.
     * @input List with no values inside.
     * @output List with only one value, the value inserted by the method.
     */
    @SuppressWarnings("unchecked")
    @Test(groups = {"withInheritance"})
    void test01();

}
