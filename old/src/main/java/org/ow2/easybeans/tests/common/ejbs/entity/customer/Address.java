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
package org.ow2.easybeans.tests.common.ejbs.entity.customer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The customer address.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Address implements Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = -2074717414531301945L;

    /**
     * The address identifier.
     */
    private long id;

    /**
     * The street name.
     */
    private String street;

    /**
     * The country name.
     */
    private String country;

    /**
     * The house number.
     */
    private int number;

    /**
     * Creates a new instance od Address.
     * @param id the identifier.
     * @param street the street.
     * @param country the country name.
     * @param number the house number.
     */
    public Address(final long id, final String street, final String country, final int number) {
        this.id = id;
        this.street = street;
        this.country = country;
        this.number = number;
    }

    /**
     * Creates a new instance of address.
     */
    public Address() {

    }

    /**
     * Gets the country name.
     * @return the country name.
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
     * Sets the address identifier.
     * @return the identifier.
     */
    @Id
    public long getId() {
        return id;
    }

    /**
     * Sets the address identifier.
     * @param id the identifier.
     */
    public void setId(final long id) {
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
     * @param number the house number.
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
