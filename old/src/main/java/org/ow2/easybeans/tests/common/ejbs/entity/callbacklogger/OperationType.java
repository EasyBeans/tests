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
 * $Id: OperationType.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger;

import java.io.Serializable;

/**
 * The operations that are registered in the database.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public enum OperationType implements Serializable {
    /**
     * Indicates that the operation is a resource manager use.
     */
    RESOURCE_MANAGER,

    /**
     * Indicates that the operation is a MessageDrivenContext use.
     */
    MESSAGE_DRIVEN_CONTEXT,

    /**
     * Indicates that the operation is a enterprise bean use.
     */
    ENTERPRISE_BEAN,

    /**
     * Indicates that the operation is a JNDI use.
     */
    JNDI_ACCESS,

    /**
     * Indicates that the operation is a entity manager factory use.
     */
    ENTITY_MANAGER_FACTORY,

    /**
     * Indicates that the operation is a entity manager use.
     */
    ENTITY_MANAGER,

    /**
     * Indicates that the operation is a timer use.
     */
    TIMER,

    /**
     * Indicates that the operation is an user transaction use.
     */
    USER_TRANSACTION,

    /**
     * Indicates that the operation is the getCallerPrincipal() invocation.
     */
    GET_CALLER_PRINCIPAL,

    /**
     * Indicates that the operation is the getRollbackOnly() invocation.
     */
    GET_ROLLBACK_ONLY,

    /**
     * Indicates that the operation is the setRollbackOnly() invocation.
     */
    SET_ROLLBACK_ONLY,

    /**
     * Indicates that the operation is a resource declaration using annotation.
     */
    ANNOTATION_RESOURCE_DECLARATION,

    /**
     * Indicates that the operation is a resource declaration using annotation.
     */
    ANNOTATION_RESOURCES_DECLARATION,

    /**
     * Indicates that the operation is a resource declaration using a deployment
     * descriptor.
     */
    XML_RESOURCE_DECLARATION,

    /**
     * Indicates that the operation is an injection in a field using annotation.
     */
    ANNOTATION_INJECTION_FIELD,

    /**
     * Indicates that the operation is an injection in a method using
     * annotation.
     */
    ANNOTATION_INJECTION_METHOD,

    /**
     * Indicates that the operation is an injection in a field using a
     * deployment descriptor.
     */
    XML_INJECTION_FIELD,

    /**
     * Indicates that the operation is an injection in a method using a
     * deployment descriptor.
     */
    XML_INJECTION_METHOD,

    /**
     * Indicates that the operation is an field injection using annotation
     * overrided by an deployment descriptor.
     */
    OVERRIDE_INJECTION_FIELD,

    /**
     * Indicates that the operation is a method injection using annotation
     * overrided by an deployment descriptor.
     */
    OVERRIDE_INJECTION_METHOD,

    /**
     * Indicates that the operation is undefined.
     */
    UNDEFINED;

    /**
     * Verifies if a String is equal to a value of this enumeration.
     * @param type operation.
     * @param op string representation of the operation.
     * @return true if equals.
     */
    public static boolean isEqual(final OperationType type, final String op) {
        return type.toString().equals(op);
    }

}
