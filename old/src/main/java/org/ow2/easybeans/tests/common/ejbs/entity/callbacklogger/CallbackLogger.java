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
 * $Id: CallbackLogger.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Used to log the callback events.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@Table(name = "CALLBACKLOGGER")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Type")
@TableGenerator(name = "MANAGER_SEQ", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "findAll", query = "SELECT e FROM CallbackLogger e "),
        @NamedQuery(name = "findLifecycleEvent", query = "SELECT e FROM CallbackLogger e WHERE e.className = :className AND"
                + " e.callbackEvent= :event"),
        @NamedQuery(name = "findLifecycleEventByClass", query = "SELECT e FROM CallbackLogger e WHERE e.className = :className"),
        @NamedQuery(name = "findLifecycleEventByCallbackMethod", query = "SELECT e FROM CallbackLogger e "
                + "WHERE e.className = :className AND e.callbackClassName= :callbackClassName AND e.callbackEvent= :event")})
public class CallbackLogger implements Serializable{

    /**
     * Class ID.
     */
    private static final long serialVersionUID = 5508063145575159629L;

    /**
     * The logger identifier.
     */
    private int id;

    /**
     * The class for which the callback method was called.
     */
    private String className;

  /**
     * The callback event.
     */
   private CallbackType callbackEvent;

    /**
     * The time in milliseconds when the callback event was called.
     */
    private long insertionDate;

    /**
     * The class that contains the method callback.
     */
    private String callbackClassName;

    /**
     * Gets the name of the class that has the callback method.
     * @return the class name.
     */
    public String getCallbackClassName() {
        return callbackClassName;
    }

    /**
     * Sets the cname of the class that has the callback method.
     * @param callbackClassName the class name.
     */
    public void setCallbackClassName(final String callbackClassName) {
        this.callbackClassName = callbackClassName;
    }

    /**
     * Gets the callback event type.
     * @return the callback event type.
     */
    @Enumerated(EnumType.STRING)
    public CallbackType getCallbackEvent() {
         return callbackEvent;
    }

    /**
     * Sets the callback event type.
     * @param callbackEvent the callback event.
     */
    public void setCallbackEvent(final CallbackType callbackEvent) {
        this.callbackEvent = callbackEvent;
    }

    /**
     * Gets the name of the class that was intercepted by the callback method.
     * @return the class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the name of the class that was intercepted by the callback method.
     * @param className the class name.
     */
    public void setClassName(final String className) {
        this.className = className;
    }

    /**
     * Gets the entity identifier.
     * @return the identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MANAGER_SEQ")
    public int getId() {
        return id;
    }

    /**
     * Sets the entity identifier.
     * @param id the identifier.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the time in millisencodes in which the entity was inserted in the database.
     * @return the time.
     */
    public long getInsertionDate() {
        return insertionDate;
    }

    /**
     * Gets the time in millisencodes in which the entity is inserted in the database.
     * @param insertionDate the date.
     */
    public void setInsertionDate(final long insertionDate) {
        this.insertionDate = insertionDate;
    }

    /**
     * String representation of the object.
     * @return object representation
     */
    @SuppressWarnings("boxing")
    @Override
    public String toString(){
        return MessageFormat.format("{0}, ID = {1}, ClassName = {2}, CallbackClassName = {3}, CallbackEvent = {4}, Date = {5}",
                CallbackLogger.class.getName(), id, className, callbackClassName, callbackEvent, insertionDate);
    }
}
