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
import com.fragstealers.ipsedixit.core.FieldHandlerFinder;
import com.fragstealers.ipsedixit.core.FieldHandler;

import java.util.List;

public class DefaultFieldHandlerFinder implements FieldHandlerFinder {
    private final List<FieldHandler> fieldHandlers;
    private final FieldHandler defaultFieldHandler;

    public DefaultFieldHandlerFinder(List<FieldHandler> fieldHandlers, FieldHandler defaultFieldHandler) {
        this.fieldHandlers = fieldHandlers;
        this.defaultFieldHandler = defaultFieldHandler;
    }

    public FieldHandler findFieldHandler(MutableField mutableField) {
        for (FieldHandler fieldHandler : fieldHandlers) {
            if (fieldHandler.supports(mutableField)) {
                return fieldHandler;
            }
        }
        return defaultFieldHandler;
    }

    public void add(FieldHandler fieldHandler) {
    }
}
