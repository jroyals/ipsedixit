package net.sf.ipsedixit.core.impl;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.JavaProxyFieldHandler;
import net.sf.ipsedixit.core.MutableField;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JavaProxyFieldHandlerUnitTest {

    private JavaProxyFieldHandler javaProxyFieldHandler;
    private MutableField mutableField;

    @Before
    public void setup() {
        javaProxyFieldHandler = new JavaProxyFieldHandler();
        mutableField = createMock(MutableField.class);

    }

    @Test
    public void implementsInterface() {
        assertTrue(FieldHandler.class.isAssignableFrom(javaProxyFieldHandler.getClass()));
    }

    @Test
    public void canHandleInterfaces() {
        expect(mutableField.getType()).andReturn(TestInterface.class);
        replay(mutableField);
        assertTrue(javaProxyFieldHandler.supports(mutableField));
        verify(mutableField);
    }

    @Test
    public void willNotHandleClasses() {
        expect(mutableField.getType()).andReturn(this.getClass());
        replay(mutableField);
        assertFalse(javaProxyFieldHandler.supports(mutableField));
        verify(mutableField);
    }

    @Test
    public void willNotHandlePrimitives() {
        expect(mutableField.getType()).andReturn(int.class);
        replay(mutableField);
        assertFalse(javaProxyFieldHandler.supports(mutableField));
        verify(mutableField);
    }

    @Test
    public void returnsProxyOverInterfaceWithHelpfulToString() {
        expect(mutableField.getType()).andReturn(TestInterface.class);
        expect(mutableField.getType()).andReturn(TestInterface.class);
        expect(mutableField.getName()).andReturn("foo");
        replay(mutableField);
        Object result = javaProxyFieldHandler.getValueFor(mutableField);
        assertTrue(result instanceof TestInterface);
        String value = result.toString();
        assertTrue(value, value.matches("net.sf.ipsedixit.core.impl.\\$Proxy\\d* : Java proxy for interface net.sf.ipsedixit.core.impl.JavaProxyFieldHandlerUnitTest\\$TestInterface defined by field foo"));
        verify(mutableField);
    }

    private interface TestInterface {

        void helloWorld();

    }
}
