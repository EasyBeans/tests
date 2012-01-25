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
 * $Id: ItfSimplePrintMessage.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

/**
 * Stores a message and returns the value stored.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public interface ItfSimplePrintMessage {
    /**
     * Default message.
     */
     String DEFAULT_MESSAGE = "Hello World";

    /**
     * Sets the message value.
     * @param message value saved.
     */
    void startup(final String message);

    /**
     * Sets the message value.The value will be the message1+message2.
     * @param message1 value saved.
     * @param message2 that is concatenated with message1.
     */
    void startup(final String message1, final String message2);

    /**
     * Gets the message saved.
     * @return the message.
     */
    String getMessage();

    /**
     * Gets the message default.
     * @return the message default.
     */
    String getDefaultMessage();

}
