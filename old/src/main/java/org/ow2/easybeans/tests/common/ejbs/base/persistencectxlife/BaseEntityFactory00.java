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
 * $Id: BaseEntityFactory00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife;

import static org.testng.Assert.assertTrue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.ow2.easybeans.tests.common.ejbs.entity.ebstore.EBStore;

/**
 * This bean creates an entity instance and calls another entity factory to create another entity.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@EJB(name="ejb/ebFactory", beanInterface=ItfPCtxLifetime00.class, mappedName="SFSBPCtxLifeCME01")
public class BaseEntityFactory00 implements ItfEntityFactory {

    /**
     * Manager.
     */
    @PersistenceContext(unitName = "testEntity00", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    /**
     * Context.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Entity.
     */
    private EBStore eb;

    /**
     * Id.
     */
    private int id = this.hashCode();

    /**
     * Entity Creator.
     */
    private ItfPCtxLifetime00 bean;

    /**
     * Initialization.
     */
    @PostConstruct
    public void init(){
        bean = (ItfPCtxLifetime00) ctx.lookup("ejb/ebFactory");
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfEntityFactory
     */
    public void createEntity() {
        eb = new EBStore(id);
        em.persist(eb);

        bean.createCheckEntity00();

        this.checkManaged();
        bean.checkManaged();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfEntityFactory
     */
    @SuppressWarnings("boxing")
    public void checkManaged() {
        assertTrue(em.contains(eb), "The entity instance should be managed.");

        EBStore ebFind = em.find(EBStore.class, id);

        assertTrue(ebFind == eb, "The entity instances should be equals, "
                + "they are in the same persistence context.");

        bean.checkManaged();
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.persistencectxlife.ItfEntityFactory
     */
    @SuppressWarnings("boxing")
    public void removeEntity() {
        EBStore ebFind = em.find(EBStore.class, id);
        if (ebFind != null) {
            em.remove(ebFind);
        }

        ItfPCtxLifetime00 beanRemove = (ItfPCtxLifetime00) ctx.lookup("ejb/ebFactory");
        beanRemove.removeEntity();
    }
}
