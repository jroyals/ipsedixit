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

package com.fragstealers.ipsedixit;

import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.core.impl.FieldHandlerRepository;
import com.fragstealers.ipsedixit.core.FieldHandlerFinder;
import com.fragstealers.ipsedixit.core.impl.FieldHandlerDelegate;
import com.fragstealers.ipsedixit.core.impl.DefaultFieldHandlerFinder;
import com.fragstealers.ipsedixit.core.impl.LoggingNullFieldHandler;
import com.fragstealers.ipsedixit.plugin.Configuration;

import java.util.List;
import java.util.ArrayList;

public class DefaultDataPopulatorFactory implements DataPopulatorFactory {

    public DataPopulator createDefaultDataPopulator(Configuration configuration) {
        FieldHandlerFinder fieldHandlerFinder = createFieldHandlerFinder(configuration.getAdditionalFieldHandlers(),
                configuration.getMockingFrameworkHandler());

        FieldHandler fieldHandler = new FieldHandlerDelegate(fieldHandlerFinder);
        return new DefaultDataPopulator(fieldHandler, configuration.getClassAnalyser());
    }

    private FieldHandlerFinder createFieldHandlerFinder(List<? extends FieldHandler> additionalHandlers,
            FieldHandler mockingFrameworkFieldHandler) {

        return new DefaultFieldHandlerFinder(composeFieldHandlers(additionalHandlers, mockingFrameworkFieldHandler),
                new LoggingNullFieldHandler());
    }

    private List<FieldHandler> composeFieldHandlers(List<? extends FieldHandler> additionalHandlers,
            FieldHandler mockingFrameworkFieldHandler) {
        List<FieldHandler> fieldHandlers = new ArrayList<FieldHandler>();
        fieldHandlers.addAll(additionalHandlers);
        fieldHandlers.addAll(FieldHandlerRepository.getStandardFieldHandlers());
        fieldHandlers.add(mockingFrameworkFieldHandler);
        return fieldHandlers;
    }
}
