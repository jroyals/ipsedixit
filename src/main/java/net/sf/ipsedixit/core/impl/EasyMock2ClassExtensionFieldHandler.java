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

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.FieldHandler;
import org.easymock.classextension.EasyMock;

import java.lang.reflect.Modifier;

/**
 * A specialised field handler that returns EasyMock 2 mock objects. The specific implementation of EasyMock 2 is
 * org.easymock.classextension.EasyMock, so mocks can be provided over interfaces and classes through CGLIB.
 */
public class EasyMock2ClassExtensionFieldHandler implements FieldHandler {

    /**
     * Creates an EasyMock 2 mock object for the type encapsulated in the MutableField.
     * @param mutableField the mutableField.
     * @return an EasyMock 2 mock of the correct type.
     */
    public Object getValueFor(MutableField mutableField) {
        return EasyMock.createMock(mutableField.getType());
    }

    /**
     * EasyMock 2 mocks can be provided for any non-final Class.  Final classes cannot be extended, and so cannot be
     * mocked by CGLIB.
     *
     * @param mutableField a MutableField.
     * @return true, if the type is an Object (ie, non-primitive), and the type is non-final.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return !Modifier.isFinal(type.getModifiers()) && Object.class.isAssignableFrom(type);
    }
}
