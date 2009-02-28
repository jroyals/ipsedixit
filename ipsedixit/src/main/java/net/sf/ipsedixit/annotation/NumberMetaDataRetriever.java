package net.sf.ipsedixit.annotation;

import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.MutableField;

/**
 * Created by IntelliJ IDEA. User: jroyals Date: 28/02/2009 Time: 6:20:08 PM To change this template use File | Settings
 * | File Templates.
 */
public interface NumberMetaDataRetriever {

    NumberMetaData getMetaData(MutableField mutableField);
}
