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

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.RandomDataProvider;

/**
 * Handler for Booelean and boolean fields.
 */
public class BooleanFieldHandler implements FieldHandler {

    private final RandomDataProvider randomDataProvider;

    /**
     * Creates a BooleanFieldHandler.
     *
     * @param randomDataProvider to generate the random value.
     */
    public BooleanFieldHandler(RandomDataProvider randomDataProvider) {
        this.randomDataProvider = randomDataProvider;
    }

    /**
     * @param mutableField the {@link net.sf.ipsedixit.core.MutableField} containing a boolean field.
     * @return a random double boolean.
     */
    public Boolean getValueFor(MutableField mutableField) {
        return randomDataProvider.randomBoolean();
    }

    /**
     * @param mutableField a MutableField.
     * @return true, if and only if the MutableField represents a boolean.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return boolean.class.equals(type) || Boolean.class.equals(type);
    }

}
