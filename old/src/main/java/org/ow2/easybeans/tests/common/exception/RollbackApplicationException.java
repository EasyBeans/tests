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
 * $Id: RollbackApplicationException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.exception;

import javax.ejb.ApplicationException;

/**
 * Application exception used to force a rollback in the container.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@ApplicationException(rollback = true)
public class RollbackApplicationException extends Exception{

    /**
     * The serial verion.
     */
    private static final long serialVersionUID = 531375703301732178L;


    /**
     * Creates a new instance of RollbackApplicationException.
     * @param cause the exception cause.
     */
    public RollbackApplicationException(final Throwable cause){
        super(cause);
    }

    /**
     * Creates a new instance of RollbackApplicationException.
     * @param msg the error message.
     */
    public RollbackApplicationException(final String msg) {
       super(msg);
    }
}
