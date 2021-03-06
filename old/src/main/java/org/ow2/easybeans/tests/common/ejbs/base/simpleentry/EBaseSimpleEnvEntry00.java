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
 * $Id: EBaseSimpleEnvEntry00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.simpleentry;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkSimpleEntry;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry;

/**
 * This class is used to test inheritance and the annotation Resource.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class EBaseSimpleEnvEntry00 implements ItfSimpleEnvEntry {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The default value must be injected by the container.
     */
    @Resource(name = "str00")
    private String strInjection;

    /**
     * The default value must be injected by the container.
     */
    @Resource(name = "chr00")
    public char chrInjection;

    /**
     * The default value must be injected by the container.
     */
    @Resource(name = "int00")
    private int intInjection;

    /**
     * Boolean with FALSE value.
     */
    public boolean bolInjection = false;

    /**
     * Property inicialized by an injection.
     */
    private double dblInjection;

    /**
     * Property inicialized by an injection.
     */
    private byte bteInjection;

    /**
     * Property inicialized by an injection.
     */
    private short shrInjection;

    /**
     * Property inicialized by an injection.
     */
    private long lngInjection;

    /**
     * Property inicialized by an injection.
     */
    private float fltInjection;

    /**
     * The default value must be injected by the container.
     * @param value information value
     */
    @Resource(name = "dbl00")
    public void setDblInjection(final double value) {
        dblInjection = value;
    }

    /**
     * The default value must be injected by the container.
     * @param value information value
     */
    @Resource(name = "bte00")
    private void setBteInjection(final byte value) {
        bteInjection = value;
    }

    /**
     * The default value must be injected by the container.
     * @param value information value
     */
    @Resource(name = "shr00")
    private void setShrInjection(final short value) {
        shrInjection = value;
    }

    /**
     * The default value must be injected by the container.
     * @param value information value
     */
    @Resource(name = "lng00")
    protected void setLngInjection(final long value) {
        lngInjection = value;
    }

    /**
     * The default value must be injected by the container. The name must be
     * generated by the container: class name/field name.
     * @param value information value
     */
    @Resource
    void setFltInjection(final float value) {
        fltInjection = value;
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkString00() {
        checkSimpleEntry(sessionContext, "str00", strInjection, ENTRY_STRING);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void checkCharacter00() {
        checkSimpleEntry(sessionContext, "chr00", chrInjection, ENTRY_CHARACTER);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void checkInteger00() {
        checkSimpleEntry(sessionContext, "int00", intInjection, ENTRY_INTEGER);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void checkBoolean00() {
        if (bolInjection) {
            throw new IllegalStateException("The value should be false.");
        }
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void checkDouble00() {
        checkSimpleEntry(sessionContext, "dbl00", dblInjection, ENTRY_DOUBLE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void checkByte00() {
        checkSimpleEntry(sessionContext, "bte00", bteInjection, ENTRY_BYTE);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    @SuppressWarnings("boxing")
    public void checkShort00() {
        checkSimpleEntry(sessionContext, "shr00", shrInjection, ENTRY_SHORT);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry The
     *      setter method has protected modifier.
     */
    @SuppressWarnings("boxing")
    public void checkLong00() {
        checkSimpleEntry(sessionContext, "lng00", lngInjection, ENTRY_LONG);
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry The
     *      setter method has package modifier.
     */
    @SuppressWarnings("boxing")
    public void checkFloat00() {
        checkSimpleEntry(sessionContext, EBaseSimpleEnvEntry00.class.getName().toString() + "/" + "fltInjection",
                fltInjection, ENTRY_FLOAT);
    }

}
