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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Concrete implementation of {@link net.sf.ipsedixit.core.ObjectAnalyser} that only considers a field mutable if
 * (and only if):
 * <ul>
 *   <li>The field is not final, and</li>
 *   <li>The field is annotated by any annotation in the <code>net.sf.ipsedixit</code> package
 * </ul>
 * Visibility of the field is not important, private fields are fair game so long as they are not final.
 *
 * @see net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser
 */
public class AnnotationOnlyObjectAnalyser extends NonFinalFieldObjectAnalyser {

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
        return annotation.annotationType().getPackage().getName().startsWith("net.sf.ipsedixit");
    }
}
