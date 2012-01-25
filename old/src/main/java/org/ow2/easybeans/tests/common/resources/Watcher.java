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
 * $Id: Watcher.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

/**
 * This class is used to support the timeout callback method tests.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class Watcher {

    /**
     * Exception stored by the watcher.
     */
    private Exception exp;

    /**
     * Class instance.
     */
    private static Watcher instance;

    /**
     * Gets the instance of this class.
     * @return instance
     */
    public static Watcher getInstance(){
        if (instance==null){
            instance = new Watcher();
        }
        return instance;
    }


    /**
     * Gets the exception.
     * @return Returns the exp.
     */
    public synchronized Exception getException() {
        return exp;
    }


    /**
     * Sets the exception.
     * @param e The exception to set.
     */
    public synchronized void setException(final Exception e) {
        this.exp = e;
    }

}
