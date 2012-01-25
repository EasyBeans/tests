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
 * $Id: ProfessorRoom.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest04;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The professor room.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
public class ProfessorRoom implements Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 4584689820008347097L;

    /**
     * The room name.
     */
    private String name;

    /**
     * Room identifier.
     */
    private Long id;

    /**
     * Owner.
     */
    private Professor professor;

    /**
     * The professor that is using the room.
     * @return the professor.
     */
    @OneToOne(mappedBy = "professorRoom")
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Sets the professor that is using the room.
     * @param professor the professor.
     */
    public void setProfessor(final Professor professor) {
        this.professor = professor;
    }

    /**
     * Gets the room identifier.
     * @return the identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the room identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Returns the room name.
     * @return the name.
     */
    @Basic(fetch = FetchType.LAZY)
    public String getName() {
        return name;
    }

    /**
     * Sets the room name.
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }

}
