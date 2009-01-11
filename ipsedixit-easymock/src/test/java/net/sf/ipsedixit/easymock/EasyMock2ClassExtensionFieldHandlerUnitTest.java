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

package net.sf.ipsedixit.easymock;

import java.io.Serializable;

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EasyMock2ClassExtensionFieldHandlerUnitTest {
    private MutableField mutableField;

    private EasyMock2ClassExtensionFieldHandler easyMock2ClassExtensionFieldHandler;

    @Before
    public void setup() {
        mutableField = createMock(MutableField.class);
        easyMock2ClassExtensionFieldHandler = new EasyMock2ClassExtensionFieldHandler();
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(easyMock2ClassExtensionFieldHandler.getClass()));
    }

    @Test
    public void supportsNonFinalClassesAndNonPrimitiveClasses() {
        assertTrue(CustomTestMethods.supportsType(mutableField, easyMock2ClassExtensionFieldHandler, Object.class)); // non-final class
        assertFalse(CustomTestMethods.supportsType(mutableField, easyMock2ClassExtensionFieldHandler, Class.class)); // final class
        assertTrue(CustomTestMethods.supportsType(mutableField, easyMock2ClassExtensionFieldHandler, Serializable.class)); // interface
        assertFalse(CustomTestMethods.supportsType(mutableField, easyMock2ClassExtensionFieldHandler, int.class)); // primitive class
    }

    @Test
    public void returnsEasyMock2ProxyOfSameTypeAsMutableField() {
        expect(mutableField.getType()).andReturn(Number.class);   // class, not interface, to ensure cglib mocks
        replay(mutableField);
        Object result = easyMock2ClassExtensionFieldHandler.getValueFor(mutableField);
        verify(mutableField);
        assertTrue(result instanceof Number);
        assertTrue(result.toString().contains("EasyMock for class java.lang.Number"));
    }
}
