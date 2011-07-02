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

import java.lang.reflect.Method;

class ProxiedMethodInvocationHandler {

    private static final ProxiedMethodInvocationHandler INSTANCE = new ProxiedMethodInvocationHandler();

    protected ProxiedMethodInvocationHandler() {
    }

    static ProxiedMethodInvocationHandler proxiedMethodInvoker() {
        return INSTANCE;
    }

    public Object invoke(Object proxy, Method method, Object[] args, Class clazz, final String additionalContextInToString) {
        if ("toString".equals(method.getName())) {
            return (proxy.getClass().getName() + " : Proxy for " + clazz.getName() + " " + additionalContextInToString).trim();
        }

        if ("equals".equals(method.getName())) {
            return proxy != null && proxy == args[0];
        }

        if ("hashCode".equals(method.getName())) {
            return 13; // inefficient but hopefully OK for testing purposes.
        }

        throw new UnsupportedOperationException("Cannot call method " + method.getName() +
                " on a dummy object.  Did you mean to use a mock instead?");
    }
}
