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
 * $Id: OperationLogger.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger;

import java.text.MessageFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Makes the log of the operations.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Entity
@DiscriminatorValue("operation")
public class OperationLogger extends CallbackLogger {

    /**
     * Class ID.
     */
    private static final long serialVersionUID = 6121867108408882235L;

    /**
     * The identifier of the operation.
     */
    private OperationType opType;

    /**
     * The operation description.
     */
    private String opDescription;

    /**
     * Gets the operation type.
     * @return the operation type.
     */
    @Enumerated(EnumType.STRING)
    public OperationType getOperationType() {
        return opType;
    }

    /**
     * Sets the operation type.
     * @param operationType the operation type.
     */
    public void setOperationType(final OperationType operationType) {
        this.opType = operationType;
    }

    /**
     * Gets the operation description.
     * @return the operation description.
     */
    public String getDescription() {
        return opDescription;
    }

    /**
     * Sets the operation description.
     * @param description the operation description.
     */
    public void setDescription(final String description) {
        this.opDescription = description;
    }

    /**
     * String representation of the object.
     * @return object representation
     */
    @SuppressWarnings("boxing")
    @Override
    public String toString() {
        return MessageFormat.format("{0}, ID = {1}, ClassName = {2}, CallbackClassName = {3}, CallbackEvent = {4}, "
                + "OperationType = {5}, Description = {6}, Date = {7}", OperationLogger.class.getName(), getId(),
                getClassName(), getCallbackClassName(), getCallbackEvent(), getOperationType(), getDescription(),
                getInsertionDate());
    }

}
