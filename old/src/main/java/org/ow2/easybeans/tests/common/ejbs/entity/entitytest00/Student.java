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
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest00;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The student.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@DiscriminatorValue("Student")
@Table(name = "STUDENT", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@AttributeOverrides({
      @AttributeOverride(name="startDate", column = @Column(name = "StudentStartDate")),
      @AttributeOverride(name="endDate", column = @Column(name = "StudentEndDate"))
})
public class Student extends Person {

    /**
     *Serial version.
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
     * @param graduated true if the student has finished the course, false otherwise.
     */
    public void setGraduated(final boolean graduated) {
        this.graduated = graduated;
    }

}
