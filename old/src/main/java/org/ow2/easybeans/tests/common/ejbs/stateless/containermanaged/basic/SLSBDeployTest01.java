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
 * $Id: SLSBDeployTest01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimplePrintMessage;

/**
 * Bean that does not implements the remote interface.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless(name = "SLSBDeployTest01")
@Remote(ItfSimplePrintMessage.class)
public class SLSBDeployTest01 {

    /**
     * Default message.
     */
    public static final String MESSAGE = "Hi";

    /**
     * Does nothing.
     * @param message -.
     */
    public void startup(final String message) {

    }

    /**
     * Does nothing.
     * @param message1 -.
     * @param message2 -.
     */
    public void startup(final String message1, final String message2) {

    }

    /**
     * Gets the message default.
     * @return the message.
     */
    public String getMessage() {
        return MESSAGE;
    }

    /**
     * Gets the message default.
     * @return the message default.
     */
    public String getDefaultMessage() {
        return MESSAGE;
    }
}

