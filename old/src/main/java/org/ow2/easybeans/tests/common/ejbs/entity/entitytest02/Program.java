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
 * $Id: Program.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.entitytest02;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

/**
 * The study program.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@MappedSuperclass
@TableGenerator(name  = "PROGRAM_SEQ", allocationSize = 1)
public class Program {

    /**
     * The program identifier.
     */
    private Long programId;

    /**
     * The program name.
     */
    private String name;

   /**
     * Returns the program name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the program name.
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the program identifier.
     * @return the identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROGRAM_SEQ")
    public Long getProgramId() {
        return programId;
    }

    /**
     * Sets the program identifier.
     * @param programId the program identifier.
     */
    public void setProgramId(final Long programId) {
        this.programId = programId;
    }

}
