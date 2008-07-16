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

import com.fragstealers.ipsedixit.core.MutableField;
import com.fragstealers.ipsedixit.core.FieldHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class JavaProxyFieldHandler implements FieldHandler, InvocationHandler {

    public Object getValueFor(MutableField mutableField) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {mutableField.getType()}, this);
    }

    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return !Modifier.isFinal(type.getModifiers()) && Object.class.isAssignableFrom(type) && type.isInterface();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("toString".equals(method.getName())) {
            return "Simple proxy for " + proxy.getClass().getName();
        }
        return null;
    }
}
