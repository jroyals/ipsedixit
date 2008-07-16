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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationOnlyObjectAnalyser extends AbstractNonFinalFieldObjectAnalyser {

    protected boolean isFieldAllowed(Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            if (hasCompatibleAnnotation(annotation)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCompatibleAnnotation(Annotation annotation) {
        return annotation.annotationType().getPackage().getName().startsWith("com.fragstealers.ipsedixit");
    }
}
