package net.sf.ipsedixit.core.fieldhandler.impl;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.ipsedixit.core.MutableField;
import net.sf.ipsedixit.core.fieldhandler.FieldHandler;

/**
 * A handler that wraps a CGLIB Proxy around an class.
 */
public class CGLIBFieldHandler implements FieldHandler {

    /**
     * @param mutableField a representation of the Field that should be created.
     * @return a CGLIB proxy that extends the type defined by the MutableField.
     */
    public Object getValueFor(MutableField mutableField) {
        return Enhancer.create(mutableField.getType(), new MutableFieldAwareCallback(mutableField));
    }

    /**
     * @param mutableField the <code>MutableField</code> to test.
     * @return true only if the MutableField is not a primitive, and is not an Interface type.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return !type.isPrimitive() && !type.isInterface();
    }

    private static final class MutableFieldAwareCallback implements MethodInterceptor {
        private final MutableField mutableField;

        private MutableFieldAwareCallback(MutableField mutableField) {
            this.mutableField = mutableField;
        }

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if ("toString".equals(method.getName())) {
                return proxy.getClass().getName() + " : CGLIB proxy for class " + mutableField.getType().getName()
                        + " defined by field " + mutableField.getName();
            }
            throw new UnsupportedOperationException("Cannot call method " + method.getName()
                    + " on a dummy object.  Did you mean to use a mock instead?");
        }
    }
}
