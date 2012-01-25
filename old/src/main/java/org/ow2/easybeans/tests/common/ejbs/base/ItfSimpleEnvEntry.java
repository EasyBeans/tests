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
 * $Id: ItfSimpleEnvEntry.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

/**
 * This class is used to test the annotations and environment access to simple
 * environment entries using annotations, deployment descriptors or combination
 * of both.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public interface ItfSimpleEnvEntry {

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    String ENTRY_STRING = new String("STRING00");

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    String ENTRY_STRING_1 = new String("STRING01");

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Character ENTRY_CHARACTER = new Character('A');

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Character ENTRY_CHARACTER_1 = new Character('B');
    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Integer ENTRY_INTEGER = new Integer(12);

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Boolean ENTRY_BOOLEAN = new Boolean(true);

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Double ENTRY_DOUBLE = new Double(100);

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Byte ENTRY_BYTE = new Byte("2");

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Byte ENTRY_BYTE_1 = new Byte("3");

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Short ENTRY_SHORT = new Short("200");

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Short ENTRY_SHORT_1 = new Short("201");

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Long ENTRY_LONG = new Long(300);

    /**
     * This constant has an value to be compared with the injected value or
     * obtained value from the environment.
     */
    Float ENTRY_FLOAT = new Float(400);

    /**
     * Checks if a String was value correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkString00();

    /**
     * Checks if a character value was correctly injected or if it can be accessed from
     * the environment using the session context and the JNDI API. If the
     * comparison between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkCharacter00();

    /**
     * Checks if an integer value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkInteger00();

    /**
     * Checks if a boolean value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkBoolean00();

    /**
     * Checks if a double value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkDouble00();

    /**
     * Checks if a byte value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkByte00();

    /**
     * Checks if a short value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkShort00();

    /**
     * Checks if a long value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkLong00();

    /**
     * Checks if a float value was correctly injected or if it can be accessed from the
     * environment using the session context and the JNDI API. If the comparison
     * between the constant value and the obtained value fails an
     * IllegalStateException is thrown.
     */
    void checkFloat00();
}
