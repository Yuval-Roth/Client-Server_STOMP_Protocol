package bgu.spl.net.impl.stomp.Backend;

import java.util.HashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class ConnectionController {

    private final HashMap<Integer,ConnectionHandler<String>> idToHandler;
    private final HashMap<ConnectionHandler<String>,Integer> handlerToId;
    private final HashMap<String, Integer> usernameToConnectionId;
    private final HashMap<Integer, String> connectionIdToUsername;

    public ConnectionController() {
        idToHandler = new HashMap<>();
        handlerToId = new HashMap<>();
        usernameToConnectionId = new HashMap<>();
        connectionIdToUsername = new HashMap<>();
    }

    public void startConnection(ConnectionHandler<String> connectionHandler, int connectionId, String username) {
        idToHandler.put(connectionId, connectionHandler);
        handlerToId.put(connectionHandler, connectionId);
        usernameToConnectionId.put(username, connectionId);
        connectionIdToUsername.put(connectionId, username);
    }

    public void endConnection(int connectionId) {
        ConnectionHandler<String> handler = idToHandler.get(connectionId);
        idToHandler.remove(connectionId);
        handlerToId.remove(handler);
        String username = connectionIdToUsername.get(connectionId);
        usernameToConnectionId.remove(username);
        connectionIdToUsername.remove(connectionId);
    }

    public void endConnection(ConnectionHandler<String> handler) {
        int connectionId = handlerToId.get(handler);
        idToHandler.remove(connectionId);
        handlerToId.remove(handler);
        String username = connectionIdToUsername.get(connectionId);
        usernameToConnectionId.remove(username);
        connectionIdToUsername.remove(connectionId);
    }

    public void endConnection(String username) {
        int connectionId = usernameToConnectionId.get(username);
        ConnectionHandler<String> handler = idToHandler.get(connectionId);
        idToHandler.remove(connectionId);
        handlerToId.remove(handler);
        usernameToConnectionId.remove(username);
        connectionIdToUsername.remove(connectionId);
    }

    public ConnectionHandler<String> getHandler(int connectionId) {
        return idToHandler.get(connectionId);
    }

    public ConnectionHandler<String> getHandler(String username) {
        int connectionId = usernameToConnectionId.get(username);
        return idToHandler.get(connectionId);
    }

    public int getConnectionId(ConnectionHandler<String> handler) {
        return handlerToId.get(handler);
    }
    public int getConnectionId(String username) {
        return usernameToConnectionId.get(username);
    }
    
    public String getUsername(int connectionId) {
        return connectionIdToUsername.get(connectionId);
    }
    
    public String getUsername(ConnectionHandler<String> handler) {
        int connectionId = handlerToId.get(handler);
        return connectionIdToUsername.get(connectionId);
    }
}
