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

import net.sf.ipsedixit.core.DataProvider;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.StringMetaData;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.StringType;
import org.apache.commons.lang.StringUtils;

/**
 * Handler for String fields.
 */
public class StringFieldHandler implements FieldHandler {

    private final MetaDataCreator<StringMetaData> metaDataProvider;
    private final DataProvider dataProvider;

    /**
     * Creates a StringFieldHandler.
     *
     * @param metaDataCreator to get any metadata on the field, such as maximum length.
     * @param dataProvider to generate the random value.
     */
    public StringFieldHandler(MetaDataCreator<StringMetaData> metaDataCreator, DataProvider dataProvider) {
        this.metaDataProvider = metaDataCreator;
        this.dataProvider = dataProvider;
    }

    /**
     * @param mutableField a MutableField.
     * @return true, if and only if the MutableField represents a String.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return String.class.equals(type);
    }

    /**
     * @param mutableField the {@link net.sf.ipsedixit.core.MutableField} containing a String field.
     * @return a random String value, constrained by any metadata that is present on the field.
     */
    public String getValueFor(MutableField mutableField) {
        StringBuilder stringBuilder = new StringBuilder();
        StringMetaData stringMetaData = metaDataProvider.getMetaData(mutableField);

        int totalLength = stringMetaData.length();
        StringType stringType = stringMetaData.type();

        appendFieldNameIfRequired(stringBuilder, mutableField, stringType, totalLength);
        appendContainedStringIfRequired(stringBuilder, stringType, stringMetaData);
        stringBuilder.append(dataProvider.randomString(stringType, totalLength));

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
        return stringType == StringType.ANY ? "_" : "";
    }

}
