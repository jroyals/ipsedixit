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

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.LoggingNullFieldHandler;
import net.sf.ipsedixit.core.FieldHandlerFinder;
import net.sf.ipsedixit.core.impl.DefaultFieldHandlerFinder;
import net.sf.ipsedixit.core.impl.FieldHandlerRepository;
import net.sf.ipsedixit.configuration.Configuration;

/**
 * Default factory class for creating DataPopulator instances.  Will always return a configured instance of
 * {@link DefaultDataPopulator}.
 */
public class DefaultDataPopulatorFactory implements DataPopulatorFactory {

    /**
     * Creates a new {@link net.sf.ipsedixit.DefaultDataPopulator} that uses the following strategy to configure it's
     * internal {@link net.sf.ipsedixit.core.fieldhandler.FieldHandler} chain:
     *
     * <ul>
     * <li>Get the "before standard" FieldHandler instances from the
     * {@link net.sf.ipsedixit.configuration.Configuration#getBeforeStandardFieldHandlers()} and add them to the
     * FieldHandler chain. These FieldHandler instances will always be used first to test for a match.</li>
     * <li>Get the standard set of FieldHandler's, as defined in the
     * {@link net.sf.ipsedixit.core.impl.FieldHandlerRepository} and add them to the FieldHandler chain</li>
     * <li>Get the "after standard" FieldHandler instances from the
     * {@link net.sf.ipsedixit.configuration.Configuration#getAfterStandardFieldHandlers()} and add them to the
     * FieldHandler chain. These FieldHandler instances will always be used after all the other FieldHandler's have been
     * checked for suitability.</li>
     * <li>Add a {@link net.sf.ipsedixit.core.fieldhandler.impl.LoggingNullFieldHandler} to handle the default case
     * where no FieldHandlers can be found for a given field.</li>
     * </ul>
     *
     * @param configuration a {@link net.sf.ipsedixit.configuration.Configuration} to use.
     * @return a DataPopulator ready to use.
     */
    public DataPopulator createDataPopulator(Configuration configuration) {
        FieldHandlerFinder fieldHandlerFinder = createFieldHandlerFinder(configuration);

        return new DefaultDataPopulator(fieldHandlerFinder, configuration.getObjectAnalyser());
    }

    private DefaultFieldHandlerFinder createFieldHandlerFinder(Configuration configuration) {
        List<FieldHandler> fieldHandlerChain = composeFieldHandlers(configuration.getBeforeStandardFieldHandlers(),
                configuration.getAfterStandardFieldHandlers());
        return new DefaultFieldHandlerFinder(
                fieldHandlerChain,
                new LoggingNullFieldHandler());
    }

    private List<FieldHandler> composeFieldHandlers(List<? extends FieldHandler> beforeStandardHandlers,
            List<? extends FieldHandler> afterStandardHandlers) {
        List<FieldHandler> fieldHandlers = new ArrayList<FieldHandler>();
        fieldHandlers.addAll(beforeStandardHandlers);
        fieldHandlers.addAll(FieldHandlerRepository.getStandardFieldHandlers());
        fieldHandlers.addAll(afterStandardHandlers);
        return fieldHandlers;
    }
}
