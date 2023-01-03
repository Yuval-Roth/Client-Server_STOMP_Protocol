package bgu.spl.net.impl.stomp.Backend.interfaces;

import java.io.IOException;

import bgu.spl.net.genericServers.interfaces.ConnectionHandler;
import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface SubscriptionManager {
    
    /**
     * subscribe to a game's channel
     * @param username
     * @param topic
     */
    public void subscribe(ConnectionHandler<String> handler, int subId, String topic) throws IOException;

    /**
     * unsubscribe from a game's channel
     * @param username - the user that wants to unsubscribe
     * @param team1 - any of the teams in the game
     * @param team2 - any of the teams in the game
     * @throws UserException
     * @throws GameChannelException
     */
    public void unsubscribe(ConnectionHandler<String> handler ,int subId)throws IOException;
}
