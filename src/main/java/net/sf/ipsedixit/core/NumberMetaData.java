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
 * Represents some metadata about a numeric field.
 */
public interface NumberMetaData {

    /**
     * Default minimum number is 1.
     */
    int DEFAULT_MINIMUM_NUMBER = 1;

    /**
     * Default maximum value is 127.
     *
     * @see Byte#MAX_VALUE
     */
    int DEFAULT_MAXIMUM_NUMBER = Byte.MAX_VALUE;

    /**
     * @return the minimum value of the random number.
     */
    double getMinValue();

    /**
     * @return the maximum value of the random number.
     */
    double getMaxValue();
}
