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
 * $Id: SLSBListenerTester02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.FormType;
import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.Square;


/**
 * Verifies if the container manages a bean that has the annotation
 * ExcludeSuperclassListeners.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateless
@Remote(ItfListenerTester.class)
public class SLSBListenerTester02 extends ListenerTesterBase {


    /**
     * The persistence context used to manipulate the entity.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a new instance of Square and modifies it.
     */
    @Override
    protected void createAndModifyEntity() {
        Square square = new Square();
        square.setSide(1);
        square.setFormType(FormType.SQUARE);
        entityManager.persist(square);
        entityManager.flush();

        square.setSide(2);
        entityManager.flush();

    }

    /**
     * Creates a new instance of the Square and refreshes it.
     */
    @Override
    protected void createAndRefreshEntity() {
        Square square = new Square();
        square.setSide(1);
        square.setFormType(FormType.SQUARE);
        entityManager.persist(square);
        entityManager.flush();

        entityManager.refresh(square);
    }

    /**
     * Creates a Square and removes it.
     */
    @Override
    protected void createAndRemoveEntity() {
        Square square = new Square();
        square.setSide(1);
        square.setFormType(FormType.SQUARE);
        entityManager.persist(square);
        entityManager.flush();

        entityManager.remove(square);
        entityManager.flush();
    }

    /**
     * Creates a Square.
     */
    @Override
    protected void createEntity() {
        Square square = new Square();
        square.setSide(1);
        square.setFormType(FormType.SQUARE);
        entityManager.persist(square);
        entityManager.flush();
    }


    /**
     * Creates the list of listeners that are called for the entity Square.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    private String[] createListenersList(){
        String[] strListeners = new String[0];
        return strListeners;
    }

    /**
     * Creates the list of listeners that are called for the entity Square.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListLoadListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Square.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListPersistListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Square.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListRemoveListeners() {
        return createListenersList();
    }

    /**
     * Creates the list of listeners that are called for the entity Square.
     * The list is order by the callback invocation order.
     * @return the list of listeners ordered by the invocation order.
     */
    @Override
    protected String[] createListUpdateListeners() {
        return createListenersList();
    }

    /**
     * Returns the name of the entity class that the listeners are defined, in
     * this case Square.
     * @return the class name.
     */
    @Override
    protected String getFormName() {
        return Square.class.getName();
    }


}
