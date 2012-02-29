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

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.Assert;

/**
 * In this bean, we specify @Resource annotation with the env-entry name.
 * but there is no value in ejb-jar.xml so we shouldn't update the value.
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
@Stateless(mappedName = "DefaultEnvEntryBean@Remote")
public class DefaultEnvEntryBean implements IEnvEntry {

    @Resource(name="default/entry-boolean")
    private boolean entryBoolean = false;
    private Boolean entryBooleanSetter = true;

    @Resource(name="default/entry-byte")
    private byte entryByte = 5;
    private Byte entryByteSetter = 5;

    @Resource(name="default/entry-character")
    private char entryCharacter = 'f';
    private Character entryCharacterSetter = 'b';

    @Resource(name="default/entry-string")
    private String entryString = "Florent";
    private String entryStringSetter = "Benoit";

    @Resource(name="default/entry-short")
    private short entryShort = 2;
    private Short entryShortSetter = 3;

    @Resource(name="default/entry-int")
    private int entryInteger = 2503;
    private Integer entryIntegerSetter = 2512;

    @Resource(name="default/entry-long")
    private long entryLong = 2503L;
    private Long entryLongSetter = 2512L;

    @Resource(name="default/entry-float")
    private float entryFloat = 25.03F;
    private Float entryFloatSetter = 25.12F;

    @Resource(name="default/entry-double")
    private double entryDouble = 2503D;
    private Double entryDoubleSetter = 2512D;

    @Resource(name="default/entry-class")
    private Class<DummyEntryClass> entryClass = DummyEntryClass.class;
    private Class<DummyEntryClass> entryClassSetter = DummyEntryClass.class;

    @Resource(name="default/entry-enum")
    private MyEnum entryEnum = MyEnum.FLORENT;
    private MyEnum entryEnumSetter = MyEnum.BENOIT;

    public void checkInjectedFields() {
        Assert.assertFalse(this.entryBoolean);
        Assert.assertTrue(this.entryBooleanSetter);

        Assert.assertEquals(this.entryByte, 5);
        Assert.assertEquals(this.entryByteSetter, Byte.valueOf("5"));

        Assert.assertEquals(this.entryCharacter, 'f');
        Assert.assertEquals(this.entryCharacterSetter, Character.valueOf('b'));

        Assert.assertEquals(this.entryString, "Florent");
        Assert.assertEquals(this.entryStringSetter, "Benoit");

        Assert.assertEquals(this.entryShort, 2);
        Assert.assertEquals(this.entryShortSetter, Short.valueOf("3"));

        Assert.assertEquals(this.entryInteger, 2503);
        Assert.assertEquals(this.entryIntegerSetter, Integer.valueOf(2512));

        Assert.assertEquals(this.entryLong, 2503L);
        Assert.assertEquals(this.entryLongSetter, Long.valueOf(2512L));

        Assert.assertEquals(this.entryFloat, 25.03F);
        Assert.assertEquals(this.entryFloatSetter, Float.valueOf("25.12"));

        Assert.assertEquals(this.entryDouble, 2503D);
        Assert.assertEquals(this.entryDoubleSetter, Double.valueOf("2512"));

        Assert.assertEquals(this.entryEnum, MyEnum.FLORENT);
        Assert.assertEquals(this.entryEnumSetter, MyEnum.BENOIT);

        Assert.assertEquals(this.entryClass, DummyEntryClass.class);
        Assert.assertEquals(this.entryClassSetter, DummyEntryClass.class);


    }

    /**
     * Ensures that lookup in java:module and java:comp is not the same.
     */
    public void checkCompNotEqualsModule() throws NamingException {
        Integer lookupCompValue = (Integer) new InitialContext().lookup("java:module/env/resource/entry-int");
        Assert.assertNotNull(lookupCompValue);
        
        try {
            new InitialContext().lookup("java:comp/env/resource/entry-int");
            throw new NamingException("We shouldn't be able to get the value");
        } catch (NamingException ne) {
            // expected
        }
        
        
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-boolean")
    private void setEntryBooleanSetterMethod(final Boolean entryBooleanSetter) {
        this.entryBooleanSetter = entryBooleanSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-byte")
    private void setEntryByteSetterMethod(final Byte entryByteSetter) {
        this.entryByteSetter = entryByteSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-character")
    private void setEntryCharacterSetterMethod(final Character entryCharacterSetter) {
        this.entryCharacterSetter = entryCharacterSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-string")
    private void setEntryStringSetterMethod(final String entryStringSetter) {
        this.entryStringSetter = entryStringSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-short")
    private void setEntryShortSetterMethod(final Short entryShortSetter) {
        this.entryShortSetter = entryShortSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-int")
    private void setEntryIntegerSetterMethod(final Integer entryIntegerSetter) {
        this.entryIntegerSetter = entryIntegerSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-long")
    private void setEntryLongSetterMethod(final Long entryLongSetter) {
        this.entryLongSetter = entryLongSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-float")
    private void setEntryFloatSetterMethod(final Float entryFloatSetter) {
        this.entryFloatSetter = entryFloatSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-double")
    private void setEntryDoubleSetterMethod(final double entryDoubleSetter) {
        this.entryDoubleSetter = entryDoubleSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-class")
    private void setEntryClassSetterMethod(final Class<DummyEntryClass> entryClassSetter) {
        this.entryClassSetter = entryClassSetter;
    }

    @SuppressWarnings("unused")
    @Resource(name = "default/entry-enum")
    private void setEntryEnumSetterMethod(final MyEnum entryEnumSetter) {
        this.entryEnumSetter = entryEnumSetter;
    }

}
