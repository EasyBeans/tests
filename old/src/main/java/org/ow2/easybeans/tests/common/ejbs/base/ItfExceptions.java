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
 * $Id: ItfExceptions.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.common.exception.CustomException01;


/**
 * Interface used to test exceptions.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public interface ItfExceptions {

    /**
     * Constant value.
     */
    Integer VALUE = new Integer(1);


    /**
     * Gets an integer.
     * @return an integer
     */
    Integer getInt();

    /**
     * Gets an integer.
     * @return an integer
     * @throws CustomException00 if a problem occurs
     */
    Integer throwsOneException00() throws CustomException00;

    /**
     * Gets an integer.
     * @return an integer
     * @throws CustomException01 if a problem occurs
     */
    Integer throwsOneException01() throws CustomException01;

    /**
     * Gets an integer.
     * @return an integer
     * @throws CustomException00 if a problem occurs
     * @throws CustomException01 if a problem occurs
     */
    Integer throwsTwoExceptions() throws CustomException00, CustomException01;
}
