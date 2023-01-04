package bgu.spl.net.impl.stomp.Backend;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class Session implements Closeable {

    private String username;
    private ConnectionHandler<String> handler;
    private int connectionId;
    private HashMap<Integer,String> subIdToChannel; //TODO: debating if this is needed

    public Session(ConnectionHandler<String> handler, int connectionId, String username) {
        this.handler = handler;
        this.connectionId = connectionId;
        this.username = username;
        subIdToChannel = new HashMap<>(); //debating if this is needed
    }

    //debating if this is needed
    public void addSub(int subId, String channel) {

        if(subIdToChannel.containsKey(subId))
            throw new RuntimeException("SubId already exists");

        subIdToChannel.put(subId, channel);
    }

    //debating if this is needed
    public void removeSub(int subId) {

        if(subIdToChannel.containsKey(subId) == false)
            throw new RuntimeException("SubId does not exist");

        subIdToChannel.remove(subId);
    }

    public String getUsername() {return username;}
    public int getConnectionId() {return connectionId;}
    public String getChannelFromSubId(int subId) {return subIdToChannel.get(subId);} //debating if this is needed
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
