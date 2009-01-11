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

package net.sf.ipsedixit.plugin.extension;

import net.sf.ipsedixit.annotation.Ipsedixit;
import net.sf.ipsedixit.core.fieldhandler.impl.JavaProxyFieldHandler;
import static org.junit.Assert.*;
import org.junit.Test;

@Ipsedixit(postHandlers = JavaProxyFieldHandler.class)
public class AnnotatedClassRegisterAfterHandlersIntegrationTest extends IpsedixitDataPopulator {
    private Number mockClass;
    private Runnable mockInterface;

    @Test
    public void hasWorked() {
        assertNull(mockClass); // we dont have a handler registered for this any more because we've overridden the "after" handler...
        assertTrue(mockInterface.toString().contains("Java proxy"));
    }
}