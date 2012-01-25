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
 * $Id: SLSBSimpleEnvEntryInheritance.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.simpleentry;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkSimpleEntry;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry;
import org.ow2.easybeans.tests.common.ejbs.base.simpleentry.EBaseSimpleEnvEntry00;


/**
 * This bean is used to test annotation resource with inheritance.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless(name="SLSBSimpleEnvEntryInheritance")
@Remote(ItfSimpleEnvEntry.class)
public class SLSBSimpleEnvEntryInheritance extends EBaseSimpleEnvEntry00{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The default value must be injected by the container.
     */
    @Resource(name = "str01")
    private String strInjection;

    /**
     * This value overrides the inherited field.
     */
    @Resource(name = "chr01")
    public char chrInjection;

    /**
     * The default value must be injected by the container.
     */
    @Resource(name = "bol00")
    public boolean bolInjection;

    /**
     * Property inicialized by an injection.
     */
    private byte bteInjection;

    /**
     * Property inicialized by an injection.
     */
    private short shrInjection;

    /**
     * The default value must be injected by the container.
     * @param value information value
     */
    @Resource(name = "bte01")
    private void setBteInjection(final byte value){
        bteInjection = value;
    }

    /**
     * The default value must be injected by the container.
     * @param value information value
     */
    @Resource(name = "shr01")
    protected void setShrInjection(final short value){
        shrInjection = value;
    }

    /**
     * Checks if the injection is working in this class and in the superclass.
     */
    @Override
    public void checkString00() {
        checkSimpleEntry(sessionContext, "str01", strInjection, ENTRY_STRING_1);
        super.checkString00();
    }

    /**
     * Tests if the overriding is working. The value must be the ENTRY_CHARACTER_1 value.
     */
    @SuppressWarnings("boxing")
    @Override
    public void checkCharacter00(){
        checkSimpleEntry(sessionContext, "chr01", chrInjection, ENTRY_CHARACTER_1);
        super.checkCharacter00();
    }

    /**
     * Tests if the resource annotation is working in the superclass.
     */
    @SuppressWarnings("boxing")
    @Override
    public void checkInteger00(){
        super.checkInteger00();
    }

    /**
     * Tests if the resource annotation is working in this class and if the value is not overridded in the superclass.
     */
    @SuppressWarnings("boxing")
    @Override
    public void checkBoolean00(){
        checkSimpleEntry(sessionContext, "bol00", bolInjection, ENTRY_BOOLEAN);
        super.checkBoolean00();
    }

    /**
     * Tests if the resource annotation is working in the superclass.
     */
    @SuppressWarnings("boxing")
    @Override
    public void checkDouble00(){
        super.checkDouble00();
    }

    /**
     * Tests if the resource annotation is working in this class and if the value is not overridded in the superclass.
     */
    @SuppressWarnings("boxing")
    @Override
    public void checkByte00(){
        checkSimpleEntry(sessionContext, "bte01", bteInjection, ENTRY_BYTE_1);
        super.checkByte00();
    }

    /**
     * Tests if the resource annotation is working in this class and if the value is not overridded in the superclass.
     */
    @SuppressWarnings("boxing")
    @Override
    public void checkShort00(){
        checkSimpleEntry(sessionContext, "shr01", shrInjection, ENTRY_SHORT_1);
        super.checkShort00();
    }

}
