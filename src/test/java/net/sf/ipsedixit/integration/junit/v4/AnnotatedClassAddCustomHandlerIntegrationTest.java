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
package net.sf.ipsedixit.integration.junit.v4;


import java.util.Calendar;
import java.util.Date;
import net.sf.ipsedixit.annotation.Arbitrary;
import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.integration.junit.JUnit4IpsedixitTestRunner;
import net.sf.ipsedixit.test.TodaysDateFieldHandler;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.apache.commons.lang.time.DateUtils.truncate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(JUnit4IpsedixitTestRunner.class)
@Ipsedixit(additionalHandlers = {TodaysDateFieldHandler.class})
public class AnnotatedClassAddCustomHandlerIntegrationTest {
    @Arbitrary private Date today;
    @Arbitrary private Date stubDate;

    @Test
    public void hasWorked() {
        assertThat(stubDate.toString(), Matchers.containsString("Proxy for java.util.Date defined by field stubDate"));
        Date todaysDate = truncate(new Date(), Calendar.DATE);
        assertThat(today.getTime(), equalTo(todaysDate.getTime()));
    }
}
