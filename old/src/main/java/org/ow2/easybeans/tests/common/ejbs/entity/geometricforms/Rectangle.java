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
 * $Id: Rectangle.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.geometricforms;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.TableGenerator;

import org.ow2.easybeans.tests.common.listeners.FormsListener01;
import org.ow2.easybeans.tests.common.listeners.FormsListener02;

/**
 * Representas a rectangle.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@EntityListeners({FormsListener01.class, FormsListener02.class })
@TableGenerator(name  = "ID_SEQ", allocationSize = 1)
public class Rectangle extends Form {

    /**
     * A retangle side.
     */
    private float side1;

    /**
     * A retangle side.
     */
    private float side2;

    /**
     * Gets a retangle side.
     * @return the side.
     */
    public float getSide1() {
        return side1;
    }

    /**
     * Sets a retangle side.
     * @param side1 the side.
     */
    public void setSide1(final float side1) {
        this.side1 = side1;
    }

    /**
     * Gets a retangle side.
     * @return the side.
     */
    public float getSide2() {
        return side2;
    }

    /**
     * Sets a retangle side.
     * @param side2 the side.
     */
    public void setSide2(final float side2) {
        this.side2 = side2;
    }

}
