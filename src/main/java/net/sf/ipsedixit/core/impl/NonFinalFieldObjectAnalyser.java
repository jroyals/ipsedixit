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

import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.MutableField;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of {@link net.sf.ipsedixit.core.ObjectAnalyser} that assumes any field declared in the class,
 * regardless of it's visibility, is mutable.  Specifically, these conditions must be true:
 * <ul>
 *      <li>The field must be declared in the class.  Inherited fields ase <b>not</b> declared in the class, so
 *          inherited fields will not be considered mutable.</li>
 *      <li>The field must not be final.  Fields declared final are considered constant, and so are immutable</li>
 * </ul>
 *
 */
public class NonFinalFieldObjectAnalyser implements ObjectAnalyser {

    /**
     * Return the list of fields that are not declared final in the class that the object parameter was created from.
     *
     * @see net.sf.ipsedixit.core.ObjectAnalyser#getMutableFields(Object)
     * @param obj the object to analyse.
     * @return the List of mutable fields.
     */
    public List<MutableField> getMutableFields(Object obj) {
        List<MutableField> mutableFields = new ArrayList<MutableField>();
        processNonFinalFields(obj, mutableFields);
        return mutableFields;
    }

    /**
     * Is the current field in the set of non-final fields considered mutable?
     *
     * @param field the field under analysis.  It will not be final.
     * @return <code>true</code> if the field should be considered mutable.
     */
    protected boolean isFieldAllowed(Field field) {
        return true;
    }

    private void processNonFinalFields(Object obj, List<MutableField> mutableFields) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (fieldIsNotFinal(field) && isFieldAllowed(field)) {
                mutableFields.add(new DefaultMutableField(field, obj));
            }
        }
    }

    private boolean fieldIsNotFinal(Field field) {
        return !Modifier.isFinal(field.getModifiers());
    }
}
