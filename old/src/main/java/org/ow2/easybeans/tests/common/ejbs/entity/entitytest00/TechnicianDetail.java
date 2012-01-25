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
 * $Id: TechnicianDetail.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest00;

import javax.persistence.Id;


/**
 * Details about the technician.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public class TechnicianDetail {

    /**
     * The technician id.
     */
    private Long id;

    /**
     * The work area.
     */
    private String area;

    /**
     * The years of experience.
     */
    private int yearExperience;

    /**
     * Returns the work area.
     * @return the area.
     */
    public String getArea() {
        return area;
    }


    /**
     * Sets the work area.
     * @param area the area.
     */
    public void setArea(final String area) {
        this.area = area;
    }

    /**
     * Returns the years of experience.
     * @return the years.
     */
    public int getYearExperience() {
        return yearExperience;
    }

    /**
     * Sets the years of experience.
     * @param yearExperience the years.
     */
    public void setYearExperience(final int yearExperience) {
        this.yearExperience = yearExperience;
    }

    /**
     * The technician identifier.
     * @return the identifier.
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Sets the technician identifier.
     * @param personID the identifier.
     */
    public void setId(final Long personID) {
        this.id= personID;
    }

}
