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
 * $Id: Person.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest00;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents the common data among the diffent possitions.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@Table(name = "PERSON")
@DiscriminatorColumn(name = "PersonType")
@DiscriminatorValue("Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@TableGenerator(name = "PERSON_SEQ", allocationSize = 2)
public class Person implements Serializable {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -2588575691131751927L;

    /**
     * The person identifier.
     */
    private Long id;

    /**
     * The family name.
     */
    private String familyName;

    /**
     * The first name.
     */
    private String firstName;

    /**
     * The date fo admission.
     */
    private Date startDate;

    /**
     * The date of depart.
     */
     private Date endDate;

    /**
     * The picture.
     */
    private byte[] picture;

    /**
     * Returns the depart date.
     * @return the date.
     */
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the depart date.
     * @param endDate the date.
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the family name.
     * @return the name.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the family name.
     * @param familyName the name.
     */
    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    /**
     * Returns the first name.
     * @return the name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * @param firstName the name.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the person identifier.
     * @return the identifier.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PERSON_SEQ")
    public Long getId() {
        return id;
    }

    /**
     * Returns the admission date.
     * @return the date.
     */
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the admission date.
     * @param startDate the date.
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the person picture.
     * @return the picture.
     */
    @Lob
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Sets the person picture.
     * @param picture the picture.
     */
    public void setPicture(final byte[] picture) {
        this.picture = picture;
    }

    /**
     * Sets the identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
