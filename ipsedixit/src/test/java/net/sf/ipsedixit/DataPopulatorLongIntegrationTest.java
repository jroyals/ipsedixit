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
import static net.sf.ipsedixit.test.CustomTestMethods.*;
import net.sf.ipsedixit.test.DefaultTestConfiguration;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DataPopulatorLongIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasLongDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(10, (long) target.immutable);
        assertThat(target.defaultValue, inRange((long) NumberMetaData.DEFAULT_MINIMUM_NUMBER, (long) NumberMetaData.DEFAULT_MAXIMUM_NUMBER));
        assertEquals(10, target.primitiveValueOfTen);
        assertEquals(10, (long) target.valueOfTen);
        assertThat(target.bigNegativeValue, inRange(Long.MIN_VALUE, (long) Integer.MIN_VALUE));
        assertThat(target.numberBetweenOneAndFive, inRange(1L, 5L));
    }

    private static class Target {
        private final Long immutable = 10L;
        private long defaultValue;

        @ArbitraryNumber(min = 10, max = 10)
        private long primitiveValueOfTen;

        @ArbitraryNumber(min = 10, max = 10)
        private Long valueOfTen;

        @ArbitraryNumber(min = Long.MIN_VALUE, max = Integer.MIN_VALUE)
        private Long bigNegativeValue;

        @ArbitraryNumber(min = 1, max = 5)
        private Long numberBetweenOneAndFive;
    }
}