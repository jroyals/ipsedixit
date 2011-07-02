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

package net.sf.ipsedixit.core;

/**
 * Implementations of this interface are able to generate random data.
 */
public interface DataProvider {

    /**
     * Generate a random String in a particular format.
     *
     * @param stringType the type of String to generate.
     * @param length the length of the String.
     * @return a random String of the given type and length.
     */
    String randomString(StringType stringType, int length);

    /**
     * @return <code>true</code> or <code>false</code>.
     */
    boolean randomBoolean();

    /**
     * Generate a random Long in a particular range.
     *
     * @param minInclusive the minimum inclusive number.
     * @param maxInclusive the maximum inclusive number.
     * @return a number within the specified range.
     */
    long randomLongInRange(long minInclusive, long maxInclusive);

    /**
     * Return a randum enum value from within the specified type.
     *
     * @param enumType the type of Enum.
     * @param <T> the enum type.
     * @return a random enum value from within the given type.
     */
    <T extends Enum> T randomEnumValue(Class<? extends T> enumType);

    /**
     * From an array, return a random element from it.
     *
     * @param array the array to pick a value from.
     * @param <T> the type of array.
     * @return a random value from the array.
     */
    <T> T randomArrayElement(T[] array);

    /**
     * Generate a random Double in a particular range.
     *
     * @param minInclusive the minimum inclusive number.
     * @param maxExclusive the maximum exclusive number.
     * @return a number within the specified range.
     */
    double randomDoubleInRange(double minInclusive, double maxExclusive);

    <T> T proxy(Class<T> clazz);
}
