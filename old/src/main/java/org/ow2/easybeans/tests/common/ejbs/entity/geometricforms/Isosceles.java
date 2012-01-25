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
 * $Id: Isosceles.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.geometricforms;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.TableGenerator;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.listeners.FormsListenerBase;

/**
 * Represents an isosceles triangle. Overrides the callback methods, but changes
 * the method name associated with the callback event.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
@TableGenerator(name  = "ID_SEQ", allocationSize = 1)
public class Isosceles extends Triangle {

    /**
     * A dummy field, just to have some field in this class.
     */
    private int dummyField;

    /**
     * Gets the dummy field.
     * @return the dummy field.
     */
    public int getDummyField() {
        return dummyField;
    }

    /**
     * Sets the dummy field.
     * @param dummyField the dummy field.
     */
    public void setDummyField(final int dummyField) {
        this.dummyField = dummyField;
    }

    /**
     * Inserts in the lifecycle callback logger that the PostPersist callback
     * method was called.
     */
    @PostPersist
    @Override
    public void prePersist() {
        FormsListenerBase.insertEntity(CallbackType.POST_PERSIST, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PrePersist callback
     * method was called.
     */
    @Override
    @PrePersist
    public void postPersist() {
        FormsListenerBase.insertEntity(CallbackType.PRE_PERSIST, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostRemove callback
     * method was called.
     */
    @PostRemove
    @Override
    public void preRemove() {
        FormsListenerBase.insertEntity(CallbackType.POST_REMOVE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PreRemove callback
     * method was called.
     */
    @PreRemove
    @Override
    public void postRemove() {
        FormsListenerBase.insertEntity(CallbackType.PRE_REMOVE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostUpdate callback
     * method was called.
     */
    @PostUpdate
    @Override
    public void preUpdate() {
        FormsListenerBase.insertEntity(CallbackType.POST_UPDATE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PreUpdate callback
     * method was called.
     */
    @PreUpdate
    @Override
    public void postLoad() {
        FormsListenerBase.insertEntity(CallbackType.PRE_UPDATE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostLoad callback
     * method was called.
     */
    @PostLoad
    @Override
    public void postUpdate() {
        FormsListenerBase.insertEntity(CallbackType.POST_LOAD, this, this.getClass().getName());
    }
}
