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
 * $Id: ClassPK.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest03;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The class primary key.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Embeddable
public class ClassPK implements Serializable {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 6916779921562492042L;

    /**
     * The class year.
     */
    private String classYear;

    /**
     * The class name.
     */
    private String className;

    /**
     * Returns the class name.
     * @return the name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class name.
     * @param className the name.
     */
    public void setClassName(final String className) {
        this.className = className;
    }

    /**
     * Returns the class year.
     * @return the class year.
     */
    public String getClassYear() {
        return classYear;
    }

    /**
     * Sets the class year.
     * @param classYear the year.
     */
    public void setClassYear(final String classYear) {
        this.classYear = classYear;
    }

}
