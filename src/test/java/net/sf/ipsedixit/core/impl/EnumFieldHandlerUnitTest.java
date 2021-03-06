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

import net.sf.ipsedixit.core.DataProvider;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MutableField;
import org.junit.Before;
import org.junit.Test;
import static net.sf.ipsedixit.test.CustomTestMethods.supportsType;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.expect;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class EnumFieldHandlerUnitTest {

    private MutableField mutableField;
    private Enum enumeration;
    private DataProvider dataProvider;
    private EnumFieldHandler enumHandler;
    private Class<? extends Enum> enumType;

    @Before
    public void setup() {
        enumeration = createMock(Enum.class);
        mutableField = createMock(MutableField.class);
        dataProvider = createMock(DataProvider.class);

        enumType = Enum.class;
        enumHandler = new EnumFieldHandler(dataProvider);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(enumHandler.getClass()));
    }

    @Test
    public void supportsEnumeratedTypes() {
        assertTrue(supportsType(mutableField, enumHandler, Enum.class));
    }

    @Test
    public void returnsRandomEnum() {
        expect(mutableField.getType()).andReturn(enumType);
        expect(dataProvider.randomEnumValue(enumType)).andReturn(enumeration);
        replay(mutableField, dataProvider);
        Object result = enumHandler.getValueFor(mutableField);
        verify(mutableField, dataProvider);
        assertSame(enumeration, result);
    }
}
