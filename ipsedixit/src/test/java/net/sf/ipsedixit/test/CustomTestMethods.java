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

package net.sf.ipsedixit.test;

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import static org.easymock.EasyMock.*;
import org.hamcrest.Matcher;

public final class CustomTestMethods {

    private CustomTestMethods() {

    }

    public static boolean supportsType(MutableField mutableField, FieldHandler fieldHandler,
            Class supportedType) {
        expect(mutableField.getType()).andReturn(supportedType);
        replay(mutableField);
        boolean result = fieldHandler.supports(mutableField);
        verify(mutableField);
        reset(mutableField);
        return result;
    }

    public static <T extends Comparable> Matcher inRange(T minValue, T maxValue) {
        return new BetweenMatcher(minValue, maxValue);
    }
}
