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
 * $Id: Student.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest03;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * The student.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
public class Student {

    /**
     * Student identifier.
     */
    private Long id;

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -7986846870958654109L;

    /**
     * Says if the student completed the studies in the university.
     */
    private boolean graduated;

    /**
     * The email.
     */
    private String email;

    /**
     * The current courses.
     */
    private Collection<Class> currentCours;

    /**
     * Returns the current courses.
     * @return the courses.
     */
    @ManyToMany
    @JoinTable(name = "CLASS_STUDENT", joinColumns = {@JoinColumn(name = "STUDENT_ID", referencedColumnName = "id")},
            inverseJoinColumns = {
            @JoinColumn(name = "CLASS_NAME", referencedColumnName = "className"),
            @JoinColumn(name = "CLASS_YEAR", referencedColumnName = "classYear")})
    public Collection<Class> getCurrentCours() {
        return currentCours;
    }

    /**
     * Sets the current courses.
     * @param currentCours the courses.
     */
    public void setCurrentCours(final Collection<Class> currentCours) {
        this.currentCours = currentCours;
    }

    /**
     * Returns the student email.
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student e-mail.
     * @param email the student e-mail.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Returns the student status in the college.
     * @return true if the student finished the program,false otherwise.
     */
    public boolean isGraduated() {
        return graduated;
    }

    /**
     * Sets if the student is graduated.
     * @param graduated true if the student has finished the course, false
     *        otherwise.
     */
    public void setGraduated(final boolean graduated) {
        this.graduated = graduated;
    }

    /**
     * Gets the identifier.
     * @return the identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
