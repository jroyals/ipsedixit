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

import java.util.List;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.FieldHandlerFinder;
import net.sf.ipsedixit.core.MutableField;

/**
 * Default implementation of {@link net.sf.ipsedixit.core.FieldHandlerFinder}.
 */
public class DefaultFieldHandlerFinder implements FieldHandlerFinder {
    private final List<FieldHandler> fieldHandlers;
    private final FieldHandler defaultFieldHandler;

    /**
     * Create a new DefaultFieldHandlerFinder.
     * @param fieldHandlers a List of {@link net.sf.ipsedixit.core.fieldhandler.FieldHandler} objects, which acts as
     * a repository on which this object will search.
     * @param defaultFieldHandler If no handlers are found in the fieldHandlers list, return this default FieldHandler.
     */
    public DefaultFieldHandlerFinder(List<FieldHandler> fieldHandlers, FieldHandler defaultFieldHandler) {
        this.fieldHandlers = fieldHandlers;
        this.defaultFieldHandler = defaultFieldHandler;
    }

    /**
     * {@inheritDoc}
     */
    public FieldHandler findFieldHandler(MutableField mutableField) {
        for (FieldHandler fieldHandler : fieldHandlers) {
            if (fieldHandler.supports(mutableField)) {
                return fieldHandler;
            }
        }
        return defaultFieldHandler;
    }
}
