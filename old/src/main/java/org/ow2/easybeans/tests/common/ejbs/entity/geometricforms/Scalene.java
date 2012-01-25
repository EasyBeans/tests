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
 * $Id: Scalene.java 5369 2010-02-24 14:58:19Z benoitf $
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
 * Representes a triangle scalene. Overrides the callback methods, but does not specify a lifecycle event in the methdos.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@TableGenerator(name  = "ID_SEQ", allocationSize = 1)
public class Scalene extends Triangle {

    /**
     * Value appended in the overriden callback method that must not be called.
     */
    public static final String WRONG_CALL = "WRONG";

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
     * Overrides the PrePersist method, but does not specify it as a PrePersist
     * event. So the container must no call this method.
     */
    @Override
    public void prePersist() {
        FormsListenerBase.insertEntity(CallbackType.PRE_PERSIST, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Overrides the PostPersist method, but does not specify it as a PostPersist
     * event. So the container must no call this method.
     */
    @Override
    public void postPersist() {
        FormsListenerBase.insertEntity(CallbackType.POST_PERSIST, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Overrides the PreRemove method, but does not specify it as a PreRemove
     * event. So the container must no call this method.
     */
    @Override
    public void preRemove() {
        FormsListenerBase.insertEntity(CallbackType.PRE_REMOVE, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Overrides the PostRemove method, but does not specify it as a PostRemove
     * event. So the container must no call this method.
     */
    @Override
    public void postRemove() {
        FormsListenerBase.insertEntity(CallbackType.POST_REMOVE, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Overrides the PreUpdatet method, but does not specify it as a PreUpdate
     * event. So the container must no call this method.
     */
    @Override
    public void preUpdate() {
        FormsListenerBase.insertEntity(CallbackType.PRE_UPDATE, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Overrides the PostLoad method, but does not specify it as a PostLoad
     * event. So the container must no call this method.
     */
    @Override
    public void postLoad() {
        FormsListenerBase.insertEntity(CallbackType.POST_LOAD, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Overrides the PostUpdate method, but does not specify it as a PostUpdate
     * event. So the container must no call this method.
     */
    @Override
    public void postUpdate() {
        FormsListenerBase.insertEntity(CallbackType.POST_UPDATE, this, this.getClass().getName() + WRONG_CALL);
    }

    /**
     * Specifies the PrePersist callback method.
     *
     */
    @PrePersist
    public void prePersist1() {
        FormsListenerBase.insertEntity(CallbackType.PRE_PERSIST, this, this.getClass().getName());
    }

    /**
     * Specifies the PostPersist callback method.
     *
     */
    @PostPersist
    public void postPersist1() {
        FormsListenerBase.insertEntity(CallbackType.POST_PERSIST, this, this.getClass().getName());
    }

    /**
     * Specifies the PreRemove callback method.
     *
     */
    @PreRemove
    public void preRemove1() {
        FormsListenerBase.insertEntity(CallbackType.PRE_REMOVE, this, this.getClass().getName());
    }

    /**
     * Specifies the PostRemove callback method.
     *
     */
    @PostRemove
    public void postRemove1() {
        FormsListenerBase.insertEntity(CallbackType.POST_REMOVE, this, this.getClass().getName());
    }

    /**
     * Specifies the PreUpdate callback method.
     *
     */
    @PreUpdate
    public void preUpdate1() {
        FormsListenerBase.insertEntity(CallbackType.PRE_UPDATE, this, this.getClass().getName());
    }

    /**
     * Specifies the PostLoad callback method.
     *
     */
    @PostLoad
    public void postLoad1() {
        FormsListenerBase.insertEntity(CallbackType.POST_LOAD, this, this.getClass().getName());
    }

    /**
     * Specifies the PostUpdate callback method.
     *
     */
    @PostUpdate
    public void postUpdate1() {
        FormsListenerBase.insertEntity(CallbackType.POST_UPDATE, this, this.getClass().getName());
    }
}
