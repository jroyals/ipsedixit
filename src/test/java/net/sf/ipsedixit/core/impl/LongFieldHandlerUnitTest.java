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

public class LongFieldHandlerUnitTest {

    private LongFieldHandler longFieldHandler;
    private MetaDataCreator<NumberMetaData> metaDataCreator;
    private DataProvider dataProvider;
    private NumberMetaData numberMetaData;
    private MutableField mutableField;
    private double minValue;
    private double maxValue;
    private long randomLong;

    @Before
    public void setup() {
        minValue = new Random().nextInt(100);
        maxValue = new Random().nextInt(100);
        randomLong = new Random().nextInt(100);
        metaDataCreator = createMock(MetaDataCreator.class);
        dataProvider = createMock(DataProvider.class);
        numberMetaData = createMock(NumberMetaData.class);
        mutableField = createMock(MutableField.class);
        longFieldHandler = new LongFieldHandler(metaDataCreator, dataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(longFieldHandler.getClass()));
    }

    @Test
    public void supportsLong() {
        assertTrue(supportsType(mutableField, longFieldHandler, long.class));
        assertTrue(supportsType(mutableField, longFieldHandler, Long.class));
    }

    @Test
    public void generatesRandomLong() {
        expect(metaDataCreator.getMetaData(mutableField)).andReturn(numberMetaData);
        expect(numberMetaData.getMinValue()).andReturn(minValue);
        expect(numberMetaData.getMaxValue()).andReturn(maxValue);
        expect(dataProvider.randomLongInRange((long) minValue, (long) maxValue)).andReturn(randomLong);
        replay(metaDataCreator, numberMetaData, dataProvider);
        Object result = longFieldHandler.getValueFor(mutableField);
        verify(metaDataCreator, numberMetaData, dataProvider);
        assertSame(randomLong, result);
    }
}