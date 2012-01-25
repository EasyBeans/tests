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
 * $Id: Address.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest04;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Contains the address reference.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Address implements Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = -4633350356479296752L;

    /**
     * Address identifier.
     */
    private Long id;

    /**
     * Street name.
     */
    private String street;

    /**
     * Home number.
     */
    private int number;

    /**
     * Country name.
     */
    private String country;

    /**
     * Gets the country name.
     * @return the coutnry name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country name.
     * @param country the country name.
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets the address identifier.
     * @return the identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the address identifier.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the house number.
     * @return the number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the house number.
     * @param number the  new number.
     */
    public void setNumber(final int number) {
        this.number = number;
    }

    /**
     * Gets the street name.
     * @return the street name.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street name.
     * @param street the street name.
     */
    public void setStreet(final String street) {
        this.street = street;
    }
}
