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

import net.sf.ipsedixit.core.StringType;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.math.RoundingMode;

public class DefaultRandomDataProviderUnitTest {

    private DefaultRandomDataProvider defaultRandomDataProvider;

    @Before
    public void setup() {
        defaultRandomDataProvider = new DefaultRandomDataProvider();
    }

    @Test
    public void generatesRandomStringOfCorrectLengthAndFormatOnEachCall() {
        assertTrue(defaultRandomDataProvider.randomString(StringType.ALPHANUMERIC, 100), defaultRandomDataProvider.randomString(StringType.ALPHANUMERIC, 100).matches("[A-Za-z0-9]{100}"));
        assertTrue(defaultRandomDataProvider.randomString(StringType.NUMERIC, 100), defaultRandomDataProvider.randomString(StringType.NUMERIC, 100).matches("[0-9]{100}"));
        assertTrue(defaultRandomDataProvider.randomString(StringType.ALPHA, 100), defaultRandomDataProvider.randomString(StringType.ALPHA, 100).matches("[A-Za-z]{100}"));
    }

    @Test
    public void generatesRandomNumbersInRangeStartingFromZero() {
        int minInclusive = 0;
        int maxInclusive = 9;

        if (!ensureEachNumberInRangeGeneratedAtLeastOnce(minInclusive, maxInclusive)) {
            fail("A random number in the range was expected but never produced.");
        }
    }

    @Test
    public void generatesRandomNumbersInRangeStartingFromPositive() {
        int minInclusive = 100;
        int maxInclusive = 109;

        if (!ensureEachNumberInRangeGeneratedAtLeastOnce(minInclusive, maxInclusive)) {
            fail("A random number in the range was expected but never produced.");
        }
    }

    @Test
    public void generatesRandomNumbersInRangeStartingFromNegative() {
        int minInclusive = -10;
        int maxInclusive = 0;

        if (!ensureEachNumberInRangeGeneratedAtLeastOnce(minInclusive, maxInclusive)) {
            fail("A random number in the range was expected but never produced.");
        }
    }

    @Test
    public void returnsFixedLongWhenRangeLengthZero() {
        assertEquals(0, defaultRandomDataProvider.randomLongInRange(0, 0));
        assertEquals(10, defaultRandomDataProvider.randomLongInRange(10, 10));
        assertEquals(-10, defaultRandomDataProvider.randomLongInRange(-10, -10));
    }

    @Test
    public void returnsFixedDoubleWhenRangeLengthZero() {
        assertEquals(0, defaultRandomDataProvider.randomDoubleInRange(0, 0), 0);
        assertEquals(10.1, defaultRandomDataProvider.randomDoubleInRange(10.1, 10.1), 0);
        assertEquals(-10.1, defaultRandomDataProvider.randomDoubleInRange(-10.1, -10.1), 0);
    }

    @Test
    public void returnsDoubleInRange() {
        double result = defaultRandomDataProvider.randomDoubleInRange(10, 11);
        assertTrue(String.valueOf(result), result >= 10);
        assertTrue(String.valueOf(result), result < 11);
    }

    @Test
    public void canPickEveryEnumValue() {
        boolean hits[] = new boolean[RoundingMode.class.getEnumConstants().length];
        for (int i = 0; i < hits.length * 100; i++) {
            RoundingMode roundingMode = defaultRandomDataProvider.randomEnumValue(RoundingMode.class);
            hits[roundingMode.ordinal()] = true;
            if (allElementsTrue(hits)) {
                return; //all good
            }
        }
        fail("One of the enumerated types was never returned!");
    }

    private boolean ensureEachNumberInRangeGeneratedAtLeastOnce(int minInclusive, int maxInclusive) {
        boolean hits[] = new boolean[maxInclusive - minInclusive + 1];
        for (int i = 0; i < hits.length * 100; i++) {
            int result = (int) defaultRandomDataProvider.randomLongInRange(minInclusive, maxInclusive);
            assertTrue(result >= minInclusive && result < hits.length + minInclusive);
            hits[result - minInclusive] = true;
            if (allElementsTrue(hits)) {
                return true;
            }
        }
        return false;
    }

    private boolean allElementsTrue(boolean[] hits) {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }
}
