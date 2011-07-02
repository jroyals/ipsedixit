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

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.FieldHandlerFinder;
import net.sf.ipsedixit.core.MutableField;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.expect;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class DefaultFieldHandlerFinderUnitTest {
    private DefaultFieldHandlerFinder defaultFieldHandlerRepository;
    private List<FieldHandler> fieldHandlers;
    private Iterator<FieldHandler> fieldHandlersIterator;
    private int numberOfHandlers;
    private FieldHandler fieldHandler;
    private MutableField mutableField;
    private Class fieldType;
    private FieldHandler defaultFieldHandler;

    @Before
    public void setup() {
        fieldHandlers = createMock(List.class);
        fieldHandlersIterator = createMock(Iterator.class);
        fieldHandler = createMock(FieldHandler.class);
        mutableField = createMock(MutableField.class);
        defaultFieldHandler = createMock(FieldHandler.class);
        numberOfHandlers = new Random().nextInt(100) + 1;
        fieldType = Object.class;
        defaultFieldHandlerRepository = new DefaultFieldHandlerFinder(fieldHandlers, defaultFieldHandler);
    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandlerFinder.class.isAssignableFrom(defaultFieldHandlerRepository.getClass()));
    }

    @Test
    public void canFindFirstMatchingFieldHandlerAndReturn() {
        expect(fieldHandlers.iterator()).andReturn(fieldHandlersIterator);
        expect(fieldHandlersIterator.hasNext()).andReturn(true).times(numberOfHandlers);
        expect(fieldHandlersIterator.next()).andReturn(fieldHandler).times(numberOfHandlers);
        expect(fieldHandler.supports(mutableField)).andReturn(false).times(numberOfHandlers - 1).andReturn(true);
        replay(fieldHandlers, fieldHandlersIterator, mutableField, fieldHandler);
        Object result = defaultFieldHandlerRepository.findFieldHandler(mutableField);
        verify(fieldHandlers, fieldHandlersIterator, mutableField, fieldHandler);
        assertSame(fieldHandler, result);
    }

    @Test
    public void returnsDefaultFieldHandlerIfNoRegisteredHandlersFound() {
        expect(fieldHandlers.iterator()).andReturn(fieldHandlersIterator);
        expect(fieldHandlersIterator.hasNext()).andReturn(true).times(numberOfHandlers).andReturn(false);
        expect(fieldHandlersIterator.next()).andReturn(fieldHandler).times(numberOfHandlers);
        expect(fieldHandler.supports(mutableField)).andReturn(false).times(numberOfHandlers);
        replay(fieldHandlers, fieldHandlersIterator, mutableField, fieldHandler);
        Object result = defaultFieldHandlerRepository.findFieldHandler(mutableField);
        verify(fieldHandlers, fieldHandlersIterator, mutableField, fieldHandler);
        assertSame(defaultFieldHandler, result);
    }
}
