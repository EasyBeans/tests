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
 * $Id: CallbackLoggerComparator.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger;

import java.util.Comparator;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackLogger;


/**
 * Compares two interceptor method invocation by the insertion time.
 * @param <T> type
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class CallbackLoggerComparator <T extends CallbackLogger> implements Comparator <CallbackLogger> {

    /**
     * Compares the callback interceptor method invocation by insertion time.
     * @param callback1 the first callback interceptor method.
     * @param callback2 the second callback interceptor method.
     * @return a negative value if the insertion time for the callback1 is less
     *         than callback2, a positive value if the insertion time for the
     *         callback1 is greater than callback2 and a zero if the insertion
     *         time for the callback1 is equal to callback2.
     */
    public int compare(final CallbackLogger callback1, final CallbackLogger callback2) {
        if (callback1.getInsertionDate() < callback2.getInsertionDate()) {
            return -1;
        } else if (callback1.getInsertionDate() > callback2.getInsertionDate()) {
            return 1;
        } else {
            return 0;
        }
    }

}
