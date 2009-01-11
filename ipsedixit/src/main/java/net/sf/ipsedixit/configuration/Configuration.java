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

package net.sf.ipsedixit.configuration;

import java.util.List;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
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
     * @return a List of additional {@link net.sf.ipsedixit.core.fieldhandler.FieldHandler}'s to use, which will be be
     * a lower priority than the standard FieldHandlers in Ipsedixit.  This is where handlers for Object types would
     * typically be registered.
     */
    List<? extends FieldHandler> getAfterStandardFieldHandlers();

    /**
     * @return a List of additional {@link net.sf.ipsedixit.core.fieldhandler.FieldHandler}'s to use, which will be be
     * a higher priority than the standard FieldHandlers in Ipsedixit. This is where standard Ipsedixit handlers can be
     * overridden if necessary.
     */
    List<? extends FieldHandler> getBeforeStandardFieldHandlers();
}
