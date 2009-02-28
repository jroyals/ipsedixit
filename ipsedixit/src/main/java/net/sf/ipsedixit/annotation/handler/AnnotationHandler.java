package net.sf.ipsedixit.annotation.handler;

import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA. User: jroyals Date: 28/02/2009 Time: 5:30:54 PM To change this template use File | Settings
 * | File Templates.
 */
public interface AnnotationHandler {

    boolean canHandle(Annotation annotation);


}
