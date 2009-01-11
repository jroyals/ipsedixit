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

import net.sf.ipsedixit.annotation.ArbitraryString;

public final class ArbitraryClass {
    @ArbitraryString private String foo = "mutable";                    // non-final and annotated

    private final String bar = "immutable";                             // final, non annotated
    private String baz = "immutable";                                   // non final, non annotated
    @ArbitraryString private final String qux = "immutable";            // final fields must never be changed
    @SuppressWarnings("Unchecked") private String quux = "immutable";   // non-final, annotation is not compatible
}
