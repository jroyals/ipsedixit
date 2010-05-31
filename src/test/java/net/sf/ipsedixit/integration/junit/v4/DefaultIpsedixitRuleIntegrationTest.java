////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2010, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////
package net.sf.ipsedixit.integration.junit.v4;

import net.sf.ipsedixit.annotation.Arbitrary;
import net.sf.ipsedixit.annotation.ArbitraryString;
import net.sf.ipsedixit.integration.junit.IpsedixitDataPopulator;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultIpsedixitRuleIntegrationTest {
    @Rule public IpsedixitDataPopulator p = new IpsedixitDataPopulator();

    private String immutable = "immutable";
    @Arbitrary private String arbitrary;
    @ArbitraryString(ofLength = 10) private String anotherArbitrary;
    private Number mock;

    @Test
    public void hasWorked() {
        assertEquals("immutable", immutable);
        assertTrue(arbitrary.matches(".{64}"));
        assertTrue(anotherArbitrary.matches(".{10}"));
        assertNull(mock);
    }
}