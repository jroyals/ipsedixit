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
import java.lang.reflect.Modifier;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MutableField;

public class AnyClassFieldHandler implements FieldHandler {
    /**
     * @param mutableField a representation of the Field that should be created.
     * @return a CGLIB proxy that extends the type defined by the MutableField.
     */
    public Object getValueFor(MutableField mutableField) {
        return CGLIBHelper.INSTANCE.createProxy(new MutableFieldAwareCallback(mutableField), mutableField.getType());
    }

    /**
     * @param mutableField the <code>MutableField</code> to test.
     * @return true only if the MutableField is not a primitive, and is not an Interface type.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return !type.isPrimitive() && !type.isInterface() && !Modifier.isFinal(type.getModifiers());
    }

    private static final class MutableFieldAwareCallback implements MethodInterceptor {
        private final MutableField mutableField;
        private final ProxiedMethodHandler proxiedMethodHandler = new ProxiedMethodHandler();

        private MutableFieldAwareCallback(MutableField mutableField) {
            this.mutableField = mutableField;
        }

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return proxiedMethodHandler.invoke(obj, method, mutableField, args);
        }
    }


}
