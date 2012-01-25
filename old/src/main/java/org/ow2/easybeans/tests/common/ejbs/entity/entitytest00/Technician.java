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
 * $Id: Technician.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest00;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Transient;

/**
 * The technician.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@DiscriminatorValue("technician")
@SecondaryTables({@SecondaryTable(name = "technicianDetails"), @SecondaryTable(name = "bankingData")})
public class Technician extends Person {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -1732670933017289750L;

    /**
     * The type of contract.
     * @author Gisele Pinheiro Souza
     * @author Eduardo Studzinski Estima de Castro
     *
     */
    public enum ContractType {CDD, CDI, INTERNSHIP}

    /**
     * The current work place.
     */
    private String place;

    /**
     * The contratc type.
     */
    private ContractType contract;

    /**
     * The telephone number.
     */
    private Telephone phone;


    /**
     * Returns the telephone number.
     * @return the telephone.
     */
    @Embedded
    public Telephone getPhone() {
        return phone;
    }

    /**
     * Sets the telephone number.
     * @param phone the phone number.
     */
    public void setPhone(final Telephone phone) {
        this.phone = phone;
    }
    /**
     * Returns the contract type.
     * @return the contract type.
     */
    @Enumerated(EnumType.ORDINAL)
    public ContractType getContract() {
        return contract;
    }

    /**
     * Sets the contract type.
     * @param contract the contract type.
     */
    public void setContract(final ContractType contract) {
        this.contract = contract;
    }

    /**
     * Returns the current work place.
     * @return the work place.
     */
    @Transient
    public String getPlace() {
        return place;
    }

    /**
     * Sets the current work place.
     * @param place the work place.
     */
    public void setPlace(final String place) {
        this.place = place;
    }

}
