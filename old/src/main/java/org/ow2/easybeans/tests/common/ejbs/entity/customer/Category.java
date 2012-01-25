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
 * $Id: Category.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.customer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The product category.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
public class Category  implements Serializable{

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 812769477498625155L;

    /**
     * The category identifier.
     */
    private long id;

    /**
     * The category description.
     */
    private String description;

    /**
     * Gets the category description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the category description.
     * @param description the category description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the category identifier.
     * @return the category identifier.
     */
    @Id
    public long getId() {
        return id;
    }

    /**
     * Sets the category identifier.
     * @param id the identifier.
     */
    public void setId(final long id) {
        this.id = id;
    }

}
