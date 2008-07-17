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
import net.sf.ipsedixit.core.RandomDataProvider;
import net.sf.ipsedixit.core.impl.BooleanFieldHandler;
import static net.sf.ipsedixit.test.CustomTestMethods.supportsType;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BooleanFieldHandlerUnitTest {

    private BooleanFieldHandler booleanFieldHandler;
    private MutableField mutableField;
    private RandomDataProvider randomDataProvider;
	private boolean randomBoolean;

    @Before
    public void setup() {
    	randomBoolean = ((int) Math.random() * 2) == 0;
        randomDataProvider = createMock(RandomDataProvider.class);
        mutableField = createMock(MutableField.class);
        booleanFieldHandler = new BooleanFieldHandler(randomDataProvider);
    }

    @Test
    public void supportsPrimitiveAndObjectBoolean() {
        assertTrue(supportsType(mutableField, booleanFieldHandler, boolean.class));
        assertTrue(supportsType(mutableField, booleanFieldHandler, Boolean.class));
    }

    @Test
    public void returnsTrueOrFalse() {
    	expect(randomDataProvider.randomBoolean()).andReturn(randomBoolean);
    	replay(randomDataProvider);
        assertEquals(randomBoolean, booleanFieldHandler.getValueFor(mutableField));
        verify(randomDataProvider);
    }
}
