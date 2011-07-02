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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.sf.ipsedixit.annotation.AnnotationNumberMetaDataCreator;
import net.sf.ipsedixit.annotation.AnnotationStringMetaDataCreator;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.DataProvider;

/**
 * A repository containing the standard set of {@link net.sf.ipsedixit.core.FieldHandler} objects.
 */
public final class FieldHandlerRepository {
    private static final DataProvider DATA_PROVIDER = DefaultDataProvider.dataProvider();

    private static final List<FieldHandler> FIELD_HANDLERS = Collections.unmodifiableList(
            new ArrayList<FieldHandler>() {{
        add(new StringFieldHandler(new AnnotationStringMetaDataCreator(), DATA_PROVIDER));
        add(new IntegerFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new BooleanFieldHandler(DATA_PROVIDER));
        add(new EnumFieldHandler(DATA_PROVIDER));
        add(new LongFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new ShortFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new CharacterFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new ByteFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new DoubleFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new FloatFieldHandler(new AnnotationNumberMetaDataCreator(), DATA_PROVIDER));
        add(new ClassFieldHandler(DATA_PROVIDER));
    }});

    private FieldHandlerRepository() {
    }

    /**
     * @return the List of standard {@link net.sf.ipsedixit.core.FieldHandler}'s.
     */
    public static List<FieldHandler> getStandardFieldHandlers() {
        return FIELD_HANDLERS;
    }
}
