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
import net.sf.ipsedixit.core.DataProvider;
import static net.sf.ipsedixit.test.CustomTestMethods.supportsType;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class FloatFieldHandlerUnitTest {

    private FloatFieldHandler floatFieldHandler;
    private MetaDataCreator<NumberMetaData> metaDataCreator;
    private DataProvider dataProvider;
    private NumberMetaData numberMetaData;
    private MutableField mutableField;
    private double minValue;
    private double maxValue;
    private float randomFloat;

    @Before
    public void setup() {
        minValue = new Random().nextInt(100);
        maxValue = new Random().nextInt(100);
        randomFloat = (float) Math.random() * 100;
        metaDataCreator = createMock(MetaDataCreator.class);
        dataProvider = createMock(DataProvider.class);
        numberMetaData = createMock(NumberMetaData.class);
        mutableField = createMock(MutableField.class);
        floatFieldHandler = new FloatFieldHandler(metaDataCreator, dataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(floatFieldHandler.getClass()));
    }

    @Test
    public void supportsFloat() {
        assertTrue(supportsType(mutableField, floatFieldHandler, float.class));
        assertTrue(supportsType(mutableField, floatFieldHandler, Float.class));
    }

    @Test
    public void generatesRandomFloat() {
        expect(metaDataCreator.getMetaData(mutableField)).andReturn(numberMetaData);
        expect(numberMetaData.getMinValue()).andReturn(minValue);
        expect(numberMetaData.getMaxValue()).andReturn(maxValue);
        expect(dataProvider.randomDoubleInRange(minValue, maxValue)).andReturn((double) randomFloat);
        replay(metaDataCreator, numberMetaData, dataProvider);
        Object result = floatFieldHandler.getValueFor(mutableField);
        verify(metaDataCreator, numberMetaData, dataProvider);
        assertEquals(randomFloat, result);
    }
}