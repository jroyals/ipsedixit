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
package net.sf.ipsedixit.plugin.spring;


import java.util.Date;

import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.test.TodaysDateFieldHandler;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({IpsedixitTestExecutionListener.class})
@Ipsedixit(preHandlers = {TodaysDateFieldHandler.class})
public class AnnotatedClassAddCustomHandlerIntegrationTest {
    private Date today;
    private Date mockDate;

    @Test
    public void hasWorked() {
        assertTrue(mockDate.toString().contains("CGLIB")); // this is still a mock
        assertTrue(today.toString().contains("Hello World")); // this is not, it is handled by TodaysDateFieldHandler
    }
}