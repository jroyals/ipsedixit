/*
 *  Copyright 2008 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.sf.ipsedixit.core.impl;

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.RandomDataProvider;
import net.sf.ipsedixit.core.impl.CharacterFieldHandler;
import static net.sf.ipsedixit.test.CustomTestMethods.supportsType;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class CharacterFieldHandlerUnitTest {

    private CharacterFieldHandler characterFieldHandler;
    private MetaDataCreator<NumberMetaData> metaDataCreator;
    private RandomDataProvider randomDataProvider;
    private NumberMetaData numberMetaData;
    private MutableField mutableField;
    private double minValue;
    private double maxValue;
    private char randomChar;

    @Before
    public void setup() {
        minValue = new Random().nextInt(100);
        maxValue = new Random().nextInt(100);
        randomChar = (char) new Random().nextInt(100);
        metaDataCreator = createMock(MetaDataCreator.class);
        randomDataProvider = createMock(RandomDataProvider.class);
        numberMetaData = createMock(NumberMetaData.class);
        mutableField = createMock(MutableField.class);
        characterFieldHandler = new CharacterFieldHandler(metaDataCreator, randomDataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(characterFieldHandler.getClass()));
    }

    @Test
    public void supportsCharacter() {
        assertTrue(supportsType(mutableField, characterFieldHandler, char.class));
        assertTrue(supportsType(mutableField, characterFieldHandler, Character.class));
    }

    @Test
    public void generatesRandomCharacter() {
        expect(metaDataCreator.getMetaData(mutableField)).andReturn(numberMetaData);
        expect(numberMetaData.getMinValue()).andReturn(minValue);
        expect(numberMetaData.getMaxValue()).andReturn(maxValue);
        expect(randomDataProvider.randomLongInRange((char) minValue, (char) maxValue)).andReturn((long) randomChar);
        replay(metaDataCreator, numberMetaData, randomDataProvider);
        Object result = characterFieldHandler.getValueFor(mutableField);
        verify(metaDataCreator, numberMetaData, randomDataProvider);
        assertSame(randomChar, result);
    }
}