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

import java.util.ArrayList;
import java.util.List;

import net.sf.ipsedixit.configuration.Configuration;
import net.sf.ipsedixit.configuration.DefaultConfiguration;
import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser;

/**
 * Implementation of Configuration that reads annotations on the class.
 */
public class AnnotationConfiguration implements Configuration {

    private final Class clazz;
    private final Configuration defaultConfiguration;

    /**
     * Creates a new AnnotationConfiguration.
     *
     * @param clazz the class containing Ipsedixit annotations.
     */
    public AnnotationConfiguration(Class clazz) {
        this.clazz = clazz;
        defaultConfiguration = new DefaultConfiguration();
    }

    /**
     * Returns the {@link net.sf.ipsedixit.core.ObjectAnalyser} as specified on the annotation, or if not supplied,
     * returns the default ObjectAnalyser.
     *
     * @return the ObjectAnalyser that Ipsedixit will use.
     * @see net.sf.ipsedixit.configuration.DefaultConfiguration
     */
    public ObjectAnalyser getObjectAnalyser() {
        Ipsedixit annotation = getAnnotation(clazz);
        return annotation == null ? new NonFinalFieldObjectAnalyser() : instantiateClass(annotation.value());
    }

    /**
     * {@inheritDoc}
     */
    public List<? extends FieldHandler> getAfterStandardFieldHandlers() {
        Ipsedixit annotation = getAnnotation(clazz);
        if (annotation == null || annotation.postHandlers() == null || annotation.postHandlers().length == 0) {
            return defaultConfiguration.getAfterStandardFieldHandlers();
        }
        return instantiateFieldHandlers(annotation.postHandlers());
    }

    /**
     * {@inheritDoc}
     */
    public List<? extends FieldHandler> getBeforeStandardFieldHandlers() {
        Ipsedixit annotation = getAnnotation(clazz);
        if (annotation == null || annotation.preHandlers() == null || annotation.preHandlers().length == 0) {
            return defaultConfiguration.getBeforeStandardFieldHandlers();
        }

        return instantiateFieldHandlers(annotation.preHandlers());
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

    private List<FieldHandler> instantiateFieldHandlers(Class<? extends FieldHandler>[] classes) {
        List<FieldHandler> fieldHanders = new ArrayList<FieldHandler>(classes.length);
        for (Class<? extends FieldHandler> clazz : classes) {
            fieldHanders.add(instantiateClass(clazz));
        }
        return fieldHanders;
    }
}
