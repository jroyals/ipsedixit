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

public class DataPopulatorByteIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasLongDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(10, (byte) target.immutable);
        assertThat(target.defaultValue, inRange((byte) NumberMetaData.DEFAULT_MINIMUM_NUMBER, (byte) NumberMetaData.DEFAULT_MAXIMUM_NUMBER));
        assertEquals(10, target.primitiveValueOfTen);
        assertEquals(10, (byte) target.valueOfTen);
        assertThat(target.betweenZeroAndMax, inRange(Byte.MIN_VALUE, Byte.MAX_VALUE));
        assertThat(target.numberBetweenOneAndFive, inRange((byte) 1, (byte) 5));
    }

    private static class Target {
        private final Byte immutable = 10;
        private byte defaultValue;

        @ArbitraryNumber(min = 10, max = 10)
        private byte primitiveValueOfTen;

        @ArbitraryNumber(min = 10, max = 10)
        private Byte valueOfTen;

        @ArbitraryNumber(min = Byte.MIN_VALUE, max = Byte.MAX_VALUE)
        private Byte betweenZeroAndMax;

        @ArbitraryNumber(min = 1, max = 5)
        private Byte numberBetweenOneAndFive;
    }
}