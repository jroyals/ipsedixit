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

public class DataPopulatorIntegerIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasIntegerDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(10, (int) target.immutable);
        assertThat(target.defaultValue, inRange(NumberMetaData.DEFAULT_MINIMUM_NUMBER, NumberMetaData.DEFAULT_MAXIMUM_NUMBER));
        assertEquals(10, target.primitiveValueOfTen);
        assertEquals(10, (int) target.valueOfTen);
        Integer minValue = -2;
        Integer maxValue = -1;
        assertThat(target.negativeValue, inRange(minValue, maxValue));
        assertThat(target.numberBetweenOneAndFive, inRange(1, 5));
    }

    private static class Target {
        private final Integer immutable = 10;
        private Integer defaultValue;

        @ArbitraryNumber(min = 10, max = 10)
        private int primitiveValueOfTen;

        @ArbitraryNumber(min = 10, max = 10)
        private Integer valueOfTen;

        @ArbitraryNumber(min = -2, max = -1)
        private Integer negativeValue;

        @ArbitraryNumber(min = 1, max = 5)
        private Integer numberBetweenOneAndFive;
    }
}