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
 * $Id: Form.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.geometricforms;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.ow2.easybeans.tests.common.listeners.FormsListener00;

/**
 * Represents a geometric form.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@MappedSuperclass
@EntityListeners(FormsListener00.class)
@NamedQuery(name = "findAllByType", query = "SELECT f FROM Form f WHERE f.formType = :formType")
@TableGenerator(name  = "ID_SEQ", allocationSize = 1)
public class Form {

    /**
     * The entity identifier.
     */
    private int id;

    /**
     * The form area.
     */
    private float area;

    /**
     *The form perimeter.
     */
    private float perimeter;

    /**
     * The form type.
     */
   private FormType formType;

   /**
    * Gets the form type.
    * @return the form type.
    */
    @Enumerated(EnumType.STRING)
    public FormType getFormType() {
        return formType;
    }

    /**
     * Sets the form type.
     * @param formType the form type.
     */
    public void setFormType(final FormType formType) {
        this.formType = formType;
    }


    /**
     * Gets the form area.
     * @return the area.
     */
    public float getArea() {
        return area;
    }

    /**
     * Sets the form area.
     * @param area the area.
     */
    public void setArea(final float area) {
        this.area = area;
    }

    /**
     * Gets the form identifier.
     * @return the identifier.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_SEQ")
    public int getId() {
        return id;
    }

    /**
     * Sets the form identifier.
     * @param id the form identifier.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the form perimeter.
     * @return the form perimeter.
     */
    public float getPerimeter() {
        return perimeter;
    }

    /**
     * Sets the form perimeter.
     * @param perimeter the form perimeter.
     */
    public void setPerimeter(final float perimeter) {
        this.perimeter = perimeter;
    }

}
