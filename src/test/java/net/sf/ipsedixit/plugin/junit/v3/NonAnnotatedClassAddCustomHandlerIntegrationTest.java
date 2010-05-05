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

/**
 *
 */
package net.sf.ipsedixit.plugin.junit.v3;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.plugin.junit.JUnit38IpsedixitTestCase;
import net.sf.ipsedixit.test.TodaysDateFieldHandler;
import org.hamcrest.Matchers;
import static org.apache.commons.lang.time.DateUtils.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NonAnnotatedClassAddCustomHandlerIntegrationTest extends JUnit38IpsedixitTestCase {
    private Date today;
    private Date stubDate;

    public void testHasWorked() {
                assertThat(stubDate.toString(), Matchers.containsString("Proxy for java.util.Date defined by field stubDate"));
        Date todaysDate = truncate(new Date(), Calendar.DATE);
        assertThat(today.getTime(), equalTo(todaysDate.getTime()));
    }

    public List<? extends FieldHandler> getAdditionalFieldHandlers() {
        return Arrays.asList(new TodaysDateFieldHandler());
    }
}
