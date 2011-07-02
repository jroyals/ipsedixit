////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2010, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package net.sf.ipsedixit.core.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.sf.ipsedixit.core.MutableField;

/**
 * Returns a Java Dynamic Proxy around an interface. The proxy is able to print a toString representation of itself,
 * and can also determine equality (by using a default "equals" and "hashCode") but that's all. Every other method call
 * will result in an exception being raised.
 */
public class JavaReflectProxyFactory implements ProxyFactory {

    protected JavaReflectProxyFactory() {
    }

    public static JavaReflectProxyFactory interfaceProxyFactory() {
        return new JavaReflectProxyFactory();
    }

    @SuppressWarnings("unchecked")
    public <T> T proxy(Class<T> clazz, final String additionalContextInToString) {
        return (T) Proxy.newProxyInstance(resolveClassLoader(clazz), new Class<?>[]{clazz}, new InternalInvocationHandler(clazz,
                additionalContextInToString));
    }

    private ClassLoader resolveClassLoader(Class clazz) {
        String typeName = clazz.getName();
        ClassLoader cl = getClass().getClassLoader();
        if (canLoaderResolveClass(typeName, cl)) {
            return cl;
        }
        
        cl = Thread.currentThread().getContextClassLoader();
        if (canLoaderResolveClass(typeName, cl)) {
            return cl;
        }
        throw new RuntimeException("No class loader could load class " + typeName);
    }

    private boolean canLoaderResolveClass(String typeName, ClassLoader cl) {
        try {
            Class.forName(typeName, false, cl);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public boolean supports(MutableField mutableField) {
        return mutableField.getType().isInterface();
    }

    private static final class InternalInvocationHandler implements InvocationHandler {
        private final Class<?> clazz;
        private final ProxiedMethodInvocationHandler proxiedMethodInvoker = ProxiedMethodInvocationHandler.proxiedMethodInvoker();
        private final String additionalContextInToString;

        public InternalInvocationHandler(Class<?> clazz, final String additionalContextInToString) {
            this.clazz = clazz;
            this.additionalContextInToString = additionalContextInToString;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return proxiedMethodInvoker.invoke(proxy, method, args, clazz, additionalContextInToString);
        }
    }
}
