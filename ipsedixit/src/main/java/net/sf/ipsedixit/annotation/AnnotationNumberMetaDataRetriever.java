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

package net.sf.ipsedixit.annotation;

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.NumberMetaData;

/**
 * Creates StringMetaData from annotations found on a numeric field.
 */
public class AnnotationNumberMetaDataRetriever implements NumberMetaDataRetriever {

    /**
     * Returns a {@link net.sf.ipsedixit.core.NumberMetaData} instance that contains the metadata on this field.
     *
     * @param mutableField the field to look for annotations.
     * @return the NumberMetaData instance encapsulating all metadata on this field.
     */
    public NumberMetaData getMetaData(MutableField mutableField) {
        InternalNumberMetaData internalNumberMetaData = new InternalNumberMetaData();

        ArbitraryNumber arbitraryNumber = mutableField.getAnnotation(ArbitraryNumber.class);
        if (arbitraryNumber != null) {
            internalNumberMetaData.maxValue = arbitraryNumber.max();
            internalNumberMetaData.minValue = arbitraryNumber.min();
        }
        return internalNumberMetaData;
    }

    private static class InternalNumberMetaData implements NumberMetaData {
        private double minValue = DEFAULT_MINIMUM_NUMBER;
        private double maxValue = DEFAULT_MAXIMUM_NUMBER;

        public double getMinValue() {
            return minValue;
        }

        public double getMaxValue() {
            return maxValue;
        }
    }
}
