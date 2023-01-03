package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;
import java.util.Map;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class ConnectionController {

    private final HashMap<String,ConnectionHandler<String>> connections;

    public ConnectionController() {
        connections = new HashMap<String,ConnectionHandler<String>>();
    }

    public void addConnection(String username, ConnectionHandler<String> connectionHandler) {
        connections.put(username, connectionHandler);
    }

    public void removeConnection(String username) {
        connections.remove(username);
    }

    public boolean containsConnection(String username) {
        return connections.containsKey(username);
    }

    public ConnectionHandler<String> getHandler(String username) {
        return connections.get(username);
    }

    //get username by connection handler
    

    
}
