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

package com.fragstealers.ipsedixit.core.impl;

import com.fragstealers.ipsedixit.core.MutableField;
import com.fragstealers.ipsedixit.core.MetaDataCreator;
import com.fragstealers.ipsedixit.core.StringMetaData;
import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.core.StringType;
import com.fragstealers.ipsedixit.core.RandomDataProvider;
import org.apache.commons.lang.StringUtils;

public class StringFieldHandler implements FieldHandler {

    private final MetaDataCreator<StringMetaData> metaDataProvider;
    private final RandomDataProvider randomDataProvider;

    public StringFieldHandler(MetaDataCreator<StringMetaData> metaDataProvider, RandomDataProvider randomDataProvider) {
        this.metaDataProvider = metaDataProvider;
        this.randomDataProvider = randomDataProvider;
    }

    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return String.class.equals(type);
    }

    public String getValueFor(MutableField mutableField) {
        StringBuilder stringBuilder = new StringBuilder();
        StringMetaData stringMetaData = metaDataProvider.getMetaData(mutableField);
        int totalLength = stringMetaData.length();
        StringType stringType = stringMetaData.type();
        appendFieldNameIfRequired(stringBuilder, mutableField, stringType, totalLength);
        appendContainedStringIfRequired(stringBuilder, stringType, stringMetaData);
        stringBuilder.append(randomDataProvider.randomString(stringType, totalLength));

        return StringUtils.left(stringBuilder.toString(), totalLength);
    }

    private void appendContainedStringIfRequired(StringBuilder stringBuilder, StringType stringType,
            StringMetaData stringMetaData) {
        String containedString = stringMetaData.contains();
        if (StringUtils.isNotEmpty(containedString)) {
            stringBuilder.append(containedString).append(getSeparatorChar(stringType));
        }
    }

    private void appendFieldNameIfRequired(StringBuilder stringBuilder, MutableField mutableField,
            StringType stringType, int totalLength) {
        String prefix = mutableField.getName();
        if (prefix.length() < totalLength && stringType != StringType.NUMERIC) {
            stringBuilder.append(prefix).append(getSeparatorChar(stringType));
        }
    }

    private String getSeparatorChar(StringType stringType) {
        return stringType == StringType.NUMERIC ? "" : "_";
    }

}
