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
 * $Id: BankingData.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest00;

import javax.persistence.Id;


/**
 * Contains the technician account data.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public class BankingData {

    /**
     * The technician identifier.
     */
    private Long id;

    /**
     * The account number.
     */
    private String accountNumber;

    /**
     * The bank name.
     */
    private String bankName;

    /**
     * Returns the technician account number.
     * @return the account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the technician account number.
     * @param accountNumber th account number.
     */
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Retuns the bank name of the technician.
     * @return the bank name.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the bank name of the technician.
     * @param bankName the bank name.
     */
    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    /**
     * Returns the technician identifier in the database.
     * @return the identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the technician identifier in the database.
     * @param id the identifier.
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
