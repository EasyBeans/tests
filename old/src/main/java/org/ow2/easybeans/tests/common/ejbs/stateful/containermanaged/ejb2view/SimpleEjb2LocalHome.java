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
 * $Id: SimpleEjb2LocalHome.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * The bean local home interface.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface SimpleEjb2LocalHome extends EJBLocalHome {

    /**
     * Creates an instance of the bean with the default name and the code
     * defined in the parameter.
     * @param code the bean code.
     * @return the bean.
     * @throws CreateException if a creation error occurs.
     */
    SimpleEjb2Local create(final int code) throws CreateException;

    /**
     * Creates an instance of the bean with the default code and the name
     * defined in the parameter.
     * @param name the bean name.
     * @return the bean.
     * @throws CreateException if a creation error occurs.
     */
    SimpleEjb2Local create(final String name) throws CreateException;

}
