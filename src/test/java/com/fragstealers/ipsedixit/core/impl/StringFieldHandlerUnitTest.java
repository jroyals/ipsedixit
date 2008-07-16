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

import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.core.MetaDataCreator;
import com.fragstealers.ipsedixit.core.MutableField;
import com.fragstealers.ipsedixit.core.StringMetaData;
import com.fragstealers.ipsedixit.core.StringType;
import com.fragstealers.ipsedixit.core.RandomDataProvider;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StringFieldHandlerUnitTest {
    private StringFieldHandler stringFieldHandler;
    private MetaDataCreator<StringMetaData> metaDataProvider;
    private RandomDataProvider randomDataProvider;
    private StringMetaData stringMetaData;
    private MutableField mutableField;

    private String contains = "contains";
    private int length = 100;
    private String prefix = "prefix";
    private String randomString = "randomString";
    private StringType stringType = StringType.ALPHANUMERIC;

    @Before
    public void setup() {
        metaDataProvider = createMock(MetaDataCreator.class);
        randomDataProvider = createMock(RandomDataProvider.class);
        stringMetaData = createMock(StringMetaData.class);
        mutableField = createMock(MutableField.class);
        stringFieldHandler = new StringFieldHandler(metaDataProvider, randomDataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(stringFieldHandler.getClass()));
    }

    @Test
    public void canHandleStringType() {
        expect(mutableField.getType()).andReturn(String.class);
        replay(mutableField);
        assertTrue(stringFieldHandler.supports(mutableField));
        verify(mutableField);
    }

    @Test
    public void willGetMetaDataAndRandomiserToProduceStringInCorrectFormat() {
        runExpectations();
        Object result = replayAndVerifyInteractions();
        assertEquals("prefix_contains_randomString", result);
    }

    @Test
    public void willTrimStringToCorrectSizeWhenTooLong() {
        length = 20;
        runExpectations();
        Object result = replayAndVerifyInteractions();
        assertEquals("prefix_contains_rand", result);
    }

    @Test
    public void doesNotIncludeContainedStringIfNotPresent() {
        contains = null;
        runExpectations();
        Object result = replayAndVerifyInteractions();
        assertEquals("prefix_randomString", result);
    }

    @Test
    public void doesNotIncludePrefixIfLengthOfFieldNameGreaterThanLengthOfString() {
        length = 6;
        contains = "";

        runExpectations();
        Object result = replayAndVerifyInteractions();
        assertEquals("random", result);
    }

    @Test
    public void doesNotIncludeStringCharsIfStringTypeIsNumeric() {
        stringType = StringType.NUMERIC;
        runExpectations();
        Object result = replayAndVerifyInteractions();
        assertEquals("containsrandomString", result);
    }

    private void runExpectations() {
        expect(metaDataProvider.getMetaData(mutableField)).andReturn(stringMetaData);
        expect(mutableField.getName()).andReturn(prefix);
        expect(stringMetaData.length()).andReturn(length);
        expect(stringMetaData.contains()).andReturn(contains);
        expect(stringMetaData.type()).andReturn(stringType);
        expect(randomDataProvider.randomString(stringType, length)).andReturn(randomString);
    }

    private Object replayAndVerifyInteractions() {
        replay(metaDataProvider, stringMetaData, randomDataProvider, mutableField);
        Object result = stringFieldHandler.getValueFor(mutableField);
        verify(metaDataProvider, stringMetaData, randomDataProvider, mutableField);
        return result;
    }
}
