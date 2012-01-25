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
 * $Id: Professor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest04;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * The Professor data.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
@DiscriminatorValue("Professor")
public class Professor implements Serializable {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -6979637156066932597L;

    /**
     * Professor id.
     */
    private Long id;

    /**
     * The professor degree.
     */
    private Degree degree;


    /**
     * The professor room.
     */
    private ProfessorRoom profRoom;

    /**
     * The address.
     */
    private Address address;

    /**
     * Gets the professor address.
     * @return the address.
     */
    @OneToOne
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the professor address.
     * @param address the professor address.
     */
    public  void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Returns the professor room.
     * @return the room.
     */
    @OneToOne
    @JoinColumn
    public ProfessorRoom getProfessorRoom() {
        return profRoom;
    }

    /**
     * Sets the professor room.
     * @param profRoom the room.
     */
    public void setProfessorRoom(final ProfessorRoom profRoom) {
        this.profRoom = profRoom;
    }


    /**
     * Returns the professor degree.
     * @return the degree.
     */
    @Enumerated(EnumType.STRING)
    public Degree getDegree() {
        return degree;
    }

    /**
     * Sets the professor degree.
     * @param degree the degree.
     */
    public void setDegree(final Degree degree) {
        this.degree = degree;
    }

    /**
     * Gets the professor identifier.
     * @return the professor identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the professor identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
