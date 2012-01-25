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
 * $Id: Customer.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.customer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The customer.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Customer  implements Serializable{

    /**
     * The serial version.
     */
    private static final long serialVersionUID = -5935600735100590395L;

    /**
     * The customer id.
     */
    private long id;

    /**
     * The customer name.
     */
    private String name;

    /**
     * The orders.
     */
    private List<ProductOrder> orders;

    /**
     * The address.
     */
    private Address address;

    /**
     * Gets the customer address.
     * @return the address.
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the customer address.
     * @param address the new address.
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Gets the customer identifier.
     * @return the identifier.
     */
    @Id
    public long getId() {
        return id;
    }

    /**
     * Sets the customer identifier.
     * @param id the customer identifier.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets the customer name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name.
     * @param name the new name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the client orders.
     * @return the orders.
     */
    @OneToMany(cascade = CascadeType.MERGE)
    public List<ProductOrder> getOrders() {
        return orders;
    }

    /**
     * Sets the orders.
     * @param orders the list of orders.
     */
    public void setOrders(final List<ProductOrder> orders) {
        this.orders = orders;
    }

}
