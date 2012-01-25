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
 * $Id: ProductOrder.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.customer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * The order.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
public class ProductOrder implements Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 6701790488965035830L;

    /**
     * The order identifier.
     */
    private long id;

    /**
     * The order description.
     */
    private String description;

    /**
     * The customer.
     */
    private Customer customer;

    /**
     * The products bought in this order.
     */
    private List<Product> products;

    /**
     * Creates an instance of Order.
     * @param id the identifier.
     * @param description the order description.
     * @param customer the customer.
     * @param products the products that was bought in this order.
     */
    public ProductOrder(final long id, final String description, final Customer customer, final List<Product> products) {
        this.id = id;
        this.description = description;
        this.customer = customer;
        this.products = products;
    }

    /**
     * Creates a new instance of Order.
     */
    public ProductOrder() {

    }

    /**
     * Gets the customer.
     * @return the customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     * @param customer the customer.
     */
    @ManyToOne
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the order description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the order description.
     * @param description the description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the order identifier.
     * @return the identifier.
     */
    @Id
    public long getId() {
        return id;
    }

    /**
     * Sets the order identifier.
     * @param id the identifier.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets the products that were bought in this order.
     * @return the products.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the products that were bought in this order.
     * @param products the products bought.
     */
    public void setProducts(final List<Product> products) {
        this.products = products;
    }

}
