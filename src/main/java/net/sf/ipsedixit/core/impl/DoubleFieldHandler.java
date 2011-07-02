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
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.MutableField;

/**
 * Handler for Double and double fields.
 */
public class DoubleFieldHandler implements FieldHandler {
    private final MetaDataCreator<NumberMetaData> metaDataCreator;
    private final DataProvider dataProvider;

    /**
     * Creates a DoubleFieldHandler.
     *
     * @param metaDataCreator to get any metadata on the field, such as maximum and minimum number.
     * @param dataProvider to generate the random value.
     */
    public DoubleFieldHandler(MetaDataCreator<NumberMetaData> metaDataCreator, DataProvider dataProvider) {
        this.metaDataCreator = metaDataCreator;
        this.dataProvider = dataProvider;
    }

    /**
     * @param mutableField the {@link net.sf.ipsedixit.core.MutableField} containing a double field.
     * @return a random double value, constrained by any metadata that is present on the field.
     */
    public Double getValueFor(MutableField mutableField) {
        NumberMetaData numberMetaData = metaDataCreator.getMetaData(mutableField);
        return dataProvider.randomDoubleInRange(numberMetaData.getMinValue(), numberMetaData.getMaxValue());
    }

    /**
     * @param mutableField a MutableField.
     * @return true, if and only if the MutableField represents a double.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return double.class.equals(type) || Double.class.equals(type);
    }
}
