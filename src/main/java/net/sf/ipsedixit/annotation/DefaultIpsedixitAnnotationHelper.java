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

package net.sf.ipsedixit.annotation;

import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser;
import net.sf.ipsedixit.core.impl.EasyMock2ClassExtensionFieldHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of IpsedixitAnnotationHelper.
 */
public class DefaultIpsedixitAnnotationHelper implements IpsedixitAnnotationHelper {

    /**
     * {@inheritDoc}
     */
    public ObjectAnalyser getClassAnalyser(Class<?> clazz) {
        Ipsedixit annotation = getAnnotation(clazz);
        return annotation == null ? new NonFinalFieldObjectAnalyser() : instantiateClass(annotation.value());
    }

    /**
     * {@inheritDoc}
     */
    public FieldHandler getMockFrameworkFieldHandler(Class<?> clazz) {
        Ipsedixit annotation = getAnnotation(clazz);
        return annotation == null ? new EasyMock2ClassExtensionFieldHandler()
                : instantiateClass(annotation.mockFrameworkHandler());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<FieldHandler> getAdditionalFieldHandlers(Class<?> clazz) {
        Ipsedixit annotation = getAnnotation(clazz);
        if (annotation == null || annotation.additionalHandlers() == null) {
            return Collections.EMPTY_LIST;
        }

        return instantiateCustomFieldHandlers(annotation);
    }

    private List<FieldHandler> instantiateCustomFieldHandlers(Ipsedixit annotation) {
        List<FieldHandler> fieldHanders = new ArrayList<FieldHandler>(annotation.additionalHandlers().length);
        for (Class<? extends FieldHandler> clazz : annotation.additionalHandlers()) {
            fieldHanders.add(instantiateClass(clazz));
        }
        return fieldHanders;
    }

    private Ipsedixit getAnnotation(Class<?> clazz) {
        Ipsedixit annotation = clazz.getAnnotation(Ipsedixit.class);
        if (annotation == null) {
            return clazz.getPackage().getAnnotation(Ipsedixit.class);
        }
        return annotation;
    }

    private <T> T instantiateClass(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
