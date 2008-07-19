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
import net.sf.ipsedixit.plugin.Configuration;

import java.util.List;

public class AnnotationConfiguration implements Configuration {

    private static final IpsedixitAnnotationHelper IPSEDIXIT_ANNOTATION_HELPER = new DefaultIpsedixitAnnotationHelper();

    private final Class clazz;

    public AnnotationConfiguration(Class clazz) {
        this.clazz = clazz;
    }

    public ObjectAnalyser getObjectAnalyser() {
        return IPSEDIXIT_ANNOTATION_HELPER.getClassAnalyser(clazz);
    }

    public FieldHandler getMockingFrameworkHandler() {
        return IPSEDIXIT_ANNOTATION_HELPER.getMockFrameworkFieldHandler(clazz);
    }

    public List<? extends FieldHandler> getAdditionalFieldHandlers() {
        return IPSEDIXIT_ANNOTATION_HELPER.getAdditionalFieldHandlers(clazz);
    }
}
