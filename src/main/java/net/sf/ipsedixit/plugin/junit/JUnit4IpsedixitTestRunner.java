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

import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.annotation.AnnotationConfiguration;
import net.sf.ipsedixit.plugin.Configuration;
import net.sf.ipsedixit.plugin.ConfigurationProvider;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;

/**
 * Integration with JUnit4 by providing an extension of {@link org.junit.internal.runners.JUnit4ClassRunner}. Add this
 * annotation to your unit tests to use Ipsedixit:
 * <p/>
 * <code>@RunWith(JUnit4IpsedixitTestRunner.class)</code>
 */
public class JUnit4IpsedixitTestRunner extends JUnit4ClassRunner implements ConfigurationProvider {

    private final DataPopulator dataPopulator;

    /**
     * Create a new JUnit4IpsedixitTestRunner.  This is usually called by the JUnit4 runtime.
     * @param clazz the test class.
     * @throws InitializationError if something goes pop.
     */
    public JUnit4IpsedixitTestRunner(Class clazz) throws InitializationError {
        super(clazz);
        Configuration configuration = getConfiguration(clazz);
        dataPopulator = getDefaultDataPopulator(configuration);
    }

    /**
     * Get the default DataPopulator instance.
     * @param configuration the {@link net.sf.ipsedixit.plugin.Configuration} to use when getting the DataPopulator.
     * @return the DataPopulator to use.
     */
    protected DataPopulator getDefaultDataPopulator(Configuration configuration) {
        return DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
    }

    /**
     * Overrides the JUnit4ClassRunner by using the DataPopulator to populate the test object before running it.
     *
     * @return the test object.
     * @throws Exception if something goes bang.
     */
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        dataPopulator.populate(test);
        return test;
    }

    /**
     * Override only if you want to use a different Configuration mechanism.  By default this method returns an {@link
     * net.sf.ipsedixit.annotation.AnnotationConfiguration} instance.
     *
     * @param testClass the class under test.
     * @return the {@link net.sf.ipsedixit.plugin.Configuration} to use.
     */
    public Configuration getConfiguration(Class<?> testClass) {
        return new AnnotationConfiguration(testClass);
    }
}
