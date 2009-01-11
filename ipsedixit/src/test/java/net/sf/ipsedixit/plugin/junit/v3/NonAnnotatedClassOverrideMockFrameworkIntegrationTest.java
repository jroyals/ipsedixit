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

package net.sf.ipsedixit.plugin.junit.v3;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.JavaProxyFieldHandler;
import net.sf.ipsedixit.plugin.junit.JUnit38IpsedixitTestCase;

public class NonAnnotatedClassOverrideMockFrameworkIntegrationTest extends JUnit38IpsedixitTestCase {
    private Runnable mock;
    private Date empty;

    public void testHasWorked() {
        String message = mock.toString();
        assertTrue(message, message.contains("Java proxy"));
        assertNull(empty); // we overrode the post handlers and did not set any handler to deal with concrete classes.
    }

    public List<? extends FieldHandler> getAfterStandardFieldHandlers() {
        return Arrays.asList(new JavaProxyFieldHandler());
    }
}