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

package net.sf.ipsedixit.core.impl;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import org.objenesis.ObjenesisStd;

/**
 * Adapted from Mockito's ClassImposterizer class. This enables a CGLIB proxy to be created over a class with no default constructor.
 */
final class CGLIBHelper {

    public static final CGLIBHelper INSTANCE = new CGLIBHelper();

    private static final CallbackFilter IGNORE_BRIDGE_METHODS = new CallbackFilter() {
        public int accept(Method method) {
            return method.isBridge() ? 1 : 0;
        }
    };

    private ObjenesisStd objenesis = new ObjenesisStd();

    private CGLIBHelper() {
    }

    public <T> T createProxy(final MethodInterceptor interceptor, Class<T> mockedType, Class<?>... ancillaryTypes) {
        try {
            setConstructorsAccessible(mockedType, true);
            Class<?> proxyClass = createProxyClass(mockedType, ancillaryTypes);
            return mockedType.cast(createProxy(proxyClass, interceptor));
        } finally {
            setConstructorsAccessible(mockedType, false);
        }
    }

    private void setConstructorsAccessible(Class<?> mockedType, boolean accessible) {
        for (Constructor<?> constructor : mockedType.getDeclaredConstructors()) {
            constructor.setAccessible(accessible);
        }
    }

    private Class<?> createProxyClass(Class<?> mockedType, Class<?>... interfaces) {
        Class<?> typeToMock = mockedType;

        if (typeToMock == Object.class) {
            typeToMock = ClassWithSuperclassToWorkAroundCglibBug.class;
        }

        Enhancer enhancer = new NonConstructorFilteringEnhancer();

        enhancer.setClassLoader(typeToMock.getClassLoader());
        enhancer.setUseFactory(true);
        if (typeToMock.isInterface()) {
            enhancer.setSuperclass(Object.class);
            enhancer.setInterfaces(prepend(typeToMock, interfaces));
        } else {
            enhancer.setSuperclass(typeToMock);
            enhancer.setInterfaces(interfaces);
        }
        enhancer.setCallbackTypes(new Class[]{MethodInterceptor.class, NoOp.class});
        enhancer.setCallbackFilter(IGNORE_BRIDGE_METHODS);

        return enhancer.createClass();
    }

    private Object createProxy(Class<?> proxyClass, final MethodInterceptor interceptor) {
        Factory proxy = (Factory) objenesis.newInstance(proxyClass);
        proxy.setCallbacks(new Callback[]{interceptor, SerializableNoOp.SERIALIZABLE_INSTANCE});
        return proxy;
    }

    private Class<?>[] prepend(Class<?> first, Class<?>... rest) {
        Class<?>[] all = new Class<?>[rest.length + 1];
        all[0] = first;
        System.arraycopy(rest, 0, all, 1, rest.length);
        return all;
    }

    public static class ClassWithSuperclassToWorkAroundCglibBug {
    }

    private static final class SerializableNoOp implements Serializable, NoOp {
        public static final Callback SERIALIZABLE_INSTANCE = new SerializableNoOp();
        private static final long serialVersionUID = -7330501287798237627L;

        private SerializableNoOp() {
        }
    }

    private static class NonConstructorFilteringEnhancer extends Enhancer {
        @Override
        @SuppressWarnings("unchecked")
        protected void filterConstructors(Class sc, List constructors) {
            // Don't filter
        }
    }
}
