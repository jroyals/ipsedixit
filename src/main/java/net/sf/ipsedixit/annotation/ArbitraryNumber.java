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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.sf.ipsedixit.core.NumberMetaData;

/**
 * Can be applied to a numeric primitive or object wrapper type.  Using this annotation also allows customisation of
 * the value to set.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArbitraryNumber {

    /**
     * Return the minimum inclusive value to generate.
     */
    double min() default NumberMetaData.DEFAULT_MINIMUM_NUMBER;

    /**
     * Return the maximum number to generate.  Inclusive for integral types, exclusive for floating types.
     */
    double max() default NumberMetaData.DEFAULT_MAXIMUM_NUMBER;
}
