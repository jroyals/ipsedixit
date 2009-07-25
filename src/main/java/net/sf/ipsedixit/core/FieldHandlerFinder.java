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

/**
 * Finds a {@link net.sf.ipsedixit.core.FieldHandler} implementation for a given
 * {@link net.sf.ipsedixit.core.MutableField}.
 */
public interface FieldHandlerFinder {

    /**
     * Finds a {@link net.sf.ipsedixit.core.FieldHandler} implementation for a given
     * {@link net.sf.ipsedixit.core.MutableField}.  This method must not return <code>null</code>
     * @param mutableField the mutableField.
     * @return a {@link net.sf.ipsedixit.core.FieldHandler} that can handle the
     * {@link net.sf.ipsedixit.core.MutableField}
     */
    FieldHandler findFieldHandler(MutableField mutableField);
}
