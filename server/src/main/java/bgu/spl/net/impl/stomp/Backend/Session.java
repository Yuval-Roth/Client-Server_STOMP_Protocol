package bgu.spl.net.impl.stomp.Backend;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class Session implements Closeable {

    private final String username;
    private final ConnectionHandler<String> handler;
    private final int connectionId;
    private volatile HashMap<SubscriberId,String> subscriptions;

    public Session(ConnectionHandler<String> handler, int connectionId, String username) {
        this.handler = handler;
        this.connectionId = connectionId;
        this.username = username;
        this.subscriptions = new HashMap<>();
    }

    public String getUsername() {return username;}
    public int getConnectionId() {return connectionId;}
    public ConnectionHandler<String> getHandler() {return handler;}

    public void addSubscription(SubscriberId subcription, String topic) { 
        subscriptions.put(subcription,topic);
    }

    public void removeSubscription(SubscriberId subcription) {
        subscriptions.remove(subcription);
    }
   
    /**
     * Returns a map of all the subscriptions of the session.
     * The key is the SubscriberId object and the value is the topic name.
     */
    public HashMap<SubscriberId,String> getSubscriptions() {
        return subscriptions;
    }

    @Override
    public void close() throws IOException {
        // handler.close();  
    }

    @Override
    public int hashCode() {
        return handler.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (handler == null) {
            if (other.handler != null)
                return false;
        } else if (!handler.equals(other.handler))
            return false;
        return true;
    } 
}
