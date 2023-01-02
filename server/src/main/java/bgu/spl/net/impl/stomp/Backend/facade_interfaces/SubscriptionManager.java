package bgu.spl.net.impl.stomp.Backend.facade_interfaces;

import bgu.spl.net.impl.stomp.StompExceptions.GameChannelException;
import bgu.spl.net.impl.stomp.StompExceptions.UserException;

public interface SubscriptionManager {
    
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
