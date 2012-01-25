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
 * $Id: Program.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest03;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

/**
 * The study program.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@MappedSuperclass
public class Program {

    /**
     * The program identifier.
     */
    private Long programId;

    /**
     * The program name.
     */
    private String name;

    /**
     * The courses list for this program.
     */
    private List<Course> courses;

    /**
     * Returns the courses of this program.
     * @return the courses.
     */
    @ManyToMany
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses.
     * @param courses the courses.
     */
    public void setCourses(final List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns the program name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the program name.
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the program identifier.
     * @return the identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROGRAM_SEQ")
    public Long getProgramId() {
        return programId;
    }

    /**
     * Sets the program identifier.
     * @param programId the program identifier.
     */
    public void setProgramId(final Long programId) {
        this.programId = programId;
    }

}
