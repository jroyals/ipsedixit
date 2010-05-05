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


import net.sf.ipsedixit.annotation.ArbitraryString;
import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.core.impl.AnnotationOnlyObjectAnalyser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({IpsedixitTestExecutionListener.class})
@Ipsedixit(AnnotationOnlyObjectAnalyser.class)
public class AnnotatedClassOverrideClassAnalyserIntegrationTest {
    private String immutable = "immutable";
    @ArbitraryString private String arbitrary;
    private Number mock;

    @Test
    public void hasWorked() {
        assertEquals("immutable", immutable);
        assertTrue(arbitrary.matches(".{64}"));
        assertNull(mock);
    }
}
