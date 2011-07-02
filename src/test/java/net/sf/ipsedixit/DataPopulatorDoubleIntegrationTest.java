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

public class DataPopulatorDoubleIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasLongDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(10.1, target.immutable, 0);
        assertThat(target.defaultValue, inRange((double) NumberMetaData.DEFAULT_MINIMUM_NUMBER, (double) NumberMetaData.DEFAULT_MAXIMUM_NUMBER));
        assertEquals(10, target.primitiveValueOfTen, 0);
        assertEquals(10, target.valueOfTen, 0);
        Double minValue = -Double.MAX_VALUE;
        assertThat(target.bigNegative, inRange(minValue, 0.0));
        assertThat(target.numberBetweenOneAndFive, inRange(1.0, 5.0));
    }

    private static class Target {
        private final Double immutable = 10.1;
        private double defaultValue;

        @ArbitraryNumber(min = 10, max = 10)
        private double primitiveValueOfTen;

        @ArbitraryNumber(min = 10, max = 10)
        private Double valueOfTen;

        @ArbitraryNumber(min = -Double.MAX_VALUE, max = 0)
        private Double bigNegative;

        @ArbitraryNumber(min = 1, max = 5)
        private Double numberBetweenOneAndFive;
    }
}