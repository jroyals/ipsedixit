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
package net.sf.ipsedixit.integration.spring;

import java.util.EventListener;
import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({IpsedixitTestExecutionListener.class})
@Ipsedixit(NonFinalFieldObjectAnalyser.class)
public class NonAnnotatedClassIntegrationTest {
    private final String immutable = "immutable";
    private String arbitrary;
    private Number stubClass;
    private EventListener stubInterface;

    @Test
    public void hasWorked() {
        assertEquals("immutable", immutable);
        assertTrue(arbitrary.matches(".{64}"));
        assertThat(stubClass.toString(), Matchers.containsString("Proxy for java.lang.Number defined by field stubClass"));
        assertThat(stubInterface.toString(), Matchers.containsString("Proxy for java.util.EventListener defined by field stubInterface"));
    }
}
