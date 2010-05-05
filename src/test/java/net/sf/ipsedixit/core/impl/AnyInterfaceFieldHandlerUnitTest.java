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

import java.util.EventListener;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnyInterfaceFieldHandlerUnitTest {

    private AnyInterfaceFieldHandler anyInterfaceFieldHandler;

    @Before
    public void setup() {
        anyInterfaceFieldHandler = new AnyInterfaceFieldHandler();
    }

    @Test
    public void canHandleInterface() {
        assertThat(anyInterfaceFieldHandler.supports(new DummyMutableField(EventListener.class)), is(true));
    }

    @Test
    public void cannotHandleClass() {
        assertThat(anyInterfaceFieldHandler.supports(new DummyMutableField(Number.class)), is(false));
    }

    @Test
    public void cannotHandlePrimitives() {
        assertThat(anyInterfaceFieldHandler.supports(new DummyMutableField(int.class)), is(false));
    }

    @Test
    public void returnsJavaProxy() {
        Object proxy = anyInterfaceFieldHandler.getValueFor(new DummyMutableField(EventListener.class));
        assertThat(proxy.toString(), startsWith("$Proxy"));
    }

    @Test
    public void sameProxyIsEqual() {
        Object proxy = anyInterfaceFieldHandler.getValueFor(new DummyMutableField(EventListener.class));
        assertThat(proxy, equalTo(proxy));
    }
}
