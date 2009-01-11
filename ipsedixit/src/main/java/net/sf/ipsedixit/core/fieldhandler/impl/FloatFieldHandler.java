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

package net.sf.ipsedixit.core.fieldhandler.impl;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.RandomDataProvider;

/**
 * Handler for Float and float fields.
 */
public class FloatFieldHandler implements FieldHandler {
    private final MetaDataCreator<NumberMetaData> metaDataCreator;
    private final RandomDataProvider randomDataProvider;

    /**
     * Creates a FloatFieldHandler.
     *
     * @param metaDataCreator to get any metadata on the field, such as maximum length.
     * @param randomDataProvider to generate the random value.
     */
    public FloatFieldHandler(MetaDataCreator<NumberMetaData> metaDataCreator, RandomDataProvider randomDataProvider) {
        this.metaDataCreator = metaDataCreator;
        this.randomDataProvider = randomDataProvider;
    }

    /**
     * @param mutableField the {@link net.sf.ipsedixit.core.MutableField} containing a float field.
     * @return a random float value, constrained by any metadata that is present on the field.
     */
    public Float getValueFor(MutableField mutableField) {
        NumberMetaData numberMetaData = metaDataCreator.getMetaData(mutableField);
        return (float) randomDataProvider.randomDoubleInRange(
                numberMetaData.getMinValue(), numberMetaData.getMaxValue());
    }

    /**
     * @param mutableField a MutableField.
     * @return true, if and only if the MutableField represents a float.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return float.class.equals(type) || Float.class.equals(type);
    }
}
