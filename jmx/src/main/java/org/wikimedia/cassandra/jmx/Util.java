package org.wikimedia.cassandra.jmx;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.google.common.base.Throwables;

class Util {

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
