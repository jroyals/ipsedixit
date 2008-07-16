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

/**
 *
 */
package com.fragstealers.ipsedixit.plugin.junit.v3;


import com.fragstealers.ipsedixit.annotation.ArbitraryString;
import com.fragstealers.ipsedixit.core.ObjectAnalyser;
import com.fragstealers.ipsedixit.core.impl.AnnotationOnlyObjectAnalyser;
import com.fragstealers.ipsedixit.plugin.junit.JUnit38IpsedixitTestCase;

public class NonAnnotatedClassOverrideClassAnalyserIntegrationTest extends JUnit38IpsedixitTestCase {
    private String immutable = "immutable";
    @ArbitraryString
    private String arbitrary;
    private Number mock;

    public void testHasWorked() {
        assertEquals("immutable", immutable);
        assertTrue(arbitrary.matches(".{64}"));
        assertNull(mock);
    }

    public ObjectAnalyser getClassAnalyser() {
        return new AnnotationOnlyObjectAnalyser(); // yeah a bit weird in a non-annotated test, but it tests what it needs to test...
    }
}