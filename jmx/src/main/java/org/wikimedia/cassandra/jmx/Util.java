package org.wikimedia.cassandra.jmx;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.google.common.base.Throwables;

class Util {

    static ObjectName newObjectName(String objName, Object... args) {
        return newObjectName(String.format(objName, args));
    }

    /** Convenience method for creating ObjectName instances. */
    static ObjectName newObjectName(String objName) {
        try {
            return ObjectName.getInstance(objName);
        }
        catch (MalformedObjectNameException e) {
            throw Throwables.propagate(e);
        }
    }

}
