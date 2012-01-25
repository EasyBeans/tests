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
 * $Id: Class.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest03;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Contains the information about the class. Each course can have more than one class.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
/**
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@IdClass(ClassPK.class)
@Entity
@PrimaryKeyJoinColumn(name = "course", referencedColumnName = "courseId")
public class Class {

    /**
     * The course identifier.
     */
    private Course course;

    /**
     * The class year.
     */
    private String classYear;

    /**
     * The class name.
     */
    private String className;

    /**
     * The class room.
     */
    private ClassRoom classRoom;

    /**
     * The professor identifier.
     */
    private Professor professor;

    /**
     * The class students.
     */
    private Collection<Student> students;

    /**
     * The begin date for the class.
     */
    private Date startDate;

    /**
     * The end date for the class.
     */
    private Date endDate;

    /**
     * Returns the end date for the classes.
     * @return the date.
     */
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date for the classes.
     * @param endDate the date.
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the start date for the classes.
     * @return the date.
     */
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date for the classes.
     * @param startDate the date.
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the class room.
     * @return the room.
     */
    @ManyToOne
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    /**
     * Sets the class room.
     * @param classRoom the room.
     */
    public void setClassRoom(final ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    /**
     * Sets the students that are in this class.
     * @param students the students.
     */
    public void setStudents(final Collection<Student> students) {
        this.students = students;
    }

    /**
     * Returns the class name.
     * @return the name.
     */
    @Id
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class name.
     * @param className the name.
     */
    public void setClassName(final String className) {
        this.className = className;
    }

    /**
     * Returns the class year.
     * @return the year.
     */
    @Id
    public String getClassYear() {
        return classYear;
    }

    /**
     * Sets the class year.
     * @param classYear the year.
     */
    public void setClassYear(final String classYear) {
        this.classYear = classYear;
    }

    /**
     * Returns the class course.
     * @return the course.
     */
    @ManyToOne
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the course.
     * @param course the course.
     */
    public void setCourse(final Course course) {
        this.course = course;
    }

/**
     * Returns the class professor.
     * @return the professor.
     */
    @ManyToOne
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Sets the class professor.
     * @param professor the professor.
     */
    public void setProfessor(final Professor professor) {
        this.professor = professor;
    }

    /**
     * Returns the class students.
     * @return the students.
     */
    @ManyToMany(mappedBy = "currentCours")
    public Collection<Student> getStudents() {
        return students;
    }

}
