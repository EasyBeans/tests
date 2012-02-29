/**
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
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
 * $Id: EnvEntryBean.java 5743 2011-02-28 16:02:42Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.enventry;

import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.Assert;

/**
 * This beans inject some env-entry reference but without specifying the type
 * in ejb-jar.xml as it's set by injection-target
 * 
 *  The following are the legal values of env-entry-type-valuesType:
 *      java.lang.Boolean
 *      java.lang.Byte
 *      java.lang.Character
 *      java.lang.String
 *      java.lang.Short
 *      java.lang.Integer
 *      java.lang.Long
 *      java.lang.Float
 *      java.lang.Double
 *      java.lang.Class
 *      any enumeration type (i.e. a subclass of java.lang.Enum)
 * @author Florent Benoit
 */
@Singleton(mappedName = "NoTypeEnvEntryBean@Remote")
public class NoTypeEnvEntryBean implements IEnvEntry {

    private boolean entryBoolean;
    private Boolean entryBooleanSetter;

    private byte entryByte;
    private Byte entryByteSetter;

    private char entryCharacter;
    private Character entryCharacterSetter;

    private String entryString;
    private String entryStringSetter;

    private short entryShort;
    private Short entryShortSetter;

    private int entryInteger;
    private Integer entryIntegerSetter;

    private long entryLong;
    private Long entryLongSetter;

    private float entryFloat;
    private Float entryFloatSetter;

    private double entryDouble;
    private Double entryDoubleSetter;

    private Class<DummyEntryClass> entryClass;
    private Class<DummyEntryClass> entryClassSetter;

    private MyEnum entryEnum;
    private MyEnum entryEnumSetter;

    public void checkInjectedFields() {
        Assert.assertTrue(this.entryBoolean);
        Assert.assertTrue(this.entryBooleanSetter);

        Assert.assertEquals(this.entryByte, 1);
        Assert.assertEquals(this.entryByteSetter, Byte.valueOf("1"));

        Assert.assertEquals(this.entryCharacter, 'f');
        Assert.assertEquals(this.entryCharacterSetter, Character.valueOf('f'));

        Assert.assertEquals(this.entryString, "Florent");
        Assert.assertEquals(this.entryStringSetter, "Florent");

        Assert.assertEquals(this.entryShort, 25);
        Assert.assertEquals(this.entryShortSetter, Short.valueOf("25"));

        Assert.assertEquals(this.entryInteger, 2503);
        Assert.assertEquals(this.entryIntegerSetter, Integer.valueOf(2503));

        Assert.assertEquals(this.entryLong, 10L);
        Assert.assertEquals(this.entryLongSetter, Long.valueOf(10));

        Assert.assertEquals(this.entryFloat, 25.03F);
        Assert.assertEquals(this.entryFloatSetter, Float.valueOf("25.03"));

        Assert.assertEquals(this.entryDouble, 2503D);
        Assert.assertEquals(this.entryDoubleSetter, Double.valueOf("2503"));

        Assert.assertEquals(this.entryEnum, MyEnum.FLORENT);
        Assert.assertEquals(this.entryEnumSetter, MyEnum.FLORENT);

        Assert.assertEquals(this.entryClass, DummyEntryClass.class);
        Assert.assertEquals(this.entryClassSetter, DummyEntryClass.class);


    }

    /**
     * Ensures that lookup in java:module and java:comp is not the same.
     */
    public void checkCompNotEqualsModule() throws NamingException {
        Integer lookupModuleValue = (Integer) new InitialContext().lookup("java:comp/env/notype/entry-int");
        Integer lookupCompValue = (Integer) new InitialContext().lookup("java:module/env/notype/entry-int");

        Assert.assertNotSame(lookupModuleValue, lookupCompValue);
    }

    @SuppressWarnings("unused")
    private void setEntryBooleanSetterMethod(final Boolean entryBooleanSetter) {
        this.entryBooleanSetter = entryBooleanSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryByteSetterMethod(final Byte entryByteSetter) {
        this.entryByteSetter = entryByteSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryCharacterSetterMethod(final Character entryCharacterSetter) {
        this.entryCharacterSetter = entryCharacterSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryStringSetterMethod(final String entryStringSetter) {
        this.entryStringSetter = entryStringSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryShortSetterMethod(final Short entryShortSetter) {
        this.entryShortSetter = entryShortSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryIntegerSetterMethod(final Integer entryIntegerSetter) {
        this.entryIntegerSetter = entryIntegerSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryLongSetterMethod(final Long entryLongSetter) {
        this.entryLongSetter = entryLongSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryFloatSetterMethod(final Float entryFloatSetter) {
        this.entryFloatSetter = entryFloatSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryDoubleSetterMethod(final double entryDoubleSetter) {
        this.entryDoubleSetter = entryDoubleSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryClassSetterMethod(final Class<DummyEntryClass> entryClassSetter) {
        this.entryClassSetter = entryClassSetter;
    }

    @SuppressWarnings("unused")
    private void setEntryEnumSetterMethod(final MyEnum entryEnumSetter) {
        this.entryEnumSetter = entryEnumSetter;
    }

}
