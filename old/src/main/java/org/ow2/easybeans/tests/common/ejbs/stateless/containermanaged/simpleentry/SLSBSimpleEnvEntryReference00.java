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
 * $Id: SLSBSimpleEnvEntryReference00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.simpleentry;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkSimpleEntry;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry;

/**
 * This bean is used to test the "resource" annotation on the bean class. Resources will be declared in the
 *           environment, but they will not be injected.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name="SLSBSimpleEnvEntryReference00")
@Remote({ItfSimpleEnvEntry.class})
@Resources({@Resource(name = "str00", type = java.lang.String.class),
        @Resource(name = "chr00", type = java.lang.Character.class),
        @Resource(name = "str00", type = java.lang.Integer.class),
        @Resource(name = "int00", type = java.lang.Integer.class),
        @Resource(name = "bol00", type = java.lang.Boolean.class),
        @Resource(name = "dbl00", type = java.lang.Double.class),
        @Resource(name = "bte00", type = java.lang.Byte.class),
        @Resource(name = "shr00", type = java.lang.Short.class),
        @Resource(name = "lng00", type = java.lang.Long.class),
        @Resource(name = "flt00", type = java.lang.Float.class)})
@Resource(name = "fltAlone00", type = java.lang.Float.class)
public class SLSBSimpleEnvEntryReference00 implements ItfSimpleEnvEntry{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkString00() {
        checkSimpleEntry(sessionContext, "str00", ENTRY_STRING);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkCharacter00() {
        checkSimpleEntry(sessionContext, "chr00", ENTRY_CHARACTER);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkInteger00() {
        checkSimpleEntry(sessionContext, "int00", ENTRY_INTEGER);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkBoolean00() {
        checkSimpleEntry(sessionContext, "bol00", ENTRY_BOOLEAN);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkDouble00() {
        checkSimpleEntry(sessionContext, "dbl00", ENTRY_DOUBLE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkByte00() {
        checkSimpleEntry(sessionContext, "bte00", ENTRY_BYTE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkShort00() {
        checkSimpleEntry(sessionContext, "shr00", ENTRY_SHORT);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkLong00() {
        checkSimpleEntry(sessionContext, "lng00", ENTRY_LONG);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkFloat00() {
        //@Resources tes
        checkSimpleEntry(sessionContext, "flt00", ENTRY_FLOAT);
        //@Resource test
        checkSimpleEntry(sessionContext, "fltAlone00", ENTRY_FLOAT);
    }

}
