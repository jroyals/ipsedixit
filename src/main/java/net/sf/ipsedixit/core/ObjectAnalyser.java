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

package net.sf.ipsedixit.core;

import java.util.List;

/**
 * Instances of <code>ObjectAnalyser</code> are able to inspect an object, and find any fields that are candidates for
 * further processing.  Fields are wrapped in instances of {@link net.sf.ipsedixit.core.MutableField} for convienience.
 * <p/>
 * Ipsedixit provides a few implementations of this class which can be used.  If you have special requirements, you can
 * implement this interface yourself and configure Ipsedixit to use your implementation instead.
 */
public interface ObjectAnalyser {

    /**
     * Given an Object of any type, analyse it and return a List of fields that are candidates for further processing.
     *
     * @param obj the Object to analyse.
     * @return a List of {@link net.sf.ipsedixit.core.MutableField}'s, or an empty List if no fields exist.
     */
    List<MutableField> getMutableFields(Object obj);
}
