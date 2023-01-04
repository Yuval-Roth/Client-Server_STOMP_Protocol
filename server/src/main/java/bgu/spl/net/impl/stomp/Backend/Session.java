package bgu.spl.net.impl.stomp.Backend;

import java.io.Closeable;
import java.io.IOException;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class Session implements Closeable {

    private String username;
    private ConnectionHandler<String> handler;
    private int connectionId;

    public Session(ConnectionHandler<String> handler, int connectionId, String username) {
        this.handler = handler;
        this.connectionId = connectionId;
        this.username = username;
    }

    public String getUsername() {return username;}
    public int getConnectionId() {return connectionId;}
    public ConnectionHandler<String> getHandler() {return handler;}

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
