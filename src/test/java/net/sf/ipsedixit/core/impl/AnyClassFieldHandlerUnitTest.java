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
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnyClassFieldHandlerUnitTest {

    private AnyClassFieldHandler anyClassFieldHandler;

    @Before
    public void setup() {
        anyClassFieldHandler = new AnyClassFieldHandler();
    }

    @Test
    public void canHandleNonFinalClass() {
        assertThat(anyClassFieldHandler.supports(new DummyMutableField(Number.class)), is(true));
    }

    @Test
    public void cannotHandleFinalClass() {
        assertThat(anyClassFieldHandler.supports(new DummyMutableField(String.class)), is(false));
    }

    @Test
    public void cannotHandleInterfaces() {
        assertThat(anyClassFieldHandler.supports(new DummyMutableField(EventListener.class)), is(false));
    }
    
    @Test
    public void cannotHandlePrimitives() {
        assertThat(anyClassFieldHandler.supports(new DummyMutableField(int.class)), is(false));
    }

    @Test
    public void returnsJavaProxy() {
        Object proxy = anyClassFieldHandler.getValueFor(new DummyMutableField(Number.class));
        assertThat(proxy.toString(), startsWith("$java.lang.Number$$EnhancerByCGLIB"));
    }

    @Test
    public void sameProxyIsEqual() {
        Object proxy = anyClassFieldHandler.getValueFor(new DummyMutableField(Number.class));
        assertThat(proxy, equalTo(proxy));
    }

    @Test
    public void canProvideProxyOverClassWithNoDefaultConstructor() {
        Object proxy = anyClassFieldHandler.getValueFor(new DummyMutableField(FileInputStream.class));
        // no assertions necessary, if above fails an exception is thrown...
    }
}
