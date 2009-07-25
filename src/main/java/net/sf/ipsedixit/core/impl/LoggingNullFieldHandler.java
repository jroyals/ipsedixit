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

import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.FieldHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a catch-all type FieldHandler that simply logs a warning to the console.
 */
public class LoggingNullFieldHandler implements FieldHandler {
    private static final Log LOGGER = LogFactory.getLog(LoggingNullFieldHandler.class);

    /**
     * Always returns <code>null</code>, however will log out a warning to the configured Commons Logging logger
     * advising that a handler could not be found for a field of the type.
     *
     * @param mutableField the MutableField that has no FieldHandler's available.
     * @return <code>null</code>
     */
    public Object getValueFor(MutableField mutableField) {
        LOGGER.warn("No handlers registered for type " + mutableField.getType()
                + " which is declared in field named " + mutableField.getName());
        return null;
    }

    /**
     * @param mutableField it doesn't matter.
     * @return <code>true</code>, this handler supports all types.
     */
    public boolean supports(MutableField mutableField) {
        return true;
    }
}
