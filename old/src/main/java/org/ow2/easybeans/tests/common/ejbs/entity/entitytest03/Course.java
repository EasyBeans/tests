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
 * $Id: Course.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest03;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 * The course that ccan have many classes.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Course {

    /**
     * The identifier.
     */
    private Long courseId;

    /**
     * The course name.
     */
    private String name;

    /**
     * The number of credits.
     */
    private int credits;

    /**
     * The classes of this course.
     */
    private Collection<Class> classes;

    /**
     * Returns the classes of the course.
     * @return the classes
     */
    @OneToMany(mappedBy = "course")
    public Collection<Class> getClasses() {
        return classes;
    }

    /**
     * Sets the classes of the course.
     * @param classes the classes.
     */
    public void setClasses(final Collection<Class> classes) {
        this.classes = classes;
    }

    /**
     * Returns the course identifier.
     * @return the identifier.
     */
    @TableGenerator(name = "courseGen", table = "idTable", allocationSize = 2)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "courseGen")
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the course identifier.
     * @param courseId the course identifier.
     */
    public void setCourseId(final Long courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns the course credits.
     * @return the credits.
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the course credits.
     * @param credits the credits.
     */
    public void setCredits(final int credits) {
        this.credits = credits;
    }

    /**
     * Returns the course name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the course name.
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }
}
