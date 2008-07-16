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

package com.fragstealers.ipsedixit;

import com.fragstealers.ipsedixit.test.DefaultTestConfiguration;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.math.RoundingMode;

public class DataPopulatorEnumIntegrationTest {

    private DataPopulator dataPopulator;

    @Before
    public void setup() {
        dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(new DefaultTestConfiguration());
    }

    @Test
    public void hasLongDataPopulatedAsExpected() {
        Target target = new Target();
        dataPopulator.populate(target);
        assertEquals(RoundingMode.CEILING, target.immutable);
        assertNotNull(target.defaultValue);
    }

    private static class Target {
        private final RoundingMode immutable = RoundingMode.CEILING;
        private RoundingMode defaultValue;
    }
}