package org.wikimedia.cassandra.jmx;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import com.google.common.base.Throwables;

class MBean {
    protected final JmxClient client;
    protected final ObjectName objectName;

    protected MBeanInfo info;

    MBean(JmxClient client, ObjectName objectName) {
        this.client = checkNotNull(client, "client argument");
        this.objectName = checkNotNull(objectName, "objectName argument");
    }

    MBeanAttributeInfo[] listAttributes() throws IOException {
        return getMBeanInfo().getAttributes();
    }

    // Throw an IOException only, (if there is a network communication problem
    // for example); Propagate everything else as a runtime?
    Object getAttribute(String attribute) throws IOException {
        try {
            return this.client.getConnection().getAttribute(getObjectName(), checkNotNull(attribute, "attribute argument"));
        }
        catch (AttributeNotFoundException | InstanceNotFoundException | MBeanException | ReflectionException e) {
            throw Throwables.propagate(e);
        }
    }

    Object invoke(String operation, Object[] args, String[] signature) throws IOException {
        try {
            return this.client.getConnection().invoke(getObjectName(), operation, args, signature);
        }
        catch (InstanceNotFoundException | MBeanException | ReflectionException e) {
            throw Throwables.propagate(e);
        }
    }

    String getClassName() throws IOException {
        return getMBeanInfo().getClassName();
    }

    ObjectName getObjectName() {
        return this.objectName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MBean other = (MBean) obj;
        if (objectName == null) {
            if (other.objectName != null)
                return false;
        }
        else if (!objectName.equals(other.objectName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MBean[objectName=" + objectName + "]";
    }

    private MBeanInfo getMBeanInfo() throws IOException {
        if (this.info == null)
            try {
                this.info = this.client.getConnection().getMBeanInfo(this.objectName);
            }
            catch (InstanceNotFoundException | IntrospectionException | ReflectionException e) {
                throw Throwables.propagate(e);
            }

        return this.info;
    }

}
