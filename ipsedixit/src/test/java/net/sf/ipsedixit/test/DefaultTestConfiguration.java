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

import java.util.Collections;
import java.util.List;

import net.sf.ipsedixit.configuration.Configuration;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser;

public class DefaultTestConfiguration implements Configuration {

    public ObjectAnalyser getObjectAnalyser() {
        return new NonFinalFieldObjectAnalyser();
    }

    public List<? extends FieldHandler> getAfterStandardFieldHandlers() {
        return Collections.EMPTY_LIST;
    }

    @SuppressWarnings("unchecked")
    public List<? extends FieldHandler> getBeforeStandardFieldHandlers() {
        return Collections.EMPTY_LIST;
    }
}
