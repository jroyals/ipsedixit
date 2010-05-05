////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2010, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package net.sf.ipsedixit.plugin.junit;

import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.annotation.AnnotationConfiguration;
import net.sf.ipsedixit.plugin.Configuration;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * <p>A JUnit Rule that can be used to populate your tests. To use this class, you must be using JUnit 4.8 or better.</p>
 *
 * <p>
 * For example:<br/>
 * <code>@org.junit.Rule public PopulateWithArbitraryDataRule rule = new PopulateWithArbitraryDataRule(this.getClass())</code>
 * </p>
 *
 *
 */
public class PopulateWithArbitraryDataRule extends TestWatchman {

    private final DataPopulator dataPopulator;
    private Object fixture;

    /**
     * Creates a new Rule that will use the supplied Configuration to populate the class with test data.
     *
     * @param configuration the Configuration to use.
     */
    public PopulateWithArbitraryDataRule(Configuration configuration) {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
    }

    /**
     * Creates a new Rule that will use a {@link net.sf.ipsedixit.annotation.AnnotationConfiguration} to inspect your test
     * class for annotations, which will be used to configure Ipsedixit.
     *
     * @param clazz the class under test, that contains annotations.
     */
    public PopulateWithArbitraryDataRule(Class clazz) {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new AnnotationConfiguration(clazz));
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        fixture = target;
        return super.apply(base, method, target);
    }

    @Override
    public void starting(FrameworkMethod method) {
        dataPopulator.populate(fixture);
    }
}
