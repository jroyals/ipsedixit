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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.ipsedixit.annotation.ArbitraryString;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser;
import net.sf.ipsedixit.integration.Configuration;
import net.sf.ipsedixit.integration.junit.IpsedixitDataPopulator;
import net.sf.ipsedixit.test.TodaysDateFieldHandler;
import org.junit.Rule;
import org.junit.Test;
import static org.apache.commons.lang.time.DateUtils.truncate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SuppliedConfigurationIpsedixitRuleIntegrationTest {
    @Rule public IpsedixitDataPopulator p = new IpsedixitDataPopulator(new Configuration() {
        public ObjectAnalyser getObjectAnalyser() {
            return new NonFinalFieldObjectAnalyser();
        }

        public List<? extends FieldHandler> getAdditionalFieldHandlers() {
            return Arrays.asList(new TodaysDateFieldHandler());
        }
    });

    private final String immutable = "immutable";
    private String arbitrary;
    @ArbitraryString(ofLength = 10) private String anotherArbitrary;
    private Date today;

    @Test
    public void hasWorked() {
        assertEquals("immutable", immutable);
        assertTrue(arbitrary.matches(".{64}"));
        assertTrue(anotherArbitrary.matches(".{10}"));
        
        Date todaysDate = truncate(new Date(), Calendar.DATE);
        assertThat(today.getTime(), equalTo(todaysDate.getTime()));
    }
}
