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
 * $Id: SFSBDeployTest.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.basic;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimplePrintMessage;


/**
 * Verifies if the deploy works well when the bean has many methods with the same name.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateful(name = "SFSBDeployTest")
@Remote
public class SFSBDeployTest implements ItfSimplePrintMessage {

    /**
     * The message saved.
     */
    private String strMessage = "";

    /**
     * Sets the message value.
     * @param message value saved.
     */
    public void startup(final String message) {
        strMessage = message;
    }

    /**
     * Sets the message value.The value will be the message1+message2.
     * @param message1 value saved.
     * @param message2 that is concatenated with message1.
     */
    public void startup(final String message1, final String message2) {
        strMessage = message1 + message2;
    }

    /**
     * Gets the message saved.
     * @return the message.
     */
    public String getMessage() {
        return strMessage;
    }

    /**
     * Gets the message default.
     * @return the message default.
     */
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}
