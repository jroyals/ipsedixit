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

import java.lang.annotation.Annotation;
import net.sf.ipsedixit.core.MutableField;

class DummyMutableField implements MutableField {
    private final Class clazz;

    public DummyMutableField(Class clazz) {
        this.clazz = clazz;
    }

    public void setValue(Object value) {
    }

    public String getName() {
        return "";
    }

    public Class getType() {
        return clazz;
    }

    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return null;
    }
}
