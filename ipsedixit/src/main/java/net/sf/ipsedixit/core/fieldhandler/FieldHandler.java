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

package net.sf.ipsedixit.core.fieldhandler;

import net.sf.ipsedixit.core.MutableField;

/**
 * A <code>FieldHandler</code> is able to create values for it's supported field type.  Ipsedixit contains a set of
 * <code>FieldHandler</code>'s for most standard Java types - see the list of implementing classes.
 */
public interface FieldHandler {

    /**
     * Create a value for the supported type.  The value must be equal to, or be a subclass/implementation of
     * the supported type.  Implementations may use metadata from the
     * <code>{@link net.sf.ipsedixit.core.MutableField}</code> to help in creating values.
     *
     * @param mutableField a representation of the Field that should be created.
     * @return an instance of mutableField.getType(), or an implementation/subclass of it.
     */
    Object getValueFor(MutableField mutableField);

    /**
     * Can (or should) this <code>FieldHandler</code> create instances of the type identified by the
     * <code>{@link net.sf.ipsedixit.core.MutableField}</code>.
     *
     * @param mutableField the <code>MutableField</code> to test.
     * @return true if and only if both conditions are true: this <code>FieldHandler</code> can create instances of
     * <code>{@link net.sf.ipsedixit.core.MutableField#getType()}</code>, and should also handle this
     * field based on extra metadata in the <code>MutableField</code>.
     */
    boolean supports(MutableField mutableField);
}
