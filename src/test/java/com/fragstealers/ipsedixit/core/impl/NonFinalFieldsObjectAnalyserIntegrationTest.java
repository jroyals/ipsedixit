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
import com.fragstealers.ipsedixit.core.ObjectAnalyser;
import com.fragstealers.ipsedixit.test.ArbitraryClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NonFinalFieldsObjectAnalyserIntegrationTest {

    private NonFinalFieldsObjectAnalyser nonFinalFieldsClassAnalyser;

    @Before
    public void setup() {
        nonFinalFieldsClassAnalyser = new NonFinalFieldsObjectAnalyser();
    }

    @Test
    public void implementsInterface() {
        assertTrue(ObjectAnalyser.class.isAssignableFrom(nonFinalFieldsClassAnalyser.getClass()));
    }

    @Test
    public void returnsMutableAnnotatedFieldsOnly() {
        List<MutableField> results = nonFinalFieldsClassAnalyser.getMutableFields(new ArbitraryClass());
        assertEquals(3, results.size());
        assertContainsFieldName("foo", results);
        assertContainsFieldName("baz", results);
        assertContainsFieldName("quux", results);
    }

    private static void assertContainsFieldName(String name, List<MutableField> mutableFields) {
        for (MutableField mutableField : mutableFields) {
            if (name.equals(mutableField.getName())) {
                return;
            }
        }
        fail("Field with name '" + name + "' not found in results");
    }
}
