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
 * $Id: Telephone.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest00;

import javax.persistence.Embeddable;

/**
 * The telephone data.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Embeddable
public class Telephone {

    /**
     * The mobile number.
     */
    private String mobile;

    /**
     * The home number.
     */
    private String homePhone;

    /**
     * The work number.
     */
    private String workPhone;

    /**
     * Returns the home telephone number.
     * @return the telephone number.
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Sets the home telephone number.
     * @param homePhone the telephone number.
     */
    public void setHomePhone(final String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * Returns the mobile telephone number.
     * @return the telephone number.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile telephone number.
     * @param mobile the telephone number.
     */
    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }


    /**
     * Returns the work telephone number.
     * @return the telephone number.
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * Sets the work telephone number.
     * @param workPhone the telephone number.
     */
    public void setWorkPhone(final String workPhone) {
        this.workPhone = workPhone;
    }



}
