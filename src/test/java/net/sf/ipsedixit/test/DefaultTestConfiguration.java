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

package net.sf.ipsedixit.test;

import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.impl.NonFinalFieldsObjectAnalyser;
import net.sf.ipsedixit.core.impl.EasyMock2ClassExtensionFieldHandler;
import net.sf.ipsedixit.plugin.Configuration;

import java.util.Collections;
import java.util.List;

public class DefaultTestConfiguration implements Configuration {

    public ObjectAnalyser getClassAnalyser() {
        return new NonFinalFieldsObjectAnalyser();
    }

    public FieldHandler getMockingFrameworkHandler() {
        return new EasyMock2ClassExtensionFieldHandler();
    }

    @SuppressWarnings("unchecked")
    public List<? extends FieldHandler>getAdditionalFieldHandlers() {
        return Collections.EMPTY_LIST;
    }
}
