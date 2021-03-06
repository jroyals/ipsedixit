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

package net.sf.ipsedixit;

import net.sf.ipsedixit.annotation.ArbitraryString;
import net.sf.ipsedixit.core.StringMetaData;
import net.sf.ipsedixit.core.StringType;
import net.sf.ipsedixit.test.DefaultTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataPopulatorStringIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasStringDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals("immutable", target.immutable);
        assertEquals(StringMetaData.DEFAULT_SIZE, target.string.length());
        assertTrue(target.string, target.string.matches("string_.+"));
        assertEquals(10, target.stringOfLength.length());
        assertTrue(target.stringContaining, target.stringContaining.matches(".*contains.*"));
        assertTrue(target.alphaString, target.alphaString.matches("alphaString[A-Za-z]+"));
        assertTrue(target.numericString, target.numericString.matches("[0-9]+"));
        assertTrue(target.alphaNumericString, target.alphaNumericString.matches("alphaNumericString[A-Za-z0-9]+"));
        assertTrue(target.anyString, target.anyString.matches("anyString_.+"));
    }

    private static class Target {
        private final String immutable = "immutable";
        private String string;
        @ArbitraryString(ofLength = 10) private String stringOfLength;
        @ArbitraryString(containing = "contains") private String stringContaining;
        @ArbitraryString(type = StringType.ALPHA) private String alphaString;
        @ArbitraryString(type = StringType.NUMERIC) private String numericString;
        @ArbitraryString(type = StringType.ALPHANUMERIC) private String alphaNumericString;
        @ArbitraryString private String anyString;
    }
}
