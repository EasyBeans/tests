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
 * $Id: Building.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest01;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 * Contains the building information.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Building {

    /**
     * The building identifier.
     */
    private Long id;

    /**
     * The building name.
     */
    private String name;

    /**
     * The room list in the building.
     */
    private Map< RoomPK, Room> rooms;

    /**
     * Returns the building identifier.
     * @return the identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }


    /**
     * Sets the building identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }


    /**
     * Returns the building name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the building name.
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the map with all rooms in the building.
     * @return the rooms map.
     */
    @OneToMany(mappedBy="building")
    @MapKey
    public Map<RoomPK, Room> getRooms() {
        return rooms;
    }


    /**
     * Sets the rooms map.
     * @param rooms the rooms map.
     */
    public void setRooms(final Map<RoomPK, Room> rooms) {
        this.rooms = rooms;
    }


}
