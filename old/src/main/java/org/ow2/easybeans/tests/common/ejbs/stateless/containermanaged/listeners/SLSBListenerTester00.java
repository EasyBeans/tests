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
 * $Id: SLSBListenerTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.FormType;
import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.Trapezoid;
import org.ow2.easybeans.tests.common.listeners.FormsListener00;

/**
 * Verifies if the container manages the listener invocation for a entity that
 * has an listeners specified in the superclass and other listeners specified in
 * the entity class.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfListenerTester.class)
public class SLSBListenerTester00 extends ListenerTesterBase {

    /**
     * The persistence context used to manipulate the entity.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates an entity Trapezoid and modifies it.
     */
    @Override
    protected void createAndModifyEntity() {
        //creates a trapezoid
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setSide1(1);
        trapezoid.setSide2(2);
        trapezoid.setFormType(FormType.TRAPEZOID);
        entityManager.persist(trapezoid);
        entityManager.flush();

        //modifies the trapezoid
        trapezoid.setSide1(2);
        trapezoid.setSide2(1);
        entityManager.flush();
    }

    /**
     * Creates an entity Trapezoid, modifies and refreshes it.
     */
    @Override
    protected void createAndRefreshEntity() {
        //creates a trapezoid
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setSide1(1);
        trapezoid.setSide2(2);
        trapezoid.setFormType(FormType.TRAPEZOID);
        entityManager.persist(trapezoid);
        entityManager.flush();

        //refresh the trapezoid
        trapezoid.setSide1(2);
        trapezoid.setSide2(1);
        entityManager.refresh(trapezoid);

    }

    /**
     * Creates an entity Trapezoid and removes it.
     */
    @Override
    protected void createAndRemoveEntity() {
        //creates a trapezoid
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setSide1(1);
        trapezoid.setSide2(2);
        trapezoid.setFormType(FormType.TRAPEZOID);
        entityManager.persist(trapezoid);
        entityManager.flush();

        //removes the trapezoid
        entityManager.remove(trapezoid);
        entityManager.flush();
    }

    /**
     * Creates an entity Trapezoid.
     */
    @Override
    protected void createEntity() {
        //creates a trapezoid
        Trapezoid trapezoid = new Trapezoid();
        trapezoid.setSide1(1);
        trapezoid.setSide2(2);
        trapezoid.setFormType(FormType.TRAPEZOID);
        entityManager.persist(trapezoid);
        entityManager.flush();
    }

    /**
     * Creates the list of listeners that are called for the entity Trapezoid.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    private String[] createListenersList(){
        String[] strListeners = new String[2];
        strListeners[0] = FormsListener00.class.getName();
        strListeners[1] = Trapezoid.class.getName();
        return strListeners;
    }


    /**
     * Creates the list of listeners that are called for the entity Trapezoid.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListLoadListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Trapezoid.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListPersistListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Trapezoid.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListRemoveListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Trapezoid.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListUpdateListeners() {
        return createListenersList();
    }

    /**
     * Returns the name of the entity class that the listeners are defined, in
     * this case Trapezoid.
     * @return the class name.
     */
    @Override
    protected String getFormName() {
        return Trapezoid.class.getName();
    }

}
