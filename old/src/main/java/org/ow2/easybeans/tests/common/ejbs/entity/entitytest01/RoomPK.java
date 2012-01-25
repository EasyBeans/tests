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
 * $Id: RoomPK.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest01;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The room primary key.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Embeddable
public class RoomPK implements Serializable{

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -4342617189420663508L;

    /**
     * The room identifier.
     */
    private Long roomID;

    /**
     * The building.
     */
    private Building building;

    /**
     * Returns the building that contains the room.
     * @return the building.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the room building.
     * @param building the building.
     */
    @ManyToOne
    @JoinColumn(name = "id")
    public void setBuilding(final Building building) {
        this.building = building;
    }

    /**
     * Returns the romm identifier.
     * @return the identifier.
     */
    public Long getRoomID() {
        return roomID;
    }

    /**
     * Sets the identifier.
     * @param roomID the identifier.
     */
    public void setRoomID(final Long roomID) {
        this.roomID = roomID;
    }

}
