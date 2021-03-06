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
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.FieldHandler;

/**
 * Handler for Character and char fields.
 */
public class CharacterFieldHandler implements FieldHandler {
    private final MetaDataCreator<NumberMetaData> metaDataCreator;
    private final DataProvider dataProvider;

    /**
     * Creates a CharacterFieldHandler.
     *
     * @param metaDataCreator to get any metadata on the field, such as maximum value.
     * @param dataProvider to generate the random value.
     */
    public CharacterFieldHandler(
            MetaDataCreator<NumberMetaData> metaDataCreator, DataProvider dataProvider) {
        this.metaDataCreator = metaDataCreator;
        this.dataProvider = dataProvider;
    }

    /**
     * @param mutableField the {@link net.sf.ipsedixit.core.MutableField} containing a character field.
     * @return a random character value, constrained by any metadata that is present on the field.
     */
    public Character getValueFor(MutableField mutableField) {
        NumberMetaData numberMetaData = metaDataCreator.getMetaData(mutableField);
        return (char) dataProvider.randomLongInRange(
                (char) numberMetaData.getMinValue(), (char) numberMetaData.getMaxValue());
    }

    /**
     * @param mutableField a MutableField.
     * @return true, if and only if the MutableField represents a character.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return char.class.equals(type) || Character.class.equals(type);
    }
}
