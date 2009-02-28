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

import net.sf.ipsedixit.annotation.AnnotationNumberMetaDataRetriever;
import net.sf.ipsedixit.annotation.AnnotationStringMetaDataRetriever;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.BooleanFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.ByteFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.CharacterFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.DoubleFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.EnumFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.FloatFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.IntegerFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.StringFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.ShortFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.LongFieldHandler;
import net.sf.ipsedixit.core.RandomDataProvider;

/**
 * A repository containing the standard set of {@link net.sf.ipsedixit.core.fieldhandler.FieldHandler} objects.
 */
public final class FieldHandlerRepository {
    private static final RandomDataProvider RANDOM_DATA_PROVIDER = new DefaultRandomDataProvider();

    private static final List<FieldHandler> FIELD_HANDLERS = Collections.unmodifiableList(
            new ArrayList<FieldHandler>() {{
        add(new StringFieldHandler(new AnnotationStringMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new IntegerFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new BooleanFieldHandler(RANDOM_DATA_PROVIDER));
        add(new EnumFieldHandler(RANDOM_DATA_PROVIDER));
        add(new LongFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new ShortFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new CharacterFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new ByteFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new DoubleFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
        add(new FloatFieldHandler(new AnnotationNumberMetaDataRetriever(), RANDOM_DATA_PROVIDER));
    }});

    private FieldHandlerRepository() {
    }

    /**
     * @return the List of standard {@link net.sf.ipsedixit.core.fieldhandler.FieldHandler}'s.
     */
    public static List<FieldHandler> getStandardFieldHandlers() {
        return FIELD_HANDLERS;
    }
}
