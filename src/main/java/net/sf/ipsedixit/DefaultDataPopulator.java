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

package net.sf.ipsedixit;

import java.util.List;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.FieldHandlerFinder;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.ObjectAnalyser;

/**
 * Default implementation of DataProvider.
 */
public class DefaultDataPopulator implements DataPopulator {
    private final FieldHandlerFinder fieldHandlerFinder;
    private final ObjectAnalyser objectAnalyser;

    /**
     * Creates a new DefaultDataPopulator.
     *
     * @param fieldHandlerFinder a FieldHandlerFinder to locate the most field handlers.
     * @param objectAnalyser an ObjectAnalyser to determine which fields are mutable and which ones are not.
     */
    public DefaultDataPopulator(FieldHandlerFinder fieldHandlerFinder, ObjectAnalyser objectAnalyser) {
        this.fieldHandlerFinder = fieldHandlerFinder;
        this.objectAnalyser = objectAnalyser;
    }

    /**
     * Populate the fields of an object using the configured FieldHandlerFinder and ObjectAnalyser.
     * @param obj the Object to populate.
     */
    public void populate(Object obj) {
        List<MutableField> mutableFields = objectAnalyser.getMutableFields(obj);
        for (MutableField mutableField : mutableFields) {
            FieldHandler fieldHandler = fieldHandlerFinder.findFieldHandler(mutableField);
            Object value = fieldHandler.getValueFor(mutableField);
            mutableField.setValue(value);
        }
    }
}
