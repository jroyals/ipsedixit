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

package net.sf.ipsedixit;

/**
 * DataPopulator's are able to populate data into fields of any Object. Implementations of this interface do all the
 * work of analysing and populating data into an Object.  They are the main entry point into Ipsedixit.
 */
public interface DataPopulator {

    /**
     * Given an Object, populate it's declared fields.
     *
     * @param obj the Object whose fields should be populated with data.
     */
    void populate(Object obj);
}
