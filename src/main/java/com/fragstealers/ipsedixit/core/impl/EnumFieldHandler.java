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

import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.core.RandomDataProvider;
import com.fragstealers.ipsedixit.core.MutableField;

public class EnumFieldHandler implements FieldHandler {
    private final RandomDataProvider randomDataProvider;

    public EnumFieldHandler(RandomDataProvider randomDataProvider) {
        this.randomDataProvider = randomDataProvider;
    }

    public Enum getValueFor(MutableField mutableField) {
        return randomDataProvider.randomEnumValue((Class<? extends Enum>) mutableField.getType());
    }

    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return Enum.class.isAssignableFrom(type);
    }
}
