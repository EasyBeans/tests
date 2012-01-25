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
 * $Id: ListenerLogger.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * Makes the log of the listeners events.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
@DiscriminatorValue("listener")
public class ListenerLogger extends CallbackLogger {

    /**
     * Class ID.
     */
    private static final long serialVersionUID = -2136670571679866782L;

    /**
     * The identifier of the entity that the event was called.
     */
    private int entityId;

    /**
     * Gets the identifier of the entity that the event was called.
     * @return the entity identifier.
     */
    public int getEntityId() {
        return entityId;
    }

    /**
     * Sets the identifier of the entity that the event was called.
     * @param entityId the entity identifier.
     */
    public void setEntityId(final int entityId) {
        this.entityId = entityId;
    }

}
