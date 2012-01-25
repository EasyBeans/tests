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
 * $Id: Triangle.java 5369 2010-02-24 14:58:19Z benoitf $
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
 * Represents the triangle. Defines the callback methods in the class.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@TableGenerator(name  = "ID_SEQ", allocationSize = 1)
public class Triangle extends Form {

    /**
     * The triangle height.
     */
    private float height;

    /**
     * The triangle base.
     */
    private float base;

    /**
     * Gets the triangle base.
     * @return the base.
     */
    public float getBase() {
        return base;
    }

    /**
     * Sets the triangle base.
     * @param base the base.
     */
    public void setBase(final float base) {
        this.base = base;
    }

    /**
     * Gets the triangle height.
     * @return the height.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the triangle height.
     * @param height the height.
     */
    public void setHeight(final float height) {
        this.height = height;
    }

    /**
     * Inserts in the lifecycle callback logger that the PrePersist callback
     * method was called.
     */
    @PrePersist
    public void prePersist() {
        FormsListenerBase.insertEntity(CallbackType.PRE_PERSIST, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostPersist callback
     * method was called.
     */
    @PostPersist
    public void postPersist() {
        FormsListenerBase.insertEntity(CallbackType.POST_PERSIST, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostRemove callback
     * method was called.
     */
    @PreRemove
    public void preRemove() {
        FormsListenerBase.insertEntity(CallbackType.POST_REMOVE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostRemove callback
     * method was called.
     */
    @PostRemove
    public void postRemove() {
        FormsListenerBase.insertEntity(CallbackType.POST_REMOVE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PreUpdate callback
     * method was called.
     */
    @PreUpdate
    public void preUpdate() {
        FormsListenerBase.insertEntity(CallbackType.PRE_UPDATE, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostLoad callback
     * method was called.
     */
    @PostLoad
    public void postLoad() {
        FormsListenerBase.insertEntity(CallbackType.POST_LOAD, this, this.getClass().getName());
    }

    /**
     * Inserts in the lifecycle callback logger that the PostUpdate callback
     * method was called.
     */
    @PostUpdate
    public void postUpdate() {
        FormsListenerBase.insertEntity(CallbackType.POST_UPDATE, this, this.getClass().getName());
    }
}
