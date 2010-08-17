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
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MutableField;

/**
 * Returns a Java Dynamic Proxy around an interface. The proxy is able to print a toString representation of itself,
 * and can also determine equality (by using a default "equals" and "hashCode") but that's all. Every other method call
 * will result in an exception being raised.
 */
public class AnyInterfaceFieldHandler implements FieldHandler {

    public Object getValueFor(MutableField mutableField) {
        return Proxy.newProxyInstance(
                resolveClassloader(mutableField),
                new Class[]{mutableField.getType()},
                new MutableFieldAwareInvocationHandler(mutableField));
    }

    private ClassLoader resolveClassloader(MutableField mutableField) {
        String typeName = mutableField.getType().getName();
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

    private static final class MutableFieldAwareInvocationHandler implements InvocationHandler {
        private final MutableField mutableField;
        private final ProxiedMethodHandler proxiedMethodHandler = new ProxiedMethodHandler();

        private MutableFieldAwareInvocationHandler(MutableField mutableField) {
            this.mutableField = mutableField;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return proxiedMethodHandler.invoke(proxy, method, mutableField, args);
        }
    }
}
