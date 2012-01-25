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
 * $Id: ItfSessionObjectIdentity.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

/**
 * Verifies the identity test for stateful and stateless beans.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfSessionObjectIdentity {

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were injected by annotation.
     */
    void testSLSameInstanceIdentityInjected();

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with other bean that has the same name. Example
     * bean1.equals(bean2).The beans in this test were injected by annotation.
     */
    void testSLDifferentInstanceIdentityInjected();

    /**
     * Verifies if the method equals of a stateful bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were injected by annotation.
     */
    void testSFSameInstanceIdentityInjected();

    /**
     * Verifies if the method equals of a stateful bean returns false when the
     * bean is compared with other bean that has the same name. Example bean1.equals(bean2). The beans in
     * this test were injected by annotation.
     */
    void testSFDifferentInstanceIdentityInjected();

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    void testSLSameInstanceIdentityLookup() throws Exception;

    /**
     * Verifies if the method equals of a stateless bean returns true when the
     * bean is compared with other bean that has the same name. Example bean1.equals(bean2). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    void testSLDifferentInstanceIdentityLookup() throws Exception;

    /**
     * Verifies if the method equals of a stateful bean returns true when the
     * bean is compared with itself. Example bean1.equals(bean1). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    void testSFSameInstanceIdentityLookup() throws Exception;

    /**
     * Verifies if the method equals of a stateful bean returns false when the
     * bean is compared with other bean that has the same name. Example bean1.equals(bean2). The beans in
     * this test were goten by a lookup.
     * @throws Exception if a lookup error occurs.
     */
    void testSFDifferentInstanceIdentityLookup() throws Exception;

}
