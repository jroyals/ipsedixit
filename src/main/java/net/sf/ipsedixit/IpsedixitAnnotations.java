////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2012, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package net.sf.ipsedixit;

import net.sf.ipsedixit.annotation.AnnotationConfiguration;

/**
 * A tool-agnostic utility class to bootstrap Ipsedixit and populate the object's annotated fields.
 */
public final class IpsedixitAnnotations {

    private IpsedixitAnnotations() {
        
    }
    
    /**
     * Look for any {@link net.sf.ipsedixit.annotation.Arbitrary} annotations in the declared fields and populate them.
     * @param obj the Object to inspect and populate.
     */
    public static void initArbitraries(Object obj) {
        AnnotationConfiguration configuration = new AnnotationConfiguration(obj.getClass());
        DataPopulator dataPopulator = DataPopulatorFactory.INSTANCE.createDefaultDataPopulator(configuration);
        dataPopulator.populate(obj);
    }

}
