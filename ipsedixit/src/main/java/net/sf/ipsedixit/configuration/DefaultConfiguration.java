package net.sf.ipsedixit.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.ipsedixit.core.fieldhandler.FieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.JavaProxyFieldHandler;
import net.sf.ipsedixit.core.fieldhandler.impl.CGLIBFieldHandler;
import net.sf.ipsedixit.core.ObjectAnalyser;
import net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser;

/**
 * Represents a default configuration for Ipsedixit. This class is not intended for use directly, but is used by other
 * Configuration classes to obtain default values.
 *
 * This configuration can be overridden at runtime using various techniques, depending on how you use Ipsedixit. See
 * {@link net.sf.ipsedixit.configuration.Configuration} for a list of Configuration's.
 */
public class DefaultConfiguration implements Configuration {

    /**
     * By default, a {@link net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser} is used.
     *
     * @return a {@link net.sf.ipsedixit.core.impl.NonFinalFieldObjectAnalyser}.
     */
    public ObjectAnalyser getObjectAnalyser() {
        return new NonFinalFieldObjectAnalyser();
    }

    /**
     * By default, Ipsedixit will add an extra two field handlers on the end of the standard handlers.  This is to
     * handle Object types by creating dynapmic proxies at runtime. These are suitable for dummy objects, but can be
     * replaced by more advanced proxies (such as a mocking framework FieldHandler) if automocking is required.
     *
     * @return a List containing a {@link net.sf.ipsedixit.core.fieldhandler.impl.JavaProxyFieldHandler} and
     * {@link net.sf.ipsedixit.core.fieldhandler.impl.CGLIBFieldHandler}.
     */
    public List<? extends FieldHandler> getAfterStandardFieldHandlers() {
        return Arrays.asList(new JavaProxyFieldHandler(), new CGLIBFieldHandler());
    }

    /**
     * By default there are no FieldHandlers we want to register to run before the standard set of handlers.
     *
     * @return an empty list.
     */
    public List<? extends FieldHandler> getBeforeStandardFieldHandlers() {
        return Collections.EMPTY_LIST;
    }
}
