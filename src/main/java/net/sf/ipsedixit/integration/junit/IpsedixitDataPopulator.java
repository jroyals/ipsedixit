////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2010, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package net.sf.ipsedixit.integration.junit;

import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.annotation.AnnotationConfiguration;
import net.sf.ipsedixit.integration.Configuration;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * <p>A JUnit Rule that can be used to populate your tests. To use this class, you must be using JUnit 4.8 or better.</p>
 * <p/>
 * <p>
 * For example:<br/>
 * <code>@org.junit.Rule public IpsedixitDataPopulator rule = new IpsedixitDataPopulator(this.getClass())</code>
 * </p>
 */
public class IpsedixitDataPopulator extends TestWatchman {

    private DataPopulator dataPopulator;
    private Object fixture;

    /**
     * Creates a new Rule that will use a {@link net.sf.ipsedixit.annotation.AnnotationConfiguration} to inspect your test
     * class for annotations, which will be used to configure Ipsedixit.
     */
    public IpsedixitDataPopulator() {

    }

    /**
     * Creates a new Rule that will use the supplied Configuration to populate the class with test data.
     *
     * @param configuration the Configuration to use.
     */
    public IpsedixitDataPopulator(Configuration configuration) {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        fixture = target;
        if (dataPopulator == null) {
            AnnotationConfiguration configuration = new AnnotationConfiguration(fixture.getClass());
            dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
        }
        return super.apply(base, method, target);
    }

    @Override
    public void starting(FrameworkMethod method) {
        dataPopulator.populate(fixture);
    }
}
