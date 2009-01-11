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

package net.sf.ipsedixit.core.fieldhandler.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.MutableField;

/**
 * A handler that wraps a Java Proxy around an interface.
 */
public class JavaProxyFieldHandler implements FieldHandler {

    /**
     * @param mutableField a representation of the Field that should be created.
     * @return a Java proxy dummy object of the same type as the MutableField.
     */
    public Object getValueFor(MutableField mutableField) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mutableField.getType()},
                new MutableFieldAwareInvocationHandler(mutableField));
    }

    /**
     *
     * @param mutableField the <code>MutableField</code> to test.
     * @return true if the MutableField is an Interface type.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return type.isInterface();
    }

    private static final class MutableFieldAwareInvocationHandler implements InvocationHandler {
        private final MutableField mutableField;

        private MutableFieldAwareInvocationHandler(MutableField mutableField) {
            this.mutableField = mutableField;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("toString".equals(method.getName())) {
                return proxy.getClass().getName() + " : Java proxy for interface " + mutableField.getType().getName()
                        + " defined by field " + mutableField.getName();
            }
            throw new UnsupportedOperationException("Cannot call method " + method.getName()
                    + " on a dummy object.  Did you mean to use a mock instead?");
        }
    }
}
