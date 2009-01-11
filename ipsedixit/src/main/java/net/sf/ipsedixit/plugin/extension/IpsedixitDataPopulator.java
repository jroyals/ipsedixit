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

package net.sf.ipsedixit.plugin.extension;

import java.util.List;

import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.annotation.AnnotationConfiguration;
import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.configuration.Configuration;
import net.sf.ipsedixit.configuration.ConfigurationProvider;
import net.sf.ipsedixit.configuration.DefaultConfiguration;
import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;

/**
 * Extend this class if you cannot use annotations, but still want to use Ipsedixit to populate your data.  To configure
 * Ipsedixit, override the relevant methods in {@link DefaultConfiguration} in your class.  Otherwise, the defaults
 * will be used.
 */
public class IpsedixitDataPopulator implements Configuration, ConfigurationProvider {

    private Configuration defaultConfiguration;

    /**
     * Creates a new IpsedixitDataPopulator.  Data is populated at this point.
     */
    public IpsedixitDataPopulator() {
        defaultConfiguration = new DefaultConfiguration();
        Configuration configuration = getConfiguration(getClass());
        DataPopulator dataPopulator = DataPopulatorFactory.INSTANCE.createDataPopulator(configuration);
        dataPopulator.populate(this);
    }

        /**
     * {@inheritDoc}
     */
    public ObjectAnalyser getObjectAnalyser() {
        return defaultConfiguration.getObjectAnalyser();
    }

    /**
     * {@inheritDoc}
     */
    public List<? extends FieldHandler> getAfterStandardFieldHandlers() {
        return defaultConfiguration.getAfterStandardFieldHandlers();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<? extends FieldHandler> getBeforeStandardFieldHandlers() {
        return defaultConfiguration.getBeforeStandardFieldHandlers();
    }

    /**
     * Override only if you want to use a different Configuration mechanism.  By default this method returns an {@link
     * net.sf.ipsedixit.annotation.AnnotationConfiguration} instance.
     *
     * @param testClass the class under test.
     * @return the {@link net.sf.ipsedixit.configuration.Configuration} to use.
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
