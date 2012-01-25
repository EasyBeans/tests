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
 * $Id: SLSBBeanManagedUtxRefFieldInjection00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.beanmanaged.usertxref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;

/**
 * This bean is used to test the &#64;Resource annotation for user transaction on fields.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBBeanManagedUtxRefFieldInjection00")
@Remote(ItfCheck00.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class SLSBBeanManagedUtxRefFieldInjection00 implements ItfCheck00 {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Field injection.
     */
    @Resource(name = "utx/utx00")
    private UserTransaction utx00;

    /**
     * Field injection.
     */
    @Resource
    private UserTransaction utx01;


    /**
     * Checks if the injection using &#64;Resource for user transaction is working properly on bean fields;
     * The cotainer must:
     * <li>inject only defined name</li>
     * <li>inject without defined name, the default name must be defined by the container.</li>
     */
    public void check() {
        checkResource(sessionContext, utx00, "utx/utx00");
        checkResource(sessionContext, utx01, "org.ow2.easybeans.tests.common.ejbs."
                + "stateless.beanmanaged.usertxref.SLSBBeanManagedUtxRefFieldInjection00/utx01");
    }

}
