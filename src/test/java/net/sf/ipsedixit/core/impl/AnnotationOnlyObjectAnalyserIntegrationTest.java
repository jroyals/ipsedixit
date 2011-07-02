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

import java.util.List;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.test.ArbitraryClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnnotationOnlyObjectAnalyserIntegrationTest {

    private AnnotationOnlyObjectAnalyser annotationOnlyClassAnalyser;

    @Before
    public void setup() {
        annotationOnlyClassAnalyser = new AnnotationOnlyObjectAnalyser();
    }

    @Test
    public void implementsInterface() {
        assertTrue(ObjectAnalyser.class.isAssignableFrom(annotationOnlyClassAnalyser.getClass()));
    }

    @Test
    public void returnsMutableAnnotatedFieldsOnly() {
        List<MutableField> results = annotationOnlyClassAnalyser.getMutableFields(new ArbitraryClass());
        assertEquals(1, results.size());
        assertEquals("foo", results.get(0).getName());
    }
}
