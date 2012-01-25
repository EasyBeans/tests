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
 * $Id: ListenerManagerComparator.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners;

import java.util.Comparator;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.ListenerLogger;

/**
 * Compares two listeners by the insertion time.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 * @param <T>
 */
public class ListenerManagerComparator <T extends ListenerLogger> implements Comparator <ListenerLogger> {

    /**
     * Compares the listeners isertion time.
     * @param listener1 the first listener.
     * @param listener2 the second listener.
     * @return a negative value if the insertion time for the listener1 is less
     *         than listener2, a positive value if the insertion time for the
     *         listener1 is greater than listener2 and a zero if the insertion
     *         time for the listener1 is equal to listener2.
     */
    public int compare(final ListenerLogger listener1, final ListenerLogger listener2) {
        if (listener1.getInsertionDate() < listener2.getInsertionDate()) {
            return -1;
        } else if (listener1.getInsertionDate() > listener2.getInsertionDate()) {
            return 1;
        } else {
            return 0;
        }
    }


}
