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

package com.fragstealers.ipsedixit.core.impl;

import com.fragstealers.ipsedixit.core.MutableField;
import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.core.MetaDataCreator;
import com.fragstealers.ipsedixit.core.NumberMetaData;
import com.fragstealers.ipsedixit.core.RandomDataProvider;
import com.fragstealers.ipsedixit.core.impl.IntegerFieldHandler;
import static com.fragstealers.ipsedixit.test.CustomTestMethods.supportsType;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class IntegerFieldHandlerUnitTest {

    private IntegerFieldHandler integerFieldHandler;
    private MetaDataCreator<NumberMetaData> metaDataCreator;
    private RandomDataProvider randomDataProvider;
    private NumberMetaData numberMetaData;
    private MutableField mutableField;
    private double minValue;
    private double maxValue;
    private int randomInteger;

    @Before
    public void setup() {
        minValue = new Random().nextInt(100);
        maxValue = new Random().nextInt(100);
        randomInteger = new Random().nextInt(100);
        metaDataCreator = createMock(MetaDataCreator.class);
        randomDataProvider = createMock(RandomDataProvider.class);
        numberMetaData = createMock(NumberMetaData.class);
        mutableField = createMock(MutableField.class);
        integerFieldHandler = new IntegerFieldHandler(metaDataCreator, randomDataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(integerFieldHandler.getClass()));
    }

    @Test
    public void supportsInteger() {
        assertTrue(supportsType(mutableField, integerFieldHandler, int.class));
        assertTrue(supportsType(mutableField, integerFieldHandler, Integer.class));
    }

    @Test
    public void generatesRandomInteger() {
        expect(metaDataCreator.getMetaData(mutableField)).andReturn(numberMetaData);
        expect(numberMetaData.getMinValue()).andReturn(minValue);
        expect(numberMetaData.getMaxValue()).andReturn(maxValue);
        expect(randomDataProvider.randomLongInRange((int) minValue, (int) maxValue)).andReturn((long) randomInteger);
        replay(metaDataCreator, numberMetaData, randomDataProvider);
        Object result = integerFieldHandler.getValueFor(mutableField);
        verify(metaDataCreator, numberMetaData, randomDataProvider);
        assertSame(randomInteger, result);
    }
}
