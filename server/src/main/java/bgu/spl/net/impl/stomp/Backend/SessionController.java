package bgu.spl.net.impl.stomp.Backend;

import java.util.concurrent.ConcurrentHashMap;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;

public class SessionController {

    private volatile ConcurrentHashMap<Integer,Integer> connectionIdToHashCode;
    private volatile ConcurrentHashMap<String,Integer> usernameToHashCode;
    private volatile ConcurrentHashMap<Integer,Session> hashCodeToSession;

    public SessionController() {
        connectionIdToHashCode = new ConcurrentHashMap<>();
        usernameToHashCode = new ConcurrentHashMap<>();
        hashCodeToSession = new ConcurrentHashMap<>();
    }

    public void newSession(ConnectionHandler<String> handler, int connectionId, String username) {
        Session session = new Session(handler, connectionId, username);
        int hashCode = session.hashCode();
        connectionIdToHashCode.put(connectionId, hashCode);
        usernameToHashCode.put(username, hashCode);
        hashCodeToSession.put(hashCode, session);
    }

    public Session getSession(int connectionId) {
        int hashCode = connectionIdToHashCode.get(connectionId);
        return hashCodeToSession.get(hashCode);
    }
    
    public Session getSession(String username) {
        int hashCode = usernameToHashCode.get(username);
        return hashCodeToSession.get(hashCode);
    }

    public Session getSession(ConnectionHandler<String> handler) {
        int hashCode = handler.hashCode();
        return hashCodeToSession.get(hashCode);
    }

    public void closeSession(int connectionId) {
        int hashCode = connectionIdToHashCode.get(connectionId);
        Session session = hashCodeToSession.get(hashCode);
        closeSession(session);
    }

    public void closeSession(String username) {
        int hashCode = usernameToHashCode.get(username);
        Session session = hashCodeToSession.get(hashCode);
        closeSession(session);
    }

    public void closeSession(ConnectionHandler<String> handler) {
        int hashCode = handler.hashCode();
        Session session = hashCodeToSession.get(hashCode);
        closeSession(session);
    }

    private void closeSession(Session session) {
        connectionIdToHashCode.remove(session.getConnectionId());
        usernameToHashCode.remove(session.getUsername());
        hashCodeToSession.remove(session.hashCode());
    }
}
