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

import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.StringMetaData;
import net.sf.ipsedixit.core.StringType;

/**
 * Creates StringMetaData from annotations found on a String field.
 */
public class AnnotationStringMetaDataCreator implements MetaDataCreator<StringMetaData> {

    /**
     * Returns a {@link net.sf.ipsedixit.core.StringMetaData} instance that contains the metadata on this field.
     *
     * @param mutableField the field to look for annotations.
     * @return the StringMetaData instance encapsulating all metadata on this field.
     */
    public StringMetaData getMetaData(MutableField mutableField) {
        InternalStringMetaData stringMetaData = new InternalStringMetaData();

        ArbitraryString arbitraryString = mutableField.getAnnotation(ArbitraryString.class);
        if (arbitraryString != null) {
            stringMetaData.length = arbitraryString.ofLength();
            stringMetaData.contains = arbitraryString.containing();
            stringMetaData.type = arbitraryString.type();
        }

        return stringMetaData;
    }

    private static class InternalStringMetaData implements StringMetaData {
        private int length = DEFAULT_SIZE;
        private String contains;
        private StringType type = StringType.ALPHANUMERIC;

        public int length() {
            return length;
        }

        public String contains() {
            return contains;
        }

        public StringType type() {
            return type;
        }
    }
}
