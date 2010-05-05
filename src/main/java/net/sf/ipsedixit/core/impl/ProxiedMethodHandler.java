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
import net.sf.ipsedixit.core.MutableField;

class ProxiedMethodHandler {

    public Object invoke(Object proxy, Method method, MutableField mutableField, Object[] args) {
        if ("toString".equals(method.getName())) {
            return proxy.getClass().getName() + " : Proxy for " + mutableField.getType().getName() +
                    " defined by field " + mutableField.getName();
        }

        if ("equals".equals(method.getName())) {
            return proxy != null && proxy == args[0];
        }

        if ("hashCode".equals(method.getName())) {
            return proxy.hashCode();
        }

        throw new UnsupportedOperationException("Cannot call method " + method.getName() +
                " on a dummy object.  Did you mean to use a mock instead?");
    }
}
