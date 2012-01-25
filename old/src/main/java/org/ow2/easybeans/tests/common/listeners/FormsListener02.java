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
 * $Id: FormsListener02.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.listeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.geometricforms.Form;

/**
 * An entity listener.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
public class FormsListener02{

    /**
     * Inserts in the database that the PrePersist method was called.
     * @param f the form.
     */
    @PrePersist
    public void prePersist(final Form f) {
       FormsListenerBase.insertEntity(CallbackType.PRE_PERSIST, f,  this.getClass().getName());
    }

    /**
     * Inserts in the database that the PostPersist method was called.
     * @param f the form.
     */
    @PostPersist
    public void postPersist(final Form f) {
        FormsListenerBase.insertEntity(CallbackType.POST_PERSIST, f,  this.getClass().getName());
    }

    /**
     * Inserts in the database that the PreRemove method was called.
     * @param f the form.
     */
    @PreRemove
    public void preRemove(final Form f) {
        FormsListenerBase.insertEntity(CallbackType.PRE_REMOVE, f,  this.getClass().getName());
    }

    /**
     * Inserts in the database that the PostRemove method was called.
     * @param f the form.
     */
    @PostRemove
    public void postRemove(final Form f) {
        FormsListenerBase.insertEntity(CallbackType.POST_REMOVE, f,  this.getClass().getName());
    }

    /**
     * Inserts in the database that the PreUpdate method was called.
     * @param f the form.
     */
    @PreUpdate
    public void preUpdate(final Form f) {
        FormsListenerBase.insertEntity(CallbackType.PRE_UPDATE, f,  this.getClass().getName());
    }

    /**
     * Inserts in the database that the PostUpdate method was called.
     * @param f the form.
     */
    @PostUpdate
    public void postUpdate(final Form f) {
        FormsListenerBase.insertEntity(CallbackType.POST_UPDATE, f,  this.getClass().getName());
    }

    /**
     * Inserts in the database that the PostLoad method was called.
     * @param f the form.
     */
    @PostLoad
    public void postLoad(final Form f) {
        FormsListenerBase.insertEntity(CallbackType.POST_LOAD, f,  this.getClass().getName());
    }
}
