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
 * $Id: SFSBPCtxLifeCME00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.persistencectxlife;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.BasePCtxLifetimeCM00;
import org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfPCtxLifetime00;


/**
 * Tests the persistence lifetime.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateful(name = "SFSBPCtxLifeCME00")
@Remote(ItfPCtxLifetime00.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SFSBPCtxLifeCME00 extends BasePCtxLifetimeCM00{

    /**
     * Persistence.
     */
    @PersistenceContext(unitName="testEntity00", type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    /**
     * Initialization.
     */
    @Override
    public void initEntityManager(){
        super.initEntityManager(em);
    }

}
