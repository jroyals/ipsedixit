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

import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.ObjectAnalyser;

import java.util.List;

public class DefaultDataPopulator implements DataPopulator {
    private final FieldHandler fieldHandler;
    private final ObjectAnalyser objectAnalyser;

    public DefaultDataPopulator(FieldHandler fieldHandler, ObjectAnalyser objectAnalyser) {
        this.fieldHandler = fieldHandler;
        this.objectAnalyser = objectAnalyser;
    }

    public void populate(Object obj) {
        List<MutableField> mutableFields = objectAnalyser.getMutableFields(obj);
        for (MutableField mutableField : mutableFields) {
            Object value = fieldHandler.getValueFor(mutableField);
            mutableField.setValue(value);
        }
    }
}
