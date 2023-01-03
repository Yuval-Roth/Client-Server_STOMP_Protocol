package bgu.spl.net.impl.stomp.Backend.interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface SubscriptionManager {
    
    
    /**
     * unsubscribe from a game's channel
     * @param username - the user that wants to unsubscribe
     * @param team1 - any of the teams in the game
     * @param team2 - any of the teams in the game
     * @throws UserException
     * @throws GameChannelException
     */
    public void unsubscribe(String username, String topic) throws UserException, GameChannelException;

    /**
     * subscribe to a game's channel
     * @param username
     * @param topic
     * @throws UserException
     * @throws GameChannelException
     */
    public void subscribe(String username, String topic) throws UserException, GameChannelException;
}
