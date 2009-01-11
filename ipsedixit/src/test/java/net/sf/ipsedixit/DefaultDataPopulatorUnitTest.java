
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

package net.sf.ipsedixit;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.FieldHandlerFinder;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.ObjectAnalyser;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class DefaultDataPopulatorUnitTest {

    private DefaultDataPopulator testDefaultDataProvider;
    private FieldHandlerFinder fieldHandlerFinder;
    private ObjectAnalyser objectAnalyser;
    private MutableField mutableField;
    private List<MutableField> mutableFields;
    private Iterator<MutableField> mutableFieldsIterator;
    private int numberOfFields;
    private Object value;
    private Object target;
    private FieldHandler fieldHandler;

    @Before
    @SuppressWarnings("unchecked")
    public void setup() {
        numberOfFields = ((int) Math.random() * 100) + 1;
        fieldHandlerFinder = createMock(FieldHandlerFinder.class);
        fieldHandler = createMock(FieldHandler.class);
        objectAnalyser = createMock(ObjectAnalyser.class);
        mutableField = createMock(MutableField.class);
        mutableFields = createMock(List.class);
        mutableFieldsIterator = createMock(Iterator.class);
        value = createMock(Object.class);
        target = createMock(Object.class);
        testDefaultDataProvider = new DefaultDataPopulator(fieldHandlerFinder, objectAnalyser);
    }

    @Test
    public void implementsInterface() {
        assertTrue(DataPopulator.class.isAssignableFrom(testDefaultDataProvider.getClass()));
    }

    @Test
    public void randomisesTestDataBeforeTestMethodExecution() {
        expect(objectAnalyser.getMutableFields(target)).andReturn(mutableFields);
        expect(mutableFields.iterator()).andReturn(mutableFieldsIterator);
        expect(mutableFieldsIterator.hasNext()).andReturn(true).times(numberOfFields).andReturn(false);
        expect(mutableFieldsIterator.next()).andReturn(mutableField).times(numberOfFields);
        expect(fieldHandlerFinder.findFieldHandler(mutableField)).andReturn(fieldHandler);
        expect(fieldHandler.getValueFor(mutableField)).andReturn(value);
        mutableField.setValue(value);
        replay(objectAnalyser, mutableFields, mutableFieldsIterator, fieldHandlerFinder, fieldHandler, mutableField);
        testDefaultDataProvider.populate(target);
        verify(objectAnalyser, mutableFields, mutableFieldsIterator, fieldHandlerFinder, fieldHandler, mutableField);
    }
}
