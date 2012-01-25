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
 * $Id: EBStore.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.entity.ebstore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Define a store with an id and a name.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Entity
@Table(name = "STORE")
public class EBStore implements java.io.Serializable {

    /**
     * Id for serializable class.
     */
    private static final long serialVersionUID = 9126890320818098157L;

    /**
     * Default store name.
     */
    public static final String DEFAULT_NAME = "default";
    /**
     * Store Id.
     */
    private int id;

    /**
     * Name of the store.
     */
    private String name;

    /**
     * Default constructor.
     */
    public EBStore(){
    }

    /**
     * Parametrized constructor.
     * @param id store id
     * @param name store name
     */
    public EBStore(final int id, final String name){
        setId(id);
        setName(name);
    }

    /**
     * Creates a store with the default name.
     * @param id store id
     */
    public EBStore(final int id){
        setId(id);
        setName(DEFAULT_NAME);
    }

    /**
     * Gets the store Id.
     * @return the id of the store.
     */
    @Id
    public int getId() {
        return id;
    }

    /**
     * Sets store Id.
     * @param id the id's store
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the name.
     * @param name of store.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the store name.
     * @return name of the store.
     */
    public String getName() {
        return name;
    }

    /**
     * Computes a string representation of this store.
     * @return string representation.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EBStore[id=").append(id).append(", name=").append(getName()).append("]");
        return sb.toString();
    }

}
