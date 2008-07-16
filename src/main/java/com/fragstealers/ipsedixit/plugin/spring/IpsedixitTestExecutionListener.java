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

package com.fragstealers.ipsedixit.plugin.spring;

import com.fragstealers.ipsedixit.DataPopulator;
import com.fragstealers.ipsedixit.DataPopulatorFactory;
import com.fragstealers.ipsedixit.annotation.AnnotationConfiguration;
import com.fragstealers.ipsedixit.plugin.Configuration;
import com.fragstealers.ipsedixit.plugin.ConfigurationProvider;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class IpsedixitTestExecutionListener extends AbstractTestExecutionListener implements ConfigurationProvider {

    public void beforeTestMethod(TestContext testContext) throws Exception {
        Class<?> testClass = testContext.getTestClass();
        Configuration configuration = getConfiguration(testClass);
        DataPopulator dataPopulator = getDefaultDataPopulator(configuration);
        dataPopulator.populate(testContext.getTestInstance());
    }

    protected DataPopulator getDefaultDataPopulator(Configuration configuration) {
        return DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
    }

    /**
     * Override only if you want to use a different Configuration mechanism.  By default this method returns an
     * {@link com.fragstealers.ipsedixit.annotation.AnnotationConfiguration} instance.
     *
     * @param testClass the class under test.
     * @return the {@link com.fragstealers.ipsedixit.plugin.Configuration} to use.
     */
    public Configuration getConfiguration(Class<?> testClass) {
        return new AnnotationConfiguration(testClass);
    }
}
