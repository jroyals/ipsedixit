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

package net.sf.ipsedixit.plugin.junit;

import java.util.List;

import junit.framework.TestCase;
import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.annotation.AnnotationConfiguration;
import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.configuration.Configuration;
import net.sf.ipsedixit.configuration.ConfigurationProvider;
import net.sf.ipsedixit.configuration.DefaultConfiguration;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.ObjectAnalyser;

/**
 * <p>There are two ways in which to use Ipsedixit with JUnit 3.8.</p>
 *
 * <p>You can set up your JUnit test in the following way:
<pre>
 public void setUp() {
    DataPopulator dp = DataPopulatorFactory.INSTANCE.createDataPopulator(new DefaultConfiguration());
    dp.populate(this);
    ... other setup stuff
 }
</pre>
 * You can choose to provide a custom implementation of net.sf.ipsedixit.Configuration if you wish.</p>
 *
 * <p>Otherwise, you may extend this class instead of {@link junit.framework.TestCase}, in which case the
 * {@link #setUp()} is overridden and will do this for you.  However, if you need to set up your own data, you must
 * override {@link #onSetUp()} instead.</p>
 */
public class JUnit38IpsedixitTestCase extends TestCase implements Configuration, ConfigurationProvider {

    private Configuration defaultConfiguration;

    /**
     * {@inheritDoc}
     */
    public final void setUp() {
        defaultConfiguration = new DefaultConfiguration();
        Configuration configuration = getConfiguration(getClass());
        DataPopulator dataPopulator = DataPopulatorFactory.INSTANCE.createDataPopulator(configuration);
        dataPopulator.populate(this);
        onSetUp();
    }

    /**
     *
     */
    protected void onSetUp() {

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
