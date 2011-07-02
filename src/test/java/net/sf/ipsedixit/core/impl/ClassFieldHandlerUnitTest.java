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

import java.io.FileInputStream;
import java.util.EventListener;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class ClassFieldHandlerUnitTest {

    private ClassFieldHandler classFieldHandler;

    @Before
    public void setup() {
        classFieldHandler = new ClassFieldHandler(DefaultDataProvider.dataProvider());
    }

    @Test
    public void canHandleInterface() {
        assertThat(classFieldHandler.supports(new DummyMutableField(EventListener.class)), is(true));
    }

    @Test
    public void canHandleClass() {
        assertThat(classFieldHandler.supports(new DummyMutableField(Number.class)), is(true));
    }

    @Test
    public void cannotHandlePrimitives() {
        assertThat(classFieldHandler.supports(new DummyMutableField(int.class)), is(false));
    }

    @Test
    public void cannotHandleFinal() {
        assertThat(classFieldHandler.supports(new DummyMutableField(Class.class)), is(false));
    }

    @Test
    public void returnsJavaProxyForInterface() {
        Object proxy = classFieldHandler.getValueFor(new DummyMutableField(EventListener.class));
        assertThat(proxy.toString(), startsWith("$Proxy"));
    }

    @Test
    public void sameInterfaceProxyIsEqual() {
        Object proxy = classFieldHandler.getValueFor(new DummyMutableField(EventListener.class));
        assertThat(proxy, equalTo(proxy));
    }

    @Test
    public void returnsCGLIBProxyForClass() {
        Object proxy = classFieldHandler.getValueFor(new DummyMutableField(Number.class));
        assertThat(proxy.toString(), startsWith("$java.lang.Number$$EnhancerByCGLIB"));
    }

    @Test
    public void sameClassProxyIsEqual() {
        Object proxy = classFieldHandler.getValueFor(new DummyMutableField(Number.class));
        assertThat(proxy, equalTo(proxy));
    }

    @Test
    public void canProvideProxyOverClassWithNoDefaultConstructor() {
        Object proxy = classFieldHandler.getValueFor(new DummyMutableField(FileInputStream.class));
        // no assertions necessary, if above fails an exception is thrown...
    }
}
