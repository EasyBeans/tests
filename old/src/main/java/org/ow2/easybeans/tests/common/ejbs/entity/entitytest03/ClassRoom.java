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
 * $Id: ClassRoom.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest03;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * The room where there are the classes.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class ClassRoom {

    /**
     * Room identifier.
     */
    private Long id;
    /**
     * The number of places in the class.
     */
    private int capacity;

    /**
     * The classes in this room.
     */
    private Collection<Class> classes;

    /**
     * The classes in this room.
     * @return the classes.
     */
    @OneToMany(mappedBy="classRoom")
    @OrderBy("className ASC")
    public Collection<Class> getClasses() {
        return classes;
    }

    /**
     * Set The classes in this room.
     * @param classes the classes.
     */
    public void setClasses(final Collection<Class> classes) {
        this.classes = classes;
    }

    /**
     * Returns the number of places in this room.
     * @return the capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the number of places in this room.
     * @param capacity the capacity.
     */
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the class identifier.
     * @return the identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the class identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }


}
