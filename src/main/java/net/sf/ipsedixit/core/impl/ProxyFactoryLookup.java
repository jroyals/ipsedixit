////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2011, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package net.sf.ipsedixit.core.impl;

import static net.sf.ipsedixit.core.impl.CGLIBProxyFactory.cglibProxyFactory;
import static net.sf.ipsedixit.core.impl.JavaReflectProxyFactory.interfaceProxyFactory;

public final class ProxyFactoryLookup {

    private static final ProxyFactoryLookup INSTANCE = new ProxyFactoryLookup();

    private ProxyFactoryLookup() {
    }

    public static ProxyFactoryLookup lookup() {
        return INSTANCE;
    }

    public ProxyFactory proxyFactory(Class<?> clazz) {
        if (clazz.isInterface()) {
            return interfaceProxyFactory();
        }
        return cglibProxyFactory();
    }

}
