package bgu.spl.net.impl.stomp.Backend;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class Session implements Closeable {

    private final String username;
    private final ConnectionHandler<String> handler;
    private final int connectionId;
    private final HashSet<Tuple<Integer>> subscriptions;

    public Session(ConnectionHandler<String> handler, int connectionId, String username) {
        this.handler = handler;
        this.connectionId = connectionId;
        this.username = username;
        this.subscriptions = new HashSet<>();
    }

    public String getUsername() {return username;}
    public int getConnectionId() {return connectionId;}
    public ConnectionHandler<String> getHandler() {return handler;}

    public void addSubscription(Tuple<Integer> subcription) { 
        subscriptions.add(subcription);
    }
    public void removeSubscription(Tuple<Integer> subcription) {
        subscriptions.remove(subcription);
    }

    public HashSet<Tuple<Integer>> getSubscriptions() {
        return subscriptions;
    }

    @Override
    public void close() throws IOException {
        handler.close();  
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
