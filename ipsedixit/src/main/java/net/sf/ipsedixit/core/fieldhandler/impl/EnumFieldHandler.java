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
import net.sf.ipsedixit.core.RandomDataProvider;
import net.sf.ipsedixit.core.MutableField;

/**
 * Handles Java 5 enumerations by returning a random value from within the enumeration.
 */
public class EnumFieldHandler implements FieldHandler {
    private final RandomDataProvider randomDataProvider;

    /**
     * Creates a new EnumFieldHandler.
     * @param randomDataProvider a RandomDataProvider to use when creating the random emum.
     */
    public EnumFieldHandler(RandomDataProvider randomDataProvider) {
        this.randomDataProvider = randomDataProvider;
    }

    /**
     * @param mutableField the enumeration, wrapped by the mutable field.
     * @return a random value from within the enumerated type.
     */
    public Enum getValueFor(MutableField mutableField) {
        return randomDataProvider.randomEnumValue((Class<? extends Enum>) mutableField.getType());
    }

    /**
     * @param mutableField an arbitrary field.
     * @return true if, and only if, the mutableField wraps a Java 5 Enumeration.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return Enum.class.isAssignableFrom(type);
    }
}
