package net.sf.ipsedixit.plugin.junit.v3;

import java.io.Serializable;

import junit.framework.TestCase;
import net.sf.ipsedixit.DataPopulator;
import net.sf.ipsedixit.DataPopulatorFactory;
import net.sf.ipsedixit.configuration.DefaultConfiguration;

public class NonExtensionIntegrationTest extends TestCase {
    private final String immutable = "immutable";
    private String arbitrary;
    private Number mockClass;
    private Serializable mockInterface;

    public void setUp() {
        DataPopulator dataPopulator = DataPopulatorFactory.INSTANCE.createDataPopulator(new DefaultConfiguration());
        dataPopulator.populate(this);
    }

    public void testDataHasBeenPopulated() {
        assertEquals("immutable", immutable);
        assertTrue(arbitrary.matches(".{64}"));
        assertTrue(mockClass.toString().contains("CGLIB"));
        assertTrue(mockInterface.toString().contains("Java proxy"));
    }
}
