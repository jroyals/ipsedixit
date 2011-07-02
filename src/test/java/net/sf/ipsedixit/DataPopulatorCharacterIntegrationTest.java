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

public class DataPopulatorCharacterIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasLongDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(10, (char) target.immutable);
        assertThat(target.defaultValue, inRange((char) NumberMetaData.DEFAULT_MINIMUM_NUMBER, (char) NumberMetaData.DEFAULT_MAXIMUM_NUMBER));
        assertEquals(10, target.primitiveValueOfTen);
        assertEquals(10, (char) target.valueOfTen);
        assertThat(target.betweenZeroAndMax, inRange((char) 0, Character.MAX_VALUE));
        assertThat(target.numberBetweenOneAndFive, inRange((char) 1, (char) 5));
    }

    private static class Target {
        private final Character immutable = 10;
        private char defaultValue;

        @ArbitraryNumber(min = 10, max = 10)
        private char primitiveValueOfTen;

        @ArbitraryNumber(min = 10, max = 10)
        private Character valueOfTen;

        @ArbitraryNumber(min = 0, max = Character.MAX_VALUE)
        private Character betweenZeroAndMax;

        @ArbitraryNumber(min = 1, max = 5)
        private Character numberBetweenOneAndFive;
    }
}