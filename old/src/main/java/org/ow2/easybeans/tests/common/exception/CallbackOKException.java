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
 * $Id: CallbackOKException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.exception;


/**
 * This exception must be used to show a correct behaviour of
 * an life cycle callback method.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class CallbackOKException extends IllegalStateException{

    /**
     *
     */
    private static final long serialVersionUID = 84316203048467415L;
    /**
     * Wrapped exception.
     */
    private Exception e;

    /**
     * Default contructor.
     * @param msg message
     */
    public CallbackOKException(final String msg){
        super(msg);
    }

    /**
     * Wraps other exception.
     * @param msg exception message
     * @param e exception that caused this illegal exception.
     */
    public CallbackOKException(final String msg, final Exception e){
        super(msg + "Wrapped exception:" + e.getClass().getName().toString() + ", " + e.toString());
    }

    /**
     * Returns wrapped exception.
     * @return wrapped exception
     */
    public Exception getWrapped(){
        return e;
    }
}
