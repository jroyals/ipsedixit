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
import net.sf.ipsedixit.core.MutableField;
import static net.sf.ipsedixit.core.impl.CGLIBHelper.cglibHelper;

public class CGLIBProxyFactory implements ProxyFactory {

    private static final CGLIBProxyFactory INSTANCE = new CGLIBProxyFactory();

    protected CGLIBProxyFactory() {
    }

    public static CGLIBProxyFactory cglibProxyFactory() {
        return INSTANCE;
    }

    /**
     *
     * @param clazz the type to proxy
     * @param additionalContextInToString
     * @return a CGLIB proxy that extends the type defined by the MutableField.
     */
    public <T> T proxy(Class<T> clazz, final String additionalContextInToString) {
        return cglibHelper().createProxy(new MutableFieldAwareInterceptor(clazz, additionalContextInToString), clazz);
    }

    /**
     * @param mutableField the <code>MutableField</code> to test.
     * @return true only if the MutableField is not a primitive, and is not an Interface type.
     */
    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return !type.isPrimitive() && !type.isInterface() && !Modifier.isFinal(type.getModifiers());
    }

    private static final class MutableFieldAwareInterceptor implements MethodInterceptor {
        private final Class<?> clazz;
        private final String additionalContextInToString;
        private final ProxiedMethodInvocationHandler proxiedMethodInvoker = ProxiedMethodInvocationHandler.proxiedMethodInvoker();

        private MutableFieldAwareInterceptor(Class<?> clazz, final String additionalContextInToString) {
            this.clazz = clazz;
            this.additionalContextInToString = additionalContextInToString;
        }

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return proxiedMethodInvoker.invoke(obj, method, args, clazz, additionalContextInToString);
        }
    }


}
