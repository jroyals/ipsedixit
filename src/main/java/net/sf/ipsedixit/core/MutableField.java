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

package net.sf.ipsedixit.core;

import java.lang.annotation.Annotation;

/**
 * Abstraction over a declared field in a object.  Mutable in this
 * sense is in the context of the class being checked, not necessarily any modifiers
 * applied to the field.  Mutable fields are, by definition, able to have their value changed.
 */
public interface MutableField {

    /**
     * Set the value of this field to the nominated value.
     *
     * @param value the value to set
     * @throws RuntimeException if something bad happens when setting the value.
     */
    void setValue(Object value);

    /**
     * Get the name of this field.
     * @return the name of the field.
     */
    String getName();

    /**
     * Get the field type as a {@link Class}.
     * @return the field type.
     */
    Class getType();

    /**
     * Get the annotation declared on this field of the specified type.  If the annotation does not
     * exist, the method must return <code>null</code>.
     *
     * @param annotationClass the type of annotation.
     * @param <A> the annotation class.
     * @return the Annotation instance of type <code>&lt;A&gt;</code>
     */
    <A extends Annotation> A getAnnotation(Class<A> annotationClass);
}
