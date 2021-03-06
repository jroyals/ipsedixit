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

import java.util.Random;
import net.sf.ipsedixit.core.DataProvider;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.NumberMetaData;
import org.junit.Before;
import org.junit.Test;
import static net.sf.ipsedixit.test.CustomTestMethods.supportsType;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ByteFieldHandlerUnitTest {

    private ByteFieldHandler byteFieldHandler;
    private MetaDataCreator<NumberMetaData> metaDataCreator;
    private DataProvider dataProvider;
    private NumberMetaData numberMetaData;
    private MutableField mutableField;
    private double minValue;
    private double maxValue;
    private byte randomByte;

    @Before
    public void setup() {
        minValue = new Random().nextInt(100);
        maxValue = new Random().nextInt(100);
        randomByte = (byte) new Random().nextInt(100);
        metaDataCreator = createMock(MetaDataCreator.class);
        dataProvider = createMock(DataProvider.class);
        numberMetaData = createMock(NumberMetaData.class);
        mutableField = createMock(MutableField.class);
        byteFieldHandler = new ByteFieldHandler(metaDataCreator, dataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(byteFieldHandler.getClass()));
    }

    @Test
    public void supportsByte() {
        assertTrue(supportsType(mutableField, byteFieldHandler, byte.class));
        assertTrue(supportsType(mutableField, byteFieldHandler, Byte.class));
    }

    @Test
    public void generatesRandomByte() {
        expect(metaDataCreator.getMetaData(mutableField)).andReturn(numberMetaData);
        expect(numberMetaData.getMinValue()).andReturn(minValue);
        expect(numberMetaData.getMaxValue()).andReturn(maxValue);
        expect(dataProvider.randomLongInRange((byte) minValue, (byte) maxValue)).andReturn((long) randomByte);
        replay(metaDataCreator, numberMetaData, dataProvider);
        Object result = byteFieldHandler.getValueFor(mutableField);
        verify(metaDataCreator, numberMetaData, dataProvider);
        assertSame(randomByte, result);
    }
}