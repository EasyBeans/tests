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
 * $Id: SLSBListenerTester01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.Circle;
import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.FormType;
import org.ow2.easybeans.tests.common.listeners.FormsListener00;

/**
 * Verifies if the container manages the listeners invocation when the entity
 * has only superclass listener.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfListenerTester.class)
public class SLSBListenerTester01 extends ListenerTesterBase {

    /**
     * Used to manages the entity.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a circle and modifies it.
     */
    @Override
    protected void createAndModifyEntity() {
        Circle c = new Circle();
        c.setRadius(1);
        c.setFormType(FormType.CIRCLE);
        entityManager.persist(c);
        entityManager.flush();

        c.setRadius(2);
        entityManager.flush();
    }

    /**
     * Creates a Circle and refreshes it.
     */
    @Override
    protected void createAndRefreshEntity() {
        Circle c = new Circle();
        c.setRadius(1);
        c.setFormType(FormType.CIRCLE);
        entityManager.persist(c);
        entityManager.flush();

        entityManager.refresh(c);
    }

    /**
     * Creates a circle and removes it.
     */
    @Override
    protected void createAndRemoveEntity() {
        Circle c = new Circle();
        c.setRadius(1);
        c.setFormType(FormType.CIRCLE);
        entityManager.persist(c);
        entityManager.flush();

        entityManager.remove(c);
        entityManager.flush();
    }

    /**
     * Creates a circle.
     */
    @Override
    protected void createEntity() {
        Circle c = new Circle();
        c.setRadius(1);
        c.setFormType(FormType.CIRCLE);
        entityManager.persist(c);
        entityManager.flush();
    }


    /**
     * Creates the list of listeners that are called for the entity Circle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    private String[] createListenersList(){
        String[] strListeners = new String[1];
        strListeners[0] = FormsListener00.class.getName();
        return strListeners;
    }

    /**
     * Creates the list of listeners that are called for the entity Circle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListLoadListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Circle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListPersistListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Circle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListRemoveListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Circle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListUpdateListeners() {
        return createListenersList();
    }

    /**
     * Returns the name of the entity class that the listeners are defined, in
     * this case Circle.
     * @return the class name.
     */
    @Override
    protected String getFormName() {
        return Circle.class.getName();
    }

}
