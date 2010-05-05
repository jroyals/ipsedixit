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

import java.util.ArrayList;
import java.util.List;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.FieldHandlerFinder;
import net.sf.ipsedixit.core.impl.DefaultFieldHandlerFinder;
import net.sf.ipsedixit.core.impl.FieldHandlerRepository;
import net.sf.ipsedixit.core.impl.LoggingNullFieldHandler;
import net.sf.ipsedixit.plugin.Configuration;

/**
 * Default factory class for creating DataPopulator instances.  This factory makes the following assumptions for the
 * DataPopulator it creates:
 *
 * <ul>
 * <li>Uses the DefaultFieldHandlerFinder</li>
 * <li>Adds additional FieldHandler instances <em>before</em> the standard handlers</li>
 * <li>The {@link net.sf.ipsedixit.core.impl.LoggingNullFieldHandler} is the default FieldHandler</li>
 * </ul>
 */
public class DefaultDataPopulatorFactory implements DataPopulatorFactory {

    /**
     * {@inheritDoc}
     */
    public DataPopulator createDefaultDataPopulator(Configuration configuration) {
        FieldHandlerFinder fieldHandlerFinder = createFieldHandlerFinder(configuration.getAdditionalFieldHandlers());

        return new DefaultDataPopulator(fieldHandlerFinder, configuration.getObjectAnalyser());
    }

    private FieldHandlerFinder createFieldHandlerFinder(List<? extends FieldHandler> additionalHandlers) {

        return new DefaultFieldHandlerFinder(composeFieldHandlers(additionalHandlers),
                new LoggingNullFieldHandler());
    }

    private List<FieldHandler> composeFieldHandlers(List<? extends FieldHandler> additionalHandlers) {
        List<FieldHandler> fieldHandlers = new ArrayList<FieldHandler>();
        fieldHandlers.addAll(additionalHandlers);
        fieldHandlers.addAll(FieldHandlerRepository.getStandardFieldHandlers());
        return fieldHandlers;
    }
}
