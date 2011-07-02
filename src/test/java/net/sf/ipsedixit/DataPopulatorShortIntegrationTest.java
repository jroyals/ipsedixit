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

import net.sf.ipsedixit.annotation.ArbitraryNumber;
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.test.DefaultTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import static net.sf.ipsedixit.test.CustomTestMethods.inRange;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class DataPopulatorShortIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasLongDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(10, (short) target.immutable);
        assertThat(target.defaultValue, inRange((short) NumberMetaData.DEFAULT_MINIMUM_NUMBER, (short) NumberMetaData.DEFAULT_MAXIMUM_NUMBER));
        assertEquals(10, target.primitiveValueOfTen);
        assertEquals(10, (short) target.valueOfTen);
        assertThat(target.bigNegativeShort, inRange(Short.MIN_VALUE, (short) 0));
        assertThat(target.numberBetweenOneAndFive, inRange((short) 1, (short) 5));
    }

    private static class Target {
        private final Short immutable = 10;
        private short defaultValue;

        @ArbitraryNumber(min = 10, max = 10)
        private short primitiveValueOfTen;

        @ArbitraryNumber(min = 10, max = 10)
        private Short valueOfTen;

        @ArbitraryNumber(min = Short.MIN_VALUE, max = 0)
        private Short bigNegativeShort;

        @ArbitraryNumber(min = 1, max = 5)
        private Short numberBetweenOneAndFive;
    }
}