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

package com.fragstealers.ipsedixit.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class BetweenMatcher<T extends Comparable> extends BaseMatcher<Comparable> {
        private final Comparable minInclusive;
        private final Comparable maxInclusive;

        public
        BetweenMatcher(Comparable minInclusive, Comparable maxInclusive) {
            this.minInclusive = minInclusive;
            this.maxInclusive = maxInclusive;
        }

        public boolean matches(Object o) {
            T comparable = (T) o;
            return comparable.compareTo(minInclusive) >= 0 && comparable.compareTo(maxInclusive) <= 0;
        }

        public void describeTo(Description description) {
        }
    }
