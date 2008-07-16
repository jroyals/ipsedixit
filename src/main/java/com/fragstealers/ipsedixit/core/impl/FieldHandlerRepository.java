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

package com.fragstealers.ipsedixit.core.impl;

import com.fragstealers.ipsedixit.annotation.AnnotationNumberMetaDataCreator;
import com.fragstealers.ipsedixit.annotation.AnnotationStringMetaDataCreator;
import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.core.RandomDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FieldHandlerRepository {
    private static final RandomDataProvider RANDOM_DATA_PROVIDER = new DefaultRandomDataProvider();

    private static final List<FieldHandler> FIELD_HANDLERS = Collections.unmodifiableList(
            new ArrayList<FieldHandler>() {{
        add(new StringFieldHandler(new AnnotationStringMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new IntegerFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new BooleanFieldHandler(RANDOM_DATA_PROVIDER));
        add(new EnumFieldHandler(RANDOM_DATA_PROVIDER));
        add(new LongFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new ShortFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new CharacterFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new ByteFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new DoubleFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
        add(new FloatFieldHandler(new AnnotationNumberMetaDataCreator(), RANDOM_DATA_PROVIDER));
    }});

    private FieldHandlerRepository() {
    }

    public static List<FieldHandler> getStandardFieldHandlers() {
        return FIELD_HANDLERS;
    }
}
