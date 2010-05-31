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

package net.sf.ipsedixit.integration.spring;

import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.annotation.AnnotationConfiguration;
import net.sf.ipsedixit.integration.Configuration;
import net.sf.ipsedixit.integration.ConfigurationProvider;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Integration with Spring by providing an implementation of
 * {@link org.springframework.test.context.TestExecutionListener}. Add this to your Spring-based unit tests by
 * including the following annotation:
 * <code>
 * @RunWith(SpringJUnit4ClassRunner.class)<br/>
 * @TestExecutionListeners({IpsedixitTestExecutionListener.class})
 * </code>
 */
public class IpsedixitTestExecutionListener extends AbstractTestExecutionListener implements ConfigurationProvider {

    /**
     * As the name suggests, this runs before each test method.  Ipsedixit will inspect and populate your test object
     * at this point.
     *
     * @param testContext the {@link org.springframework.test.context.TestContext}, as passed through from Spring.
     * @throws Exception is something goes bump in the night (or day, for that matter).
     */
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Class<?> testClass = testContext.getTestClass();
        Configuration configuration = getConfiguration(testClass);
        DataPopulator dataPopulator = getDefaultDataPopulator(configuration);
        dataPopulator.populate(testContext.getTestInstance());
    }

    /**
     * Get the default DataPopulator instance.
     * @param configuration the {@link net.sf.ipsedixit.integration.Configuration} to use when getting the DataPopulator.
     * @return the DataPopulator to use.
     */
    protected DataPopulator getDefaultDataPopulator(Configuration configuration) {
        return DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
    }

    /**
     * Override only if you want to use a different Configuration mechanism.  By default this method returns an {@link
     * net.sf.ipsedixit.annotation.AnnotationConfiguration} instance.
     *
     * @param testClass the class under test.
     * @return the {@link net.sf.ipsedixit.integration.Configuration} to use.
     */
    public Configuration getConfiguration(Class<?> testClass) {
        return new AnnotationConfiguration(testClass);
    }
}
