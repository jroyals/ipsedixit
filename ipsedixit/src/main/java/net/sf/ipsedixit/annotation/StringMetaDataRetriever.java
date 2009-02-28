package net.sf.ipsedixit.annotation;

import net.sf.ipsedixit.core.StringMetaData;
import net.sf.ipsedixit.core.MutableField;

/**
 * Created by IntelliJ IDEA. User: jroyals Date: 28/02/2009 Time: 6:21:14 PM To change this template use File | Settings
 * | File Templates.
 */
public interface StringMetaDataRetriever {
    StringMetaData getMetaData(MutableField mutableField);
}
