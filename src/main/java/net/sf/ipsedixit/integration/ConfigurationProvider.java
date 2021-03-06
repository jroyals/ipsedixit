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

package net.sf.ipsedixit.integration;

/**
 * Implementations of this interface can create a {@link Configuration},
 * given a class to configure from.
 */
public interface ConfigurationProvider {

    /**
     * @param testClass the class.
     * @return a {@link Configuration} object.
     */
    Configuration getConfiguration(Class<?> testClass);
}
