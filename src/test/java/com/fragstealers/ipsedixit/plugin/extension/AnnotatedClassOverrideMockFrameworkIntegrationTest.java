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

package com.fragstealers.ipsedixit.plugin.extension;

import com.fragstealers.ipsedixit.annotation.Ipsedixit;
import com.fragstealers.ipsedixit.test.JavaProxyFieldHandler;
import static org.junit.Assert.*;
import org.junit.Test;

@Ipsedixit(mockFrameworkHandler = JavaProxyFieldHandler.class)
public class AnnotatedClassOverrideMockFrameworkIntegrationTest extends IpsedixitDataPopulator {
    private Runnable mock;

    @Test
    public void hasWorked() {
        String message = mock.toString();
        assertTrue(message, message.startsWith("Simple proxy for $Proxy"));
    }
}