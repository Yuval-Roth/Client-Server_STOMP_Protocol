package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class ConnectionController {

    private final HashMap<String,ConnectionHandler<String>> connections;

    public ConnectionController() {
        connections = new HashMap<String,ConnectionHandler<String>>();
    }

    public void startConnection(ConnectionHandler<String> connectionHandler,String username) {
        connections.put(username, connectionHandler);
    }

    public void endConnection(String username) {
        connections.remove(username);
    }

    public boolean existsConnection(String username) {
        return connections.containsKey(username);
    }

    public ConnectionHandler<String> getHandler(String username) {
        return connections.get(username);
    }
}
