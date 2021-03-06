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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import net.sf.ipsedixit.core.MutableField;

/**
 * Default implementation of MutableField.
 */
public class DefaultMutableField implements MutableField {
    private final Field field;
    private final Object obj;

    /**
     * Creates a DefaultMutableField.
     * @param field the Field to wrap.
     * @param obj the Object that contains an instance of the Field.
     */
    public DefaultMutableField(Field field, Object obj) {
        this.field = field;
        this.obj = obj;
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(Object value) {
        if (value != null) {
            tryToSetFieldTo(value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return field.getName();
    }

    /**
     * {@inheritDoc}
     */
    public Class getType() {
        return field.getType();
    }

    /**
     * {@inheritDoc}
     */
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    /**
     * {@inheritDoc}
     */
    private void tryToSetFieldTo(Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
