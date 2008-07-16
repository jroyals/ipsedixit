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

package com.fragstealers.ipsedixit.plugin.extension;

import com.fragstealers.ipsedixit.DataPopulator;
import com.fragstealers.ipsedixit.DataPopulatorFactory;
import com.fragstealers.ipsedixit.core.ObjectAnalyser;
import com.fragstealers.ipsedixit.core.FieldHandler;
import com.fragstealers.ipsedixit.annotation.Ipsedixit;
import com.fragstealers.ipsedixit.annotation.AnnotationConfiguration;
import com.fragstealers.ipsedixit.core.impl.NonFinalFieldsObjectAnalyser;
import com.fragstealers.ipsedixit.core.impl.EasyMock2ClassExtensionFieldHandler;
import com.fragstealers.ipsedixit.plugin.Configuration;
import com.fragstealers.ipsedixit.plugin.ConfigurationProvider;

import java.util.Collections;
import java.util.List;

public class IpsedixitDataPopulator implements Configuration, ConfigurationProvider {

    public IpsedixitDataPopulator() {
        Configuration configuration = getConfiguration(getClass());
        DataPopulator dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
        dataPopulator.populate(this);
    }

    public ObjectAnalyser getClassAnalyser() {
        return new NonFinalFieldsObjectAnalyser();
    }

    public FieldHandler getMockingFrameworkHandler() {
        return new EasyMock2ClassExtensionFieldHandler();
    }

    @SuppressWarnings("unchecked")
    public List<? extends FieldHandler> getAdditionalFieldHandlers() {
        return Collections.EMPTY_LIST;
    }

    /**
     * Override only if you want to use a different Configuration mechanism.  By default this method returns an
     * {@link com.fragstealers.ipsedixit.annotation.AnnotationConfiguration} instance.
     *
     * @param testClass the class under test.
     * @return the {@link com.fragstealers.ipsedixit.plugin.Configuration} to use.
     */
    public Configuration getConfiguration(Class<?> testClass) {
        Configuration configuration = this;
        if (isAnnotated()) {
            configuration = new AnnotationConfiguration(this.getClass());
        }
        return configuration;
    }

    private boolean isAnnotated() {
        return this.getClass().isAnnotationPresent(Ipsedixit.class)
                || this.getClass().getPackage().isAnnotationPresent(Ipsedixit.class);
    }
}
