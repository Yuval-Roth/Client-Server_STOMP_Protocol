package bgu.spl.net.genericServers.interfaces;

import java.io.IOException;

public interface ConnectionsManager<T> {

    /**
     * Connect a user to the server. 
     * @param username
     * @param password
     * @throws IOException if the user is already connected 
     */
    public void connect(ConnectionHandler<T> handler, String username, String password) throws IOException;

    /**
     * Disconnect a user from the server.
     * @param username
     * @throws IOException if the user is not connected
     */
    public void disconnect(ConnectionHandler<T> handler) throws IOException;
}
