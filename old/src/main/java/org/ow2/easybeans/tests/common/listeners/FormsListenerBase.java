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
 * $Id: FormsListenerBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.listeners;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.Form;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfListenerLoggerAccess;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.SLSBListenerLoggerAccess;
import org.ow2.easybeans.tests.common.helper.EJBHelper;

/**
 * Accesses the bean used to register the callback.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public final class FormsListenerBase {

    /**
     * Creates a new instance of FormsListenerBase.
     *
     */
    private FormsListenerBase() {

    }

    /**
     * Inserts the event in the database.
     * @param lifecycleEvent the event name;
     * @param f the form.
     * @param listenerClassName the listener class.
     */
    public static void insertEntity(final CallbackType lifecycleEvent, final Form f, final String listenerClassName) {
        final ItfListenerLoggerAccess slsbFormManager;
        try {
            slsbFormManager = EJBHelper.getBeanRemoteInstance(SLSBListenerLoggerAccess.class,
                    ItfListenerLoggerAccess.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        slsbFormManager.insertCallbackLogger(f.getClass().getName(), lifecycleEvent, listenerClassName, f.getId());
    }
}
