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
import net.sf.ipsedixit.core.RandomDataProvider;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Default implementation of RandomDataProvider.
 */
public class DefaultRandomDataProvider implements RandomDataProvider {

    /**
     * {@inheritDoc}
     */
    public String randomString(StringType stringType, int length) {
        if (stringType == StringType.ALPHANUMERIC) {
            return RandomStringUtils.randomAlphanumeric(length);
        } else if (stringType == StringType.ALPHA) {
            return RandomStringUtils.randomAlphabetic(length);
        } else if (stringType == StringType.NUMERIC) {
            return RandomStringUtils.randomNumeric(length);
        }
        return RandomStringUtils.randomAscii(length);
    }

    /**
     * {@inheritDoc}
     */
    public boolean randomBoolean() {
        return randomLongInRange(0, 1) == 0;
    }

    /**
     * {@inheritDoc}
     */
    public long randomLongInRange(long minInclusive, long maxInclusive) {
        return (long) (Math.random() * (maxInclusive - minInclusive + 1)) + minInclusive;
    }

    /**
     * {@inheritDoc}
     */
    public <T extends Enum> T randomEnumValue(Class<? extends T> enumType) {
        return randomArrayElement(enumType.getEnumConstants());
    }

    /**
     * {@inheritDoc}
     */
    public <T> T randomArrayElement(T[] array) {
        return array[(int) randomLongInRange(0, array.length - 1)];
    }

    /**
     * {@inheritDoc}
     */
    public double randomDoubleInRange(double minInclusive, double maxExclusive) {
        return (Math.random() * (maxExclusive - minInclusive)) + minInclusive;
    }
}
