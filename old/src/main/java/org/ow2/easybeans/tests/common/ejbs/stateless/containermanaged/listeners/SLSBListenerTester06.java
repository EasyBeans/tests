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
 * $Id: SLSBListenerTester06.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.FormType;
import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.Rectangle;
import org.ow2.easybeans.tests.common.listeners.FormsListener00;
import org.ow2.easybeans.tests.common.listeners.FormsListener01;
import org.ow2.easybeans.tests.common.listeners.FormsListener02;

/**
 * Verifies if the container manages multiples listeners that are defined in the
 * superclass adn in the class.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfListenerTester.class)
public class SLSBListenerTester06 extends ListenerTesterBase {

    /**
     * The number of listener methods that will be called.
     */
    private static final int LISTENER_NUMBER = 3;

    /**
     * The entity manager used to manages the entities.
     */
    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Creates and modifies a rectangle.
     */
    @Override
    protected void createAndModifyEntity() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSide1(1);
        rectangle.setFormType(FormType.RECTANGLE);
        entityManager.persist(rectangle);
        entityManager.flush();

        rectangle.setSide1(2);
        entityManager.flush();

    }

    /**
     * Creates and refreshes a rectangle.
     */
    @Override
    protected void createAndRefreshEntity() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSide1(1);
        rectangle.setFormType(FormType.RECTANGLE);
        entityManager.persist(rectangle);
        entityManager.flush();

        entityManager.refresh(rectangle);
    }

    /**
     * Creates and removes a rectangle.
     */
    @Override
    protected void createAndRemoveEntity() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSide1(1);
        rectangle.setFormType(FormType.RECTANGLE);
        entityManager.persist(rectangle);
        entityManager.flush();

        entityManager.remove(rectangle);
        entityManager.flush();
    }

    /**
     * Creates a rectangle.
     */
    @Override
    protected void createEntity() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSide1(1);
        rectangle.setFormType(FormType.RECTANGLE);
        entityManager.persist(rectangle);
        entityManager.flush();
    }

    /**
     * Creates the list of listeners that are called for the entity rectangle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    private String[] createListenersList(){
        String[] strListeners = new String[LISTENER_NUMBER];
        strListeners[0] = FormsListener00.class.getName();
        strListeners[1] = FormsListener01.class.getName();
        strListeners[2] = FormsListener02.class.getName();
        return strListeners;
    }

    /**
     * Creates the list of listeners that are called for the entity rectangle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListLoadListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity rectangle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListPersistListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity rectangle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListRemoveListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity rectangle.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListUpdateListeners() {
        return createListenersList();
    }

    /**
     * Returns the name of the entity class that the listeners are defined, in
     * this case rectangle.
     * @return the class name.
     */
    @Override
    protected String getFormName() {
        return Rectangle.class.getName();
    }


}
