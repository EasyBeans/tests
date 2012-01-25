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
 * $Id: ItfSimpleEnvEntryByDescriptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

/**
 * This interface is used to test the injection of simple environment entries using
 * annotations or the combination with deployment descriptors.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public interface ItfSimpleEnvEntryByDescriptor {

    /**
     * This constant is used to verify if the container does not inject a value
     * when the application assembler or deployer does not specifies a new value to
     * override the field value declared in the bean class.
     */
    String DECLARED_STRING = "Declared";

    /**
     * This constant has an value to be compared with the injected value.
     */
    String INJECTED_STRING = new String("STRING00");

    /**
     * Checks if a string was correctly injected. The
     * deployment descriptor doesn't have a default value, so the value defined
     * in the variable declaration must not be overridden.
     */
    void checkNotOverride00();

    /**
     * Checks if a string was correctly injected in the environment using the deployment descriptor without annotations.
     */
    void checkStringInjection00();

}
