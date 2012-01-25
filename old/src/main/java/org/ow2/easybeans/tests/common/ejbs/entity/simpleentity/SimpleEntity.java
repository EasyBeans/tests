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
 * $Id: SimpleEntity.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.simpleentity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

/**
 * Simple entity used in the EntityManager tests.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
@Table(name = "SIMPLE")
@NamedQueries({@NamedQuery(name = "findByName", query = "SELECT e FROM SimpleEntity e WHERE e.name = :entityName "),
        @NamedQuery(name = "findByIdNamed", query = "SELECT e FROM SimpleEntity e WHERE e.id > :entityId")})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SimpleEntityResult", entities = @EntityResult(entityClass = SimpleEntity.class)),
        @SqlResultSetMapping(name = "MappedSimpleEntity", entities = @EntityResult(entityClass = SimpleEntity.class, fields = {
                @FieldResult(name = "id", column = "entity_id"), @FieldResult(name = "name", column = "entity_name")}))})
@NamedNativeQuery(name = "findByAll", query = "SELECT e.id, e.name FROM SIMPLE e ORDER BY e.id",
        resultSetMapping = "SimpleEntityResult")
public class SimpleEntity implements Serializable {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 7281003617281981005L;

    /**
     * The bean Id.
     */
    private int id;

    /**
     * The bean name.
     */
    private String name;

    /**
     * Gets the bean Id.
     * @return the id of the bean.
     */
    @Id
    public int getId() {
        return id;
    }

    /**
     * Sets bean Id.
     * @param id the bean id.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the bean name.
     * @param name bean name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the bean name.
     * @return bean name.
     */
    public String getName() {
        return name;
    }

    /**
     * Computes a string representation of this bean.
     * @return string representation.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleEntity[id=").append(id).append(", name=").append(getName()).append("]");
        return sb.toString();
    }

}
