package bgu.spl.net.impl.stomp.Backend;

public class SubscriberId{

    public final int connectionId;
    public final int subId;

    public SubscriberId(int connectionId, int subId) {
        this.connectionId = connectionId;
        this.subId = subId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + connectionId;
        result = prime * result + subId;
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
        SubscriberId other = (SubscriberId) obj;
        if (connectionId != other.connectionId)
            return false;
        if (subId != other.subId)
            return false;
        return true;
    } 
}
