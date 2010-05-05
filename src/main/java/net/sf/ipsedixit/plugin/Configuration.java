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

package net.sf.ipsedixit.plugin;

import java.util.List;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.ObjectAnalyser;

/**
 * Defines an Ipsedixit configuration.
 */
public interface Configuration {

    /**
     * @return the ObjectAnalyser to use in this invocation of Ipsedixit.
     */
    ObjectAnalyser getObjectAnalyser();

    /**
     * @return a List of additional {@link net.sf.ipsedixit.core.FieldHandler}'s to use, over and above the standard
     * set of FieldHandler's.
     */
    List<? extends FieldHandler> getAdditionalFieldHandlers();
}
