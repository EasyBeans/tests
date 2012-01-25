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
 * $Id: SFSBBeanManagedPCtxLifeCMETest00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.beanmanaged.persistencectxlife;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfEntityFactory;
import org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifeCMETest00;


/**
 * Persistence Context lifetime Tests.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateful(name="SFSBBeanManagedPCtxLifeCMETest00")
@Remote(ItfPCtxLifeCMETest00.class)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class SFSBBeanManagedPCtxLifeCMETest00 implements ItfPCtxLifeCMETest00{

    /**
     * Transaction.
     */
    @Resource
    private UserTransaction utx;

    /**
     * Bean.
     */
    @EJB(mappedName="SFSBEntityFactoryCME00")
    private ItfEntityFactory bean;

    /**
     * Starts a transaction.
     * @throws Exception if a problem occurs.
     */
    @PostConstruct
    protected void beginTransaction() throws Exception{
        utx.begin();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifeCMETest00
     * @throws Exception if a problem occurs.
     */
    public void create() throws Exception{
        bean.createEntity();
        bean.checkManaged();
        utx.commit();
        bean.checkManaged();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifeCMETest00
     */
    public void check(){
        bean.checkManaged();
        this.remove();
    }

    /**
     * Remove method.
     */
    @Remove
    private void remove(){
        bean.removeEntity();
    }

}
