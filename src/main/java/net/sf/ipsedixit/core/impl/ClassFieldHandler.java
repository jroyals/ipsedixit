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

import java.lang.reflect.Modifier;
import net.sf.ipsedixit.core.DataProvider;
import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.MutableField;

public class ClassFieldHandler implements FieldHandler {

    private final DataProvider dataProvider;

    public ClassFieldHandler(final DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @SuppressWarnings("unchecked")
    public Object getValueFor(final MutableField mutableField) {
        return dataProvider.proxy(mutableField.getType(), "defined by field " + mutableField.getName());
    }

    public boolean supports(final MutableField mutableField) {
        Class type = mutableField.getType();
        return !type.isPrimitive() && !Modifier.isFinal(type.getModifiers());
    }
}
