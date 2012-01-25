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
 * $Id: Product.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.customer;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The product.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Product implements Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 2265810991046125943L;

    /**
     * The product identifier.
     */
    private long id;

    /**
     * The product description.
     */
    private String description;

    /**
     * The product price.
     */
    private float price;

    /**
     * The product category.
     */
    private Category category;

    /**
     * The order that hasthis product.
     */
    private ProductOrder order;

    /**
     * Creates a new instance of Product.
     * @param id the identifier.
     * @param description the product description.
     * @param price the product price.
     * @param category the product category.
     * @param order the order that has this product.
     */
    public Product(final long id, final String description, final float price, final Category category,
            final ProductOrder order) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.category = category;
        this.order = order;
    }

    /**
     * Creates a new instance of Product.
     * @param id the identifier.
     * @param description the product description.
     * @param price the product price.
     * @param category the product category.
     */
    public Product(final long id, final String description, final float price, final Category category) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    /**
     * Creates a new instance of Product.
     *
     */
    public Product() {

    }

    /**
     * Gets the order.
     * @return the order.
     */
    @ManyToOne
    public ProductOrder getOrder() {
        return order;
    }

    /**
     * Sets the order.
     * @param order the order that has this product.
     */
    public void setOrder(final ProductOrder order) {
        this.order = order;
    }

    /**
     * Gets the product description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description.
     * @param description the product description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the product identifier.
     * @return the identifier.
     */
    @Id
    public long getId() {
        return id;
    }

    /**
     * Sets the product identifier.
     * @param id the identifier.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets the product price.
     * @return the price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     * @param price the product price.
     */
    public void setPrice(final float price) {
        this.price = price;
    }

    /**
     * Gets the product category.
     * @return the category.
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     * @param category the new category.
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

}
