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
 * $Id: SimpleEjb2Local.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import javax.ejb.EJBLocalObject;

/**
 * The bean local interface.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface SimpleEjb2Local extends EJBLocalObject {

    /**
     * The default bean code.
     */
    int DEFAULT_CODE_LOCAL = 1;

    /**
     * The default bean name.
     */
    String DEFAULT_NAME_LOCAL = "test";

    /**
     * The default message.
     */
    String DEFAULT_MESSAGE = "Hello";

    /**
     * Sets the bean values.
     * @param code the bean code.
     * @param name the bean name.
     */
    void setValues(final int code, final String name);

    /**
     * Gets the default message.
     * @return the message.
     */
    String sayHello();

    /**
     * Gets the bean code.
     * @return the code.
     */
    int getCodeLocal();

    /**
     * Gets the bean name.
     * @return the bean name.
     */
    String getNameLocal();
}
