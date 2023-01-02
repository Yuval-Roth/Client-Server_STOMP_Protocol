package bgu.spl.net.impl.stomp.Backend.facade_interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface ServerManager {

    /**
     * Connect a user to the server
     * @param username
     * @param password
     */
    public void Connect(String username, String password) throws UserException;

    /**
     * Disconnect a user from the server
     * @param username
     */
    public void Disconnect(String username) throws UserException;

    /**
     * unsubscribe from a game's channel
     * @param username
     * @param game
     */
    public void Unsubscribe(String username,String hometeam, String awayteam) throws UserException, GameChannelException;


    /**
     * Subscribe to a game's channel
     * @param username
     * @param game
     */
    public void Subscribe(String username,String hometeam, String awayteam) throws UserException, GameChannelException;

}
