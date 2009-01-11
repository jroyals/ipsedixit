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
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.RandomDataProvider;

/**
 * Handler for Integer and int fields.
 */
public class IntegerFieldHandler implements FieldHandler {
    private final MetaDataCreator<NumberMetaData> metaDataCreator;
    private final RandomDataProvider randomDataProvider;

    /**
     * Creates a IntegerFieldHandler.
     *
     * @param metaDataCreator to get any metadata on the field, such as maximum length.
     * @param randomDataProvider to generate the random value.
     */
    public IntegerFieldHandler(MetaDataCreator<NumberMetaData> metaDataCreator, RandomDataProvider randomDataProvider) {
        this.metaDataCreator = metaDataCreator;
        this.randomDataProvider = randomDataProvider;
    }

    /**
     * @param mutableField the {@link net.sf.ipsedixit.core.MutableField} containing an integer field.
     * @return a random integer value, constrained by any metadata that is present on the field.
     */
    public Integer getValueFor(MutableField mutableField) {
        NumberMetaData numberMetaData = metaDataCreator.getMetaData(mutableField);
        return (int) randomDataProvider.randomLongInRange(
                (int) numberMetaData.getMinValue(), (int) numberMetaData.getMaxValue());
    }

    /**
     * @param mutableField a MutableField.
     * @return true, if and only if the MutableField represents a integer.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return int.class.equals(type) || Integer.class.equals(type);
    }
}
